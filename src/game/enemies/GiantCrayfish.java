package game.enemies;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.ActionCompare;
import game.AttackAction;
import game.FollowBehaviour;
import game.MultiAttackAction;
import game.RandomNumberGenerator;
import game.Status;

public class GiantCrayfish extends Enemy implements Seafood {

    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, 500, 2374);
        this.setIntrinsicWeapon(new IntrinsicWeapon(527, "slams", 100));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * @param actions    The list of allowable actions for this actor to take
     * @param lastAction The last action this actor took
     * @param map        The world map
     * @param display    The display
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        List<Action> sortedActions = actions.sorted(new ActionCompare());

        Action action = sortedActions.get(0);
        if (action.getClass() == AttackAction.class) {

            // 50% chance of a slam attack
            if (RandomNumberGenerator.getRandomInt(10) > 5) {
                return new MultiAttackAction(map.locationOf(this), getIntrinsicWeapon());
            }
            return action;
        }

        if (this.hasCapability(Status.WILL_FOLLOW) && behaviours.get(998).getAction(this, map) != null) {
            return behaviours.get(998).getAction(this, map);

        }

        // enemy will wander around if not following
        System.out.println(behaviours.get(999).getAction(this, map));
        return behaviours.get(999).getAction(this, map);
    }

    /**
     * Enemies can be attacked by any actor that has the HOSTILE_TO_ENEMY
     * capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(otherActor instanceof Seafood)) {

            // Adds attacks for weapons in inventory
            for (Weapon w : otherActor.getWeaponInventory()) {
                actions.add(new AttackAction(this, direction, w));
            }
            // adds intrinsic weapon attack
            actions.add(new AttackAction(this, direction));
        }

        // Can be followed by other enemies
        if (otherActor.getClass() != this.getClass() && otherActor instanceof Enemy) {
            otherActor.addCapability(Status.WILL_FOLLOW);
        }
        // this is a janky way of doing it and I'm aware of that.
        if (otherActor.hasCapability(Status.WILL_FOLLOW)) {
            Enemy enemy = (Enemy) otherActor;
            enemy.addFollowBehaviour(new FollowBehaviour(this));
        }

        return actions;
    }
}

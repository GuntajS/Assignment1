package game.enemies;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;
import game.ActionCompare;
import game.AttackAction;
import game.MultiAttackAction;
import game.RandomNumberGenerator;
import game.Status;
import game.WanderBehaviour;

/**
 * Giant crab
 * 
 * @author Sacha Acland
 */
public class GiantCrab extends Enemy {
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        this.setIntrinsicWeapon(new IntrinsicWeapon(208, "slams", 90));
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
        List sortedActions = actions.sorted(new ActionCompare());

        Action action = (Action) sortedActions.get(0);
        if (action.getClass() == AttackAction.class) {
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

}

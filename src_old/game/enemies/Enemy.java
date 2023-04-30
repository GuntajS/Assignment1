package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;
import game.ActionCompare;
import game.AttackAction;
import game.Behaviour;
import game.FollowBehaviour;
import game.RandomNumberGenerator;
import game.Resettable;
import game.Status;
import game.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

/**
 * 
 * Class Enemy
 * 
 * @author Sacha Acland
 */
public abstract class Enemy extends Actor implements Resettable {

    protected Map<Integer, Behaviour> behaviours = new HashMap<>();
    private IntrinsicWeapon intrinsicWeapon;
    private boolean specialSkill = false;
    protected ActorLocationsIterator actorLocations = new ActorLocationsIterator();

    public Enemy(String name, char displayChar, int hp) {
        super(name, displayChar, hp);
        this.behaviours.put(999, new WanderBehaviour());
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
            return action;
        }

        if (this.hasCapability(Status.WILL_FOLLOW) && behaviours.get(998) != null) {
            System.out.println("test");
            return behaviours.get(998).getAction(this, map);

        }

        // enemy will wander around if not following
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.getClass() != otherActor.getClass()) {

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

    /**
     * Reset method for enemies. Sacha 13/4
     */
    @Override
    public void reset(GameMap map) {
        // TODO Auto-generated method stub
        map.removeActor(this);
    }

    /*
     * Weapon methods. For most cases getWeapon can be used-if an enemy only has an
     * intrinsic weapon getWeapon will return that.
     * If you want to override this and skip to an enemy's intrinisc weapon only
     * IntrinsicWeapon will return that.
     * I have not added a getWeaponItem method as I don't see it as being necessary
     * given the structure of getWeapon.
     * 
     */

    /**
     * Returns the weapon of an enemy. Default weapon is the Weapon equipped,
     * otherwise returns the intrinsic weapon
     * 
     * @return the weapon of the class
     */
    public Weapon getWeapon() {

        if (!this.getWeaponInventory().isEmpty()) {
            return this.getWeaponInventory().get(0);
        }
        return intrinsicWeapon;
    }

    /**
     * Returns the IntrinsicWeapon of an enemy.
     * 
     * @return An IntrinsicWeaopon
     */

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return intrinsicWeapon;
    }

    public void setIntrinsicWeapon(IntrinsicWeapon intrinsic) {
        this.intrinsicWeapon = intrinsic;
    }

    public void addFollowBehaviour(FollowBehaviour followBehaviour) {
        this.behaviours.put(998, followBehaviour);
    }

}

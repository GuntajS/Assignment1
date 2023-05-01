package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;
import game.AttackAction;
import game.FollowBehaviour;
import game.Status;
import game.WanderBehaviour;
import game.runes.RuneItem;

import java.util.HashMap;
import java.util.Map;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by: Sacha Acland 14/04
 *         Extends enemy
 *
 */
public class LoneWolf extends Enemy implements Dog {

    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
        this.setIntrinsicWeapon(new IntrinsicWeapon(97, "bites", 95));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(otherActor instanceof Dog)) {

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

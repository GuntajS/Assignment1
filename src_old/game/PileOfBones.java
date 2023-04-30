package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * @author Sacha Acland
 * 
 *         A class for a PileOfBones that can revive into something else
 */
public class PileOfBones extends Actor {

    private int count = 0;
    private Actor enemy;

    public PileOfBones(Actor enemy) {
        super("Pile of bones", 'X', 2);
        this.enemy = enemy;

        // transfers inventory over

        for (WeaponItem w : enemy.getWeaponInventory()) {
            this.addWeaponToInventory(w);
        }
        for (Item item : enemy.getItemInventory()) {
            this.addItemToInventory(item);
        }
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        count++;
        Action action = new DoNothingAction();
        if (count == 3) {
            action = new TransformationAction(enemy);
        }
        return action;
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

        return actions;
    }

}

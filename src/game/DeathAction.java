package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.runes.RuneItem;
import game.runes.RuneManager;
import game.enemies.Bones;

/**
 * An action executed if an actor is killed.
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {

        // player death case- basic version, just concerns runes
        // if there are any runes on the map already, despawns them.
        // not sure if this is the best way to be doing this
        if (target instanceof Player) {
            RuneManager.getRuneManagerInstance().despawnRunes();
            // despawns runes that are already out there

            if (RuneManager.hasRunes(target) != null && RuneManager.hasRunes(target).getRuneNum() != 0) {
                // if the player has runes, drops them and adds them to the RuneManager
                RuneManager.hasRunes(target).getDropAction(target).execute(target, map);
            }
            return "\nPlayer died";
        }

        String result = "";

        // case for if the enemy is a skeleton
        if (target instanceof Bones) {
            result += new TransformationAction(new PileOfBones(target)).execute(target, map);
        }

        else {

            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + menuDescription(target);

        }
        return result;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}

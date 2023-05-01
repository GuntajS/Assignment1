package game.runes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Resettable;

/**
 * @author Sacha Acland
 * 
 *         Class for a RuneManager to manage and keep track of RuneItems
 */
public class RuneManager {

    private static RuneManager instance;

    // list of runes that have been dropped
    private ArrayList<RuneItem> runeList = new ArrayList<RuneItem>();

    private HashMap<RuneItem, Location> runeMap = new HashMap<RuneItem, Location>();

    private RuneManager() {

    }

    public void despawnRunes() {
        Location loc;
        for (RuneItem rune : runeMap.keySet()) {
            loc = runeMap.get(rune);
            loc.removeItem(rune);
        }
    }

    public void addRune(RuneItem rune, Location location) {
        runeMap.put(rune, location);
    }

    public void removeRune(RuneItem rune) {
        runeList.remove(rune);
    }

    public static RuneManager getRuneManagerInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Check if an actor has a runeitem in their inventory
     * 
     * @param actor The actor to check
     * @return a runeitem or null
     */
    public static RuneItem hasRunes(Actor actor) {

        for (Item i : actor.getItemInventory()) {
            if (i instanceof RuneItem) {
                return (RuneItem) i;
            }
        }
        return null;
    }

}

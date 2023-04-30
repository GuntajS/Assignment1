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

/**
 * @author Sacha Acland
 * 
 *         Class for a RuneManager to manage and keep track of RuneItems
 */
public class RuneManager {

    // active rune items
    private static HashMap<Actor, RuneItem> runeItemsMap = new HashMap<Actor, RuneItem>();
    // dropped rune items
    private static List<RuneItem> runeItemsList = new ArrayList<RuneItem>();

    public static void addRuneItem(RuneItem runeItem, Actor actor) {
        runeItemsMap.put(actor, runeItem);
    }

    public static void actorDeath(Actor a) {
        runeItemsList.add(runeItemsMap.get(a));
        runeItemsMap.remove(a);
    }

    public static void despawn(RuneItem rune) {
        runeItemsList.remove(rune);
        rune = null;

    }

}

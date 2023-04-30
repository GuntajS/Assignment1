package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.runes.RuneItem;

public class Utils {
    public static RuneItem hasRunes(Actor actor) {

        for (Item i : actor.getItemInventory()) {
            if (i instanceof RuneItem) {
                return (RuneItem) i;
            }
        }
        return null;
    }
}

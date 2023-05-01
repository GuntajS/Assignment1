package game.runes;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class for an action to recover runes. Extends the PickUpAction class
 * 
 * @author Sacha Acland
 */
public class RecoverRuneAction extends PickUpAction {
    private RuneItem runeItem; // rune to be picked up

    public RecoverRuneAction(RuneItem runes) {
        super(runes);
        runeItem = runes;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // removes runeitems from map
        map.locationOf(actor).removeItem(runeItem);
        // check if actor has a runeitem already
        if (RuneManager.hasRunes(actor) != null) {
            RuneManager.hasRunes(actor).addRunes(runeItem);
            RuneManager.getRuneManagerInstance().removeRune(runeItem);
        }
        // case: actor does not already have a runeItem in the inventory
        actor.addItemToInventory(runeItem);
        return actor + " recovers " + runeItem.getRuneNum() + " runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Pick up runes (value: " + runeItem.getRuneNum() + ")";
    }

}

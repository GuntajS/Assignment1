package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class DropRuneAction extends DropAction {
    private RuneItem item;

    public DropRuneAction(RuneItem item) {
        super(item);
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        Location loc = map.locationOf(actor).getExits().get(4).getDestination();
        RuneManager.getRuneManagerInstance().addRune(item, loc);
        loc.addItem(item);
        return menuDescription(actor);
    }

}

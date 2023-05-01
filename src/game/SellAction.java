package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RuneItem;
import game.runes.RuneManager;

public class SellAction extends Action {

    /**
     * Current player
     */
    private Player player;

    /**
     * Name of item to sell
     */
    private final String itemName;

    /**
     * Reward for selling item
     */
    private final int itemReward;

    public SellAction(Player player, String itemName, int itemReward) {
        this.player = player;
        this.itemName = itemName;
        this.itemReward = itemReward;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // checking if player has runes and then adding the number of runes that they
        // recieve from the purchase
        if (RuneManager.hasRunes(player) != null) {
            RuneManager.hasRunes(player).addRunes(itemReward);
        } else {
            player.addItemToInventory(new RuneItem(itemReward));
        }
        switch (itemName) {
            case "Uchigatana":
                player.removeWeaponFromInventory(new Uchigatana());
                break;
            case "Great Knife":
                player.removeWeaponFromInventory(new GreatKnife());
                break;
            case "Club":
                player.removeWeaponFromInventory(new Club());
                break;
        }

        return "Sold " + this.itemName + " for " + this.itemReward + " runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Sell " + itemName + " to Merchant Kale for " + String.valueOf(itemReward) + " runes";
    }
}

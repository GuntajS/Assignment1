package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RuneItem;
import game.runes.RuneManager;

public class BuyAction extends Action {

    /**
     * Current Player
     */
    private Player player;

    /**
     * Name of item to buy
     */
    private final String itemName;

    /**
     * Cost of item
     */
    private final int itemCost;

    public BuyAction(Player player, String itemName, int itemCost) {
        this.player = player;
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // changes amount of runes in player inventory- removing cost
        if (RuneManager.hasRunes(player) != null && RuneManager.hasRunes(player).getRuneNum() != 0) {
            RuneManager.hasRunes(player).subtractRune(itemCost);
        }
        switch (itemName) {
            case "Uchigatana":
                player.addWeaponToInventory(new Uchigatana());
                break;
            case "Great Knife":
                player.addWeaponToInventory(new GreatKnife());
                break;
            case "Club":
                player.addWeaponToInventory(new Club());
                break;
        }

        return "Bought " + this.itemName + " for " + this.itemCost + " runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + itemName + " from Merchant Kale for " + String.valueOf(itemCost) + " runes";
    }
}
package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class BuyAction extends Action {

    /**
     * Current Player
     */
    private Player player;

    /**
     * Name of item to buy
     */
    private String itemName;

    /**
     * Cost of item
     */
    private int itemCost;

    public BuyAction(Player player, String itemName, int itemCost) {
        this.player = player;
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        switch (itemName) {
            case "Uchigatana":
                player.addWeaponToInventory(new Uchigatana());

            case "Great Knife":
                player.addWeaponToInventory(new GreatKnife());
            case "Club":
                player.addWeaponToInventory(new Club());
        }

        return "Bought " + this.itemName + " for " + this.itemCost + " runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + itemName + " from Merchant Kale for " + String.valueOf(itemCost) + " runes";
    }
}

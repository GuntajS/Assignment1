package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;


public class Trader extends Actor {

    /**
     * Current player
     */
    protected Player player;

    public Trader(Player player) {
        super("Merchant Kale", 'k', 999999);
        this.player = player;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //System.out.println("Test Nig");

        return lastAction;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//        return super.allowableActions(otherActor, direction, map);

        ActionList actions = new ActionList();

        actions.add(new BuyAction(player, "Uchigatana", 5000));
        actions.add(new BuyAction(player, "Great Knife", 3500));
        actions.add(new BuyAction(player, "Club", 600));

    return actions;
    }
}

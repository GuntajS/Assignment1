package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;
import game.Status;
import game.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by: Sacha Acland 14/04
 *
 */
public class LoneWolf extends Enemy {

    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        this.setIntrinsicWeapon(new IntrinsicWeapon(97, "bites", 95));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

}

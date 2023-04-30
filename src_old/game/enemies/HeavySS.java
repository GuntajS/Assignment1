package game.enemies;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;
import game.ActionCompare;
import game.AttackAction;
import game.Bones;
import game.Grossmesser;
import game.PileOfBones;
import game.RandomNumberGenerator;
import game.Status;

/**
 * A class for the Heavy Skeletal Swordsman.
 * 
 * @author Sacha Acland
 */
public class HeavySS extends Enemy implements Bones {
    public HeavySS() {
        super("Heavy Skeletal Swordsman", 'q', 153);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addWeaponToInventory(new Grossmesser());
        this.setIntrinsicWeapon(new IntrinsicWeapon(7, "punches", 90));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        List sortedActions = actions.sorted(new ActionCompare());

        Action action = (Action) sortedActions.get(0);
        if (action.getClass() == AttackAction.class) {

            // there's got to be a better way to do this
            if ((RandomNumberGenerator.getRandomInt(10) > 5) && getWeapon().getClass() == Grossmesser.class) {
                Grossmesser weapon = (Grossmesser) getWeapon();
                return weapon.getSpinAttack(map.locationOf(this));
            }
            return action;
        }

        if (this.hasCapability(Status.WILL_FOLLOW) && behaviours.get(998).getAction(this, map) != null) {
            return behaviours.get(998).getAction(this, map);

        }

        // enemy will wander around if not following
        return behaviours.get(999).getAction(this, map);
    }

}
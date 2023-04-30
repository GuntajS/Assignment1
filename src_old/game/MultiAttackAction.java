package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A class to attack multiple targets around a location
 * 
 * @author Sacha Acland
 */

public class MultiAttackAction extends Action {
    private Location location;
    private Weapon weapon;
    Random rand = new Random();

    /**
     * Constructor for the MultiAttackAction
     * 
     * @param loc Location the actor is at
     * @param wp  the weapon the actor is using
     */
    public MultiAttackAction(Location loc, Weapon wp) {
        this.location = loc;
        this.weapon = wp;

    }

    /**
     * Execute the action and return string. chance of hitting is calculated for
     * each individual target.
     * 
     * @param actor The actor performing the action
     * @param map   The game map
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        String result = actor + " " + weapon.verb() + " all surrounding targets \n";

        for (Exit e : location.getExits()) {
            if (e.getDestination().containsAnActor()) {
                actors.add(e.getDestination().getActor());
            }
        }
        for (Actor target : actors) {
            if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                return actor + " misses " + target + ".";
            }
            int damage = weapon.damage();
            result += "\n" + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. \n";
            target.hurt(damage);
            if (!target.isConscious()) {
                result += new DeathAction(actor).execute(target, map);
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks surrounding enemies with "
                + (weapon != null ? weapon : "Intrinsic Weapon");
    }

}

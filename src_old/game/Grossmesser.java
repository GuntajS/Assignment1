package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.engine.actions.*;

/**
 * A curved sword that can be used to attack the enemy.
 * It deals 115 damage with 85% hit rate
 * Created by:
 * 
 * @author Sacha Acland
 *
 */
public class Grossmesser extends WeaponItem {

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slashes", 85);

    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAction(target, direction, this);

    }

    public Action getSpinAttack(Location location) {
        return new MultiAttackAction(location, this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}

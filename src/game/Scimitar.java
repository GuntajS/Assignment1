package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Scimitar extends WeaponItem implements MultiAttackWeapon {

    /**
     * Constructor
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88);

    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAction(target, direction, this);

    }

    public Action getMultiAttack(Location location) {
        return new MultiAttackAction(location, this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}

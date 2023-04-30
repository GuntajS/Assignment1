package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Location;

public interface MultiAttackWeapon {

    public Action getMultiAttack(Location location);
}
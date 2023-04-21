package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class ClubGunni extends WeaponItem {
    /**
     * Constructor.
     *
     * Club is the name        name of the item
     * ! is the displayChar character to use for display when item is on the ground
     * 103 damage      amount of damage this weapon does
     * "Wacks" is the  verb        verb to use for this weapon, e.g. "hits", "zaps"
     * 80% hitRate     the probability/chance to hit the target.
     */
    public ClubGunni() {
        super("Club", '!', 103, "Wacks  ", 80);
    }
}

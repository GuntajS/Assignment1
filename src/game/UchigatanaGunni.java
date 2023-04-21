package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class UchigatanaGunni extends WeaponItem {
    /**
     * Constructor.
     *
     * Uchigatana is the name        name of the item
     * ! is the displayChar character to use for display when item is on the ground
     * 115 damage      amount of damage this weapon does
     * "Slices" is the  verb        verb to use for this weapon, e.g. "hits", "zaps"
     * 80% hitRate     the probability/chance to hit the target.
     */
    public UchigatanaGunni() {
        super("Uchigatana", ')', 115, "Slices", 80);
    }
}

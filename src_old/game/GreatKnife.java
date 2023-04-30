package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class GreatKnife extends WeaponItem{
    /**
     * Constructor.
     *
     * Great Knife is the name        name of the item
     * / is the displayChar character to use for display when item is on the ground
     * 75 damage      amount of damage this weapon does
     * "slash" is the  verb        verb to use for this weapon, e.g. "hits", "zaps"
     * 70% hitRate     the probability/chance to hit the target.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "Slashes ", 70);
    }
    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
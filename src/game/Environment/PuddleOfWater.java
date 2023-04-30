package game.Environment;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantCrab;

public class PuddleOfWater extends Ground {
    private double spawnChance;
    public PuddleOfWater() {
        super('~');
        spawnChance = 0;
    }

    @Override
    public void tick(Location location) {
        spawnChance = Math.random();
        if (spawnChance <= 0.02 && !location.containsAnActor()) {
            location.addActor(new GiantCrab());
        }
    }
}

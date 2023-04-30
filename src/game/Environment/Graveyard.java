package game.Environment;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.HeavySS;


public class Graveyard extends Ground {

    private double spawnChance;

    public Graveyard() {
        super('n');
        spawnChance = 0;
    }

    @Override
    public void tick(Location location) {
        spawnChance =  Math.random() ;
        if(spawnChance <= 0.27 && !location.containsAnActor()){
            location.addActor(new HeavySS());
        }

    }

}

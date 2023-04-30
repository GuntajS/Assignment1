package game.Environment;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.LoneWolf;

public class GustOfWind extends Ground {
    private double spawnChance;
    public GustOfWind() {
        super('&');
        spawnChance = 0;
    }



    @Override
    public void tick(Location location) {
        spawnChance =  Math.random() ;
        if(spawnChance <= 0.33 && !location.containsAnActor()){
            location.addActor(new LoneWolf());
        }

    }
}

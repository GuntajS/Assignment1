package game;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by: Sacha Acland 13/4
 *
 */
public interface Resettable {
    void reset(GameMap map);
    // Added Map to reset method(for removing actors)
}

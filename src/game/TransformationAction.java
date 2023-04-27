package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * 
 * @author Sacha Acland
 *         A class that transforms one actor into another. Can be used for
 *         changing skeletons into piles of bones and vice versa.
 *         could be used for other things also
 */
public class TransformationAction extends Action {

    private Actor target;

    public TransformationAction(Actor actor) {
        target = actor;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(target, loc);
        return actor + " turned into " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        // TODO Auto-generated method stub
        return actor + " transforms";
    }

}

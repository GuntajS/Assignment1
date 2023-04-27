package game;

import java.util.Comparator;
import edu.monash.fit2099.engine.actions.*;

/**
 * Comparator for Actions
 * 
 * @author Sacha Acland
 */
public class ActionCompare implements Comparator<Action> {

    public ActionCompare() {

    }

    // Comparator for actions. Attacks have the highest priority, followed by move,
    // followed by anything else.
    public int compare(Action a, Action b) {

        if (a.getClass() == b.getClass()) {
            return 0;
        }

        else if (a.getClass() == AttackAction.class && b.getClass() != AttackAction.class) {
            return -1;
        } else if (a.getClass() != AttackAction.class && b.getClass() == AttackAction.class) {
            return 1;
        }

        else if (a.getClass() == MoveActorAction.class && b.getClass() != MoveActorAction.class) {
            return 0;
        } else if (a.getClass() != MoveActorAction.class && b.getClass() == MoveActorAction.class) {
            return 0;
        }

        else if (a.getClass() == AttackAction.class && b.getClass() == MoveActorAction.class) {
            return -1;
        }

        else if (a.getClass() == MoveActorAction.class && b.getClass() == AttackAction.class) {
            return 1;
        }

        else if (a.getClass() == AttackAction.class && b.getClass() == DoNothingAction.class) {
            return 1;
        }

        return 0;
    }

}

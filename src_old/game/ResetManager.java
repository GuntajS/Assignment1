package game;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by: Sacha Acland 22/4
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Gets the instance of ResetManager
     * 
     * @return resetManager class
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    public void run() {
    }

    public void registerResettable(Resettable resettable) {
    }

    public void removeResettable(Resettable resettable) {
    }
}

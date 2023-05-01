package game.runes;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.RandomNumberGenerator;
import game.Resettable;

/**
 * @author Sacha Acland
 * 
 *         Class for an Item representing runes
 */
public class RuneItem extends Item implements Resettable {
    // the amount of runes a RuneItem has
    private int runeNum;

    // if runes will not be despawned
    private boolean stable = true;

    /**
     * Constructor for RuneItem
     * 
     * @param amount amount of runes
     */
    public RuneItem(int amount) {
        super("Runes", '$', true);
        runeNum = amount;

    }

    /**
     * Constructor for runeitems that generates a random number of ruens
     * 
     * @param runeNumLower lower bound
     * @param runeNumUpper upper bound
     */
    public RuneItem(int runeNumLower, int runeNumUpper) {
        super("Runes", '$', true);
        runeNum = RandomNumberGenerator.getRandomInt(runeNumLower, runeNumUpper);
    }

    /**
     * Get the amount of runes
     * 
     * @return runeNum
     */
    public int getRuneNum() {
        return runeNum;
    }

    /**
     * Add runes to runeNum
     * 
     * @param runeNum integer value of runes
     */
    public void addRunes(int runeNum) {
        this.runeNum += runeNum;
    }

    /**
     * Add two RuneItems
     * 
     * @param runes RuneItem to be added
     */
    public void addRunes(RuneItem runes) {
        this.runeNum += runes.getRuneNum();
    }

    /**
     * Subtract runes from runeNum
     * 
     * @param runeNum number of runes to subtract
     */
    public void subtractRune(int runeNum) {
        this.runeNum -= runeNum;
    }

    /**
     * Subtract one RuneItem from another
     * 
     * @param runes the RuneItem to subtract
     */
    public void subtractRune(RuneItem runes) {
        this.runeNum -= runes.getRuneNum();
    }

    public void setStable(boolean stable) {
        this.stable = stable;
    }

    public boolean isStable() {
        return this.stable;
    }

    /**
     * Pickupaction for runes- returns a recoverrune action
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new RecoverRuneAction(this);
    }

    /**
     * Pickupaction for runes- returns a recoverrune action
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return new DropRuneAction(this);
    }

    @Override
    public void reset(GameMap map) {
        stable = true;
    }

}

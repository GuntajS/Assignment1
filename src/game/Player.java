package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.*;
import game.enemies.Enemy;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by:
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		// The player can be followed by enemies
		ActionList actions = new ActionList();

		// Can be followed by other enemies
		if (otherActor instanceof Enemy) {
			otherActor.addCapability(Status.WILL_FOLLOW);
		}

		if (otherActor.hasCapability(Status.WILL_FOLLOW)) {
			Enemy enemy = (Enemy) otherActor;
			enemy.addFollowBehaviour(new FollowBehaviour(this));
		}
		// The player can be attacked by enemies
		if (otherActor instanceof Enemy) {
			Weapon weapon = ((Enemy) otherActor).getWeapon();
			actions.add(new AttackAction(this, direction, weapon));
		}
		return actions;
	}

	@Override
	public void reset(GameMap map) {

	}
}

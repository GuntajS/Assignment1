package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Environment.Graveyard;
import game.Environment.GustOfWind;
import game.Environment.PuddleOfWater;
import game.enemies.*;

/**
 * The main class to start the game.
 * Created by:
 * 
 * @author Adrian Kristanto
 *         Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(),
				new GustOfWind(), new PuddleOfWater());

		List<String> map = Arrays.asList(
				"..nnnn................................................~~~~~~~~~~~~~~~~~~~~~",
				"......................#####....######..................~~~~~~~~~~~~~~~~~~~~",
				"..nnnn................#..___....____#...................~~~~~~~~~~~~~~~~~~~",
				"..................................__#....................~~~~~~~~~~~~~~~~~~",
				"......................._____........#.....................~~~~~~~~~~~~~~~~~",
				"......................#............_#......................~~~~~~~~~~~~~~~~",
				"......................#...........###......................................",
				"...........................................................................",
				"...........................................................................",
				"~~~~~~~~~~~.......................###___###................................",
				"~~~~~~~~~~~~......................________#....nnnn........................",
				"~~~~~~~~~~~~~.....................#________................................",
				"~~~~~~~~~~~~......................#_______#....nnnn........................",
				"~~~~~~~~~~~.......................###___###................................",
				"~~~~~~~~~~..........................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##...........................................&&&......######..##...",
				"..#.....__...........&&&.............................&&&......#....____....",
				"..#___...............&&&.............................&&&........__.....#...",
				"..####__###..........&&&......................................_.....__.#...",
				"..............................................................###..__###...",
				"...........................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		// checking cardinal location- higher than 50% of x is east
		for (int y : gameMap.getYRange()) {
			// iterates through each location
			// could potentially add north and south as well
			for (int x : gameMap.getXRange()) {
				if (x >= (gameMap.getXRange().max() / 2)) {
					gameMap.at(x, y).getGround().addCapability(Compass.EAST);
				} else {
					gameMap.at(x, y).getGround().addCapability(Compass.WEST);
				}
			}
		}

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		System.out.println("Choose your Class:\n 1.Wretch\n 2.Bandit\n 3.Samurai\n");
		Scanner scanner = new Scanner(System.in);
		int classNumber = scanner.nextInt();

		int hitpoints = 0;
		switch (classNumber) {
			case 1:
				hitpoints = 455;
				break;
			case 2:
			case 3:
				hitpoints = 414;
				break;
		}
		Player player = new Player("Tarnished", '@', hitpoints, classNumber);
		world.addPlayer(player, gameMap.at(36, 14));
		gameMap.at(38, 10).addActor(new Trader(player));

		world.run();
	}
}

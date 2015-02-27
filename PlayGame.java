/**
 * Class that the player enters into in order to begin the game
 * Contains methods to read user input and perform the necessary
 * operations based on that input
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayGame {

	private static GameLogic gameLogic;

	public static void main(String[] args) {

		gameLogic = new GameLogic();
		newGame();

	}

	//Method called to begin a new game, sets up the map and then prints it
	private static void newGame() {

		gameLogic.setupGame();
		System.out.println("Welcome to Dungeons of Dooom!");
		gameLogic.printMapName();
		gameLogic.printMap();
		startGame();

	}

	//Method that is called to begin the game
	private static void startGame() {

		boolean finished = false;

		//Loop continues until user steps on an exit with enough gold, or quits
		while (!finished) {

			System.out.print("What do you wish to do?: ");
			String[] command = readInput();

			if (command[0].equalsIgnoreCase("move")) {

				if (command.length == 2) {

					boolean validMove = gameLogic.move(command[1]);

					if (!validMove) {

						System.out.println("That is not a valid move, please try a different direction");

					}

				} else {

					System.out.println("That is not a recognised direction");

				}

			} else if (command[0].equalsIgnoreCase("hello")) {

				System.out.println("Gold: " + gameLogic.getRequiredGold());

			} else if (command[0].equalsIgnoreCase("pickup")) {

				gameLogic.pickUp();

			} else if (command[0].equalsIgnoreCase("look")) {

				char[][] fieldOfView = gameLogic.look();
				System.out.println("LOOKREPLY");
				gameLogic.printCharArray(fieldOfView);

			} else if (command[0].equalsIgnoreCase("quit")) {

				quit();

			} else if (command[0].equalsIgnoreCase("help")) {

				gameLogic.help();

			}

			finished = gameLogic.checkFinished();

		}

		//If this point is reached, then the while loop has stopped, meaning the player has completed the map
		System.out.println("Well done, you have completed this map!");

	}

	//Simple method to take input with a BufferedReader and return the input as a string array
	private static String[] readInput() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input;

		try {

			input = reader.readLine();

		} catch (IOException e) {

			System.err.println(e.getMessage());
			return null;

		}

		String[] commandArray = input.split(" ");
		return commandArray;

	}

	//Method called if the player enters the quit command
	private static void quit() {

		System.out.println("You have quit. Do you wish to play again?: (Y/N)");
		String[] response = readInput();

		if (response[0].equalsIgnoreCase("n") || response[0].equalsIgnoreCase("no")) {

			System.exit(0);

		} else if (response[0].equalsIgnoreCase("y") || response[0].equalsIgnoreCase("yes")) {

			newGame();

		} else {

			System.out.println("That is not a recognised response");
			quit();

		}

	}

}

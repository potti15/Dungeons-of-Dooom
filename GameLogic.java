/**
 * Class that holds the majority of the logic of the game
 * so that the game will follow the rules given in the
 * specification
 */
public class GameLogic {

	private char[][] map;
	private int winCondition;
	private char beneathPlayer;
	private int mapHeight;
	private int mapWidth;
	private int playerRow;
	private int playerColumn;
	private int playerGold;
	private Map mapClass;

	//Method called to create a new game. Fetches the map and positions the player within it
	public void setupGame() {

		mapClass = new Map();
		map = mapClass.getMap();
		playerGold = 0;
		winCondition = mapClass.getWinCondition();
		mapHeight = map.length;
		mapWidth = map[0].length;
		positionPlayer();

	}

	//Method for positioning the player in the map at the start of the game
	private void positionPlayer() {

		boolean positioned = false;

		//A random location is selected, and the player is placed there if it is a valid
		//space, rather than a wall. If not, the method tries with a new random location
		while (!positioned) {

			int row = (int) Math.floor((Math.random() * mapHeight));
			int column = (int) Math.floor((Math.random() * mapWidth));

			if (!(map[row][column] == '#')) {

				playerRow = row;
				playerColumn = column;
				beneathPlayer = map[row][column];
				map[row][column] = 'P';
				positioned = true;

			}

		}

	}

	//Method for printing the map to the standard out
	public void printMap() {

		printCharArray(map);

	}

	//Method for printing the name indicated by the first line in the map.txt file
	public void printMapName() {

		String name = mapClass.getName();
		System.out.println("You are playing in: " + name);

	}

	//Method for printing a 2D character array
	public void printCharArray(char[][] array) {

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array[0].length; j++) {

				System.out.print(array[i][j]);

			}

			System.out.println("");

		}

	}

	//Accessor which returns the amount of gold required to exit the map
	public int getRequiredGold() {

		return winCondition;

	}

	//Method for moving the player or bot around the map
	public boolean move(String direction) {

		boolean validMove = true;

		if (direction.equalsIgnoreCase("N")) {

			if (checkForWall(playerRow - 1, playerColumn)) {

				validMove = false;

			}

		} else if (direction.equalsIgnoreCase("E")) {

			if (checkForWall(playerRow, playerColumn + 1)) {

				validMove = false;

			}

		} else if (direction.equalsIgnoreCase("S")) {

			if (checkForWall(playerRow + 1, playerColumn)) {

				validMove = false;

			}

		} else if (direction.equalsIgnoreCase("W")) {

			if (checkForWall(playerRow, playerColumn - 1)) {

				validMove = false;

			}

		} else {

			System.out.println("" + direction + " is not a recognised direction. Use N, S, E or W");

		}

		return validMove;

	}

	//Method for replacing what was beneath the player when they moved onto that tile (e.g. 'E', 'G' or '.')
	private void restoreBeneath() {

		map[playerRow][playerColumn] = beneathPlayer;

	}

	//Method that checks whether a certain position contains a wall tile, and moves the player if it does not
	private boolean checkForWall(int row, int column) {

		if (map[row][column] == '#') {

			return true;

		} else {

			//Series of statements required to move the player to the position (row, column)
			restoreBeneath();
			playerRow = row;
			playerColumn = column;
			beneathPlayer = map[playerRow][playerColumn];
			map[playerRow][playerColumn] = 'P';
			printMap();
			return false;

		}

	}

	//Method to attempt to pick up gold beneath the player
	public void pickUp() {

		if (beneathPlayer == 'G') {

			playerGold++;
			beneathPlayer = '.';
			System.out.println("Success, gold coins: " + playerGold);

		} else {

			System.out.println("Fail");

		}

	}

	//Method to check whether the player has enough gold to leave with an exit
	public boolean checkFinished() {

		if ((beneathPlayer == 'E') && (playerGold >= winCondition)) {

			return true;

		}

		return false;

	}

	//Method to return the LOOKREPLY to standard out
	public char[][] look () {

		char[][] fieldOfView = new char[5][5];
		int row = playerRow - 2;

		for (int i = 0; i < 5; i++) {

			int column = playerColumn - 2;

			for (int j = 0; j < 5; j++) {


				if (column < 0 || column >= map[0].length || row < 0 || row >= map.length) {

					//This if ensures that if the player is at the edge of the map then walls are printed outside of the map
					fieldOfView[i][j] = '#';

				} else if ((i == 0 && j == 0) || (i == 4 && j == 0) || (i == 0 && j == 4) || (i == 4 && j ==4)) {

					//This if places 'X' characters at the 4 corners of the LOOKREPLY
					fieldOfView[i][j] = 'X';

				} else {

					//If the position is not a corner, or outside the map, then the tile in that position is printed
					fieldOfView[i][j] = map[row][column];

				}

				column++;

			}

			row++;

		}

		return fieldOfView;

	}

	//Method for printing the available commands to the standard out
	public void help() {

		System.out.println("The commands available are: Move <direction>, Hello, Pickup, Look and Quit");

	}

}

/**
 * Class which contains a simple bot class to play in place
 * of a human player.
 * The bot will move towards gold that it is adjacent to, and will not
 * attempt to move into walls, but otherwise moves randomly.
 */
public class Bot {

	private static GameLogic gameLogic;
	private static char[][] fieldOfView;

	public static void main(String[] args) {

		gameLogic = new GameLogic();
		System.out.println("Bot will play");
		gameLogic.setupGame();

		while (!gameLogic.checkFinished()) {

			makeMove();

			//This try catch is to pause the bot after every move to make it easier to view its moves
			try {

				Thread.sleep(100);

			} catch(InterruptedException ex) {

					Thread.currentThread().interrupt();

			}

		}

		System.out.println("Finished.");

	}

	//Method called to make the bot take a move
	private static void makeMove() {

		//The bot attempts to pick up gold at every move for simplicitiy
		gameLogic.pickUp();
		fieldOfView = gameLogic.look();
		char nextDirection;
		//Checks for gold adjacent to itself
		nextDirection = checkForGold();

		//If not gold is found adjacent to the bot, it moves randomly
		if (nextDirection == ' ') {

			nextDirection = randomDirection();

		}

		gameLogic.move(String.valueOf(nextDirection));

	}

	//Method for checking the surrounding tiles for gold
	private static char checkForGold() {

		char immediateDirection = ' ';

		//If statements based on the fact that the player is always in fieldOfView[2][2]
		if (fieldOfView[2][1] == 'G') {

			immediateDirection = 'W';

		} else if (fieldOfView[2][3] == 'G') {

			immediateDirection = 'E';

		} else if (fieldOfView[1][2] == 'G') {

			immediateDirection = 'N';

		} else if (fieldOfView[3][2] == 'G') {

			immediateDirection = 'S';

		}

		return immediateDirection;

	}

	//Method for moving the bot randomly, whilst preventing it from walking into wallks
	private static char randomDirection() {

		char direction = ' ';
		boolean validMove = false;

		//Loops until a valid move is found (i.e. not in the direction of a wall)
		while(!validMove) {

			int directionInt = (int) Math.floor(Math.random() * 4);

			switch (directionInt) {

				case 0: if (!checkForWall(1, 2)) {

									direction = 'N';
									validMove = true;

								}

								break;

				case 1: if (!checkForWall(3, 2)) {

									direction = 'S';
									validMove = true;

								}

								break;

				case 2: if (!checkForWall(2, 3)) {

									direction = 'E';
									validMove = true;

								}

								break;

				case 3: if (!checkForWall(2, 1)) {

									direction = 'W';
									validMove = true;

								}

								break;

			}

		}

		return direction;

	}

	//Method for checking whether a tile contains a wall
	private static boolean checkForWall(int row, int column) {

		if (fieldOfView[row][column] == '#') {

			return true;

		} else {

			return false;

		}

	}

}

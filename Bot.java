
public class Bot {
	
	private static GameLogic gameLogic;
	
	public static void main(String[] args) {
		
		gameLogic = new GameLogic();
		System.out.println("Bot will play");
		gameLogic.setupGame();
		
		while (!gameLogic.checkFinished()) {
			
			makeMove();
			
		}
		
		System.out.println("Finished.");
		
	}
	
	private static void makeMove() {
		
		gameLogic.pickUp();
		char[][] fieldOfView = gameLogic.look();
		char nextDirection;
		nextDirection = checkForGold(fieldOfView);
		
		if (nextDirection == ' ') {
			
			nextDirection = randomDirection();
		
		}
		
		gameLogic.printMap();
		gameLogic.move(String.valueOf(nextDirection));
		
	}
	
	private static char checkForGold(char[][] fieldOfView) {
		
		char directionOfGold = ' ';
		
		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < 5; j++) {
				
				if (fieldOfView[i][j] == 'G') {
					
					directionOfGold = findRelativeDirection(i, j, 2, 2);
					break;
					
				}
				
			}
			
		}
		
		return directionOfGold;
		
	}
	
	private static char findRelativeDirection(int yObject, int xObject, int xSubject, int ySubject) {
		
		char direction = ' ';
		
		if ((xObject == xSubject) && (yObject < ySubject)) {
			
			direction = 'N';
			
		} else if ((xObject == xSubject) && (yObject > ySubject)) {
			
			direction = 'S';
			
		} else if (xObject < xSubject) {
			
			direction = 'W';
			
		} else if (xObject > xSubject) {
			
			direction = 'E';
			
		}
		
		return direction;
		
	}
	
	private static char randomDirection() {
		
		char direction = ' ';
		int directionInt = (int) Math.floor(Math.random() * 4);
		
		switch (directionInt) {
		
			case 0: direction = 'N';
					break;
			case 1: direction = 'E';
					break;
			case 2: direction = 'S';
					break;
			case 3: direction = 'W';
					break;
		
		}
		
		return direction;
		
	}

}

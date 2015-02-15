
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
	
	public void setupGame() {
		
		mapClass = new Map();
		map = mapClass.getMap();
		playerGold = 0;
		winCondition = mapClass.getWinCondition();
		mapHeight = map.length;
		mapWidth = map[0].length;
		positionPlayer();
		
	}
	
	private void positionPlayer() {
		
		boolean positioned = false;
		
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
	
	public void printMap() {
		
		printCharArray(map);
		
	}
	
	public void printMapName() {
		
		String name = mapClass.getName();
		System.out.println("You are playing in: " + name);
		
	}
	
	public void printCharArray(char[][] array) {
		
		for (int i = 0; i < array.length; i++) {
			
			for (int j = 0; j < array[0].length; j++) {
				
				System.out.print(array[i][j]);
				
			}
			
			System.out.println("");
			
		}
		
	}
	
	public int getRequiredGold() {
		
		return winCondition;
		
	}
	
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
	
	private void restoreBeneath() {
		
		map[playerRow][playerColumn] = beneathPlayer;
		
	}
	
	private boolean checkForWall(int row, int column) {
		
		if (map[row][column] == '#') {
			
			return true;
			
		} else {
			
			restoreBeneath();
			playerRow = row;
			playerColumn = column;
			beneathPlayer = map[playerRow][playerColumn];
			map[playerRow][playerColumn] = 'P';
			printMap();
			return false;
			
		}
		
	}
	
	public void pickUp() {
		
		if (beneathPlayer == 'G') {
			
			playerGold++;
			beneathPlayer = '.';
			System.out.println("Success, gold coins: " + playerGold);
			
		} else {
			
			System.out.println("Fail");
			
		}
		
	}
	
	public boolean checkFinished() {
		
		if ((beneathPlayer == 'E') && (playerGold >= winCondition)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public char[][] look () {
		
		char[][] fieldOfView = new char[5][5];
		int row = playerRow - 2;
		
		for (int i = 0; i < 5; i++) {
			
			int column = playerColumn - 2;
			
			for (int j = 0; j < 5; j++) {
				
				if (column < 0 || column >= map[0].length || row < 0 || row >= map.length) {
					
					fieldOfView[i][j] = '#';
					
				} else if ((i == 0 && j == 0) || (i == 4 && j == 0) || (i == 0 && j == 4) || (i == 4 && j ==4)) {
				
					fieldOfView[i][j] = 'X';
				
				} else {
					
					fieldOfView[i][j] = map[row][column];
					
				}
				
				column++;
				
			}
			
			row++;
			
		}
		
		return fieldOfView;
		
	}

	public void help() {
		
		System.out.println("The commands available are: Move <direction>, Hello, Pickup, Look and Quit");
		
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayGame {
	
	private static GameLogic gameLogic;
	
	public static void main(String[] args) {
		
		gameLogic = new GameLogic();
		newGame();
		
	}
	
	private static void newGame() {
		
		gameLogic.setupGame();
		System.out.println("Welcome to Dungeons of Dooom!");
		gameLogic.printMapName();
		gameLogic.printMap();
		startGame();
		
	}
	
	private static void startGame() {
		
		boolean finished = false;
		
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
		
		System.out.println("Well done, you have completed this map!");
		
	}
	
	//Simple method to take input with a BufferedReader
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

	private static void quit() {
		
		System.out.println("You have quit. Do you wish to play again?: (Y/N)");
		String[] response = readInput();
		
		if (response[0].equalsIgnoreCase("n") || response[0].equalsIgnoreCase("no")) {
			
			System.exit(0);
			
		} else if (response[0].equalsIgnoreCase("y") || response[0].equalsIgnoreCase("yes")) {
			
			newGame();
			
		}
		
	}

}

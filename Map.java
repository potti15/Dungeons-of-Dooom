import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

	private String name;
	private String winCondition;
	private char[][] map;
	private int height = 0;
	private int width = 0;
	private File mapFile;

	//Constructor, calls methods to read the map and store it
	public Map() {

		super();
		mapFile = new File("map.txt");
		getMapSize();
		readMap();

	}

	//Method to store the height and width of the map in variables
	private void getMapSize() {

		Scanner mapSizeScanner;

		try {

			mapSizeScanner = new Scanner(mapFile);

		} catch (FileNotFoundException e) {

			System.out.println("Map file not found.");
			return;

		}

		while (mapSizeScanner.hasNextLine()) {

			if (height == 2) {

				String firstMapLine = mapSizeScanner.nextLine();
				width = firstMapLine.length();
				height++;

			} else {

				mapSizeScanner.nextLine();
				height++;

			}

		}

		mapSizeScanner.close();
		height -= 2;

		map = new char[height][width];

	}

	//Method to read and store the map into a character array
	private void readMap() {

			Scanner mapScanner;

			try {

				mapScanner = new Scanner(mapFile);

			} catch (FileNotFoundException e) {

				System.out.println("Map file not found.");
				return;

			}

			name = mapScanner.nextLine();
			winCondition = mapScanner.nextLine();

			for (int i = 0; i < height; i++) {

				String mapLine = mapScanner.nextLine();

				for (int j = 0; j < width; j++) {

					map[i][j] = mapLine.charAt(j);

				}

			}

			mapScanner.close();



	}

	//Gets the name of the map, taken from the first line of the file
	public String getName() {

		name = name.substring(5);
		return name;

	}

	//Accessor for other classes to get a copy of the map
	public char[][] getMap() {

		return map;

	}

	//Accessor to return the amount of gold required to win
	public int getWinCondition() {

		winCondition = winCondition.substring(4);
		int winConditionInt = Integer.valueOf(winCondition);
		return winConditionInt;

	}

}


public class MapTest {
	
	public static void main(String[] args) {
		
		Map foo = new Map();
		char[][] map = foo.getMap();
		int win = foo.getWinCondition();
		
		System.out.println("Win = " + win);
		
		for (int i = 0; i < map.length; i++) {
			
			for (int j = 0; j < map[0].length; j++) {
				
				System.out.print(map[i][j]);
				
			}
			
			System.out.println("");
			
		}
		
	}

}

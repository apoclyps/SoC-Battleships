
public class AIGrid extends Grid{

	private static Fleet [] AIList = new Fleet [8];
	
	static String[] AIShips = {"AIBattleship1", "AICruiser1", "AICruiser2", "AIDestroyer1",
		"AIDestroyer2","AIDestroyer3","AISubmarine1","AISubmarine2","AISubmarine3"}; 
	
	static Fleet AIBattleship1 = new Fleet(4, false,false,"HMS.Battleship");
	static Fleet AICruiser1 = new Fleet(3,false,false,"HMS.Cruiser");
	static Fleet AICruiser2 = new Fleet(3,false,false,"HMS.Cruiser");
	static Fleet AIDestroyer1 = new Fleet(2,false,false,"HMS.Destroyer");
	static Fleet AIDestroyer2 = new Fleet(2,false,false,"HMS.Destroyer");
	static Fleet AIDestroyer3 = new Fleet(2,false,false,"HMS.Destroyer");
	static Fleet AISubmarine1 = new Fleet(1,false,false,"HMS.Submarine");
	static Fleet AISubmarine2 = new Fleet(1,false,false,"HMS.Submarine");
	static Fleet AISubmarine3 = new Fleet(1,false,false,"HMS.Submarine");
	
	public static void createShipList()
	{
			AIList [0] = AIBattleship1;
			AIList [1] = AICruiser1;
			AIList [2] = AICruiser2;
			AIList [3] = AIDestroyer1;
			AIList [4] = AIDestroyer2;
			AIList [5] = AIDestroyer3;
			AIList [6] = AISubmarine1;
			AIList [7] = AISubmarine2;
			AIList [8] = AISubmarine3;	
	}
		
	public static void initiateGame()
	{
		int [] position = new int[2];
		position[0] = 1;
		position[1] = 2;
		
		createShipList();
		
		UserGrid.initiliseGrid();
		
		UserGrid.checkIfOccupied();
		UserGrid.displayGrid();
		
		//AIGrid.PlaceShip();

		UserGrid.displayGrid();

		//AIGrid.saveGame();
	}
}

/**
 * Grid
 * imports reader/writer/IOexception and random
 * creates a new instance of Grid when the constructor is called.
 * Grid will initilise the game for the user.
 * Methods allow for the game to be reset and changed before and after each play.
 * Allows for games to be loaded and saved alongside ships cordiantes generated
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Grid {

	// global variables
	protected static int xCoordinates;
	protected static int yCoordinates;
	protected static int randomY;
	protected static int randomX;
	protected static String shipName;
	protected static int shipSize;
	protected static  String shipPosition;
	protected static int GridCount=0;
	protected static String orientation=null;
	protected static int[][] Grid = new int[10][10];
	protected static int shotsMissed=0;
	protected static int shotsHit=0;
	protected static int shipLocationRemaing=0;
	protected static int[] xlocation = new int[4];

	protected static char[] alpha = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	protected static int[] numeric = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	protected static int[][] PlayGrid = new int[10][10];
	
	
	 /**
     * Grid
     * default Constructor for objects
     * 
     */
	public Grid() 
	{

	}

	 /**
     * resetGrid
     * resets grid to zero
     * @param  none
     * @return none
     */
	public static void resetGrid() {
		System.out.println("Reset Grid");
		for (int irow = 0; irow < 10; irow++) {
			for (int icolumn = 0; icolumn < 10; icolumn++) {
				Grid[irow][icolumn] = 0;
				//System.out.println("Grid row = " + irow + " Grid Column = "+ icolumn + " = " + Grid[irow][icolumn]);
			} // end icolumn
		} // end irow
	}// end set grid

	 /**
     * resetPlayGrid
     * resets grid to zero
     * @param  none
     * @return PlayGrid
     */
	public static int [][] resetPlayGrid() {
		
		//System.out.println("RESET PLAY GRID ----->");
		for (int irow = 0; irow < 10; irow++) {
			for (int icolumn = 0; icolumn < 10; icolumn++) {
				PlayGrid[irow][icolumn] = 0;
				//System.out.print(PlayGrid[irow][icolumn]+",");
			} // end icolumn
		//	System.out.println("");
		} // end irow
		return PlayGrid;
	}// end set grid
	
	 /**
     * initiliseGrid
     * sets grid to 0
     * @param  none
     * @return PlayGrid
     */
	public void initiliseGrid() {
		System.out.println("<---Initilise Grid--->");

		for (int i = 0; i < 10; i++) {
			System.out.print("  " + alpha[i]);
		}

		System.out.println("");
		for (int irow = 0; irow < 10; irow++) {
			System.out.print(numeric[irow]);

			for (int icolumn = 0; icolumn < 10; icolumn++) {
				Grid[irow][icolumn] = 0;
				System.out.print("[" + Grid[irow][icolumn] + "]");
			} // end icolumn
			System.out.println("");
		} // end irow
	}// end set grid

	 /**
     * displayGrid
     * displays grid
     * @param  none
     * @return none
     */
	public void displayGrid() {
		for (int i = 0; i < 10; i++) 
		{
			System.out.print("  " + alpha[i]);
		}

		System.out.println("");
		for (int irow = 0; irow < 10; irow++) {
			System.out.print(numeric[irow]);

			for (int icolumn = 0; icolumn < 10; icolumn++) {
				System.out.print("[" + Grid[irow][icolumn] + "]");
			} // end icolumn
			System.out.println("");
		} // end irow
	}// end set grid
	
	 /**
     * displayUserGrid
     * displays passed in grid
     * @param  int [][] Grid
     * @return none
     */
	public static void displayUserGrid(int [][] Grid) {
		for (int i = 0; i < 10; i++) 
		{
			System.out.print("  " + alpha[i]);
		}

		System.out.println("");
		for (int irow = 0; irow < 10; irow++) {
			System.out.print(numeric[irow]);

			for (int icolumn = 0; icolumn < 10; icolumn++) {
				System.out.print("[" + Grid[irow][icolumn] + "]");
			} // end icolumn
			System.out.println("");
		} // end irow
	}// end set grid

	
	 /**
     * checkIfOccupied
     * check to see if position passed in contains a ship
     * @param  Fleet userList, int shipLocationAtI
     * @return none
     */
	// row and colum are mixed
	public boolean checkIfOccupied(Fleet userList, int shipLocationAtI ) {
				
		System.out.println("<------ Check If Occupied FI----->");
		
		boolean validOccupancy = false;
		
		if (Grid[randomY][randomX] == 0) 
		{// 0 = blank
			
			System.out.println("Free");
			
			// inverts axis to match output
			Grid[randomY][randomX] = 3;
			
			System.out.println("Ship placed at X=" + randomX + "," + randomY);
			
			//int [] shipEndingCordinates = new int [2];
			//shipEndingCordinates [0] = randomX;
			//shipEndingCordinates [1] =randomY;
			//userList.setShipEndingCordinates(shipEndingCordinates);
			
			validOccupancy=true;
		} else if (Grid[randomY][randomX] == 1) 
		{// 1 = miss
			System.out.println("Occupied by missed");
			validOccupancy = false;
		} 
		else if (Grid[randomY][randomX] == 2) {
		// 2 = fire
			System.out.println("Occupied by fired target");
			validOccupancy = false;
		} 
		else if (Grid[randomY][randomX] == 3) 
		{ // 3 = ship
			System.out.println("Occupied by ship");
		}

		System.out.println("");
		return validOccupancy;
	}
	
	
	 /**
     * returnIfOccupied
     * checks if occupied and if so, returns a value of betweeen 0 and 4
     * @param  none
     * @return ifOccupied
     */
	public int returnIfOccupied() {
		
	System.out.println("\n"+"<---- Return if Occupied --->");
		int ifOccupied =4;
		
		if (Grid[randomY][randomX] == 0) 
		{// 0 = blank
			System.out.println("Free");
			// inverts axis to match output
			Grid[randomY][randomX] = 3;
			System.out.println("Ship placed at X=" + randomX + "," + randomY);
			ifOccupied =0;
		} else if (Grid[randomY][randomX] == 1) 
		{// 1 = miss
			System.out.println("Occupied by missed");
			ifOccupied =1;
		} 
		else if (Grid[randomY][randomX] == 2) 
		{
		// 2 = fire
			System.out.println("Occupied by fired target");
			ifOccupied =2;
		} 
		else if (Grid[randomY][randomX] == 3) 
		{ // 3 = ship
			System.out.println("Occupied by ship");
			ifOccupied =3;
		}
		
		System.out.println("<--- End Return if Occupied --->");
		System.out.println("");
		return ifOccupied;

	}
	
	 /**
     * returnIfOccupied
     * checks if occupied from parameters passed in and if so, returns a value of betweeen 0 and 4
     * @param  int randomY,int randomX
     * @return ifOccupied
     */
	public static int returnIfOccupied(int randomY,int randomX) {
		
		System.out.println("\n"+"<---- Return if Occupied --->");
			int ifOccupied =4;
			
			if (Grid[randomY][randomX] == 0) 
			{// 0 = blank
				System.out.println("Free");
				// inverts axis to match output
				//Grid[randomY][randomX] = 3;
				System.out.println("Ship placed at X=" + randomX + "," + randomY);
				ifOccupied =0;
			} else if (Grid[randomY][randomX] == 1) 
			{// 1 = miss
				System.out.println("Occupied by missed");
				ifOccupied =1;
			} 
			else if (Grid[randomY][randomX] == 2) 
			{
			// 2 = fire
				System.out.println("Occupied by fired target");
				ifOccupied =2;
			} 
			else if (Grid[randomY][randomX] == 3) 
			{ // 3 = ship
				System.out.println("Occupied by ship");
				ifOccupied =3;
			}
			
			System.out.println("<--- End Return if Occupied --->");
			System.out.println("");
			return ifOccupied;

		}
	
	/**
	    * getShipInfo
	    * displays ship info - name, axis, size, orientation
	    * @param Fleet ShipObject
	    * @return none
	    */
	public void getShipInfo(Fleet ShipObject) 
	{
		System.out.println("\n<---Ship Axis--->");
		// Output the axis of the object being passed to the method
		System.out.println(" Name = " + ShipObject.getShipName());
		System.out.println(" Axis = " + ShipObject.getShipAxis());
		System.out.println(" Size = " + ShipObject.getShipSize());
		System.out.println(" Orient = " + ShipObject.getOrientation());
		shipName = ShipObject.getShipName();
		shipSize = ShipObject.getShipSize();
		//shipPosition = ShipObject.getShipAxis();
		
		System.out.println("<--- End Ship Axis--->");
		System.out.println("");
	}

	/**
	    * randomFire
	    * generates random Cordiantes to fire upon and then calls check hit
	    * not implemented
	    * @param Fleet ShipObject
	    * @return none
	    */
	public void randomFire() {
		
		System.out.println("<---randomFire--->");

		// Generate random x and y cordinates to fire upon
		Random randomGenerator = new Random();
		randomX = randomGenerator.nextInt(9);
		System.out.println(randomX);
		randomY = randomGenerator.nextInt(9);
		System.out.println(randomY);

		System.out.println("Fire at target " + randomX + "," + randomY);
		checkHit();
	}

	/**
	    * checkHit
	    * checks grid cordiantes to see if it contains a ship or missed target
	    * not implemented
	    * @param none
	    * @return none
	    */
	public void checkHit() {
		
		System.out.println("<------- Check Hit -------->");
		
		if (Grid[randomX][randomY] == 3) 
		{ // 3 = ship
			System.out.println("Occupied by ship");
			Grid[randomY][randomX] = 1;
			randomX = 0;
			randomY = 0;
		} else if (Grid[randomX][randomY] == 1) 
		{
			System.out.println("Previously fired upon");
			randomFire();
		} else if (Grid[randomX][randomY] == 0)
		{
			System.out.println("Target missed");
			Grid[randomY][randomX] = 2;
			randomX = 0;
			randomY = 0;
		}
		System.out.println("");
	}

	
	/**
	    * PlaceShip
	    * checks grid cordiantes to see if it contains a ship or missed target
	    * Places a ship after the starting position has been generated and placed.
	    * Picks an axis and then calcualtes remaining ship locations based upon that axis if valid.
	    * continues to perform methods until ship has been placed or if it cannot be placed and all code has been executed, 
	    * will return restartship to try again with new cordinates.
	    * @param Fleet shipObject
	    * @return boolean restartship
	    */
	public boolean PlaceShip(Fleet shipObject) 
	{
		System.out.println("<---Place Ship--->");

		int [] shipPlacedAt = new int [2];
		shipPlacedAt [0]=0;
		shipPlacedAt [1]=0;
		boolean reStartShipCords=false;
		
		// checks to see if the size of the ship is greater than 1
		// if greater than one then deduct 1 from shipsize
		// do block of code whilst I < shipsize
		// try block of code
		// if horizontal then do x code, if vertical then do y code
		// catch exception and add 1 to shipsize to retry

		int initialshipSize = shipObject.getShipSize();
		
		if (initialshipSize > 1 )
		{
			shipSize = shipSize - 1;
			
			String orientation = null;
			String shipPosition = shipObject.getShipAxis();
			System.out.println("Ship positon" + shipPosition);
		
			xlocation[1]=randomX;
			xlocation[0]=randomY;
			//System.out.println("Ship Posotion ")
			
			if (shipPosition == "Horizontal") 
			{
				if (randomX >= 0 && randomX <= 5) 
				{
					orientation = "Right";
					shipObject.setOrientation("Right");
				} 
				if (randomX >= 6 && randomX <= 9) 
				{
					orientation = "Left";
					shipObject.setOrientation("Left");
				}
			} 
			if (shipPosition == "Vertical") 
			{
				if (randomY >= 0 && randomY <= 5) 
				{
					orientation = "Down";
					shipObject.setOrientation("Down");
				} 
				else if (randomY >= 6 && randomY <= 9) {
					orientation = "Up";
					shipObject.setOrientation("Up");
				}
			}

			for (int i = 0; i < shipSize; i++) 
			{
				try {
					if (shipPosition == "Horizontal")
					{
						if (randomX >= 0 && randomX <= 5 && orientation == "Right")
						{
							System.out.println("<---0 and 5");
							randomX = randomX + 1;
						} 
						else if (randomX >= 6 && randomX <= 9 && orientation == "Right") 
						{
							xlocation[i] = randomX;
							System.out.println("<--------xlocation"	+ xlocation[i]);
							randomX = randomX + 1;
							//Grid[randomY][randomX]=3;
						} 
						else if (randomX >= 0 && randomX <= 5 && orientation == "Left")
						{
							xlocation[i] = randomX;
							System.out.println("<--------xlocation"	+ xlocation[i]);
							randomX = randomX - 1;
							//Grid[randomY][randomX]=3;
						} 
						else if (randomX >= 6 && randomX <= 9 && orientation == "Left") 
						{
							xlocation[i] = randomX;
							System.out.println("<--------xlocation"	+ xlocation[i]);
							randomX = randomX - 1;
							//Grid[randomY][randomX]=3;
						}
					} // end if horizontal
					
					System.out.println("<--- Status update --->");
					System.out.println("Name= "+shipObject.getShipName());
					System.out.println("Orientation = "+shipObject.getOrientation());
					
					int occupied = returnIfOccupied();
					if(occupied!=0)
					{
						reStartShipCords=true;
						int [] PreviousLocation = getXlocation();
						
						Grid[PreviousLocation[0]][PreviousLocation[1]]=0;
						Grid[PreviousLocation[2]][PreviousLocation[3]]=0;
					}
					
				}// end try
				catch (ArrayIndexOutOfBoundsException e)
				{
					shipSize = shipSize + 1;
				}

				// / VERTICAL
				try {
					if (shipPosition == "Vertical") {
						System.out.println("<--------Vertical ACCESSED ------>");
						
						if (randomY >= 0 && randomY <= 5 && orientation == "Down")
						{
							System.out.println("<---0 and 5");
							randomY = randomY + 1;
							
							//Grid[randomY][randomX]=3;
						}
						else if (randomY >= 6 && randomY <= 9 && orientation == "Down") 
						{
							randomY = randomY + 1;
							
							//Grid[randomY][randomX]=3;
						}
						else if (randomY >= 0 && randomY <= 5 && orientation == "Up") 
						{
							randomY = randomY - 1;
							
							//Grid[randomY][randomX]=3;
						}
						else if (randomY >= 6 && randomY <= 9 && orientation == "Up")
						{
							randomY = randomY - 1;
							
							//Grid[randomY][randomX]=3;
						}
						
						int occupied = returnIfOccupied();
						if(occupied!=0)
						{
							reStartShipCords=true;
							int [] PreviousLocation = getXlocation();
							
							Grid[PreviousLocation[0]][PreviousLocation[1]]=0;
							Grid[PreviousLocation[2]][PreviousLocation[3]]=0;
						}
					} // end if horizontal				
				}// end try
				catch (ArrayIndexOutOfBoundsException e) {
					shipSize = shipSize + 1;
				}

				System.out.println("place x = " + randomX + ", Place y = "	+ randomY);
			} // end for loop
		}// end if greater than 1
		xlocation[3]=randomX;
		xlocation[2]=randomY;
		return reStartShipCords;

	}// end Place Ships Method

	
	 /**
     * saveGame
     *  Allows the grid generated to be saved to a text file and then displayed in notepad.
     * @param  (boolean displayFile
     * @return none
     */
	public static void saveGame(boolean displayFile) {
		System.out.println("Creating new backupfile");
		
		
		try {
			FileWriter fileWriter = new FileWriter("saveGame.txt");// Any current file with the same name will be	overwritten
			BufferedWriter writeOut = new BufferedWriter(fileWriter);

			for (int irow = 0; irow < 10; irow++) {
				for (int icolumn = 0; icolumn < 10; icolumn++) {
					System.out.print("[" + Grid[irow][icolumn] + "]");
					
					if(Grid[irow][icolumn]==3)
					{
						GridCount++;
					}
					
					writeOut.write(""+Grid[irow][icolumn]+ ",");
				} // end icolumn
				System.out.println("");
				writeOut.newLine();
			} // end irow

			// The following method must be used because BufferedWriter's don't
			// have a method similar to System.out.println;
			writeOut.newLine();

			// We now need to flush and close the stream or the text file will
			// not be accessable
			writeOut.flush();
			writeOut.close();

		} catch (Exception e) {
			System.out.println("File cannot be written to(is it read only?)");
		}

		if(displayFile==true)
		{
			// try catch to execute notepad and launch the lotteryNumbers text file
			try {
				Runtime.getRuntime().exec("notepad saveGame.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // Displays save game in displayFile if true
		
		setGridCount(GridCount);
		System.out.println("Grid counter = "+getGridCount());	
	}
	

	 /**
     * loadGame
     *  Allows the grid to be loaded from a text file.
     * @param  none
     * @return none
     */
	public static void loadGame() {
		
		System.out.println("<--- accessed load grid");
		//System.out.println("Opening file 2");
		
		int counter=0;
		int fileInputColumn=0;
		int fileInputRow=0;
		
		FileReader fileReader =null;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(fileReader);
		}
		catch(NullPointerException e)
		{
			
		}
		
		try {
			fileReader = new FileReader("saveGame.txt");
			reader = new BufferedReader(fileReader);
			
			
			String []text=null;
			String lineIn = reader.readLine();
			 // This String will hold the data brought in fron the text file.
			
			while (lineIn != null)
			{
				
				//System.out.println(lineIn);
				
        			// Trys to split
            		try
	            	{
            			// splits by using , delimiter
	            		text = lineIn.split(",");
	            		counter++;
	            	}
	            	catch(Exception e)
	            	{
	            		//System.out.println("error - 2");
	            	}
	       
	            	///*
	            	
	            	for(int j=0; j<9; j++)
	            	{
	            		
	            		fileInputColumn=j;
	            		
	            		Grid[fileInputRow][fileInputColumn] = Integer.parseInt( text[j]);
	            		
	            		if(Grid[fileInputRow][fileInputColumn]==1)
	            		{
	            			shotsMissed++;
	            		}
	            		else if(Grid[fileInputRow][fileInputColumn]==2)
	            		{
	               			shotsHit++;
	            		}
	            		else if(Grid[fileInputRow][fileInputColumn]==3)
	            		{
	            			shipLocationRemaing++;
	            		}
	            	}
		            	
	            	fileInputRow++;
	            	
	            	// parse string to int and stores in userNumbers array
		            	
	            	//userNumbers [j] = Integer.parseInt(text [j]);			 	
	            	lineIn = reader.readLine();
	       
			} // end try

		} catch (Exception e) {
			//System.out.println("Cannot read from file");
		}
		
		try
		{
		//	System.out.println("Closing File");
			reader.close();// This step is not necessary but is advised
		}
		catch(Exception e)
		{
			
		}
	
		// DISPLAY FILE!!!
		/*
		// try catch to execute notepad and launch the lotteryNumbers text file
		try {
			Runtime.getRuntime().exec("notepad saveGame.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//*/
	}
	
	 /**
     * loadGame
     * Allows the grid to be loaded from a text file into the system.
     * @param  none
     * @return none
     */
	public void loadGameIntoGrid() 
	{
		System.out.println("Opening file");
	
		FileReader fileReader; 
	    BufferedReader reader; 
	    try {
			fileReader = new FileReader("saveGame.txt");
			reader = new BufferedReader(fileReader); 
	
		String lineIn = reader.readLine();; 
		
	    String [] text = null;
	    
	    // Continues whilst the next line is not blank
	    while(lineIn != null)
	    {
	    	//Reads next line and increment by 1
	    	 lineIn = reader.readLine();
	         
	        	// Splits line 2 into 6 parts to store each number.
	        for(int j=0; j<9;j++)
	            	{
	        			// Trys to split
	            		try
		            	{
	            			// splits by using , delimiter
		            		text = lineIn.split(",");
		            	}
		            	catch(Exception e)
		            	{
		            		System.out.println("error - load game into grid");
		            	}
		            	
		            	// parse string to int and stores in userNumbers array
			            Grid[0] [j] = Integer.parseInt(text [j]);			 	
	            	}// end
	    }  
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} // end load into grid

	/**
	 * returnGameFile
	 * @param none
	 * @return none
	 */
	public static int [] [] returnGameFile() {
		return Grid;
	}

	/**
	 * setyCoordinates
	 * @param yCoordinates
	 * @return none
	 */
	public static void setyCoordinates(int yCoordinats) {
		yCoordinates = yCoordinats;
	}

	/**
	 * getyCoordinates
	 * @param none
	 * @return yCoordinates
	 */
	public static int getyCoordinates() {
		return yCoordinates;
	}
	
	/**
	 * getOrientation
	 * @param none
	 * @return orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * setOrientation
	 * @param String orientat
	 * @return none
	 */
	public static void setOrientation(String orientat) {
		orientation = orientat;
	}

	/**
	 * getShotsMissed
	 * @param none
	 * @return shotsMissed
	 */
	public static int getShotsMissed() {
		return shotsMissed;
	}

	/**
	 * setShotsMissed
	 * @param int shotMissed
	 * @return none
	 */
	public static void setShotsMissed(int shotMissed) {
		shotsMissed = shotMissed;
	}

	/**
	 * getShotsHit
	 * @param none
	 * @return shotsHit
	 */
	public static int getShotsHit() {
		return shotsHit;
	}

	/**
	 * setShotsHit
	 * @param int shotHit
	 * @return none
	 */
	public void setShotsHit(int shotHit) {
		shotsHit = shotHit;
	}

	/**
	 * getShipLocationRemaing
	 * @param none
	 * @return shipLocationRemaing
	 */
	public static int getShipLocationRemaing() {
		return shipLocationRemaing;
	}

	/**
	 * setShipLocationRemaing
	 * @param int shipLocationRemain
	 * @return none
	 */
	public static void setShipLocationRemaing(int shipLocationRemain) {
		shipLocationRemaing = shipLocationRemain;
	}

	/**
	 * getGrid
	 * @param none
	 * @return Grid
	 */
	public static int[][] getGrid() {
		
		return Grid;
	}

	/**
	 * setGrid
	 * resets grid to zero
	 * @param int[][] gridne
	 * @return none
	 */
	public static void setGrid(int[][] grid) {
		
		//System.out.println("SET GRID---->");
		for(int i=0; i<10;i++)
		{
			for(int j=0; j<10;j++)
			{
				Grid [i] [j] = grid [i] [j];
				//System.out.print(""+Grid[i][j]);
			}
		//	System.out.println("");
		}
	}

	/**
	 * getPlayGrid
	 * @param none
	 * @return PlayGrid
	 */
	public static int[][] getPlayGrid() {
		//System.out.println("GET PLAY GRID---->");
		return PlayGrid;
	}

	/**
	 * setPlayGrid
	 * sets playgrid to grid passed in
	 * @param int[][] playGrid
	 * @return none
	 */
	public static void setPlayGrid(int[][] playGrid) 
	{
		//System.out.println("SET GRID---->");
		for(int i=0; i<10;i++)
		{
			for(int j=0; j<10;j++)
			{
				PlayGrid [i] [j] = playGrid [i] [j];
			//	System.out.print(""+Grid[i][j]);
			}
		}
	}
	
	/**
	 * getxCoordinates
	 * @param none
	 * @return xCoordinates
	 */
	public int getxCoordinates() {
		return xCoordinates;
	}

	/**
	 * setCoordinates
	 * sets cordinates, displays and then sets grid as cordinatevalue entered
	 * @param int xCordinates, int yCordinates,int cordinateValue
	 * @return none
	 */
	public void setCoordinates(int xCordinates, int yCordinates,int cordinateValue) 
	{
		xCoordinates = xCordinates;
		yCoordinates = yCordinates;

		System.out.println("SET CORDS - X=" + xCordinates + ",Y=" + yCordinates);
		// Setting of cordinates are inverted
		Grid[yCordinates][xCordinates] = cordinateValue;
	}

	/**
	 * getXlocation
	 * @param none
	 * @return xlocation
	 */
	public int[] getXlocation() {
		return xlocation;
	}

	/**
	 * setXlocation
	 * @param int[] xlocation
	 * @return none
	 */
	public void setXlocation(int[] x_location) {
		xlocation = x_location;
	}
	
	/**
	 * setGridCount
	 * @param int GridCounter
	 * @return none
	 */
	public static void setGridCount(int GridCounter)
	{
		GridCount = GridCounter;
	}
	
	/**
	 * getGridCount
	 * @param none
	 * @return GridCount
	 */
	public static int getGridCount()
	{
		return GridCount;
	}
}// end class

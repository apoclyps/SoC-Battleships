import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Grid {

	static int xCoordinates;
	static int yCoordinates;
	static int randomY;
	static int randomX;
	// static boolean occupied;
	static String shipName;
	static int shipSize;
	static String shipPosition;
	int gridCoordinates;
	boolean hit;
	boolean miss;
	boolean randomPlacement;
	boolean manualPlacement;
	static int GridCount=0;
	private static String orientation=null;
	static int[][] Grid = new int[10][10];
	static int shotsMissed=0;
	static int shotsHit=0;
	static int shipLocationRemaing=0;
	//static char[] alpha = {'A','B','C','D','E','F','G','H','I','J'};
	static char[] alpha = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	static int[] numeric = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static int[][] PlayGrid = new int[10][10];
	
	public String getOrientation() {
		return orientation;
	}

	public static void setOrientation(String orientat) {
		orientation = orientat;
	}

	public static int getShotsMissed() {
		return shotsMissed;
	}

	public static void setShotsMissed(int shotMissed) {
		shotsMissed = shotMissed;
	}

	public static int getShotsHit() {
		return shotsHit;
	}

	public void setShotsHit(int shotHit) {
		shotsHit = shotHit;
	}

	public static int getShipLocationRemaing() {
		return shipLocationRemaing;
	}

	public static void setShipLocationRemaing(int shipLocationRemain) {
		shipLocationRemaing = shipLocationRemain;
	}

	public static int[][] getGrid() {
		
		return Grid;
	}

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

	public static int[][] getPlayGrid() {
		//System.out.println("GET PLAY GRID---->");
		return PlayGrid;
	}

	public static void setPlayGrid(int[][] playGrid) {
			
		//System.out.println("SET GRID---->");
		for(int i=0; i<10;i++)
		{
			for(int j=0; j<10;j++)
			{
				PlayGrid [i] [j] = playGrid [i] [j];
			//	System.out.print(""+Grid[i][j]);
			}
			//System.out.println("");
		}
	}

	public Grid() 
	{

	}

	public static void resetGrid() {
		System.out.println("Reset Grid");
		for (int irow = 0; irow < 10; irow++) {
			for (int icolumn = 0; icolumn < 10; icolumn++) {
				Grid[irow][icolumn] = 0;
				//System.out.println("Grid row = " + irow + " Grid Column = "+ icolumn + " = " + Grid[irow][icolumn]);
			} // end icolumn
		} // end irow
	}// end set grid

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
	
	public static void initiliseGrid() {
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

	public static void displayGrid() {
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

	public int getxCoordinates() {
		return xCoordinates;
	}

	public void setCoordinates(int xCordinates, int yCordinates,int cordinateValue) 
	{
		this.xCoordinates = xCordinates;
		this.yCoordinates = yCordinates;

		System.out.println("SET CORDS - X=" + xCordinates + ",Y=" + yCordinates);
		// Setting of cordinates are inverted
		Grid[yCordinates][xCordinates] = cordinateValue;
	}

	// row and colum are mixed
	public static boolean checkIfOccupied(Fleet userList, int shipLocationAtI ) {
				
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
	
	public static boolean checkIfOccupied() {
		
		System.out.println("<--- Check If Occupied ----->");
		
		boolean validOccupancy = false;
		
		if (Grid[randomY][randomX] == 0) 
		{// 0 = blank
			System.out.println("Free");
			
			// inverts axis to match output
			Grid[randomY][randomX] = 3;
			System.out.println("Ship placed at X=" + randomX + "," + randomY);
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

		System.out.println("<--- End Check If Occupied ----->");
		System.out.println("");
		return validOccupancy;
		
	}

	public static int returnIfOccupied() {
		
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

	public static void getShipInfo(Fleet ShipObject) 
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

	public static void randomFire() {
		
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

	public static void checkHit() {
		
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

	static int[] xlocation = new int[4];
	
	public static boolean PlaceShip(Fleet shipObject) 
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
						int [] PreviousLocation = UserGrid.getXlocation();
						
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
							int [] PreviousLocation = UserGrid.getXlocation();
							
							Grid[PreviousLocation[0]][PreviousLocation[1]]=0;
							Grid[PreviousLocation[2]][PreviousLocation[3]]=0;
						}
						//Grid[randomY][randomX]=3;
					} // end if horizontal
					
				//	shipObject.setOrientation(orientation);
					
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

	public static int[] getXlocation() {
		return xlocation;
	}

	public static void setXlocation(int[] x_location) {
		xlocation = x_location;
	}

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
	
	public static void setGridCount(int GridCounter)
	{
		GridCount = GridCounter;
	}
	
	public static int getGridCount()
	{
		return GridCount;
	}
	
	public static void loadGame() {
		
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
		
		///*
		// try catch to execute notepad and launch the lotteryNumbers text file
		try {
			Runtime.getRuntime().exec("notepad saveGame.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//*/
	}
	
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

	public static int [] [] returnGameFile() {
		
		return Grid;
	}

}// end class

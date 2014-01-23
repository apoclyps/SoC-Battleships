/**
 * UserGrid
 * UserGrid extends Grid and imports Jframe and Joption pane
 * creates a new instance of Grid when the constructor is called.
 * UserGrid will initilise the game for the user.
 * Methods allow for the game to be reset and changed before and after each play
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

// Imports Jframe and JoptionPane
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserGrid extends Grid 
{
	// Global variables
	private static Fleet [] UserList = new Fleet [9];
	
	private static Fleet UserBattleship1 = new Fleet(4, false,false,"USS.Battleship Alpha");
	private static Fleet UserCruiser1 = new Fleet(3,false,false,"USS.Cruiser Alpha");
	private static Fleet UserCruiser2 = new Fleet(3,false,false,"USS.Cruiser Beta");
	private static Fleet UserDestroyer1 = new Fleet(2,false,false,"USS.Destroyer Alpha");
	private static Fleet UserDestroyer2 = new Fleet(2,false,false,"USS.Destroyer Beta");
	private static Fleet UserDestroyer3 = new Fleet(2,false,false,"USS.Destroyer Charlie");
	private static Fleet UserSubmarine1 = new Fleet(1,false,false,"USS.Submarine Alpha");
	private static Fleet UserSubmarine2 = new Fleet(1,false,false,"USS.Submarine Beta");
	private static Fleet UserSubmarine3 = new Fleet(1,false,false,"USS.Submarine Charlie");
			
	private static UserGrid UserGrid = new UserGrid();
	private static int [] [] previousGrid = getGrid();
	
	 /**
     * createShipList
     * creates an array list of each type of ship
     * @param  none
     * @return none
     */
	public static void createShipList()
	{
			UserList [0] = UserBattleship1;
			UserList [1] = UserCruiser1;
			UserList [2] = UserCruiser2;
			UserList [3] = UserDestroyer1;
			UserList [4] = UserDestroyer2;
			UserList [5] = UserDestroyer3;
			UserList [6] = UserSubmarine1;
			UserList [7] = UserSubmarine2;
			UserList [8] = UserSubmarine3;	
	}
	
	 /**
     * gameInitilisation
     * Initilisation of the game. This will run whenever a new game is started.
     * It will create a copy of the array list containing all ship objects
     * and then sets the game board.
     * It will validate the positions of each object to ensure there is no collision.
     * game initilisation will continue to run until all ships have been placed correctly.
     * @param  none
     * @return none
     */
	public static void gameInitilisation()
	{
		int [] position = new int[2];
		position[0] = 1;
		position[1] = 2;
		boolean displayFile =false;
		
		// create array list of objects
		createShipList();
	
		// do until all ships have been placed and grid count = 19
		do
		{
			//reset everything to 0
			setGridCount(0);
			UserGrid.initiliseGrid();
			saveGame(false);
			
			boolean allShipsPlaced = false;
			//String axisEquals = null;
			int ifOccupied = 0;
			boolean isValidOccupancy=false;
							
			do
			{
				// position each of the ships
				for(int i =0; i<=8; i++)
				{
					try
					{
						Fleet shipParameter = UserList [i];
					
						// Get starting cords and axis
						getCordsAndAxis(shipParameter, ifOccupied, i,isValidOccupancy);
							
						UserGrid.displayGrid();
					}
					catch(Exception e)
					{
						// If ship placement is invalid, decrement by 1 to try again
						System.out.println("ERROR placing ship 001;");
						i--;
					}
					
					// if all ships have been placed
					if(i==8)
					{
						System.out.println("All ships have been placed"+"\n");
						allShipsPlaced=true;
					}
				}// end for
			}while(allShipsPlaced != true);
		
		// update the save game file
		saveGame(displayFile);
		
		System.out.println("<---- Game Initilised ---- >");
		displayUserGrid(Grid);
		
		// continue until GridCount == 19 as all ships may not have been placed correctly
		}while(GridCount!=19);
		
		endPosition();
			
		generateShipCordinates();
		
		// Display output in the console of the ship positions - for testing only
		System.out.println("<---- Game Initilised ---- >");
		displayUserGrid(Grid);
		
	}
	
	
	
	
	 /**
     * placeBattleShips
     * after the initial starting cordinates have been generated for each ship
     * placebattleships will be called to genereate the end ship placement cordinates.
     * This code will check to see if the x or y cords are in valid and if not, 
     * then it will set those cordinates in the grid to equal 3 which signifies a ship.
     * this loop will continue until all ship objects have been generated.
     * @param  none
     * @return none
     */	
	public static void placeBattleShips()
	{
		System.out.println("Place ships");
		
		for(int i=0;i<9;i++)
		{
			System.out.println("num"+i);
			
			boolean endShipPlacement = false;
			int [] shipCordiantes = new int [8];
			shipCordiantes = UserList[i].getShipCordinates();
			
			// gets the size of the ship object
			int size = UserList[i].getShipSize();
			
			// j = x cordinate and k = 7 cordiante
			int j =0;
			int k =1;
			
			do
			{
				System.out.println("Cycle"+j);
				// removes 1 from size as first location has been placed
				size = size-1;
				int xCord = shipCordiantes[j];
				int yCord = shipCordiantes[k];
				
				// if location is valid then place ship
				if(xCord!=10 && yCord!=10)
				{
					System.out.println("Ship Placed at = "+xCord+" "+yCord);
					
					// place ship at location
					Grid[xCord][yCord]=3;
				}
				else
				{
					endShipPlacement=true;
				}
				if(size==0)
				{
					endShipPlacement=true;
				}
				j=j+2;
				k=k+2;
			}while(endShipPlacement!=true);
			
		} // end for 
		System.out.println("End place ships");
	}
	
	
	 /**
     * endPosition
     * Endposition will map the cordinates of the ships from using start and end cordinates
     * to a set of cordinates stored in fleet.
     * it uses the orientation of the ship along with checking the value of that start cordiantes 
     * to generate where the end of the ship will be placed.
     * 
     * @param  none
     * @return none
     */
	public static void endPosition()
	{
		System.out.println("\n"+"END POSITION");
		for(int i=0;i<9;i++)
		{
			int [] startingCords = UserList[i].getShipStartingCordinates();
			int xCordinate = startingCords[0];
			int yCordinate = startingCords[1];
			System.out.println("cord 1 ="+startingCords[0]+" "+startingCords[1]);
			
			//String Orientation = UserList[i].getOrientation();
			
			int size = UserList[i].getShipSize();
			System.out.println("Size left = "+(UserList[i].getShipSize()));
			
			getOrientation(i,startingCords[0],startingCords[1]);
			
			String shipOrientation = UserList[i].getOrientation();
			
			System.out.println("Orientation = "+shipOrientation);
						
			if(size>1)
			{
				if(shipOrientation=="Left")
				{
					xCordinate = xCordinate-(size+1);
				}
				else if(shipOrientation=="Right")
				{
					xCordinate = xCordinate+(size-1);
				}
				else if(shipOrientation=="Up")
				{
					yCordinate = yCordinate-(size+1);
				}
				else if(shipOrientation=="Down")
				{
					yCordinate = yCordinate+(size-1);
				}
			}
			
			int [] endingCords = new int [2];
			endingCords[0]=xCordinate;
			endingCords[1]=yCordinate;
			
			UserList[i].setShipEndingCordinates(endingCords);
			System.out.println("<->"+ endingCords[0]+ " " +endingCords[1]);	
			System.out.println("");
		}
	}
	
	 /**
     * printGenerateShipCordinates
     * displays the shipCordinates generated in EndPosition
     * @param  none
     * @return none
     */
	public static void printGenerateShipCordinates()
	{
		for(int i=0;i<9;i++)
		{
			System.out.println("Print generate Ship Cords");
			int [] shipCordiantes = UserList[i].getShipCordinates();
			
			for(int i2=0;i2<8;i2++)
			{
				System.out.println("Position "+i2+" = "+shipCordiantes[i2]);
			}
		}
	}
	
	
	 /**
     * generateShipCordinates
     * Endposition will map the cordinates of the ships from their start to end cordinates
     * to a set of cordinates stored in fleet however GenerateShipCordinates will fill the locations inbetween
     * if the ship is greater than 2 spaces.
     * it uses the orientation of the ship along with checking the value of that location generated
     * to ensure ships are targered correctly when a user select on the grid.
     * This will allow the game to later display a pop up with a ship is hit identifying it as what ship
     * and how many targets remain on that ship until it sinks.
     * @param  none
     * @return none
     */
	public static void generateShipCordinates()
	{
		System.out.println("<---- Generate Ship Cordinates --->");
		
		// for each ship in the array
		for(int i=0; i <9;i++)
		{
			// Get cordinates
			int [] StartCords = UserList[i].getShipStartingCordinates();
			int [] EndCords = UserList[i].getShipEndingCordinates();
			
			// get size
			int size = UserList[i].getShipSize();
			String shipOrientation = UserList[i].getOrientation();
			
			// get orientation
			System.out.println(""+UserList[i].getShipName() + " Orient = "+shipOrientation);
			
			// copy starting cordiantes to new array
			int [] shipCordinates = new int [8];
			shipCordinates [0] = StartCords[0];
			shipCordinates [1] = StartCords[1];
				
			System.out.println("Cord 1 = "+shipCordinates[0]+" 2 = "+shipCordinates[1]);
			
			// set default values off the grid for any ships not yet placed
			shipCordinates [2] = 10;
			shipCordinates [3] = 10;
				
			shipCordinates [4] = 10;
			shipCordinates [5] = 10;
				
			shipCordinates [6] = 10;
			shipCordinates [7] = 10;
				
			boolean isValidCords=false;
			
			// do while is validcords = false
			do
			{
				// second set of cordinates will be placed if ship 2 or greater
				if(size>=2)
				{
					if(shipOrientation=="Left")
					{
						shipCordinates [2] = (StartCords[0]-1);
						shipCordinates [3] = StartCords[1];
					}
					else if(shipOrientation=="Right")
					{
						shipCordinates [2] = (StartCords[0]+1);
						shipCordinates [3] = StartCords[1];
					}
					else if(shipOrientation=="Up")
					{
						shipCordinates [2] = StartCords[0];
						shipCordinates [3] = (StartCords[1]+1);
					}
					else if(shipOrientation=="Down")
					{
						shipCordinates [2] = StartCords[0];
						shipCordinates [3] = (StartCords[1]-1);
					}
					else if(size==2)
					{
					shipCordinates [2] = EndCords[0];
					shipCordinates [3] = EndCords[1];
					}
					
					if(shipCordinates[2]!=10 && shipCordinates[3]!=10)
					{
						System.out.println("Cord 2 = "+shipCordinates[2]+" 3 = "+shipCordinates[3]);
					}
					else
					{
						System.out.println("Invalid - greater than 10 1");
					}
				}
					
				// third set of cordinates will be placed if ship 3 or greater
				if(size>=3)
				{
					if(shipOrientation=="Left")
					{
						shipCordinates [4] = (StartCords[0]-2);
						shipCordinates [5] = StartCords[1];
					}
					else if(shipOrientation=="Right")
					{
						shipCordinates [4] = (StartCords[0]+2);
						shipCordinates [5] = StartCords[1];
					}
					else if(shipOrientation=="Up")
					{
						shipCordinates [4] = StartCords[0];
						shipCordinates [5] = (StartCords[1]+2);
					}
					else if(shipOrientation=="Down")
					{
						shipCordinates [4] = StartCords[0];
						shipCordinates [5] = (StartCords[1]-2);
					}
					else if(size==3)
					{
					shipCordinates [4] = EndCords[0];
					shipCordinates [5] = EndCords[1];
					}
					
					if(shipCordinates[4]!=10 && shipCordinates[5]!=10)
					{
					System.out.println("Cord 4 = "+shipCordinates[4]+" 5 = "+shipCordinates[5]);
					}
					else
					{
						System.out.println("Invalid - greater than 10 2");
					}
				}
				
				// fourth set of cordinates will be placed if ship 4
				if(size>=4)
				{
					if(shipOrientation=="Left")
					{
						shipCordinates [6] = (StartCords[0]-3);
						shipCordinates [7] = StartCords[1];
					}
					else if(shipOrientation=="Right")
					{
						shipCordinates [6] = (StartCords[0]+3);
						shipCordinates [7] = StartCords[1];
					}
					else if(shipOrientation=="Up")
					{
						shipCordinates [6] = StartCords[0];
						shipCordinates [7] = (StartCords[1]+3);
					}
					else if(shipOrientation=="Down")
					{
						shipCordinates [6] = StartCords[0];
						shipCordinates [7] = (StartCords[1]-3);
					}
					else if(size==4)
					{
					shipCordinates [6] = EndCords[0];
					shipCordinates [7] = EndCords[1];
					}
					if(shipCordinates[6]!=10 && shipCordinates[7]!=10)
					{
					System.out.println("Cord 6 = "+shipCordinates[6]+" 7 = "+shipCordinates[7]);
					}
					else
					{
						System.out.println("Invalid - greater than 10 3");
					}
				}
				
				// Checks to see if cordiantes are in valid before moving to next ship
				
				boolean changeAxis=false;
				
				if(size>=2 && (shipCordinates[2]<0 || shipCordinates[3]<0))
				{
					System.out.println("Change 1");
					changeAxis=true;
				}
				if(size>=3 && (shipCordinates[4]<0 || shipCordinates[5]<0))
				{
					System.out.println("Change 2");
					changeAxis=true;
				}	
				if(size>=4 && (shipCordinates[6]<0 || shipCordinates[7]<0))
				{
					System.out.println("Change 3");
					changeAxis=true;
				}	
				
				if(changeAxis==false && size>=2)
				{
					int Occupied = returnIfOccupied(shipCordinates[3],shipCordinates[2]);
					if(Occupied!=3)
					{
						changeAxis=true;
					}
				}
				
				if(changeAxis==false && size>=3)
				{
					int Occupied = returnIfOccupied(shipCordinates[5],shipCordinates[4]);
					if(Occupied!=3)
					{
						changeAxis=true;
					}
				}
				
				// change orientation of ship if cordiantes were invalid and axis was changed
				if(changeAxis==true)
				{
					System.out.println("\n"+"Change Axis"+"\n");
					if(shipOrientation=="Left")
					{
						shipOrientation="Right";
						System.out.println(shipOrientation);
					}
					else if(shipOrientation=="Right")
					{
						shipOrientation="Left";
						System.out.println(shipOrientation);
					} 
					else if(shipOrientation=="Up")
					{
						shipOrientation="Down";
						System.out.println(shipOrientation);
					} 
					else if(shipOrientation=="Down")
					{
						shipOrientation="Up";
						System.out.println(shipOrientation);
					} 
					
				}
				else
				{
					changeAxis=false;
					isValidCords=true;
				}
				
				// WHILE STATEMENT END
		}while(isValidCords==false);
				
				UserList[i].setShipCordinates(shipCordinates);
		}// for loop
	}
	
	 /**
     * DisplayShipLocations
     * displays the each ships starting and end cordinates
     * @param  none
     * @return none
     */
	public static void DisplayShipLocations()
	{
		
		// Display starting location of ships
		System.out.println("<---- Display ship Locations ---> ");
		for(int i=0; i<9; i++)
		{
			String name = UserList [i].getShipName();
			int [] startLocation = new int [2];
			startLocation = UserList [i].getShipStartingCordinates();
			System.out.println(name+"\n"+"start location is "+startLocation[0]+ " "+ startLocation[1] );
		}
		
		// display ending location of ships
		System.out.println(" ");
		for(int j=0; j<9;j++)
		{
			String name = UserList [j].getShipName();
			int [] endLocation = new int [2];
			endLocation = UserList [j].getShipEndingCordinates();
			System.out.println(name+"\n"+"location is "+endLocation[0]+ " "+ endLocation[1] );
		}
	}
	
	/**
     * checkShipHit
     * checks if ship has been hit.
     * will search against all cordiantes for each ship and if ship is hit
     * plays sound. If shink is sunken, siren is sounded.
     * pop up is displayed each time a ship is hit and if a ship has been sunken
     * @param  int xCords, int yCords
     * @return none
     */
	public static void checkShipHit(int xCords, int yCords)
	{
		int size;
		String name;
		int [] shipCords = null;
		int num1=0;
		int num2=1;
		
		if(Grid[xCords][yCords]==3)
		{
			System.out.println("Hit");
		}

		System.out.println("<--- checkShiphit --->");
		for(int i =0;i<9;i++)
		{
			size = 0;
			name=null;
			try
			{
				size = getUserList()[i].getShipSize();
				name = UserList[i].getShipName();
				shipCords = UserList[i].getShipCordinates();
			}
			catch(NullPointerException e)
			{
				
			}
						
			for(int i2 =0; i2<8;i2=i2+2)
				{
					if(xCords==shipCords[num1+i2]&& yCords == shipCords[num2+i2])
					{
						if(size>0)
						{
							System.out.println(name+ " hit "+(size-1)+" targets left on ship");
							
							String Output = name+ " hit "+(size-1)+" targets left on ship";
							
							//default title and icon
							JFrame frame = Gameboard.getFrame();
							JOptionPane.showMessageDialog(frame, Output);
							
							size = size-1;
							
							// If ship is sunken play sound and display message
							if(size==0)
							{
								Gameboard.sound("siren.wav");
								String sunkenShip = name+" has been sunk!";
								JOptionPane.showMessageDialog(frame, sunkenShip);
								System.out.println(name+ " sunken");
							}
							
							// reduce size by 1 each time hit
							UserList[i].setShipSize(size);
							i2=8;
						}
						else
						{
							System.out.println(name+ " sunken");
						}
					}
			     } // end set 1
		} // end set 1
		System.out.println("end CSH !!!");
	}
	
	 /**
     * displayShipDetails
     * displays the name, size ,axis and orientation fo each ship in userlist array
     * @param  none
     * @return none
     */
	public static void displayShipDetails()
	{
		System.out.println("");
		
		for(int i =0; i <9;i++)
		{
			System.out.println("Ship Name = "+UserList[i].getShipName());
			System.out.println("Ship size = "+UserList[i].getShipSize());
			System.out.println("Ship Axis = "+UserList[i].getShipAxis());
			System.out.println("Ship Orientation          = "+UserList[i].getOrientation());
			System.out.println("");
		}
	}
	
	 /**
     * getCordsAndAxis
     * calls ifOccupied from super class to get starting cordinates
     * ifOccupied return zero, set a ship at that location.
     * display ship info and then get the axis for that ship, then call placeship
     * to position rest of the ship.
     * @param  Fleet shipParameter,int ifOccupied,int i,boolean isValidOccupancy
     * @return none
     */
	public static void getCordsAndAxis(Fleet shipParameter,int ifOccupied,int i,boolean isValidOccupancy)
	{
		System.out.println("<---- Get Cords and Axis --- >");

				// checks if the location is valid to place ship
				ifOccupied = getStartingCords(shipParameter,ifOccupied,i);
				
				// place ship if valid
				if(ifOccupied==0)
				{
					Grid[randomY][randomX]=3;
				}
				
				//set axis of ship
				int axis = RandomGrid.getRandomShipAxis();
				setAxis(axis,i);

				//display ship info
				UserGrid.getShipInfo(UserList [i]);
				
				// Place remaining locations of ship
				UserGrid.PlaceShip(shipParameter);
				
		System.out.println("<---- End Get Cords and Axis --- >");
	}
	
	/**
     * getStartingCords
     * Surrounded by a do while loop.
     * generates random x and y cordinates for the starting cordinates
     * of the ship being passed into the method.
     * if i being passed in does not =1 then check if occupied
     * and if occupied = 3 then update grid with previous location to reset otherwise
     * store location for the ship and end loop.
     * @param  Fleet shipParameter,int ifOccupied,int i
     * @return int ifOccupied
     */
	public static int getStartingCords(Fleet shipParameter,int ifOccupied,int i)
	{
		
		System.out.println("<--- get Starting Cords --- >");		
		do // for each ship that needs a location
		{
			// resets startingCordinates
			int [] startingCordinates = new int [2];
			startingCordinates [0]=0;
			startingCordinates [1]=0;
			
			//gets random STarting cordinates
			startingCordinates = RandomGrid.randomShipLocation(shipParameter);
			
			// if i!=1 then check if occupied
			if(i!=1)
			{
				ifOccupied = UserGrid.returnIfOccupied();
			}
			// if occupied restore to blank values in grid and try again
			if(ifOccupied==3)
			{
				
				int [] PreviousLocation = UserGrid.getXlocation();
				
				Grid[PreviousLocation[0]][PreviousLocation[1]]=0;
				Grid[PreviousLocation[2]][PreviousLocation[3]]=0;
			}
			else
			{
				// store location
				UserList [i].setShipStartingCordinates(startingCordinates);
			}
		// continue while ship is not placed
		} while(ifOccupied!=0);
			
		System.out.println("<--- End get Starting Cords --- >");
		
		return ifOccupied;
	}
	
	/**
     * getOrientation
     * gets the axis and then checks the axis and then sets the orientation
     * @param  int i, int randomX, int randomY 
     * @return none
     */
	public static void getOrientation(int i, int randomX, int randomY )
	{
		String shipOrientation = null;
		shipOrientation = UserList[i].getShipAxis();
		System.out.println("shipPosition " + shipOrientation);
		//String orient;
		
		if (shipOrientation == "Horizontal") 
		{
			if (randomY >= 0 && randomY <= 5) 
			{
				//orientation = "Right";
				UserList[i].setOrientation("Right");
			} 
			else if (randomY >= 6 && randomY <= 9) 
			{
				//orientation = "Left";
				UserList[i].setOrientation("Left");
			}
		} 
		
		if (shipOrientation == "Vertical") 
		{
			if (randomX >= 0 && randomX <= 5) 
			{
				//orientation = "Down";
				UserList[i].setOrientation("Down");
			} 
			else if (randomX >= 6 && randomX <= 9) {
				//orientation = "Up";
				UserList[i].setOrientation("Up");
			}
		}
	}
	
	/**
     * setAxis
     * sets the shipAxis
     * @param  int axis,int i 
     * @return none
     */
	public static void setAxis(int axis,int i)
	{
		System.out.println("<--- Set Axis --- >");
		System.out.println("Vertical/Horizontal Axis = "+axis);
		String axisEquals = null;
		
		if(axis ==1)
		{
			axisEquals = "Vertical";
		}
		else if(axis ==2)
		{
			axisEquals = "Horizontal";
		}
					
		UserList [i].setShipAxis(axisEquals);
		System.out.println("<--- End Set Axis --- >");
	}
	
	 /**
     * getUserList
     * returns User List
     * @param  none
     * @return UserList
     */
	public static Fleet[] getUserList() {
		return UserList;
	}
	
	 /**
     * setUserList
     * sets User List
     * @param  UserList
     * @return none
     */
	public static void setUserList(Fleet[] userList) {
		UserList = userList;
	}
	
	 /**
     * getPreviousGrid
     * gets previous grid
     * @param  none
     * @return previousGrid
     */
	public static int[][] getPreviousGrid() {
		return previousGrid;
	}

	 /**
     * setPreviousGrid
     * sets previous grid
     * @param  int[][] previous_Grid
     * @return none
     */
	public static void setPreviousGrid(int[][] previous_Grid) {
		previousGrid = previous_Grid;
	}
}


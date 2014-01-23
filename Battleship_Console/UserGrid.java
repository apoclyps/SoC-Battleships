import javax.swing.JOptionPane;

public class UserGrid extends Grid 
{
	private static Fleet [] UserList = new Fleet [9];
	
	static Fleet UserBattleship1 = new Fleet(4, false,false,"USS.Battleship Alpha");
	static Fleet UserCruiser1 = new Fleet(3,false,false,"USS.Cruiser Alpha");
	static Fleet UserCruiser2 = new Fleet(3,false,false,"USS.Cruiser Beta");
	static Fleet UserDestroyer1 = new Fleet(2,false,false,"USS.Destroyer Alpha");
	static Fleet UserDestroyer2 = new Fleet(2,false,false,"USS.Destroyer Beta");
	static Fleet UserDestroyer3 = new Fleet(2,false,false,"USS.Destroyer Charlie");
	static Fleet UserSubmarine1 = new Fleet(1,false,false,"USS.Submarine Alpha");
	static Fleet UserSubmarine2 = new Fleet(1,false,false,"USS.Submarine Beta");
	static Fleet UserSubmarine3 = new Fleet(1,false,false,"USS.Submarine Charlie");
	
	static String[] UserShips = {"UserBattleship1", "UserCruiser1", "UserCruiser2", "UserDestroyer1",
			"UserDestroyer2","UserDestroyer3","UserSubmarine1","UserSubmarine2","UserSubmarine3"}; 
		
	static UserGrid UserGrid = new UserGrid();
	
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
	
	public static void gameInitilisation()
	{
		int [] position = new int[2];
		position[0] = 1;
		position[1] = 2;
		boolean displayFile =false;
		
		createShipList();
	
		do
		{
			UserGrid.setGridCount(0);
			UserGrid.initiliseGrid();
			UserGrid.saveGame(false);
			
			boolean allShipsPlaced = false;
			String axisEquals = null;
			int ifOccupied = 0;
			boolean isValidOccupancy=false;
							
			do
			{
				for(int i =0; i<=8; i++)
				{
					try
					{
						Fleet shipParameter = UserList [i];
						//do // for each ship
						//{
							// Get starting cords and axis
							getCordsAndAxis(shipParameter, ifOccupied, i,isValidOccupancy);
							
							UserGrid.displayGrid();
					//	}while(ifOccupied !=0);	
					}
					catch(Exception e)
					{
						System.out.println("ERROR placing ship 001;");
						i--;
					}
					
					if(i==8)
					{
						System.out.println("All ships have been placed"+"\n");
						allShipsPlaced=true;
					}
				}// end for
			}while(allShipsPlaced != true);
		
		UserGrid.saveGame(displayFile);
		
		System.out.println("<---- Game Initilised ---- >");
		UserGrid.displayUserGrid(Grid);
		
		}while(GridCount!=19);
		
		//UserGrid.resetGrid();
		
		endPosition();
			
		
		generateShipCordinates();
		
		//DisplayShipLocations();
		//	printGenerateShipCordinates();
			
		//UserGrid.resetGrid();
		//UserGrid.resetPlayGrid();
		//		UserGrid.placeBattleShips();
		//UserGrid.saveGame(true);
			
		//UserGrid.displayShipDetails();
	}
	
	static int [] [] previousGrid = UserGrid.getGrid();
	
	public static int[][] getPreviousGrid() {
		return previousGrid;
	}

	public static void setPreviousGrid(int[][] previousGrid) {
		UserGrid.previousGrid = previousGrid;
	}

	public static void getCordsAndAxis(Fleet shipParameter,int ifOccupied,int i,boolean isValidOccupancy)
	{
		System.out.println("<---- Get Cords and Axis --- >");
		boolean remakeShip = false;
				
		//do
		//{
		//	do
		//	{
				// Gets starting cords for ship
				ifOccupied = getStartingCords(shipParameter,ifOccupied,i);
				
				if(ifOccupied==0)
				{
					Grid[randomY][randomX]=3;
				}
				
				int axis = RandomGrid.getRandomShipAxis();
				//String axis
				setAxis(axis,i);
				//System.out.println("Axis 5656 = "+UserList[i].getShipAxis());
				
				UserGrid.getShipInfo(UserList [i]);
				
				remakeShip = UserGrid.PlaceShip(shipParameter);
				
		System.out.println("<---- End Get Cords and Axis --- >");
	}
	
	public static int getStartingCords(Fleet shipParameter,int ifOccupied,int i)
	{
		
		System.out.println("<--- get Starting Cords --- >");		
		do // for each ship that needs a location
		{
			int [] startingCordinates = new int [2];
			startingCordinates [0]=0;
			startingCordinates [1]=0;
			
			startingCordinates = RandomGrid.randomShipLocation(shipParameter);
			
			if(i!=1)
			{
				ifOccupied = UserGrid.returnIfOccupied();
			}
			if(ifOccupied==3)
			{
				
				int [] PreviousLocation = UserGrid.getXlocation();
				
				Grid[PreviousLocation[0]][PreviousLocation[1]]=0;
				Grid[PreviousLocation[2]][PreviousLocation[3]]=0;
			}
			else
			{
			UserList [i].setShipStartingCordinates(startingCordinates);
			}
		
		} while(ifOccupied!=0);
			
		System.out.println("<--- End get Starting Cords --- >");
		
		return ifOccupied;
	}
	
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
		
	//	System.out.println("Axis 123 = "+axisEquals);
					
		UserList [i].setShipAxis(axisEquals);
		System.out.println("<--- End Set Axis --- >");
	}
	
	public static void initiateGame()
	{
		int [] position = new int[2];
		position[0] = 1;
		position[1] = 2;
		boolean displayFile =false;
		
		createShipList();
	
		do
		{
			UserGrid.setGridCount(0);
			UserGrid.initiliseGrid();
			
			boolean allShipsPlaced = false;
			String axisEquals = null;
			int ifOccupied;
			boolean isValidOccupancy=false;
		
			//int [] endingCordinates = new int [2];
							
			do
			{
				for(int i =0; i<=8; i++)
				{
					try
					{
						Fleet shipParameter = UserList [i];
						do // for each ship
						{
							do
							{
								do // for each ship that needs a location
								{
									int [] startingCordinates = new int [2];
									startingCordinates [0]=0;
									startingCordinates [1]=0;
									
									startingCordinates = RandomGrid.randomShipLocation(shipParameter);
									ifOccupied = UserGrid.returnIfOccupied();
									UserList [i].setShipStartingCordinates(startingCordinates);
									
								} while(ifOccupied!=0);
								
								int axis = RandomGrid.getRandomShipAxis();
								System.out.println("Vertical/Horizontal Axis = "+axis);
								
								if(axis ==1)
								{
									axisEquals = "Vertical";
								}
								else if(axis ==2)
								{
									axisEquals = "Horizontal";
								}
								
								System.out.println("AxisEquals = "+axisEquals);
											
								UserList [i].setShipAxis(axisEquals);
								UserGrid.getShipInfo(UserList [i]);
								UserGrid.PlaceShip(shipParameter);
							//	System.out.println("Ending cords = "+ endingCordinates[0]+ " y =  "+ endingCordinates[1]);
								
								isValidOccupancy = UserGrid.checkIfOccupied();
								
							}while(isValidOccupancy!=true);
							
							UserGrid.displayGrid();
							
							//UserList [i].setShipEndingCordinates(endingCordinates);
							
						}while(ifOccupied !=0);	
					}
					catch(Exception e)
					{
						System.out.println("ERROR placing ship 001;");
						i--;
					}
					
					if(i==8)
					{
						System.out.println("All ships have been placed");
						System.out.println("");
						allShipsPlaced=true;
					}
				}// end for
			}while(allShipsPlaced != true);
			
			if(GridCount==19)
			{
				displayFile = true;
			}
			else
			{
				displayFile = false;
			}
			
		UserGrid.saveGame(displayFile);
			
		}	while(GridCount!=19);
		
		System.out.println("<---- Game Initilised ---- >");
		
		//UserGrid.resetGrid();
				//endPosition();
		//		DisplayShipLocations();
				//generateShipCordinates();
		//	printGenerateShipCordinates();
				//UserGrid.resetGrid();
		//UserGrid.resetPlayGrid();
		//		UserGrid.placeBattleShips();
		//UserGrid.saveGame(true);
		
		UserGrid.displayUserGrid(Grid);
	}
	
	public static void placeBattleShips()
	{
		System.out.println("Place ships");
		
		for(int i=0;i<9;i++)
		{
			System.out.println("num"+i);
			
			boolean endShipPlacement = false;
			int [] shipCordiantes = new int [8];
			shipCordiantes = UserList[i].getShipCordinates();
			
			int size = UserList[i].getShipSize();
			int j =0;
			int k =1;
			
			do
			{
				System.out.println("Cycle"+j);
				size = size-1;
				int xCord = shipCordiantes[j];
				int yCord = shipCordiantes[k];
				
				if(xCord!=10 && yCord!=10)
				{
					System.out.println("Ship Placed at = "+xCord+" "+yCord);
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
			
			//shipOrientation = UserList[i].getOrientation();
			
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
	
	
	
	public static void generateShipCordinates()
	{
		System.out.println("<---- Generate Ship Cordinates --->");
		
		
		for(int i=0; i <9;i++)
		{
			int [] StartCords = UserList[i].getShipStartingCordinates();
			int [] EndCords = UserList[i].getShipEndingCordinates();
			
		//	if(StartCords!=EndCords)
		//	{
				int size = UserList[i].getShipSize();
				String shipOrientation = UserList[i].getOrientation();
				
				System.out.println(""+UserList[i].getShipName() + " Orient = "+shipOrientation);
				
				int [] shipCordinates = new int [8];
				shipCordinates [0] = StartCords[0];
				shipCordinates [1] = StartCords[1];
				
				System.out.println("Cord 1 = "+shipCordinates[0]+" 2 = "+shipCordinates[1]);
							
				shipCordinates [2] = 10;
				shipCordinates [3] = 10;
				
				shipCordinates [4] = 10;
				shipCordinates [5] = 10;
				
				shipCordinates [6] = 10;
				shipCordinates [7] = 10;
				
				boolean isValidCords=false;
				
				do
				{
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
							
							// SWAP HERE
						}
					}
					
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
						
						// SWAP HERE
					}
				}
				
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
						
						// SWAP HERE
					}
				}
				
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
	
	public static void DisplayShipLocations()
	{
		
		System.out.println("<---- Display ship Locations ---> ");
		for(int i=0; i<9; i++)
		{
			String name = UserList [i].getShipName();
			int [] startLocation = new int [2];
			startLocation = UserList [i].getShipStartingCordinates();
			System.out.println(name+"\n"+"start location is "+startLocation[0]+ " "+ startLocation[1] );
		}
		
		System.out.println(" ");
		for(int j=0; j<9;j++)
		{
			String name = UserList [j].getShipName();
			int [] endLocation = new int [2];
			endLocation = UserList [j].getShipEndingCordinates();
			System.out.println(name+"\n"+"location is "+endLocation[0]+ " "+ endLocation[1] );
		}
	}
	
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
		
		//System.out.println("x = "+xCords+ "  y = "+yCords);
		
		System.out.println("<--- checkShiphit --->");
		for(int i =0;i<9;i++)
		{
				size = UserList[i].getShipSize();
				//System.out.println("Size = "+size);
				name = UserList[i].getShipName();
				shipCords = UserList[i].getShipCordinates();
						
			for(int i2 = 0; i2<8;i2=i2+2)
				{
					if(xCords==shipCords[num1+i2]&& yCords == shipCords[num2+i2])
					{
						if(size>0)
						{
							System.out.println(name+ " hit "+(size-1)+" targets left on ship");
							UserList[i].setShipSize(size-1);
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
}


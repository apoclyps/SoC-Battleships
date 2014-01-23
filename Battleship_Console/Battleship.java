import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Battleship {
	
	//static Grid UserGrid = new Grid();
	static int numberOfShots=0;
	
	public static void main(String [] arguments)
	{
		boolean returnToMenu =true;
		char ans = 0;
		do
		{
			mainMenuDirector();
			
			String option = "null";
			System.out.println("Would you like to return to the main menu ? ");
			System.out.println("y/n");
			
			try
			{
				option = Genio.getString();
				ans = option.charAt(0);
			}
			catch(Exception e)
			{
				
			}
		
			if(ans=='y')
			{
				returnToMenu=true;
			}
			else if(ans=='n')
			{
				returnToMenu=false;
			}
		
		} while(returnToMenu!=false);
		
		System.out.println("\n"+"Program Exited.");
	}

	public static void mainMenuDirector()
	{
		boolean menuSelection=false;
		
		System.out.println("\t\t"+"<-BATTLESHIPS>");
		System.out.println(" Welcome to the tactical Battleships game"+"\n");
		System.out.println("  1"+"\t"+"Start new game");
		System.out.println("  2"+"\t"+"Load Game");
		System.out.println("  3"+"\t"+"Options");
		System.out.println("  4"+"\t"+"Rules");
		System.out.println("  5"+"\t"+"Exit");
		System.out.println("  6"+"\t"+"Continue Game"+"\n");
	
		// Do while choice is not valid selection
		do
		{
			System.out.print("Select a choice : ");
			int menuChoice = Genio.getInteger();
		
			// Start Game
			if(menuChoice==1)
			{
				System.out.println("Start Game");
				//UserGrid.initiateGame();
				
				UserGrid.gameInitilisation();
				//UserGrid.saveGame();
				//startGame();
				menuSelection=true;
			}
			// Load Game
			else if(menuChoice==2)
			{
				System.out.println("Load Game");
				UserGrid.loadGame();
				menuSelection=true;
			}
			// Options
			else if(menuChoice==3)
			{
				System.out.println("Options");
				menuSelection=true;
			}
			// Rules
			else if(menuChoice==4)
			{
				System.out.println("Rules");
				rules();
				menuSelection=true;
			}
			// Exit
			else if(menuChoice==5)
			{
				System.out.println("Exit");
				menuSelection=true;
			}
			else if(menuChoice==6)
			{
				System.out.println(" Selected Continue Game");
				startGame();
			}
			sleepTimer();
		}
		while(menuSelection!=true);
	}
	
	public static void rules()
	{
		// Output
        //System.out.println("Opening file");
        
        // Try to read from file
        try
        { 
            FileReader fileReader; 
            BufferedReader reader; 
            fileReader = new FileReader("rules.txt"); 
            reader = new BufferedReader(fileReader); 
           
            //This String will hold the data brought in from the text file.
            String lineIn = reader.readLine();; 
            // continues to read until lineIn = null
            do
            {
	            System.out.println(lineIn);
	            lineIn = reader.readLine();
            }while(lineIn != null);
             
        //    System.out.println("Closing File\n");
            System.out.println("");
            reader.close();
        }
        catch(Exception e)
        { 	
        	System.out.println("Cannot read from file");
        	System.out.println("");
        }
        
        try {
			Runtime.getRuntime().exec("notepad rules.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sleepTimer()
	{
		// Trys to sleep and catches exception
		try 
		{
			Thread.sleep(5000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void startGame()
	{
		//UserGrid.displayGrid();
		
		boolean inputRowValid=false;
		boolean inputColumnValid=false;
		int inputRow =0;
		int inputColumn =0;
		boolean shotValid =false;
		
		numberOfShots= getGameDifficulty();
		
		UserGrid.loadGame();
		
		int [][] Grid = UserGrid.returnGameFile();
		int [][] PlayGrid =null;
		
		UserGrid.setGrid(Grid);
		PlayGrid=UserGrid.resetPlayGrid();

		gameStatistics();
		
		System.out.println("\n"+"<-------- Start Game ------->"+"\n");
		UserGrid.displayUserGrid(PlayGrid);
	
			do
			{
				inputRow=getRowFire(inputRow,inputRowValid);
				inputColumn = getColumnFire(inputColumn,inputColumnValid);
						
				System.out.println("Targeted Row = "+inputRow+" Column = "+inputColumn);
				
				Grid = UserGrid.getGrid();
				PlayGrid = UserGrid.getPlayGrid();
				//UserGrid.displayGrid();
				
				// 0 empty
				
				// Miss
				if(Grid[inputColumn][inputRow]==0)
				{
					System.out.println("Target Missed");
					Grid[inputColumn][inputRow]=1;
					PlayGrid[inputColumn][inputRow]=1;
					UserGrid.setGrid(Grid);
					UserGrid.setPlayGrid(PlayGrid);
					numberOfShots--;
					
				}
				else if(Grid[inputColumn][inputRow]==1)
				{
					System.out.println("Previously Miss at Target");
					//System.out.println("Already fired at location");
					//Grid[inputRow][inputColumn]=1;
					//UserGrid.setGrid(Grid);
				}
				//fire
				else if (Grid[inputColumn][inputRow]==2)
				{
					System.out.println("Previously Hit Target");
				}
				//ship
				else if (Grid[inputColumn][inputRow]==3)
				{
					System.out.println("Target Ship Hit");
					Grid[inputColumn][inputRow]=2;
					PlayGrid[inputColumn][inputRow]=2;
					numberOfShots--;
					int targetsRemaining = UserGrid.getShipLocationRemaing();
					targetsRemaining = targetsRemaining -1;
					UserGrid.setShipLocationRemaing(targetsRemaining);
					
					UserGrid.checkShipHit(inputRow,inputColumn);
				}
				
				System.out.println("Shots left = "+numberOfShots);
				System.out.println("Ships remaining to be hit "+UserGrid.getShipLocationRemaing());
				System.out.print("\n");
				
				UserGrid.setGrid(Grid);
				UserGrid.setPlayGrid(PlayGrid);
				UserGrid.displayUserGrid(PlayGrid);
				
				if(numberOfShots==0)
				{
					gameOverLoser();
					shotValid=true;
				}
				
				int targetsRemaining = UserGrid.getShipLocationRemaing();
				
				if( targetsRemaining == 0)
				{
					gameOverWinner();
					shotValid=true;
				}			
			}while (shotValid==false);
	}
	
	public static void gameStatistics()
	{
		int shotsMissed=0;
		int shotsHit=0;
		int shipLocationRemaing=0;
		
		System.out.println("\n"+"Would you like to view pre game statistics ?");
		System.out.println(" 1      Yes");
		System.out.println(" 2      No");
		System.out.print("Choice = ");
		int preGameStats = Genio.getInteger();
		
		System.out.print("\n");
		
		if(preGameStats==1)
		{
			System.out.println("<------ Game Statistics ------>");
			shotsMissed = UserGrid.getShotsMissed();
			
			System.out.println(" Shots Missed      = "+shotsMissed);
			shotsHit = UserGrid.getShotsHit();
			
			System.out.println(" Shots Hit         = "+shotsHit);
			shipLocationRemaing = UserGrid.getShipLocationRemaing();
			
			System.out.println(" Targets remaining = "+shipLocationRemaing);
		}
	}
	
	public static void gameOverLoser()
	{
		System.out.println("\n"+"<------ Game Over ----->");
		System.out.println(" You Lose!");
	}
	
	public static void gameOverWinner()
	{
		System.out.println("\n"+"<------ Game Over ----->");
		System.out.println(" You Win!");
	}
	
	public static int getGameDifficulty()
	{
		System.out.print("\n");
		System.out.println("<--------- Game Difficulty --------->");
		System.out.println("Please select the game difficulty"+"\n");
		System.out.println(" 1   Easy     50 Shots");
		System.out.println(" 2   Medium   35 Shots");
		System.out.println(" 3   Hard     25 Shots");

		System.out.print("\n"+"Choice = ");
		int gameDifficulty =Genio.getInteger();
		System.out.print("\n");
		
		if(gameDifficulty==1)
		{
			System.out.println("Easy mode selected");
			numberOfShots=50;
		}
		else if(gameDifficulty==2)
		{
			System.out.println("Medium mode selected");
			numberOfShots=35;
		}
		else if(gameDifficulty==3)
		{
			System.out.println("Hard mode selected");
			numberOfShots=25;
		}
		System.out.println("Game difficulty = "+numberOfShots);
		
		return numberOfShots;
		
	}
	
	public static int getRowFire(int inputRow,boolean inputRowValid)
	{
		// get ROW fire position
		do
		{
			System.out.println("\n"+"Please enter a x (row) cordinate 0-9");
			inputRow=Genio.getInteger();
			
			if(inputRow>=0 && inputRow<=9)
			{
				inputRowValid=true;
			}
			else
			{
				System.out.println("Invalid input");
			}
		}while(inputRowValid!=true);
		
		return inputRow;
	}
	
	public static int getColumnFire(int inputColumn,boolean inputColumnValid)
	{
		// get COLUMN fire position
		do
		{
			System.out.println("\n"+"Please enter a y (column) cordiante 0-9");
			inputColumn=Genio.getInteger();
			
			if(inputColumn>=0 && inputColumn<=9)
			{
				inputColumnValid=true;
			}
			else
			{
				System.out.println("Invalid input");
			}
		}while(inputColumnValid!=true);
		
		return inputColumn;
	}
}

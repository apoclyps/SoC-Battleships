/**
 * Gameboard
 * Gameboard implements ActionListner and imports awt, swing,sound, io.
 * creates a new instance of Gameboard when the run method is called.
 * Gameboard will contain a GUI allowing for games to be played from 
 * a grid view of the board.
 * Also handles updates to grid such as firing at targets, image changes, sound being played
 * and pop ups being displayed.
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

// Imports MouseListeneres and action events to handle input from user when buttons are clicked.
// Imports Java swing for GUI
// Imports reader to read in files
// Imports input/ouput exception to handle errors
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.io.*;

public class Gameboard implements ActionListener 
{
 
	// Global variables
    private static int GRID_SIZE = 10;
    private JButton [] buttonArray; 
    private static Clip sound;
    
    static JFrame frame = new JFrame("Battleships");
    static JLabel hitText = new JLabel("",JLabel.CENTER);
	static JLabel missText = new JLabel("",JLabel.CENTER);
	static JLabel shotsText = new JLabel("",JLabel.CENTER);
  
	
	/**
     * Gameboard
     * default Constructor for objects
     * 
     */
    public Gameboard()
    {
    	 
    }
     
    /**
     * director
     * will call createAndShowGUI();
     * @param  none
     * @return none
     */
    public void director() 
    {
    	createAndShowGUI();
	}
    
    /**
     * createMenu
     * creates a new JMenuBar holding Main menu and Exit along with 2 action listeners
     * @param  none
     * @return menuBar
     */
    public JMenuBar createMenu() 
    {
        JMenuBar menuBar  = new JMenuBar();;
        JMenu menu = new JMenu("Battle Menu");
        JMenuItem menuItem;
       
        menuBar.add(menu);

        // A group of JMenuItems. You can create other menu items here if desired
        menuItem = new JMenuItem("Main Menu");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
      // menuItem = new JMenuItem("New Game");
      // menuItem.addActionListener(this);
      // menu.add(menuItem);

      //  menuItem = new JMenuItem("Load Game");
      //  menuItem.addActionListener(this);
      //  menu.add(menuItem);

      //  menuItem = new JMenuItem("Save Game");
      //  menuItem.addActionListener(this);
      //  menu.add(menuItem);
        
        menuItem = new JMenuItem("Exit Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        return menuBar;
    }

    /**
     * createContentPane
     * creates a new contrainer to hold grid buttons in a grid layout.
     * @param  none
     * @return grid
     */
    public Container createContentPane() 
    {
        int numButtons = GRID_SIZE * GRID_SIZE;
        JPanel grid = new JPanel(new GridLayout(GRID_SIZE,GRID_SIZE));
        buttonArray = new JButton[numButtons];
        
        for (int i=0; i<numButtons; i++)
        {
        	ImageIcon sea = new ImageIcon("sea-texture.jpg");
            buttonArray[i] = new JButton("",sea);

			// This label is used to identify which button was clicked in the action listener
            buttonArray[i].setActionCommand("" + i); // String "0", "1" etc.
            buttonArray[i].addActionListener(this);
            grid.add(buttonArray[i]);
        }
        
        grid.setBackground(Color.black);
        return grid;
    }

    /**
     * This method handles events from the Menu and the board.
     *
     */
    public void actionPerformed(ActionEvent e) 
    {
        String classname = getClassName(e.getSource());
      
        if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();
            
            // Determine which menu option was chosen
            if (menutext.equals("Load Game"))
            {
                LoadGame();
            }
            else if (menutext.equals("Save Game"))
            {
                SaveGame();
            }
            else if (menutext.equals("Main Menu"))
            {
                MainMenu();
            }
            else if (menutext.equals("Exit Game"))
            {
                ExitGame();
            }
        }
        // Handle the event from the user clicking on a command button
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            int row = bnum / GRID_SIZE;
            int col = bnum % GRID_SIZE;
                   
          // code that handles user input on grid
            fireShot(row, col);
        }  
    }
    
     /**
     * createAndShowGUI
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     * @param  none
     * @return none
     */
	private static void createAndShowGUI() 
    {
        // Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        Gameboard battlegui = new Gameboard();
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        // <-----------------------------------Header PANEL ------------------------------------>
      
        // Creating Header Panel and Setting Layout to GridLayout
        JPanel HeaderBox = new JPanel();
        GridLayout HeaderBoxLayout = new GridLayout(1,9,15,15);
        HeaderBox.setLayout(HeaderBoxLayout);
         
        // creating a new image
        ImageIcon icon = new ImageIcon("BattleshipTitle.jpg");
        // Assigning image to a label and centering it
        JLabel PicLabel = new JLabel("", icon, JLabel.CENTER);
      
        // Setting headerbox to have a border and size
        HeaderBox.setBorder(BorderFactory.createLineBorder(Color.black));
        HeaderBox.setSize(150, 150);
        HeaderBox.setVisible(true);
           
        //Adding Logo To header
        HeaderBox.add(PicLabel);
           
        // <-----------------------------------FOOTER PANEL ------------------------------------>
   
        // Creating Header Panel and Setting Layout to GridLayout
        JPanel FooterBox = new JPanel();
        GridLayout FooterBoxLayout = new GridLayout(1,1,15,15);
        FooterBox.setLayout(FooterBoxLayout);
              
        // creating a new image
        ImageIcon icon2 = new ImageIcon("BattleshipBottom.jpg");
        // Assigning image to a label and centering it
        JLabel PicLabel2 = new JLabel("", icon2, JLabel.CENTER);
          
        // Setting headerbox to have a border and size
        FooterBox.setBorder(BorderFactory.createLineBorder(Color.black));
        FooterBox.setSize(150, 150);
        FooterBox.setVisible(true);
              
        //Adding Logo To header
        FooterBox.add(PicLabel2);
        //Setting Header
        content.add(FooterBox,BorderLayout.SOUTH);

        // <-----------------------------------EAST PANEL -------------------------------------->
              
        // Creating right display
        // Creating new Panel for East box
        JPanel EastBox = new JPanel();
        //Setting East box to grid layout
        GridLayout experimentLayout = new GridLayout(14,1,10,0);
        EastBox.setLayout(experimentLayout);
        EastBox.setSize(150,150);
                    
        // Adding Labels and buttons to panel 
        JLabel padding = new JLabel("Padding to the side");
        padding.setForeground(Color.black); 
             
        EastBox.add(padding);
             
        Font font = new Font("Arial", Font.BOLD, 24);
        Color c1 = new Color(199,199,199);
     
        ImageIcon torpedo = new ImageIcon("bomb3.jpg");
        JLabel torpedoLabel = new JLabel("", torpedo, JLabel.CENTER);
            
        ImageIcon bomb = new ImageIcon("bomb.jpg");
        JLabel bombLabel = new JLabel("", bomb, JLabel.CENTER);
           
        ImageIcon splash = new ImageIcon("splash.jpg");
        JLabel splashLabel = new JLabel("", splash, JLabel.CENTER);
             
             
        JLabel bombsLabel = new JLabel("Bomb's",JLabel.CENTER);
        bombsLabel.setFont(font);
        bombsLabel.setForeground(c1);

        torpedoLabel.setFont(font);
        torpedoLabel.setForeground(c1);
        torpedoLabel.setBackground(Color.black);
        torpedoLabel.setSize(50,50);
             
        bombLabel.setFont(font);
        bombLabel.setForeground(c1);
        bombLabel.setBackground(Color.black);
             
        splashLabel.setFont(font);
        splashLabel.setForeground(c1);
        splashLabel.setBackground(Color.black);
             
        int hits = Battleship.getNumberOfShots();           
        String hitsa = Integer.toString(hits);

        shotsText.setText(hitsa);
        shotsText.setFont(font);
        shotsText.setForeground(c1);
        shotsText.setBackground(Color.black);
 
        JLabel hitLabel = new JLabel("Hit's",JLabel.CENTER);
        hitLabel.setFont(font);
        hitLabel.setForeground(c1);
        hitLabel.setBackground(Color.black);
             
        int shipsRemain = Battleship.getShipLocationRemaing();           
        String shipsRemaina = Integer.toString(shipsRemain);

        hitText.setText(shipsRemaina);
        hitText.setFont(font);
        hitText.setForeground(c1);
        hitText.setBackground(Color.black);
             
        JLabel missLabel = new JLabel("Missed",JLabel.CENTER);
        missLabel.setFont(font);
        missLabel.setForeground(c1);
        missLabel.setBackground(Color.black);
             
        int missed = Battleship.getShotsMissed();           
        String misseda = Integer.toString(missed);
              
        missText.setText(misseda);
        missText.setFont(font);
        missText.setForeground(c1);
        missText.setBackground(Color.black);
             
        JLabel blank = new JLabel("",JLabel.CENTER);
             
        EastBox.add(bombsLabel);
        EastBox.add(shotsText);
        EastBox.add(torpedoLabel);
           
        EastBox.add(blank);
        EastBox.add(hitLabel);
        EastBox.add(hitText);
        EastBox.add(bombLabel);
        EastBox.add(padding);
                         
        EastBox.add(missLabel);
        EastBox.add(missText);
        EastBox.add(splashLabel);

        //setting Bored and Adding Centre Box to Content Panel
        EastBox.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(EastBox  , BorderLayout.EAST);
   
        // <----------------------------------- Guess PANEL ------------------------------------>

        // Creating Left - West  
        JPanel WestBox = new JPanel();
        GridLayout WestBoxLayout = new GridLayout(14,1,10,10);
        WestBox.setLayout(WestBoxLayout);
           
        JLabel padding1 = new JLabel("Padding to the side");
        padding1.setForeground(Color.black);  
           
        // Adding Labels and buttons to panel
        WestBox.add(padding1);
            
        //Setting border adding content to frame
        WestBox.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(WestBox  , BorderLayout.WEST);
        // <------------------------------------------------------------------------------------>
         
        // Setting Background Color for form
        HeaderBox.setBackground(Color.black);
        WestBox.setBackground(Color.black);
           
        FooterBox.setBackground(Color.black);
        EastBox.setBackground(Color.black);
            
        // <------------------------------------------------------------------------------------>    
           
        //Setting Header
        content.add(HeaderBox , BorderLayout.NORTH);
        content.add(battlegui.createContentPane(),BorderLayout.CENTER);
        
        frame.setJMenuBar(battlegui.createMenu());
        frame.setContentPane(content);
        // frame.setContentPane(battlegui.createContentPane());

        // Display the window, setting the size
        frame.setSize(900, 900);
        frame.setVisible(true);
    }
    


    /**
     * MainMenu
     * This method is called from the Menu event: Main Menu.
     * responds to Menu and Mouse click events
     * @param none
     * @return none
     */
    public void MainMenu()
    {
    	// asks for user input to ask if they want to exit gameboard ?
    	Object[] options2 = {" Yes","Cancel"};
		int confirmExit = JOptionPane.showOptionDialog(frame,"Are you sure you would like to return to main menu ?","Exit System",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,     //do not use a custom Icon
				options2,  //the titles of buttons
				options2[0]); //default button title
		
		// if user confirms they want to return
		if(confirmExit==0)
		{
			Battleship.setNumberOfShots(0);
			Battleship.setShotsMissed(0);
			Battleship.setShipLocationRemaing(19);
					
			System.out.println("New Game");

			frame.setVisible(false);
			frame.dispose();

		    Battleship.window.setVisible(true);
		    
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
    }
    
    /**
     * run
     * used to call createandshowGUi from outside class
     * @param none
     * @return none
     */
    public void run() 
    {
    	createAndShowGUI();
    }
   
    /**
     * NewGame
     * This method is called from the Menu event: New Game.
     * responds to Menu and Mouse click events
     * @param none
     * @return none
     */
    public void NewGame()
    {
    	System.out.println("New Game");
		
		Object[] options = {" Yes","Cancel"};
		int confirmExit = JOptionPane.showOptionDialog(frame,"Are you sure you would like to quit this game ?","Exit System",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,  
				options,  
				options[0]);
		
		if(confirmExit==0)
		{
			System.out.println("New Game");
			
			frame.setVisible(false);
			Battleship.window.dispose();

			Grid.resetGrid();
			Grid.resetPlayGrid();
							
		    Battleship.window.setVisible(true);
		    Battleship.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
    }
    
   
    /**
     * LoadGame
     * This method is called from the Menu event: Load Game.
     * responds to Menu and Mouse click events
     * @param none
     * @return none
     */
    public static void LoadGame()
    {
          System.out.println("Load game selected");
          
          int [] shipCordinates = new int[8];
          Fleet [] UserList = UserGrid.getUserList(); 
          int i =0;
          
       		FileReader fileReader; 
      	    BufferedReader reader; 
      	    try {
      			fileReader = new FileReader("shipLocations.txt");
      			reader = new BufferedReader(fileReader); 
      	
      			String lineIn = reader.readLine();; 
       			String [] text = null;
      	    
      			// Continues whilst the next line is not blank
      			while(lineIn != null)
      			{
      				for(int j=0; j<8;j++)
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
		            	//shipCordinates [j] = Integer.parseInt(text [j]);			 	
	            	}// end
      				//System.out.print(text[0]+",");
      				try
      				{
      					for(int j=0;j<8;j++)
      					{
	      					int num = Integer.parseInt(text [j]);
	      					//int num2 = Integer.parseInt(text [j]);
	      					shipCordinates [j] = num;
	      					//shipCordinates [j] = num;
	      					System.out.print(num+",");
      					}
      					UserList[i].setShipCordinates(shipCordinates);
      					System.out.println("");
      				}
      				catch(NumberFormatException e)
      				{
      					System.out.println("ERROR");
      				}
 
      		        i++;
      		        lineIn = reader.readLine();
      			}
       		}
      	    catch (FileNotFoundException e1) 
      		{
      			e1.printStackTrace();
      		} catch (IOException e)
      		{
				e.printStackTrace();
			}

    }// end load
    
    /**
     * ExitGame
     * This method is called from the Menu event: Exit Game.
     * responds to Menu and Mouse click events
     * @param none
     * @return none
     */
    public void ExitGame()
    {
    	System.out.println("Exit");
		
    	// Asks user to confirm they want to exit
		Object[] options = {" Yes","Cancel"};
		int confirmExit = JOptionPane.showOptionDialog(frame,"Are you sure you would like to exit ?","Exit System",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,     
				options,  
				options[0]); 
		
		// if user confirms they want to exit
		if(confirmExit==0)
		{
			System.exit(0);
		}
    }
    
    /**
     * SaveGame
     * This method is called from the Menu event: Save Game.
     * responds to Menu and Mouse click events
     * @param none
     * @return none
     */
	public void SaveGame()
	{
	      System.out.println("Save game selected");
	      //Grid.saveGame(true);

	      try {
				FileWriter fileWriter = new FileWriter("shipLocations.txt");// Any current file with the same name will be	overwritten
				BufferedWriter writeOut = new BufferedWriter(fileWriter);

						Fleet [] UserList = UserGrid.getUserList(); 
					      
					      for(int i=0;i<9;i++)
					      {
					    	  
						      int [] shipCordinates = UserList[i].getShipCordinates();
						      
						      for(int j =0;j<8;j++)
						      {
						    	  System.out.print(shipCordinates[j]+",");
						    	  writeOut.write(""+shipCordinates[j]+",");
						      }
						      System.out.println("");
						      writeOut.newLine();
					      }

				writeOut.newLine();
				writeOut.flush();
				writeOut.close();

			} catch (Exception e) {
				System.out.println("File cannot be written to(is it read only?)");
			}  
	}
    
	 /**
     * fireShot
     * Check grid cordinates to see if location contains a ship
     * if contains a ship then set icon for that position to = exlpode image
     * and play explosion sound. Then check if all targets of that ship have been hit.
     * If all targets of ship have been hit, play siren.
     * else display splash and play splash sound.
     * Update shots,hits and misses by using getters and setters and then updating the labels in the GUI
     * @param int row, int col
     * @return none
     */
    public void fireShot(int row, int col)
    {
    	ImageIcon explode = new ImageIcon("sea-texture-bomb.jpg");
    	ImageIcon ship = new ImageIcon("sea-texture-splash2.jpg");
    	
          System.out.println("Fire shot selected: at (" + row + ", " + col + ")");
       
          int arrayPosition = (row*10)+col;
          int numberOfShots = Battleship.getNumberOfShots();
          
          int [] [] Grid = UserGrid.getGrid();
          
          if(Grid[row][col]==3)
          {
        	  buttonArray[arrayPosition].setIcon(explode);
        	  
        	  sound("bomb.wav");

        	  int hit = Battleship.getShipLocationRemaing();        
        	  hit = hit -1;
        	  Battleship.setShipLocationRemaing(hit);
              String hits = Integer.toString(hit);
        	  hitText.setText(hits);
        	  
        	  int shot = Battleship.getNumberOfShots(); 
        	  System.out.println("shot = "+shot);
        	  shot = shot -1;
        	  Battleship.setNumberOfShots(shot);
              String shots = Integer.toString(shot);
        	  shotsText.setText(shots);
        	  
        	  
          }
          else if(Grid[row][col]==2)
          {
        	  buttonArray[arrayPosition].setIcon(explode);
        	  
        	  
          }
          else if(Grid[row][col]<2)
          {
        	  buttonArray[arrayPosition].setIcon(ship);
        	  sound("splash.wav");
        	  
        	  int miss = Battleship.getShotsMissed();       
        	  miss=miss+1;
        	  Battleship.setShotsMissed(miss);
              String missed = Integer.toString(miss);
        	  missText.setText(missed);
        	  
        	  int shot = Battleship.getNumberOfShots(); 
        	  System.out.println("shot = "+shot);
        	  shot = shot -1;
        	  Battleship.setNumberOfShots(shot);
              String shots = Integer.toString(shot);
        	  shotsText.setText(shots);
        	  // add miss
          }
  
          checkGame(row,col,numberOfShots);
        
    }
    
  
    /**
     * sound
     * accepts a parameter that contains a string with the file location.
     * trys to play sound if string is valid else catches exception
     * @param String pLocation
     * @return none
     */
    public static void sound(String pLocation)
    {
        //A try-catch to play a piece of audio, to which it gets passed. Encapsulated in a method
	try
        {   AudioInputStream stream = AudioSystem.getAudioInputStream(new File(pLocation));
           // AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
            sound = (Clip) AudioSystem.getLine(info);
            sound.open(stream);
            sound.start();
        }//try 
        catch (Exception e)
        {   e.printStackTrace();
        }//catch
    }//sound
    
    /**
     * checkGame
     * gets game file and sets grid to equal game file.
     * checks if grid contains a hit, miss,ship or invalid target.
     * updates grid depending upon which is targeted.
     * if ship is hit, display message saying which ship and how many spaces remain
     * at end of method, checks to see if any ships or shots remain.
     * if no shots remain, user loses the game and GameOverLoser is called.
     * if no ships remain, user wins the game and GameOverWinner is called.
     * @return none
     */
    public static void checkGame(int inputColumn,int inputRow,int numberOfShots)
	    {
	 	
		int [][] Grid = UserGrid.returnGameFile();
		
		UserGrid.setGrid(Grid);
		
		System.out.println("\n"+"<-------- Start Game ------->"+"\n");
						
				System.out.println("Targeted Row = "+inputRow+" Column = "+inputColumn);
				
				Grid = UserGrid.getGrid();
			
				// Miss
				if(Grid[inputColumn][inputRow]==0)
				{
					System.out.println("Target Missed");
					Grid[inputColumn][inputRow]=1;
				
					UserGrid.setGrid(Grid);
				}
				else if(Grid[inputColumn][inputRow]==1)
				{
					System.out.println("Previously Miss at Target");
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
					UserGrid.checkShipHit(inputRow,inputColumn);
				}

				System.out.println("Ships remaining to be hit "+Battleship.getShipLocationRemaing());
				System.out.print("\n");
				
				UserGrid.setGrid(Grid);
				
				if(numberOfShots==0)
				{
					gameOverLoser();
				}
				
				int targetsRemaining = Battleship.getShipLocationRemaing();
				
				if( targetsRemaining == 0)
				{
					gameOverWinner();
				}			
	}

    /**
     * gameOverLoser
     * displays a jOptionpane with a you lose message and 
     * then displays lose screen after user selects ok.
     * @param none
     * @return none
     */
    public static void gameOverLoser()
	{
		System.out.println("\n"+"<------ Game Over ----->");
		System.out.println(" You Lose!");
		
		// displays j option pane information user they lose.
		JOptionPane.showMessageDialog(frame,"You Lose. Press Ok to Continue.");	
		frame.setVisible(false);
		
		// displays lose screen
		new LoseScreen();

	}
	
    /**
     * gameOverWinner
     * displays a jOptionpane with a you win message and 
     * then displays win screen after user selects ok.
     * @param none
     * @return none
     */
	public static void gameOverWinner()
	{
		System.out.println("\n"+"<------ Game Over ----->");
		System.out.println(" You Win!");
		
		// displays j option pane information user they win.
		JOptionPane.showMessageDialog(frame,"You Win. Press Ok to Continue.");	
		frame.setVisible(false);
		
		// displays win screen
		new WinScreen();
		
	}
	
	 /**
	 * setGuiSquare
     * Sets a Gui grid square at row, col to display a character
     * @param none
     * @return boolean
     */
    public boolean setGuiSquare(int row, int col, char c)
    {
        int bnum = row * GRID_SIZE + col;
        if (bnum >= (GRID_SIZE*GRID_SIZE))
        {
            return false;
        }
        else
        {
            buttonArray[bnum].setText(Character.toString(c));
        }
        return true;
    }
    
    // GETTERS
    
    /**
     *  getClassName
     *  Returns the class name
     * @param  none
     * @return grid
     */
    protected String getClassName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    
	 /**
	 * getFrame
     * returns gameboard frame
     * @param none
     * @return frame
     */
    public static JFrame getFrame() {
		return frame;
	}

    /**
	 * setFrame
     * sets gameboard frame
     * @param frame
     * @return none
     */
	public static void setFrame(JFrame frame) {
		Gameboard.frame = frame;
	}
}






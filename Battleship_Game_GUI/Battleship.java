/**
 * Battleship
 * Battleship contains the main method for the program that will run 
 * and create a new instance of battleship when the program is executed.
 * Battleship will contain a main menu system allowing for new games to be started,
 * previous games to be loaded and the rules to be viewed.
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

// Imports MouseListeneres and action events to handle input from user when buttons are clicked.
// Imports Java swing for GUI
// Imports reader to read in files
// Imports input ouput exception to handle errors
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class Battleship extends JFrame implements MouseListener
{

	// Global Variables
	private static int shotsMissed=0;
	private static int numberOfShots=0;
	private static int shipLocationRemaing=0;
	
	//Creating frame and panel to place in frame
	static JFrame window;
	static JPanel content = new JPanel();
	
	 /**
     * Battleship
     * default Constructor for objects
     * new instances of Battleship will run Battleship_Gui and set the window ( frame).
     */
    public Battleship() {
    	
    	//creates a new instance of Battleship_GUI method
    	Battleship_GUI();
    }
	
	/**
     * Main Method
     * Creates new instance of Battleship and sets window ( frame ) to equal new instance of Battleship.
     */
    public static void main(String[] args) 
    {
    	// Setting window to be a new instance of Battleship and calls constructor
    	window = new Battleship();
    	// setting visibility of window to true
        window.setVisible(true);
        //Setting window to close on exit
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    } // end main method

    
    /**
     * Battleship_GUI
     * Creates a GridLayout and sets the North, South, East,West and Center Panels.
     * Header Panel creates a new Image and add this image to header Panel.
     * West panel creates a padding label to ensure that the layout remains consistent and adds to panel.
     * Center Panel creates 4 buttons, adds action listeners and is added to the panel
     * East Panel creates a padding label and adds to panel.
     * FooterPanel creates a new image and adds this to the footer panel.
     * All backgrounds for panels are set to black and content is added to pane.
     * Title is set and frame is set to exit on close
     * @param  none
     * @return none
     */
    public void Battleship_GUI()
    {     
       //Create content pane
       content.setLayout(new BorderLayout());

       // <-----------------------------------Header PANEL ------------------------------------>

       // Creating Header Panel and Setting Layout to GridLayout
       JPanel HeaderBox = new JPanel();
       GridLayout HeaderBoxLayout = new GridLayout(1,1,15,15);
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
       //Setting Header
       content.add(HeaderBox , BorderLayout.NORTH);
                
       // <----------------------------------- West PANEL ------------------------------------>
 
       // Creating West panel 
       JPanel WestPanel = new JPanel();
       GridLayout GuessBoxLayout = new GridLayout(14,1,10,0);
       WestPanel.setLayout(GuessBoxLayout);
       
       // Creating a Jlabel to provide padding to the left
       JLabel padding1 = new JLabel("Padding to the side,Padding to the side");
       padding1.setForeground(Color.black);  
        
       // Adding Label to panel
       WestPanel.add(padding1);
       
       //Setting border adding content to frame
       WestPanel.setBorder(BorderFactory.createLineBorder(Color.black));
       content.add(WestPanel,BorderLayout.WEST);
    
       // <----------------------------------- Centre Panel ----------------------------------->
  
        // Creating centre display
       JPanel CenterBox = new JPanel();
       GridLayout CenterBoxLayout = new GridLayout(4,4,15,15);
       CenterBox.setLayout(CenterBoxLayout);
       
       // creating new buttons
       Button StartButton = new Button(" Start ");
       Button LoadButton= new Button(" Load ");
       Button RulesButton= new Button(" Rules");
       Button ExitButton= new Button(" Exit ");
       
       // creating a default font for buttons
       Font font = new Font("Arial", Font.BOLD, 32);
       Color c1 = new Color(199,199,199);
       
       // setting proprties of Start button
       StartButton.setForeground(c1);
       StartButton.setBackground(Color.black); 
       StartButton.setFont(font); 
       
       // setting proprties of Load button
       LoadButton.setFont(font);
       LoadButton.setForeground(c1);
       LoadButton.setBackground(Color.black);
       
       // setting proprties of Rules button
       RulesButton.setFont(font);
       RulesButton.setForeground(c1);
       RulesButton.setBackground(Color.black);
       
       // setting proprties of Exit button
       ExitButton.setFont(font);
       ExitButton.setForeground(c1);
       ExitButton.setBackground(Color.black);     
              
       // Adding action listeners to buttons
       StartButton.addActionListener(new startListener());
       LoadButton.addActionListener(new loadListener());
       RulesButton.addActionListener(new rulesListener());
       ExitButton.addActionListener(new ExitListener());
       
       // Adding Labels and buttons to panel  
       CenterBox.add(StartButton);
       CenterBox.add(LoadButton);
       CenterBox.add(RulesButton);
       CenterBox.add(ExitButton);

       //setting Bored and Adding Centre Box to Content Panel
       CenterBox.setBorder(BorderFactory.createLineBorder(Color.black));
       content.add(CenterBox  , BorderLayout.CENTER);

    // <-----------------------------------EAST PANEL -------------------------------------->

       // Creating new Panel for East Panel
       JPanel EastPanel = new JPanel();
       //Setting EastPanel to grid layout
       GridLayout experimentLayout = new GridLayout(14,1,10,0);
       EastPanel.setLayout(experimentLayout);
              
       // Adding Labels and buttons to panel 
       JLabel padding = new JLabel("Padding to the side,Padding to the side");
       padding.setForeground(Color.black); 
   
       EastPanel.add(padding);
      
      //setting Border and Adding Centre Box to Content Panel
       EastPanel.setBorder(BorderFactory.createLineBorder(Color.black));
       content.add(EastPanel  , BorderLayout.EAST);
   
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

       // Setting Background Color for form
       HeaderBox.setBackground(Color.black);
       WestPanel.setBackground(Color.black);
       CenterBox.setBackground(Color.black);
       FooterBox.setBackground(Color.black);
       EastPanel.setBackground(Color.black);
      
      
       //Set window characteristics.
       setContentPane(content);
       setTitle("Battleships!");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	pack();         
    } // end constructor
    	
    
    /**
     * mainMenuDirector
     * Uses menu System from console version of Battleship.
     * Accepts a int parameter to select the option from the menu.
     * Menu allows for system to select if to :
     * Start new game , Load Game,  Rules, Exit, Continue Game and calls appropriate methods
     * @param  int menuChoice
     * @return none
     */
    public static void mainMenuDirector(int menuChoice)
    {
    	    		
    	System.out.println("\t\t"+"<-BATTLESHIPS>");
    	System.out.println(" Welcome to the tactical Battleships game"+"\n");
    	System.out.println("  1"+"\t"+"Start new game");
    	System.out.println("  2"+"\t"+"Load Game");
       	System.out.println("  4"+"\t"+"Rules");
    	System.out.println("  5"+"\t"+"Exit");
    
     	// Start Game
    	if(menuChoice==1)
    	{
    		System.out.println("Start Game");
     		UserGrid.gameInitilisation();
    	}
    	// Load Game
    	else if(menuChoice==2)
    	{
    		System.out.println("Load Game");
    		UserGrid.loadGame();
    	}
    	else if(menuChoice==4)
    	{
    		System.out.println("Rules");
    		rules();
    	}
    	// Exit
    	else if(menuChoice==5)
    	{
    		System.out.println("Exit");
    	}
    	
    }
    
    /**
     * rules
     * Trys to read rules.txt into system and also display file in notepad.
     * @param  none
     * @return none
     */
    public static void rules()
    {
    	// Try to read from file
        try
        { 
        	// creating new file reader and buffer reader
        	FileReader fileReader; 
            BufferedReader reader; 
            // passing in file name
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
                 
            System.out.println("");
            reader.close();
        }
        catch(Exception e)
        { 	
        	System.out.println("Cannot read from file");
        	System.out.println("");
        }
            
        try 
        {
    		Runtime.getRuntime().exec("notepad rules.txt");
    	} catch (IOException e) 
    	{
    		e.printStackTrace();
    	}
    }
    	
    /**
     * startListener
     * creates and initilises a new version of the game.
     * asks for the user to confirm they want to start a new game.
     * if user wants to play a new game, resets all variables and grids to default value of 0
     * asks for number of shots the user wants to play with - easy,medium or hard = 75,50,25 shots.
     * calls appripriate methods to set variables updated and hides current frame.
     * creates a new instance of gameboard to display the newly created game to the user.
     * @param  none
     * @return none
     */
    class startListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			System.out.println("New Game");
			
			// Asks the user to confirm they want to start a new game
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"Start a new game?","Load Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,     
					options,  
					options[0]); 
			
			// If the user selected yes then start new game
			if(confirmExit==0)
			{
				// reset values to default of zero
				UserGrid.resetGrid();
				UserGrid.resetPlayGrid();
				setNumberOfShots(0);
				setShipLocationRemaing(0);
				setShotsMissed(0);
				
				// Setting menu choice to equal 1 and call mainMenuDirector to start new game.
				int menuChoice=1;
				mainMenuDirector(menuChoice);
					
				// Gets the number of shots 
				int shots = getNumberOfShots();
				
				// Checks the user selected a number of shots is greater than zero, else ask user for input
				if(shots==0)
				{
					try
					{
						Object [] numbers={75,50,25};
						shots = (Integer) JOptionPane.showInputDialog(
			                    content,
			                    "Select number of shots:\n","Shot Selection",
			                    JOptionPane.PLAIN_MESSAGE, null, numbers,"shots");
					}
					catch(NullPointerException e)
					{
						
					}
				}
				
				// If user did not select cancel then continue to load game.
				if(shots!=0)
				{
					//default title and icon
					JOptionPane.showMessageDialog(content,"Loading Complete. Press Ok to Continue.");	
						
					// set the number of shots and ships remaining to be hit
					setNumberOfShots(shots);
					setShipLocationRemaing(19);
							
					// set current frame visibility to false to hide and dispose of frame
					Battleship.window.setVisible(false);
					window.setVisible(false);
					window.dispose();
			
					// create new instance of gameboard and call run method within.
					Gameboard newGame = new Gameboard();
					newGame.run();
				}
			}
		}
	}
    
    /**
     * loadListener
     * loads the previous save game file from the last game, even if Battleship GUI was closed.
     * asks for the user to confirm they want to load a new game.
     * if user wants to load a saved game, call menuDirector2
     * asks for number of shots the user wants to play with - easy,medium or hard = 75,50,25 shots.
     * calls appripriate methods to set variables updated and hides current frame.
     * creates a new instance of gameboard to display the loaded game to the user.
     * @param  none
     * @return none
     */
    class loadListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			System.out.println("Load Game");
				
			// asks for confirmation to load game
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"Load save game?","Load Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			
			// If user confirmed thay wanted to load game
			if(confirmExit==0)
			{
				// Asks the user to select a number of shots
				int shots=0;
				try
				{
					Object [] numbers={75,50,25};
					shots = (Integer) JOptionPane.showInputDialog(
		                   content,
		                   "Select number of shots:\n","Shot Selection",
		                   JOptionPane.PLAIN_MESSAGE, null, numbers,"shots");
				}
				catch(NullPointerException e)
				{
					System.out.println("ERROR");
					shots=24;
				}
				
				// If user did not cancle selecting the number of shots, continue to load
				if(shots!=24)
				{
					if(confirmExit==0)
					{
						// call laod game from mainMenuDirector
						mainMenuDirector(2);
						
						// change game parameters before starting
						setNumberOfShots(shots);
						setShipLocationRemaing(19);
						setShotsMissed(0);
								
						// set current frame to be hidden
						window.setVisible(false);
						
						// create new instance of gameboard with parameters and run
						Gameboard newGame = new Gameboard();
						newGame.run();
					}
				}
			}
		}
	}
    	
    /**
     * rulesListener
     * asks for the user to confirm they want to view the rules.txt file
     * if user wants to view rules, call mainMenuDirector with parameter 4
     * displays file in notepad.  
     * @param  none
     * @return none
     */
   	class rulesListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			System.out.println("Rules");
			
			// Asks the user to confirm they want to display the readme.txt file
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"View Rules text file ?","Exit System",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
				
			// If user selected yes, then display file
			if(confirmExit==0)
			{
				// calls display Rules from mainMenuDirector
				mainMenuDirector(4);
			}
		}
	}
    	
    /**
     * ExitListener
     * asks for the user to confirm they want to exit the system
     * if user selects yes then exit system
     * @param  none
     * @return none
     */
    class ExitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			System.out.println("Exit");
			
			// asks for confirmation to exit system
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"Are you sure you would like to exit ?","Exit System",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
				
			// exits system
			if(confirmExit==0)
			{
				System.exit(0);
			}
		}
	}
    	
    // GETTERS 
    
	/**
	 * getShotsMissed
	 * @param  none
	 * @return the shotsMissed
	 */
	public static int getShotsMissed() {
		return shotsMissed;
	}
	
	/**
     * getNumberOfShots
     * @param  none
     * @return int getNumberOfShots
     */
	public static int getNumberOfShots() {
		return numberOfShots;
	}

	/**
     * getShipLocationRemaing
     * @param  none
     * @return int shipLocationRemaing
     */
	public static int getShipLocationRemaing() {
		return shipLocationRemaing;
	}
	
	// SETTERS

	/**
	 * setShotsMissed
	 * @param shotsMissed the shotsMissed to set
	 * @return none
	 */
	public static void setShotsMissed(int shotsMiss) {
		shotsMissed = shotsMiss;
	}
	
	/**
	 * setNumberOfShots
	 * @param int numberShots
	 * @return none
	 */
	public static void setNumberOfShots(int numberShots) {
		numberOfShots = numberShots;
	}
	
	/**
	 * setShipLocationRemaing
	 * @param int shipLocationRemain
	 * @return none
	 */
	public static void setShipLocationRemaing(int shipLocationRemain) {
		shipLocationRemaing = shipLocationRemain;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}


/**
 * LoseScreen
 * LoseScreen extends Jframe and implements actionlisteners.
 * contains a constructor to set the frame settings and then call
 * a build panel method that will add the newly built panel to the frame.
 * Buttons within the panel implement action listeners that will allow for
 * a user to retry the game or exit the system.
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

// Imports MouseListeneres and action events to handle input from user when buttons are clicked.
// Imports Java swing for GUI
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoseScreen extends JFrame implements ActionListener
{  
	// global variables
	static JFrame frame = new JFrame();
	static JPanel content = new JPanel();
	
	 /**
     * LoseScreen
     * default Constructor for objects
     * new instances of LoseScreen will set the frame settings and then 
     * run the build panel method to update the frame with content.
     */
	public LoseScreen()
	{   
		System.out.println("lose screen");
		
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
       // setBackground(bg);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		buildPanel();
	}//WinScreen

	  /**
     * buildPanel
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
	public void buildPanel() 
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
	                
	    // <----------------------------------- Guess PANEL ------------------------------------>
	   	        
	    // Creating Left - West
	    JPanel WestBox = new JPanel();
	    GridLayout WestBoxLayout = new GridLayout(14,1,10,0);
	    WestBox.setLayout(WestBoxLayout);
	       
	    JLabel padding1 = new JLabel("Padding to the side,Padding to the side");
	    padding1.setForeground(Color.black);  
	        
	    // Adding Labels and buttons to panel
	    WestBox.add(padding1);
	       
	    //Setting border adding content to frame
	    WestBox.setBorder(BorderFactory.createLineBorder(Color.black));
	    content.add(WestBox  , BorderLayout.WEST);
	    
	    // <----------------------------------- Centre Panel ----------------------------------->
	       
	    // Creating centre display
	   JPanel CenterBox = new JPanel();
	   GridLayout CenterBoxLayout = new GridLayout(4,4,15,15);
	   CenterBox.setLayout(CenterBoxLayout);
	             
	   JLabel StartButton = new JLabel(" You Lose ",JLabel.CENTER);
	   JLabel blankPadding = new JLabel(" You Lose ",JLabel.CENTER);
	   blankPadding.setForeground(Color.black);
	       
	   Button RetryButton= new Button(" Retry ");
	   Button ExitButton= new Button (" Exit ");
	       
	   Font font = new Font("Arial", Font.BOLD, 32);
	   Color c1 = new Color(199,199,199);
	       
	   StartButton.setForeground(c1);
	   StartButton.setBackground(Color.black);
	   StartButton.setFont(font); 
	       
	   RetryButton.setFont(font);
	   RetryButton.setForeground(c1);
	   RetryButton.setBackground(Color.black);
	           	       
	   ExitButton.setFont(font);
	   ExitButton.setForeground(c1);
	   ExitButton.setBackground(Color.black);     
	       
	   RetryButton.addActionListener(new RetryListener());
	   ExitButton.addActionListener(new ExitListener());
	       
	   // Adding Labels and buttons to panel  
	   CenterBox.add(StartButton);
	   CenterBox.add(blankPadding);
	   CenterBox.add(RetryButton);
	   CenterBox.add(ExitButton);

	   //setting Bored and Adding Centre Box to Content Panel
	   CenterBox.setBorder(BorderFactory.createLineBorder(Color.black));
	   content.add(CenterBox  , BorderLayout.CENTER);
	 
	   // <-----------------------------------EAST PANEL -------------------------------------->
	       
	   // Creating right display
	   // Creating new Panel for East box
	   JPanel EastBox = new JPanel();
	   //Setting East box to grid layout
	   GridLayout experimentLayout = new GridLayout(14,1,10,0);
	   EastBox.setLayout(experimentLayout);
	             
	   // Adding Labels and buttons to panel 
	   JLabel padding = new JLabel("Padding to the side,Padding to the side");
	   padding.setForeground(Color.black); 
	   
	   EastBox.add(padding);
	      
	   //setting Bored and Adding Centre Box to Content Panel
	   EastBox.setBorder(BorderFactory.createLineBorder(Color.black));
	   content.add(EastBox  , BorderLayout.EAST);
	   
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
      WestBox.setBackground(Color.black);
      CenterBox.setBackground(Color.black);
      FooterBox.setBackground(Color.black);
      EastBox.setBackground(Color.black);

      //Set window characteristics.
      frame.setContentPane(content);
      frame.setTitle("Battleships!");
      frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();   
      
	 } // end constructor


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	 /**
     * RetryListener
     * asks for the user to confirm they want to retry a new game.
     * if user wants to retry a new game, resets all variables and grids to default value of 0
     * calls appropriate methods to set variables updated and hides current frame.
     * returns to main menu to allow the user to play again
     * @param  none
     * @return none
     */
    class RetryListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			
			// asks for user to confirm if they want to retry
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"Are you sure you would like retry ?","Exit System",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,     
					options,  
					options[0]); 
			
			// if user confirms they want to retry, display main menu
			if(confirmExit==0)
			{
				frame.setVisible(false);
				Battleship.window.dispose();
				
				Grid.resetGrid();
				Grid.resetPlayGrid();
								
			    Battleship.window.setVisible(true);
			    Battleship.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			Object[] options = {" Yes","Cancel"};
			int confirmExit = JOptionPane.showOptionDialog(content,"Are you sure you would like to exit ?","Exit System",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,   
					options,  
					options[0]); 
			
			if(confirmExit==0)
			{
				System.exit(0);
			}
		}
	}
}//class
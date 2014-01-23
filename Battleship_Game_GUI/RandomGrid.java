/**
 * RandomGrid
 * randomGrid extends Grid
 * generates randomShiplocations in the form of x and y cordinates
 * generates randomShipAxis in the form of vertical or horizontal and returns string
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

// Imports random to generate random numbers
import java.util.Random;

public class RandomGrid extends Grid {

	/**
     * RandomGrid
     * default Constructor for objects
     */
	public RandomGrid()
	{
		
	}
	
	  /**
     * randomShipLocation
   	 * generates 2 random numbers between 0 and 9
   	 * stores both numbers in a int array of 2
   	 * returns array
     * @param  Fleet ObjectShip
     * @return int return
     */
	public static int [] randomShipLocation(Fleet ObjectShip)
	{
		System.out.println("\n"+"<---Random Ship Location --->");
		
		Random randomGenerator = new Random();
	
		randomX = randomGenerator.nextInt(9);
		//randomX = 9;
		System.out.println("Random x = "+randomX);
		
		int shipSize = ObjectShip.getShipSize();
		
		randomY = randomGenerator.nextInt(9);
	
		System.out.println("Random y = "+randomY);
		
		int shipFit = 9-shipSize;
		System.out.println("ship fit ="+shipFit);
		System.out.println("ship size ="+shipSize);
		if(randomX <=shipFit)
		{
			System.out.println("Ship will fit");
		}
		else
		{
			System.out.println("Ship wont fit");
			//randomX=randomX+1;
			// do not place first setting
		}
		int [] startingLocation = new int [2];
		startingLocation[0]=randomX;
		startingLocation[1]=randomY;
		System.out.println("<--- End Random Ship Location --->");
		return startingLocation;
	}
	
	 
	  /**
     * getRandomShipAxis
   	 * generates a new random axis as a interger between 1 and 2
   	 * returns random axis
     * @param  none
     * @return int returnAxis
     */
	public static int getRandomShipAxis()
	{
		System.out.println("<--- Random Axis --->");
		Random randomAxisGenerator = new Random();
		int randomAxis = randomAxisGenerator.nextInt(2)+1;
		
		System.out.println("random Ship Axis = "+randomAxis);
		
		return randomAxis;
	}
	
	  /**
     * getRandomShipAxisString
   	 * generates a new random axis as a interger between 1 and 2
   	 * converts axis to string as Vertical or Horizontal
   	 * returns string axis
     * @param none
     * @return int Axis
     */
	public static String getRandomShipAxisString()
	{
		System.out.println("<--- Random Axis --->");
		Random randomAxisGenerator = new Random();
		int randomAxis = randomAxisGenerator.nextInt(2)+1;
		
		System.out.println("random Ship Axis = "+randomAxis);
		
		String axis=null;
		if(randomAxis==1)
		{
			axis = "Vertical";
		}
		else if(randomAxis ==2)
		{
			axis = "Horizontal";
		}
		return axis;
	}
}

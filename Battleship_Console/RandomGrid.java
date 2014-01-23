import java.util.Random;


public class RandomGrid extends Grid {

	public RandomGrid()
	{
		
	}
	
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
	
	public static void randomShipLocation2(Fleet ObjectShip)
	{
		System.out.println("\n"+"<---Random Ship Location 2 --->");
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
		
		ObjectShip.getShipAxis();
		System.out.println("Ship Axis" + ObjectShip.getShipAxis());
		
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
		
		//int [][] usersGrid = UserGrid.getPlayGrid();
		
		//if(userGrid[][])
		
		System.out.println("<--- End Random Ship Location 2 --->");
		
	}
	
	public static int getRandomShipAxis()
	{
		System.out.println("<--- Random Axis --->");
		Random randomAxisGenerator = new Random();
		int randomAxis = randomAxisGenerator.nextInt(2)+1;
		
		System.out.println("random Ship Axis = "+randomAxis);
		
		return randomAxis;
	}
	
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


public class Fleet {

	private int shipSize;
	private boolean shipHit;
	private boolean shipSunk;
	private String shipName;
	private String shipAxis;
	private int [] shipStartingCordinates = new int[2];
	private int [] shipEndingCordinates = new int[2];
	private int [] shipCordinates = new int [8];
	private String orientation=null;
			
	public Fleet()
	{
		shipName = "Unknown";
		shipSize =0;
		shipHit = false;
		shipSunk = false;
		shipAxis = "Unknown";
		shipStartingCordinates [0] = 0;
		shipStartingCordinates [1] = 0;
		shipEndingCordinates [0] = 0;
		shipEndingCordinates [1] = 0;
	}
	
	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String Inorientation) {
		orientation = Inorientation;
	}

	public int[] getShipStartingCordinates() {
		return shipStartingCordinates;
	}

	public void setShipStartingCordinates(int[] shipStartingCordinates) {
		this.shipStartingCordinates = shipStartingCordinates;
	}

	public int[] getShipEndingCordinates() {
		return shipEndingCordinates;
	}

	public void setShipEndingCordinates(int[] EndingCordinates) {
		this.shipEndingCordinates = EndingCordinates;
	}
	
	public String getShipAxis() {
		return shipAxis;
	}

	public void setShipAxis(String Axis) {
		this.shipAxis = Axis;
	}

	public Fleet(int shipSize,boolean shipHit,boolean shipSunk,String shipName)
	{
		this.shipSize = shipSize;
		this.shipHit = shipHit;
		this.shipSunk = shipSunk;
		this.shipName = shipName;
	}
	
	public Fleet(int shipSize,boolean shipHit,boolean shipSunk,String shipName,String shipAxis)
	{
		this.shipSize = shipSize;
		this.shipHit = shipHit;
		this.shipSunk = shipSunk;
		this.shipName = shipName;
		this.shipAxis = shipAxis;
	}
	
	public Fleet(int shipSize,boolean shipHit,boolean shipSunk,String shipName,String shipAxis,
			int[] shipStartingCordinates,int[] shipEndingCordinates)
	{
		this.shipSize = shipSize;
		this.shipHit = shipHit;
		this.shipSunk = shipSunk;
		this.shipName = shipName;
		this.shipAxis = shipAxis;
		this.shipStartingCordinates = shipStartingCordinates;
		this.shipEndingCordinates = shipEndingCordinates;
	}
	
	/*
	 * This method will check if the ship has been hit and return a true or false value
	 * 
	 */
	public boolean checkShipHit()
	{
		System.out.println("Check if ship has been hit");
		
		boolean targetHitShip =false;
	
		// get cordinates of ship
		
		return(targetHitShip);
	}
	
	public void updateShipHit()
	{
		System.out.println("Update ship has been hit");
		
		//update cordinates of ship hit
		//deduct 1 from ship size
		//if ship size = 0 then
		//call ship sinks
		
		System.out.println("");
	}
		
	public void shipSink()
	{
		System.out.println("The ship has been sunk");
		System.out.println("");
	}
	
	public void displayShip()
	{
		System.out.println("Ship Name = "+shipName);
		System.out.println("Ship size = "+shipSize);
		System.out.println("Ship hit  = "+shipHit);
		System.out.println("Ship Sunk = "+shipSunk);
		System.out.println("Ship Axis = "+shipAxis);
		System.out.println("Ship Orientation          = "+orientation);
		System.out.println("Ship Starting X cordinate = "+shipStartingCordinates [0]);
		System.out.println("Ship Starting y cordiante = "+shipStartingCordinates [1]);
		System.out.println("Ship Ending X cordinate   = "+shipEndingCordinates [0]);
		System.out.println("Ship Ending y cordiante   = "+shipEndingCordinates [1]);
		System.out.println("");
	}
	
	// Getters
	public int getShipSize() {
		return shipSize;
	}
	
	public boolean getShipHit()	{
		return shipHit;
	}
	
	public boolean getShipSunk() {
		return shipSunk;
	}
	
	public String getShipName() {
		return shipName;
	}
	
	public void setShipSize(int shipSize){
		this.shipSize = shipSize;
	}
	
	public void setShipHit(boolean shipHit){
		this.shipHit = shipHit;
	}
	
	public void setShipSunk(boolean shipSunk){
		this.shipSunk = shipSunk;
	}
	
	public void setShipCordinates(int [] shipCordinates) {
		this.shipCordinates = shipCordinates;
	}

	public int [] getShipCordinates() {
		return shipCordinates;
	}
	
}// End fleet class

/**
 * Fleet
 * Fleet will contain the necessary variables needed to store information about ships
 * along with constructors to initilise these ship values and getters/setters to update
 * and change ship details when necessary. (e.g. ship cordinates).
 * @ "Kyle Harrison"
 * @ (05/08/2011)
 */

public class Fleet {

	// Global variables
	private int shipSize;
	private boolean shipHit;
	private boolean shipSunk;
	private String shipName;
	private String shipAxis;
	private int [] shipStartingCordinates = new int[2];
	private int [] shipEndingCordinates = new int[2];
	private int [] shipCordinates = new int [8];
	private String orientation=null;
	
	
	 /**
     * Fleet
     * default Constructor for objects
     * sets all variables to default value of 0, unknown or false
     */
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
	
	 /**
     * Fleet
     * default Constructor for objects
     * sets shipSize,shipHit,shipSunk and Ship name to parameters input
     * @param  int shipSize,boolean shipHit,boolean shipSunk,String shipName
     * @return none
     */
	public Fleet(int shipSize,boolean shipHit,boolean shipSunk,String shipName)
	{
		this.shipSize = shipSize;
		this.shipHit = shipHit;
		this.shipSunk = shipSunk;
		this.shipName = shipName;
	}
	
	 /**
     * Fleet
     * Constructor for fleet objects
     * sets shipSize,shipHit,shipSunk ,Ship name and shipAxis to parameters input
     * @param  int shipSize,boolean shipHit,boolean shipSunk,String shipName,String shipAxis
     * @return none
     */
	public Fleet(int shipSize,boolean shipHit,boolean shipSunk,String shipName,String shipAxis)
	{
		this.shipSize = shipSize;
		this.shipHit = shipHit;
		this.shipSunk = shipSunk;
		this.shipName = shipName;
		this.shipAxis = shipAxis;
	}
	
	
	 /**
     * Fleet
     * Constructor for fleet objects
     * sets shipSize,shipHit,shipSunk ,Ship name, shipAxis, shipStartingCordiante 
     * and shipEndingCordiantes to parameters input.
     * @param  int shipSize,boolean shipHit,boolean shipSunk,String shipName,String shipAxis,
			   int[] shipStartingCordinates,int[] shipEndingCordinates
     * @return none
     */
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
	

	/**
    * displayShip
    * Display class that will output the value of all variables for fleet objects in console
    * @param none
    * @return none
    */
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
	/**
	 * getShipSize
	 * @param  none
	 * @return shipSize
	 */
	public int getShipSize() {
		return shipSize;
	}
	
	/**
	 * getShipHit
	 * @param  none
	 * @return shipHit
	 */
	public boolean getShipHit()	{
		return shipHit;
	}
	
	/**
	 * getShipSunk
	 * @param  none
	 * @return shipSunk
	 */
	public boolean getShipSunk() {
		return shipSunk;
	}
	
	/**
	 * getShipName
	 * @param  none
	 * @return shipName
	 */
	public String getShipName() {
		return shipName;
	}
	
	/**
	 * getShipCordinates
	 * @param  none
	 * @return shipCordinates
	 */
	public int [] getShipCordinates() {
		return shipCordinates;
	}
	
	/**
	 * getOrientation
	 * @param  none
	 * @return orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * getShipStartingCordinates
	 * @param  none
	 * @return shipStartingCordinates
	 */
	public int[] getShipStartingCordinates() {
		return shipStartingCordinates;
	}

	/**
	 * setShipStartingCordinates
	 * @param  none
	 * @return shipStartingCordinates
	 */
	public void setShipStartingCordinates(int[] shipStartingCordinates) {
		this.shipStartingCordinates = shipStartingCordinates;
	}

	/**
	 * getShipEndingCordinates
	 * @param  none
	 * @return shipEndingCordinates
	 */
	public int[] getShipEndingCordinates() {
		return shipEndingCordinates;
	}

	/**
	 * getShipAxis
	 * @param  none
	 * @return shipAxis
	 */
	public String getShipAxis() {
		return shipAxis;
	}

	// SETTERS
	
	/**
	 * setShipEndingCordinates
	 * @param int[] EndingCordinates
	 * @return none
	 */
	public void setShipEndingCordinates(int[] EndingCordinates) {
		this.shipEndingCordinates = EndingCordinates;
	}
	
	/**
	 * setShipAxis
	 * @param String Axis
	 * @return none
	 */
	public void setShipAxis(String Axis) {
		this.shipAxis = Axis;
	}
	
	/**
	 * setShipSize
	 * @param int shipSize
	 * @return none
	 */
	public void setShipSize(int shipSize){
		this.shipSize = shipSize;
	}
	
	/**
	 * setShipHit
	 * @param boolean shipHit
	 * @return none
	 */
	public void setShipHit(boolean shipHit){
		this.shipHit = shipHit;
	}
	
	/**
	 * setShipSunk
	 * @param boolean shipSunk
	 * @return none
	 */
	public void setShipSunk(boolean shipSunk){
		this.shipSunk = shipSunk;
	}
	
	/**
	 * setShipCordinates
	 * @param int [] shipCordinates
	 * @return none
	 */
	public void setShipCordinates(int [] shipCordinates) {
		this.shipCordinates = shipCordinates;
	}
	
	/**
	 * setOrientation
	 * @param String Inorientation
	 * @return none
	 */
	public void setOrientation(String Inorientation) {
		orientation = Inorientation;
	}
	
}// End fleet class

package simulator;

public class Valve extends PlantComponent {
	private final static double VALVE_FAILURE_RATE = 0.0;
	private final static int VALVE_REPAIR_RATE = 0;
	private final static boolean DEFAULT_OPEN_STATE = true; 
	
	private boolean open;
	
	/**
	 * Creates a new valve that is connected to the specified
	 * two plant components.
	 */
	public Valve () {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.open = DEFAULT_OPEN_STATE;
	}
	
	/**
	 * Creates a new valve and sets it's position (open/closed)
	 * @param open true if the valve is open
	 */
	public Valve (boolean open) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.open = open;
	}
	
	/**
	 * Returns true if the valve is open.
	 * @return true if the valve is open
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Set the state of the valve.
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void updateState() {
		// Just chill like a valve.
		// (Valves do nothing themselves)
	}
	
	public boolean checkFailure() {
		return super.checkFailure();
		//Insert implementation 
	}	
	
}

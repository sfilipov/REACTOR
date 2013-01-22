package simulator;

public class Valve extends PlantComponent {
	private static final long serialVersionUID = -4238759395307525755L;
	
	private final static int DEFAULT_FAILURE_RATE = 0;
	private final static int DEFAULT_REPAIR_RATE  = 0;
	private final static int MAX_FAILURE_RATE     = 0;
	private final static boolean DEFAULT_OPEN_STATE = true;
	
	private final static int MAX_STEAM_FLOW = 300; // Maximum steam flow through allowed. 
	
	private boolean open;
	private int ID;
	
	/**
	 * Creates a new valve that is connected to the specified
	 * two plant components.
	 */
	public Valve (int ID, FlowType type) {
		super(DEFAULT_FAILURE_RATE, DEFAULT_REPAIR_RATE, MAX_FAILURE_RATE);
		this.getFlowOut().setType(type);
		this.ID = ID;
		this.open = DEFAULT_OPEN_STATE;
	}
	
	/**
	 * Creates a new valve and sets it's position (open/closed)
	 * @param open true if the valve is open
	 */
	public Valve (int ID, FlowType type, boolean open) {
		super(DEFAULT_FAILURE_RATE, DEFAULT_REPAIR_RATE, MAX_FAILURE_RATE);
		this.getFlowOut().setType(type);
		this.ID = ID;
		this.open = open;
	}
	
	/**
	 * Gets the ID of this valve.
	 * @return ID of this valve.
	 */
	public int getID()
	{
		return ID;
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
	
	public int getMaxSteamFlow() {
		return MAX_STEAM_FLOW;
	}
	
	@Override
	public void updateState() {
		// Just chill like a valve.
		// (Valves do nothing themselves)
	}
	
	@Override
	public boolean checkFailure() {
		return super.checkFailure();
		//Insert implementation 
	}	
	
}

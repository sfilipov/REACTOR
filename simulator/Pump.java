package simulator;

public class Pump extends PlantComponent {
	//DEFAULT_RPM is used when a rpm argument is not passed to the constructor
	private final static int DEFAULT_RPM = 0;
	private final static boolean DEFAULT_ON_STATE = true;
	private final static int MAX_RPM = 1000; 
	
	private int ID;
	private int rpm;
	private boolean on;
	
	public Pump(int ID) {
		super();
		this.ID = ID;
		setRpm(DEFAULT_RPM);
		this.on = DEFAULT_ON_STATE;
	}
	
	public Pump(int ID, double failureRate, int repairTime, int rpm) {
		super(failureRate, repairTime);
		this.ID = ID;
		setRpm(rpm);
		this.on = DEFAULT_ON_STATE;
	}
	
	/**
	 * Returns the ID of this pump.
	 * @return the ID of this pump.
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * Returns the current RPM value of the pump.
	 * Note: If the pump is off, the RPM returned will be zero.
	 * @return the current RPM value of the pump
	 */
	public int getRpm() {
		return on ? rpm : 0;
	}
	
	/**
	 * Sets a new value for the RPM of the pump.
	 * @param rpm the new value
	 */
	public void setRpm(int rpm) throws IllegalArgumentException {
		if (rpm <= MAX_RPM && rpm >= 0) {
			this.rpm = rpm;
		} else {
			throw new IllegalArgumentException("Pump rpm must be in the range [0 - " + MAX_RPM + "]");
		}
		if (rpm != 0 && !on) on = true;
	}
	
	public boolean isOn() {
		return on;
	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public int getMaxRpm()
	{
		return MAX_RPM;
	}

	public void updateState() {
		// Chill out with the valves, sippin' on Gin & Juice.
	}
	
	public boolean checkFailure() {
		return super.checkFailure();
	}
}

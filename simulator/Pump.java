package simulator;

public class Pump extends PlantComponent {

	private static final long serialVersionUID = -446684199807618671L;
	
	//DEFAULT_RPM is used when a rpm argument is not passed to the constructor
	private final static int DEFAULT_RPM = 0;
	private final static boolean DEFAULT_ON_STATE = true;
	private final static int MAX_RPM = 1000; 
	public final static int DEFAULT_FAILURE_RATE = 10; //1%
	public final static int DEFAULT_REPAIR_TIME = 5;
	private final static int MAX_FAILURE_RATE = 50; //5%
	
	private int ID;
	private int rpm;
	private boolean on;
	
	/**
	 * Constructs a pump with the selected ID.
	 * @param ID the selected ID for the pump.
	 */
	public Pump(int ID) {
		super(DEFAULT_FAILURE_RATE, DEFAULT_REPAIR_TIME);
		this.ID = ID;
		setRpm(DEFAULT_RPM);
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
	 * 		 If the pump is broken, the RPM returned will also be zero.
	 * @return the current RPM value of the pump
	 */
	public int getRpm() {
		return (!on || !this.isOperational()) ? 0 : rpm;
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
	
	/**
	 * Returns true if the pump is on.
	 * @return true if the pump is on.
	 */
	public boolean isOn() {
		return on;
	}
	
	/**
	 * Sets the pump (on/off).
	 * @param on true if the pump needs to be on, false if it needs to be off.
	 */
	public void setOn(boolean on) {
		this.on = on;
	}
	
	/**
	 * Returns the max RPM of the pump.
	 * @return the max RPM of the pump.
	 */
	public int getMaxRpm()
	{
		return MAX_RPM;
	}
	
	/**
	 * Update the state of the pump.
	 * 
	 * Increases the failure rate of the pump if appropriate.
	 */
	public void updateState() {
		increaseFailureRate();
	}
	
	/**
	 * Checks if the pump fails randomly.
	 */
	public boolean checkFailure() {
		return super.checkFailure();
	}
	
	/**
	 * Increases the pump's chance to fail by 0.1% per call.
	 * 
	 * Doesn't increase the chance to fail once MAX_FAILURE_RATE is reached.
	 */
	private void increaseFailureRate() {
		int currentFailureRate = this.getFailureRate();
		if (currentFailureRate < MAX_FAILURE_RATE) {
			this.setFailureRate(++currentFailureRate);
		}
	}
}

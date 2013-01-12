package simulator;

public class Pump extends PlantComponent {
	//DEFAULT_RPM is used when a rpm argument is not passed to the constructor
	private final static int DEFAULT_RPM = 0;
	private final static boolean DEFAULT_ON_STATE = true;
	
	private int rpm;
	private boolean on;
	
	public Pump() {
		super();
		setRpm(DEFAULT_RPM);
		this.on = DEFAULT_ON_STATE;
	}
	
	public Pump(double failureRate, int repairTime, int rpm) {
		super(failureRate, repairTime);
		setRpm(rpm);
		this.on = DEFAULT_ON_STATE;
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
	public void setRpm(int rpm) {
		this.rpm = rpm;
		/* Uncomment this if we decide that setting an rpm
		 * when the pump is off should turn it on. 
		 *
		 * if (rpm != 0 && !on) on = true;
		 */
	}
	
	public boolean isOn() {
		return on;
	}
	
	public void toggleState() {
		on = !on;
	}
	
	public void updateState() {
		// Chill out with the valves, sippin' on Gin & Juice.
	}
	
	public boolean checkFailure() {
		super.checkFailure();
		//Insert implementation 
	}
}

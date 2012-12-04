package simulator;

abstract class PlantComponent {
	//The values of the constants below are for illustration.
	private final static double DEFAULT_FAILURE_RATE = 5.0;
	private final static int DEFAULT_REPAIR_TIME = 10;
	private double failureRate;
	private int repairTime;
	private boolean operational;
	
	/**
	 * Creates a new operational PlantComponent object
	 * with the default chance to fail and turns to repair.
	 */
	protected PlantComponent() {
		setFailureRate(DEFAULT_FAILURE_RATE);
		setRepairTime(DEFAULT_REPAIR_TIME);
		setOperational(true);
	}
	
	/**
	 * Creates a new operational PlantComponent with
	 * the specified chance to fail and turns to repair.
	 * 
	 * @param failureRate the chance of a component to fail randomly
	 * @param repairTime the number of steps needed to repair the component
	 */
	protected PlantComponent(double failureRate, int repairTime) {
		setFailureRate(failureRate);
		setRepairTime(repairTime);
		setOperational(true);
	}
	
	/**
	 * Creates a new PlantComponent with
	 * the specified chance to fail, turns to repair and working condition.
	 * 
	 * @param failureRate the chance of the component to fail randomly
	 * @param repairTime the number of steps needed to repair the component
	 * @param operational determines whether the component is initially
	 * 					  operational or not
	 */
	protected PlantComponent(double failureRate, int repairTime, boolean operational) {
		setFailureRate(failureRate);
		setRepairTime(repairTime);
		setOperational(operational);
	}

	/**
	 * Returns the current chance of the component to fail randomly.
	 * @return the current chance of the component to fail randomly
	 */
	protected double getFailureRate() {
		return failureRate;
	}
	
	/**
	 * Sets a new value for failureRate.
	 * @param failureRate the new value for failureRate
	 */
	protected void setFailureRate(double failureRate) {
		this.failureRate = failureRate;
	}
	
	/**
	 * Returns the number of turns that it takes for a component to repair.
	 * @return the number of turns that it takes for a component to repair
	 */	
	protected int getRepairTime() {
		return repairTime;
	}
	
	/**
	 * Changes the number of turns it take for a component to get repaired.
	 * @param repairTime the number of turns
	 */
	protected void setRepairTime(int repairTime) {
		this.repairTime = repairTime;
	}
	
	/**
	 * Returns true if the component is operational (working correctly).
	 * @return true if the component is operational (working correctly)
	 */
	protected boolean isOperational() {
		return operational;
	}
	
	/**
	 * Sets the condition of the component - true if it is
	 * working correctly, false if it has failed.
	 * @param operational the new state of the component
	 */
	protected void setOperational(boolean operational) {
		this.operational = operational;
	}
	
	/**
	 * Updates the information about the component.
	 */
	abstract void updateState();
	
	/**
	 * Runs all checks for the component and changes it's operational state if needed. 
	 */
	abstract void checkFailure();
}

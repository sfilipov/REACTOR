package simulator;

abstract class PlantComponent {
	//The values of the constants below are for illustration.
	private final static double DEFAULT_FAILURE_RATE = 5.0;
	private final static int DEFAULT_REPAIR_TIME = 10;
	private double failureRate;
	private int repairTime;
	private boolean operational;
	
	/**
	 * Creates a new PlantComponent object
	 * with default failure rate and repair time.
	 * It is initially operational.
	 */
	protected PlantComponent() {
		setFailureRate(DEFAULT_FAILURE_RATE);
		setRepairTime(DEFAULT_REPAIR_TIME);
		setOperational(true);
	}
	
	/**
	 * Creates a new PlantComponent with the particular failureRate and repairTime
	 * 
	 * @param failureRate the chance of a component to fail randomly. If it is
	 * 					  0.0 then the component never fails randomly.
	 * @param repairTime the number of steps needed to repair the component.
	 */
	protected PlantComponent(double failureRate, int repairTime) {
		setFailureRate(failureRate);
		setRepairTime(repairTime);
		setOperational(true);
	}
	
	/**
	 * Creates a new PlantComponent with the particular failureRate and repairTime
	 * 
	 * @param failureRate the chance of a component to fail randomly. If it is
	 * 					  0.0 then the component never fails randomly.
	 * @param repairTime the number of steps needed to repair the component.
	 * @param operational determines whether the component is initially
	 * 					  operational or not.
	 */
	protected PlantComponent(double failureRate, int repairTime, boolean operational) {
		setFailureRate(failureRate);
		setRepairTime(repairTime);
		setOperational(operational);
	}

	
	protected double getFailureRate() {
		return this.failureRate;
	}
	
	protected void setFailureRate(double failureRate) {
		this.failureRate = failureRate;
	}
	
	protected int getRepairTime() {
		return this.repairTime;
	}
	
	protected void setRepairTime(int repairTime) {
		this.repairTime = repairTime;
	}
	
	protected boolean isOperational() {
		return this.operational;
	}
	
	protected void setOperational(boolean operational) {
		this.operational = operational;
	}
	
	/*
	 * I assume updateState and hasFailed need different implementations
	 * depending on the particular component Class.
	 * I changed the name of checkFailures to hasFailed-
	 */
	abstract void updateState();
	//abstract boolean hasFailed();
}

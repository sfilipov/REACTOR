package simulator;

abstract class PlantComponent {
	private double failureRate;
	private int repairTime;
	private boolean operational;
	
	/**
	 * Creates a new PlantComponent with the particular failureRate and repairTime
	 * 
	 * @param failureRate the chance of a component to fail randomly. If it is
	 * 					  0.0 then the component never fails randomly.
	 * @param repairTime the number of steps needed to repair the component
	 */
	protected PlantComponent(double failureRate, int repairTime) {
		setFailureRate(failureRate);
		setRepairTime(repairTime);
		setOperational(true);
	}
	
	/**
	 * Creates a new PlantComponent object
	 * that never fails randomly and is initially operational
	 */
	protected PlantComponent() {
		setFailureRate(0.0);
		setRepairTime(0);
		setOperational(true);
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
	 * I assume updateState and checkFailures need different implementations
	 * depending on the particular component Class
	 */
	protected abstract void updateState();
	protected abstract boolean hasFailed();
}
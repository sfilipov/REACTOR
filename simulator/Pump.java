package simulator;

class Pump extends PlantComponent {
	//DEFAULT_RPM is used when a rpm argument is not passed to the constructor
	private final static int DEFAULT_RPM = 0;
	private int rpm;
	private PlantComponent source;
	private PlantComponent destination;
	
	Pump(PlantComponent source, PlantComponent destination) {
		super();
		setRpm(DEFAULT_RPM);
		this.source = source;
		this.destination = destination;
	}
	
	Pump(double failureRate, int repairTime,
			int rpm, PlantComponent source, PlantComponent destination) {
		super(failureRate, repairTime);
		setRpm(rpm);
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Returns the current RPM value of the pump.
	 * @return the current RPM value of the pump
	 */
	int getRpm() {
		return rpm;
	}
	
	/**
	 * Sets a new value for the RPM of the pump.
	 * @param rpm the new value
	 */
	void setRpm(int rpm) {
		this.rpm = rpm;
	}
	
	PlantComponent getSource() {
		return source;
	}
	
	/*
	 * Uncomment if you need setter for source.
	 * 
	 *	void setSource(PlantComponent source) {
	 *		this.source = source;
	 *	}
	 */
	
	PlantComponent getDestination() {
		return destination;
	}
	
	/*
	 * Uncomment if you need setter for destination.
	 * 
	 *	void setDestination(PlantComponent destination) {
	 *		this.destination = destination;
	 *	}
	 */
	
	/**
	 * Changes the direction in which the water is being pumped.
	 * Internally swaps the source and destination fields.
	 */
	void changeDirection() {
		/*
		 * NEEDS TESTING !!!
		 * I think it will work because I think I am modifying the original references.
		 * It will either always work or never work.
		 */
		PlantComponent swap = source;
		source = destination;
		destination = swap;
	}
	
	void updateState() {
		//Insert implementation
	}
	
	void checkFailure() {
		//Insert implementation 
	}
}

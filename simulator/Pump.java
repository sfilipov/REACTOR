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
		setSource(source);
		setDestination(destination);
	}
	
	Pump(double failureRate, int repairTime,
			int rpm, PlantComponent source, PlantComponent destination) {
		super(failureRate, repairTime);
		setRpm(rpm);
		setSource(source);
		setDestination(destination);
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
	
	void setSource(PlantComponent source) {
		this.source = source;
	}
	
	PlantComponent getDestination() {
		return destination;
	}
	
	void setDestination(PlantComponent destination) {
		this.destination = destination;
	}
	
	void updateState() {
		//Insert implementation
	}
}

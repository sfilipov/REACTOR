package simulator;

public class Turbine extends PlantComponent {
	private int rpm;
	//Add steamInput or whatever you need.
	
	Turbine() {
		super();
		this.rpm = 1000; //Remove after this class is implemented.
	}
	
	@Override
	public void updateState() {
		//
	}
	
	/*
	 * Returns the RPM of the Turbine. Used only in Generator.
	 * @return the value of rpm
	 */
	public int getRpm() {
		return rpm;
	}
}
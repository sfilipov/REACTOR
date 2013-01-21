package simulator;

public class Generator extends PlantComponent {
	private static final long serialVersionUID = -7558087247939142245L;

	private static final int DIVISOR = 123; //Random number to make score look better (not a multiple of 10/100/1000)
	
	private int powerOutput;
	private Turbine turbine;
	
	Generator(Turbine turbine) {
		super(0, 0); //Perfect - never fails
		this.turbine = turbine;
		this.powerOutput = 0;
	}
	
	@Override
	public void updateState() {
		powerOutput = turbine.getRpm() / DIVISOR;
	}
	
	public int getPowerOutput() {
		return powerOutput;
	}
}

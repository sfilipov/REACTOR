package simulator;

public class Turbine extends PlantComponent {
	private static final long serialVersionUID = 1106025692966179166L;

	public final static int DEFAULT_FAILURE_RATE = 10; //1%
	public final static int DEFAULT_REPAIR_TIME = 5;
	private final static int MAX_FAILURE_RATE = 25;
	private static final int MAX_TURBINE_RPM = 3500;
	
	private int rpm;
	private int maxSteamThroughput;
	
	Turbine(int maxSteamThroughput) {
		super(DEFAULT_FAILURE_RATE, DEFAULT_REPAIR_TIME);
		this.maxSteamThroughput = maxSteamThroughput;
		this.rpm = 0;
	}
	
	@Override
	public void updateState() {
		int steamFlowIn = this.getInput().getFlowOut().getRate();
		// Need to create a couple of new doubles mid-calc here to make sure we get a decimal
		double linearMultiplier = 1 - (new Double((this.maxSteamThroughput - steamFlowIn))/new Double(this.maxSteamThroughput)); 
		int newRpm = (int) Math.round(new Double(MAX_TURBINE_RPM) * linearMultiplier);
		this.rpm = (this.isOperational()) ? newRpm : 0;
		
		increaseFailureRate();
	}
	
	/*
	 * Returns the RPM of the Turbine. Used only in Generator.
	 * @return the value of rpm
	 */
	public int getRpm() {
		return rpm;
	}
	
	private void increaseFailureRate() {
		int currentFailureRate = this.getFailureRate();
		if (currentFailureRate < MAX_FAILURE_RATE) {
			this.setFailureRate(++currentFailureRate);
		}
	}
}
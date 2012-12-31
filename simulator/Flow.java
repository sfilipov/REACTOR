package simulator;

public class Flow {
	
	private final static double DEFAULT_RATE = 0d;
	private final static FlowType DEFAULT_TYPE = FlowType.Water;
	
	private double rate;
	private FlowType type;
	
	public Flow() {
		this.rate = DEFAULT_RATE;
		this.type = DEFAULT_TYPE;
	}
	
	public Flow(FlowType type) {
		this.rate = DEFAULT_RATE;
		this.type = type;
	}
	
	public Flow(FlowType type, double rate) {
		this.rate = rate;
		this.type = type;
	}
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public FlowType getType() {
		return type;
	}

	public void setType(FlowType type) {
		this.type = type;
	}

}

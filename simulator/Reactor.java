package simulator;

class Reactor extends PlantComponent {
	private final static int MAX_TEMPERATURE = 2000;
	private final static int MAX_PRESSURE = 500;
	private final static int DEFAULT_TEMPERATURE = 0;
	private final static int DEFAULT_PRESSURE = 0;
	private final static int DEFAULT_STEAM_OUTPUT = 0;
	private int temperature;
	private int pressure;
	private int waterLevel;
	private int reactorIntegrity;
	private ControlRod controlRod;
	private int steamOutput;
	
	Reactor() {
		super();
		this.temperature = DEFAULT_TEMPERATURE;
		this.pressure = DEFAULT_PRESSURE;
		this.waterLevel = 0; //I don't know what it is supposed to be.
		this.reactorIntegrity = 1; //I don't know what it is supposed to be.
		this.controlRod = new ControlRod();
		this.steamOutput = DEFAULT_STEAM_OUTPUT;
	}
	
	void updateState() {
		//Insert implementation
	}
	
	private final class ControlRod {
		private final static int DEFAULT_PERCENTAGE = 100;
		private int percentageLowered;
		
		ControlRod() {
			//Call to the other constructor.
			this(DEFAULT_PERCENTAGE);
		}
		
		ControlRod(int percentageLowered) {
				if (percentageLowered < 0 || percentageLowered > 100) {
					throw new IllegalArgumentException("percentageLowered not in range [0..100].");
				}
				this.percentageLowered = percentageLowered;
		}
		
		//Decide whether to use lower/raise methods or setter.
	}
}

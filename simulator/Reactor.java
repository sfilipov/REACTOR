package simulator;

class Reactor extends PlantComponent {
	private final static int MAX_TEMPERATURE = 2000;
	private final static int MAX_PRESSURE = 500;
	private final static int MAX_HEALTH = 100;
	private final static int DEFAULT_TEMPERATURE = 0;
	private final static int DEFAULT_PRESSURE = 0;
	private final static int DEFAULT_WATER_LEVEL = 1000;
	private final static int DEFAULT_STEAM_OUTPUT = 0;
	private int temperature;
	private int pressure;
	private int waterLevel;
	private int health;
	private ControlRod controlRod;
	private int steamOutput;
	
	Reactor() {
		super();
		this.temperature = DEFAULT_TEMPERATURE;
		this.pressure = DEFAULT_PRESSURE;
		this.waterLevel = DEFAULT_WATER_LEVEL;
		this.health = MAX_HEALTH;
		this.controlRod = new ControlRod();
		this.steamOutput = DEFAULT_STEAM_OUTPUT;
	}
	
	int getTemperature() {
		return temperature;
	}
	
	int getPressure() {
		return pressure;
	}
	
	int getWaterLevel() {
		return waterLevel;
	}
	
	int getHealth() {
		return health;
	}
	
	int getPercentageLowered() {
		return controlRod.getPercentageLowered();
	}
	
	void setPercentageLowered(int percentageLowered) {
		controlRod.setPercentageLowered(percentageLowered);
	}
	
	int getSteamOutput() {
		return steamOutput;
	}
	
	void updateState() {
		//Insert implementation
	}
	
	void checkFailure() {
		//Insert implementation 
	}
	
	private final class ControlRod {
		private final static int DEFAULT_PERCENTAGE = 100;
		private int percentageLowered;
		
		ControlRod() {
			setPercentageLowered(DEFAULT_PERCENTAGE);
		}
		
		int getPercentageLowered() {
			return percentageLowered;
		}
		
		void setPercentageLowered(int percentageLowered) {
			if (percentageLowered < 0 || percentageLowered > 100) {
				throw new IllegalArgumentException("percentageLowered not in range [0..100].");
			}
			this.percentageLowered = percentageLowered;
		}
	}
}

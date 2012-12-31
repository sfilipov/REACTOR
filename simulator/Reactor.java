package simulator;

public class Reactor extends PlantComponent {
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
	
	public Reactor() {
		super();
		this.controlRod = new ControlRod();
		this.health = MAX_HEALTH;
		this.temperature = DEFAULT_TEMPERATURE;
		this.pressure = DEFAULT_PRESSURE;
		this.waterLevel = DEFAULT_WATER_LEVEL;
		this.steamOutput = DEFAULT_STEAM_OUTPUT;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getPercentageLowered() {
		return controlRod.getPercentageLowered();
	}
	
	public void setPercentageLowered(int percentageLowered) {
		controlRod.setPercentageLowered(percentageLowered);
	}
	
	public int getSteamOutput() {
		return steamOutput;
	}
	
	public void updateState() {
		//Insert implementation
	}
	
	public void checkFailure() {
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
				throw new IllegalArgumentException("Reactor: ControlRod: " +
								"percentageLowered not in range [0..100].");
			}
			this.percentageLowered = percentageLowered;
		}
	}
}

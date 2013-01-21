package simulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plant implements Serializable {
	/**
	 * serialVersionUID: http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 4799981348038802742L;
	
	private final static int MAX_STEAM_FLOW_RATE = 400;
	private final static int MAX_WATER_FLOW_RATE_PER_PUMP = 400;
	
	private String operatorName;
	private boolean gameOver;
	private int timeStepsUsed;
	private int score;
	private List<Repair> beingRepaired;
	private boolean isPaused;
	private List<HighScore> highScores;
	private List<PlantComponent> plantComponents;
	private List<PlantComponent> failedComponents;
	private Reactor reactor;
	private List<Valve> valves;
	private List<ConnectorPipe> connectorPipes;
	private Condenser condenser;
	private List<Pump> pumps;
	private Turbine turbine;
	private Generator generator;
	
	/**
	 * This is the default constructor that is used 
	 * when there is no saved game (i.e. new game)
	 */
	public Plant() {
		this.operatorName = null;
		this.gameOver = false;
		this.timeStepsUsed = 0;
		this.score = 0;
		this.beingRepaired = new ArrayList<Repair>();
		this.isPaused = false;
		this.highScores = new ArrayList<HighScore>();
		this.plantComponents = new ArrayList<PlantComponent>();
		this.failedComponents = new ArrayList<PlantComponent>();
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public int getScore() {
		return score;
	}

	public void calcScore() {
		int powerOutput = getGenerator().getPowerOutput();
		this.score += powerOutput * (this.timeStepsUsed + 10);
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public int getMaxSteamFlowRate()
	{
		return MAX_STEAM_FLOW_RATE;
	}

	public int getMaxWaterFlowRatePerPump()
	{
		return MAX_WATER_FLOW_RATE_PER_PUMP;
	}

	public Reactor getReactor() {
		if (this.reactor != null) {
			return reactor;
		} else {
			for (PlantComponent pc : this.plantComponents) {
				if (pc instanceof Reactor) {
					this.reactor = (Reactor) pc;
					return this.reactor;
				}
			}
			return null; // No reactor found?!
		}
	}
	
	public List<Valve> getValves() {
		ArrayList<Valve> valvesList = new ArrayList<Valve>();
		if (this.valves != null) {
			return this.valves;
		} else {
			for (PlantComponent pc : this.plantComponents) {
				if (pc instanceof Valve) valvesList.add((Valve) pc);
			}
			this.valves = valvesList;
			return this.valves;
		}
	}

	public List<ConnectorPipe> getConnectorPipes()
	{
		ArrayList<ConnectorPipe> connectorPipes = new ArrayList<ConnectorPipe>();
		if (this.connectorPipes != null) {
			return this.connectorPipes;
		} else {
			for (PlantComponent pc : this.plantComponents) {
				if (pc instanceof ConnectorPipe) connectorPipes.add((ConnectorPipe) pc);
			}
			this.connectorPipes = connectorPipes;
			return this.connectorPipes;
		}
	}

	public Condenser getCondenser()
	{
		if (this.condenser != null) {
			return this.condenser;
		} else {
			for (PlantComponent pc : this.plantComponents) {
				if (pc instanceof Condenser) {
					this.condenser = (Condenser) pc;
					return this.condenser;
				}
			}
			return null; // No condenser found?!
		}
	}
	
	public List<Pump> getPumps() {
		ArrayList<Pump> pumpsList = new ArrayList<Pump>();
		if (this.pumps != null) {
			return this.pumps;
		}
		for (PlantComponent pc : this.plantComponents) {
			if (pc instanceof Pump) pumpsList.add((Pump) pc);
		}
		this.pumps = pumpsList;
		return pumpsList;
	}
	
	public Turbine getTurbine() {
		Turbine turbine = null;
		if (this.turbine != null) {
			return this.turbine;
		}
		for (PlantComponent pc : this.plantComponents) {
			if (pc instanceof Turbine) turbine = (Turbine) pc;
		}
		this.turbine = turbine;
		return turbine;
	}
	
	public Generator getGenerator() {
		Generator generator = null;
		if (this.generator != null) {
			return this.generator;
		}
		for (PlantComponent pc : this.plantComponents) {
			if (pc instanceof Generator) generator = (Generator) pc;
		}
		this.generator = generator;
		return generator;
	}

	public List<HighScore> getHighScores() {
		if(this.highScores.size() > 20) {
			this.highScores = this.highScores.subList(0, 20); //Trims the high scores list to only the first 10 elements
		}
		return this.highScores;
	}
	
	public void setHighScores(List<HighScore> highScores) {
		this.highScores = highScores;
	}
	
	public int getTimeStepsUsed() {
		return timeStepsUsed;
	}
	
	public void updateTimeStepsUsed(int n) {
		if (n > 0) timeStepsUsed += n;
	}

	public List<Repair> getBeingRepaired() {
		return beingRepaired;
	}

	public List<PlantComponent> getPlantComponents() {
		return plantComponents;
	}
	
	public void setPlantComponents(List<PlantComponent> plantComponents) {
		this.plantComponents = plantComponents;
	}
	
	public List<PlantComponent> getFailedComponents() {
		return failedComponents;
	}
	
	/**
	 * Adds failedComponent to failedComponents List, as long as it is
	 * not already in the list.
	 * 
	 * @param failedComponent
	 */
	public void addFailedComponent(PlantComponent failedComponent) {
		if (!this.failedComponents.contains(failedComponent)) {
			this.failedComponents.add(failedComponent);
		}
	}	
	
	public void gameOver() {
		this.gameOver = true;
	}
	
	public boolean isGameOver() {
		return this.gameOver;
	}
}

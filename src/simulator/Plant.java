package simulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Plant class is holder of all plant components, the score, time steps passed, the name of the
 * player, etc. It represents the "model" of the MVC model of the game.
 */
public class Plant implements Serializable {
	/**
	 * serialVersionUID: http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 4799981348038802742L;
	
	private final static int MAX_STEAM_FLOW_RATE = 500; // Out of the reactor.
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
	
	/**
	 * Returns the name of the operator (player).
	 * @return the name of the operator (player).
	 */
	public String getOperatorName() {
		return operatorName;
	}
	
	/**
	 * Sets the name of the operator (player).
	 * @param operatorName the name of the operator (player).
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * Returns the current score.
	 * @return the current score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Updates the score.
	 * 
	 * Calculates the score based on the power output of the generator
	 * and the number of time steps passed since the start of the game.
	 */
	public void calcScore() {
		int powerOutput = getGenerator().getPowerOutput();
		this.score += powerOutput * (this.timeStepsUsed + 10);
	}

	/**
	 * Checks if the game is paused.
	 * 
	 * Currently not used as the game is turn based. Makes creating a real time game easier.
	 * @return true if the game is paused.
 	 */
	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Sets the paused state of the game.
	 * 
	 * Currently not used as the game is turn based. Makes creating a real time game easier.
	 * @param isPaused the new value for isPaused.
	 */
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

	/**
	 * Returns the reactor object of the plant.
	 * @return the reactor object of the plant.
	 */
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
	
	/**
	 * Returns a list of valves in the plant.
	 * @return a list of valves in the plant.
	 */
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

	/**
	 * Returns a list of all connector pipes in the plant.
	 * @return a list of all connector pipes in the plant.
	 */
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

	/**
	 * Returns the condenser of the plant.
	 * @return the condenser of the plant.
	 */
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
	
	/**
	 * Returns a list of all pumps in the plant.
	 * @return a list of all pumps in the plant.
	 */
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
	
	/**
	 * Returns the turbine of the plant.
	 * @return the turbine of the plant.
	 */
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
	
	/**
	 * Returns the generator of the plant.
	 * @return the generator of the plant.
	 */
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

	/**
	 * Returns a list of all highscores (maximum 20).
	 * @return a list of all highscores (maximum 20).
	 */
	public List<HighScore> getHighScores() {
		if(this.highScores.size() > 20) {
			this.highScores = this.highScores.subList(0, 20); //Trims the high scores list to only the first 10 elements
		}
		return this.highScores;
	}
	
	/**
	 * Sets the list of highscores.
	 * 
	 * Used when loading the highscores from a file.
	 * 
	 * @param highScores the list of highscores.
	 */
	public void setHighScores(List<HighScore> highScores) {
		this.highScores = highScores;
	}
	
	/**
	 * Returns the number of steps passed since the start of the game.
	 * @return the number of steps passed since the start of the game.
	 */
	public int getTimeStepsUsed() {
		return timeStepsUsed;
	}
	
	/**
	 * Adds "n" steps to timeStepsUsed.
	 * @param n the number of steps to be added.
	 */
	public void updateTimeStepsUsed(int n) {
		if (n > 0) timeStepsUsed += n;
	}

	/**
	 * Returns a list of all components that are being repaired.
	 * @return a list of all components that are being repaired.
	 */
	public List<Repair> getBeingRepaired() {
		return beingRepaired;
	}

	/**
	 * Returns a list of all plant components.
	 * @return a list of all plant components.
	 */
	public List<PlantComponent> getPlantComponents() {
		return plantComponents;
	}
	
	/**
	 * Sets the list of all plant components.
	 * @param plantComponents the list of all plant components.
	 */
	public void setPlantComponents(List<PlantComponent> plantComponents) {
		this.plantComponents = plantComponents;
	}
	
	/**
	 * Returns all failed (non-operational) components (including those that are being repaired).
	 * @return all failed (non-operational) components (including those that are being repaired).
	 */
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
	
	/**
	 * Sets the game over state to true.
	 */
	public void gameOver() {
		this.gameOver = true;
	}
	
	/**
	 * Checks  if the game is over (if gameOver is true).
	 * @return true if the game is over, false otherwise.
	 */
	public boolean isGameOver() {
		return this.gameOver;
	}
}

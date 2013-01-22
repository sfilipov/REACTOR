package simulator;

import java.util.ArrayList;
import java.util.List;
import pcomponents.*;


/**
 * UIData holds all data necessary for a UI (both text and graphical based). It provides methods 
 * for updating all fields with the latest information and getting that information.
 */
public class UIData {
	private Plant plant;
	
	private String  operatorName;
	private int     score;
	private boolean gameOver;
	
	private int reactorHealth;
	private int reactorTemperature;
	private int reactorMaxTemperature;
	private int reactorPressure;
	private int reactorMaxPressure;
	private int reactorWaterVolume;
	private int reactorMinSafeWaterVolume;
	
	private int condenserHealth;
	private int condenserTemperature;
	private int condenserMaxTemperature;
	private int condenserPressure;
	private int condenserMaxPressure;
	private int condenserWaterVolume;
	
	private int turbineRpm;
	private boolean turbineFunctional;
	private int powerOutput;
	
	private int controlRods;
	
	private List<Valve> valves;
	private List<Pump>  pumps;
	
	private List<PlantComponent> brokenOnStep;
	
	UIData(Plant plant) {
		this.plant = plant;
		
		this.operatorName = plant.getOperatorName();
		this.score        = plant.getScore();
		this.gameOver     = plant.isGameOver();
		
		this.turbineRpm			= plant.getTurbine().getRpm();
		this.turbineFunctional	= plant.getTurbine().isOperational();
		this.powerOutput  		= plant.getGenerator().getPowerOutput();
		
		this.reactorHealth             = plant.getReactor().getHealth();
		this.reactorTemperature        = plant.getReactor().getTemperature();
		this.reactorMaxTemperature     = plant.getReactor().getMaxTemperature();
		this.reactorPressure           = plant.getReactor().getPressure();
		this.reactorMaxPressure        = plant.getReactor().getMaxPressure();
		this.reactorWaterVolume        = plant.getReactor().getWaterVolume();
		this.reactorMinSafeWaterVolume = plant.getReactor().getMinSafeWaterVolume();
		
		this.condenserHealth         = plant.getCondenser().getHealth();
	    this.condenserTemperature    = plant.getCondenser().getTemperature();
	    this.condenserMaxTemperature = plant.getCondenser().getMaxTemperature();
	    this.condenserPressure       = plant.getCondenser().getPressure();
	    this.condenserMaxPressure    = plant.getCondenser().getMaxPressure();
	    this.condenserWaterVolume    = plant.getCondenser().getWaterVolume();
	    
	    this.controlRods = plant.getReactor().getPercentageLowered();
	    
	    this.valves = plant.getValves();
	    this.pumps  = plant.getPumps();
	    
	    this.brokenOnStep = new ArrayList<PlantComponent>();
	}
	
	/**
	 * Updates all necessary information for its internal fields from Plant.
	 */
	public void updateUIData() {
		this.operatorName = plant.getOperatorName();
		this.score        = plant.getScore();
		this.gameOver     = plant.isGameOver();
		
		this.turbineRpm			= plant.getTurbine().getRpm();
		this.turbineFunctional	= plant.getTurbine().isOperational();
		this.powerOutput  		= plant.getGenerator().getPowerOutput();
		
		this.reactorHealth             = plant.getReactor().getHealth();
		this.reactorTemperature        = plant.getReactor().getTemperature();
		this.reactorMaxTemperature     = plant.getReactor().getMaxTemperature();
		this.reactorPressure           = plant.getReactor().getPressure();
		this.reactorMaxPressure        = plant.getReactor().getMaxPressure();
		this.reactorWaterVolume        = plant.getReactor().getWaterVolume();
		this.reactorMinSafeWaterVolume = plant.getReactor().getMinSafeWaterVolume();
		
		this.condenserHealth         = plant.getCondenser().getHealth();
	    this.condenserTemperature    = plant.getCondenser().getTemperature();
	    this.condenserMaxTemperature = plant.getCondenser().getMaxTemperature();
	    this.condenserPressure       = plant.getCondenser().getPressure();
	    this.condenserMaxPressure    = plant.getCondenser().getMaxPressure();
	    this.condenserWaterVolume    = plant.getCondenser().getWaterVolume();
	    
	    this.controlRods = plant.getReactor().getPercentageLowered();
	    
	    this.valves = plant.getValves();
	    this.pumps  = plant.getPumps();
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int getReactorHealth() {
		return reactorHealth;
	}
	
	public int getReactorTemperature() {
		return reactorTemperature;
	}
	
	public int getReactorMaxTemperature() {
		return reactorMaxTemperature;
	}
	
	public int getReactorPressure() {
		return reactorPressure;
	}
	
	public int getReactorMaxPressure() {
		return reactorMaxPressure;
	}
	
	public int getReactorWaterVolume() {
		return reactorWaterVolume;
	}
	
	public int getReactorMinSafeWaterVolume() {
		return reactorMinSafeWaterVolume;
	}
	
	
	public int getCondenserHealth() {
		return condenserHealth;
	}
	
	public int getCondenserTemperature() {
		return condenserTemperature;
	}
	
	public int getCondenserMaxTemperature() {
		return condenserMaxTemperature;
	}
	
	public int getCondenserPressure() {
		return condenserPressure;
	}
	
	public int getCondenserMaxPressure() {
		return condenserMaxPressure;
	}
	
	public int getCondenserWaterVolume() {
		return condenserWaterVolume;
	}
	
	public int getControlRodsPercentage() {
		return controlRods;
	}
	
	public List<Valve> getValves() {
		return valves;
	}
	
	public List<Pump> getPumps() {
		return pumps;
	}

	public int getTurbineRpm() {
		return turbineRpm;
	}

	public int getPowerOutput() {
		return powerOutput;
	}

	public boolean isTurbineFunctional() {
		return turbineFunctional;
	}
	
	/**
	 * Adds a broken component to list brokenOnStep.
	 * 
	 * @param broken a broken component.
	 */
	public void addBrokenOnStep(PlantComponent broken) {
		brokenOnStep.add(broken);
	}
	
	/**
	 * Resets the list of brokenOnStep components.
	 */
	public void resetBrokenOnStep() {
		brokenOnStep = new ArrayList<PlantComponent>();
	}
	
	/**
	 * Returns a list of all broken components since last call of step command.
	 * 
	 * Used to print information about components that broke when step was called.
	 * 
	 * @return a list of all broken components since last call of step command.
	 */
	public List<PlantComponent> getBrokenOnStep() {
		return brokenOnStep;
	}
}

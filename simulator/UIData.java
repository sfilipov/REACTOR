package simulator;

import java.util.List;

public class UIData {	
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
	
	
	UIData(Plant plant) {		
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
	
	//EXPERIMENTAL
//	public boolean isOpenValve(int valveID) throws ValveNotFoundException {
//		for (Valve v : valves) {
//			if (v.getID() == valveID) {
//				return v.isOpen();
//			}
//		}
//		throw new ValveNotFoundException();
//	}
	
	//EXPERIMENTAL
//	public boolean isOnPump(int pumpID) throws PumpNotFoundException {
//		for (Pump p : pumps) {
//			if (p.getID() == pumpID) {
//				return p.isOn();
//			}
//		}
//		throw new PumpNotFoundException();
//	}

}

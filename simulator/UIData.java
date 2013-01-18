package simulator;

import java.util.List;
import java.util.ArrayList;

public class UIData {
	private PlantPresenter presenter;
	
	private String operatorName;
	private int    score;
	
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
	
	private int controlRods;
	
	private List<Valve> valves;
	private List<Pump>  pumps;
	
	
	UIData(PlantPresenter presenter) {
		this.operatorName = presenter.getOperatorName();
		this.score        = presenter.getScore();
		
		this.reactorHealth             = presenter.getReactorHealth();
		this.reactorTemperature        = presenter.getReactorTemperature();
		this.reactorMaxTemperature     = presenter.getReactorMaxTemperature();
		this.reactorPressure           = presenter.getReactorPressure();
		this.reactorMaxPressure        = presenter.getReactorMaxPressure();
		this.reactorWaterVolume        = presenter.getReactorWaterVolume();
		this.reactorMinSafeWaterVolume = presenter.getReactorMinSafeWaterVolume();
		
		this.condenserHealth         = presenter.getCondenserHealth();
	    this.condenserTemperature    = presenter.getCondenserTemperature();
	    this.condenserMaxTemperature = presenter.getCondenserMaxTemperature();
	    this.condenserPressure       = presenter.getCondenserPressure();
	    this.condenserMaxPressure    = presenter.getCondenserMaxPressure();
	    this.condenserWaterVolume    = presenter.getCondenserWaterVolume();
	    
	    this.controlRods = presenter.getControlRodsPercentage();
	    
	    this.valves = presenter.getValves();
	    this.pumps  = presenter.getPumps();
	}
}

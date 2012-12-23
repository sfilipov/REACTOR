package simulator;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plant {
	private String operatorName;
	private int timeStepsUsed;
	private int score;
	private List<Repair> beingRepaired;
	private boolean isPaused;
	private List<Integer> highScores;
	private List<PlantComponent> plantComponents;
	private List<PlantComponent> failedComponents;
	
	/**
	 * This is the default constructor that is used 
	 * when there is no saved game (i.e. new installation)
	 */
	public Plant(String operatorName) {
		this.operatorName = operatorName;
		this.timeStepsUsed = 0;
		this.score = 0;
		this.beingRepaired = new ArrayList<Repair>();
		this.isPaused = false;
		this.highScores = new ArrayList<Integer>(20);
		this.plantComponents = new ArrayList<PlantComponent>();
		//Insert all plant components into plantComponents
		this.failedComponents = new ArrayList<PlantComponent>();
	}
	
}

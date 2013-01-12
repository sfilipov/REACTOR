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
	 * when there is no saved game (i.e. new game)
	 */
	public Plant() {
		this.operatorName = "";
		this.timeStepsUsed = 0;
		this.score = 0;
		this.beingRepaired = new ArrayList<Repair>();
		this.isPaused = false;
		this.highScores = new ArrayList<Integer>(20);
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

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public List<Integer> getHighScores() {
		return highScores;
	}

	public void setHighScores(List<Integer> highScores) {
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
	public void checkFailures() {
		List<PlantComponent> temp;
		temp = new ArrayList<PlantComponent>();
		for (plantComponents p : PlantComponent)
		{
			if(p.checkFailures() = true)
				temp.add(p);
		}
		int NUMBER_FAILED = temp.size();
		Random random = new Random();
		int selection = random.nextInt(NUMBER_FAILED);
		String failed = temp.get(selection).getName();
		// code to specify element of <plantComponents>, toggle its operational state, remove it from <plantComponents> and add it to <failedComponents>
	}

	
	
}

package simulator;

public class Repair {

	private PlantComponent plantComponent;
	private int timeStepsRemaining;
	
	Repair (PlantComponent componentToRepair, int repairTime) {
		this.plantComponent = componentToRepair;
		this.timeStepsRemaining = repairTime;
	}
	
	/**
	 * Decrements the time remaining until the component is repaired 
	 */
	public void decTimeStepsRemaining() {
		timeStepsRemaining--;
	}
	
	/**
	 * Returns timeStepsRemaining
	 */
	public int getTimeStepsRemaining() {
		return timeStepsRemaining;
	}
	
	/**
	 * Returns plantComponent
	 */
	public PlantComponent getPlantComponent() {
		return plantComponent;
	}
	
}
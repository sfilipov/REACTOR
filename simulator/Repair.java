package simulator;

import java.io.Serializable;

public class Repair implements Serializable {
	private static final long serialVersionUID = 1819944421888642516L;
	
	private PlantComponent plantComponent;
	private int timeStepsRemaining;
	
	Repair (PlantComponent componentToRepair) {
		this.plantComponent = componentToRepair;
		this.timeStepsRemaining = componentToRepair.getRepairTime();
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
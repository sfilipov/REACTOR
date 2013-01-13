package simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlantPresenter {

	public Plant plant;
	
	public PlantPresenter(Plant plant)
	{
		this.plant = plant;		
	}
	
	public void saveState(String filename){
		// write serialised Plant to file?
	}
	
	public void loadState(String filename) {
		// read plant object from file
	}
	
	public void updatePlant() {
		List<PlantComponent> plantComponents = plant.getPlantComponents();
		for (PlantComponent plantComponent : plantComponents) {
			plantComponent.updateState();
		}


		// Go through all components and call updateState()
		// This will do things in Reactor and Condenser objects etc.
	}
	
	public void startRepairingComponent(PlantComponent toBeRepairedComponent) { // name of component to be repaired
		List<PlantComponent> failedComponents = plant.getFailedComponents(); 
		List<Repair> beingRepairedComponents = plant.getBeingRepaired();
		if (failedComponents.contains(toBeRepairedComponent)) {
			Repair repair = new Repair(toBeRepairedComponent);
			beingRepairedComponents.add(repair);
		}
	}
	
	public void checkForRepairedComponents() {
		List<Repair> beingRepaired = plant.getBeingRepaired();
		List<PlantComponent> failedComponents = plant.getFailedComponents();
		for (Repair repair : beingRepaired) {
			repair.decTimeStepsRemaining();
			int timeStepsRemaining = repair.getTimeStepsRemaining();
			if(timeStepsRemaining <= 0) {
				failedComponents.remove(repair.getPlantComponent());
				beingRepaired.remove(repair);
			}
		}
	}
	
	public void checkFailures() {
		List<PlantComponent> plantComponents  = plant.getPlantComponents();
		List<PlantComponent> failingComponents = new ArrayList<PlantComponent>();
		int faults = 0;
		
		//Checks all components if they randomly fail
		for (PlantComponent component : plantComponents) {
			if (component.checkFailure()) {
				failingComponents.add(component);
				faults++;
			}
		}
		
		//Picks only one of all randomly failing components.
		if(faults > 0) {
			Random random = new Random();
			int selection = random.nextInt(faults);
			plant.addFailedComponent(failingComponents.get(selection));	
		}
	}
	
	public void togglePaused() {
		
	}
	
	public void calcSystemFlow() {
		// Complex shit!
	}
	
}
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
	
	/* ----------------		Methods	for UI to call	----------------
	 * There is a method for each command that can be given by the
	 * user. 
	 */
	
	public void newGame() {
		ReactorUtils utils = new ReactorUtils();
		this.plant = utils.createNewPlant();
	}
	
	public void saveGame(String filename){
		// write serialised Plant to file?
	}
	
	public void loadGame(String filename) {
		// read plant object from file
	}
	
	public void togglePaused() {
		this.plant.setPaused(!this.plant.isPaused());
	}
	
	
	/**
	 * Returns the highscores list.
	 * @return list of highscores.
	 */
	public List<Integer> getHighScores() {
		return plant.getHighScores();
	}
	
	/**
	 * Advance the game by a number of time steps.
	 * 
	 * @param numSteps number of timesteps to advance the game by.
	 */
	public void step(int numSteps) {
		for (int i = 0; i < numSteps; i++) {
			checkFailures();
			updateBeingRepaired();
			updateFlow();
			updatePlant();
		}
	}
	
	// ----------------		Internal methods	----------------
	

	// Go through all components and call updateState()
	// This will do things in Reactor and Condenser objects etc.
	private void updatePlant() {
		List<PlantComponent> plantComponents = plant.getPlantComponents();
		for (PlantComponent plantComponent : plantComponents) {
			plantComponent.updateState();
		}
	}
	
	private void startRepairing(PlantComponent toBeRepairedComponent) {
		List<PlantComponent> failedComponents = plant.getFailedComponents(); 
		List<Repair> beingRepairedComponents = plant.getBeingRepaired();
		if (failedComponents.contains(toBeRepairedComponent)) {
			Repair repair = new Repair(toBeRepairedComponent);
			beingRepairedComponents.add(repair);
		}
	}
	
	private void updateBeingRepaired() {
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
	
	private void checkFailures() {
		List<PlantComponent> plantComponents  = plant.getPlantComponents();
		List<PlantComponent> failingComponents = new ArrayList<PlantComponent>();
		int faults = 0;
		
		//Checks all components if they randomly fail
		for (PlantComponent component : plantComponents) {
			if (component.checkFailure()) {
				if (component instanceof Reactor || component instanceof Condenser) {
					//GAME OVER
				}
				else {
					failingComponents.add(component);
					faults++;
				}
			}
		}
		
		//Picks only one of all randomly failing components.
		if(faults > 0) {
			Random random = new Random();
			int selection = random.nextInt(faults);
			plant.addFailedComponent(failingComponents.get(selection));	
		}
	}
	
	private void updateFlow() {
		// Calc total flow from steam vol diff.
		// For all closed valves, inc blocked input & output for adjoining connectorpipes.
		// 
	}
	
	private void calcConnectorFlowOut(ConnectorPipe connector) {
		ArrayList<PlantComponent> inputs = connector.getInputs();
		int totalFlow = 0;
		int numOutputs = connector.numOutputs();
		for (PlantComponent input : inputs) {
			totalFlow += input.getFlowOut().getRate();
		}
		totalFlow = (numOutputs != 0) ? totalFlow / numOutputs : 0; // average the flow across all active outputs.
		connector.getFlowOut().setRate(totalFlow);
	}
	
}
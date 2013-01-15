package simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlantPresenter {

	public Plant plant; 
	
	public PlantPresenter()
	{
		//Nothing
	}
	
	/* ----------------		Methods	for UI to call	----------------
	 * There is a method for each command that can be given by the
	 * user. 
	 */
	
	public void newGame(String operatorName) {
		ReactorUtils utils = new ReactorUtils();
		this.plant = utils.createNewPlant(operatorName);
	}
	
	//Returns true if saving a game was successful.
	public boolean saveGame(){
		// write serialised Plant to file?
		return false;
	}
	
	//Returns true if loading a game was successful.
	public boolean loadGame() {
		// read plant object from file
		return false;
	}
	
	public void togglePaused() {
		this.plant.setPaused(!this.plant.isPaused());
	}
	
	
	/**
	 * Returns the highscores list.
	 * @return list of highscores.
	 */
	public List<HighScore> getHighScores() {
		return plant.getHighScores();
	}
	
	/**
	 * Returns true if command was successful, false if a valve with that ID was not found
	 * @return true if command was successful, false if a valve with that ID was not found
	 */
	public boolean setValve(int valveID, boolean open) {
		List<Valve> valves = plant.getValves();
		for (Valve valve : valves) {
			if (valveID == valve.getID()) {
				valve.setOpen(open);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if command was successful, false if a pump with that ID was not found
	 * @return true if command was successful, false if a pump with that ID was not found
	 */
	public boolean setPump(int pumpID, boolean on) {
		List<Pump> pumps = plant.getPumps();
		for (Pump pump : pumps) {
			if (pumpID == pump.getID()) {
				pump.setOn(on);
				return true;
			}
		}
		return false;
	}
	
	public void setControlRods(int percentageLowered) {
		if(percentageLowered >= 0 && percentageLowered <= 100) {
			Reactor reactor = plant.getReactor();
			reactor.setPercentageLowered(percentageLowered);
		}
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
		List<Valve> valves = this.plant.getValves();
		for (Valve v : valves) {
			calcFlowAtValve(v);
		}
		// calc flow @ each valve.
		// reset and update blocked paths at connectors
		// divide flows by numOutput splits
		// calc ConnectorFlowOuts 
		// calc flow of water & steam out & into reactor/condenser. 
	}
	
	private void calcFlowAtValve(Valve valve) {
		if (!valve.isOpen()) {
			valve.getFlowOut().setRate(0);
			// block!
		}
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
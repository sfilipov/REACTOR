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
		blockFromValves();
		blockFromConnectorPipes();
		
		// from closed valves.. track back to nearest connector pipe & block input.
		// reset and update blocked paths at connectors
		// divide flows by numOutput splits
		// calc ConnectorFlowOuts 
		// calc flow of water & steam out & into reactor/condenser. 
	}
	
	private void blockFromValves() {
		List<Valve> valves = this.plant.getValves();
		for (Valve v : valves) {
			if (!v.isOpen()) blockPreceedingConnectorPipe(v);
		}
	}
	
	private void blockFromConnectorPipes() {
		boolean changed = true;
		while (changed) {
			changed = false;
			// iterate through all connector pipes and check if they're rawly blocked ap
			// & propagate that blockage through the system. :)
		}
	}
	
	/**
	 * Returns true if all outputs of a ConnectorPipe are blocked.
	 * @return true if all outputs of a ConnectorPipe are blocked.
	 */
	private boolean isConnectorBlocking(ConnectorPipe cp) {
		for(Boolean blocked : cp.getOutputsMap().values()) {	
			if (!blocked) return false;
		}
		return true;
	}

	/**
	 * Traces back to the first occurring connector pipe and blocks the path out leading 
	 * to blockedComponent.
	 * We assume checks have been made to ensure blockedComponent is actually blocked.
	 * 
	 * @param blockedComponent component to start from.
	 */
	private void blockPreceedingConnectorPipe(PlantComponent blockedComponent) {
		PlantComponent currentComponent = blockedComponent.getInput();
		PlantComponent prevComponent = blockedComponent;
		boolean doneBlocking = false;
		while (!doneBlocking) {
			if (currentComponent instanceof ConnectorPipe) {
				((ConnectorPipe) currentComponent).setComponentBlocked(prevComponent);
				doneBlocking = true;
			} else if (currentComponent instanceof Reactor) {
				currentComponent.getFlowOut().setRate(0);
			} else {
				prevComponent = currentComponent;
				currentComponent = currentComponent.getInput();
			}
		}
	}
	
	private void blockPreceedingFromConnectorPipe(ConnectorPipe blockedConnector) {
		List<PlantComponent> multipleInputs = ((ConnectorPipe) blockedConnector).getInputs();
	}
	
	
	/*private void calcFlowAtValve(Valve valve) {
		if (!valve.isOpen()) {
			valve.getFlowOut().setRate(0);
			// block!
		} else {
			PlantComponent forwardPressurePoint = findPressurePointForwards(valve);
			PlantComponent backwardPressurePoint = findPressurePointBackwards(valve);
			
			
			int forwardSteamVolume = getSteamVolume(forwardPressurePoint);
			int backwardSteamVolume = getSteamVolume(forwardPressurePoint);
			int steamDifference = backwardSteamVolume - forwardSteamVolume;
			int flow = (steamDifference >= valve.getMaxSteamFlow() * numSteamValves()) ? valve.getMaxSteamFlow() : 1 ;
			
		}
	}*/
	
	private int getSteamVolume(PlantComponent pressurisedComponent) {
		// Ew! Refactor common stuff out of Reactor and Condenser!!
		if (pressurisedComponent instanceof Reactor) 
			return ((Reactor) pressurisedComponent).getSteamVolume();
		if (pressurisedComponent instanceof Condenser) 
			return ((Condenser) pressurisedComponent).getSteamVolume();
		throw new IllegalArgumentException("Not a Pressurised Component!");
	}
	
	/**
	 * Track forwards along the outputs of components from the starting point, 
	 * startComponent, iteratively until a pressurised component is found.
	 * 
	 * @param startComponent Component to start from.
	 * @return first pressurised component found.
	 */
	private PlantComponent findPressurePointForwards(PlantComponent startComponent) {
		PlantComponent currentComponent = startComponent;
		while(!currentComponent.isPressurised()) {
			if (currentComponent instanceof ConnectorPipe) {
				List<PlantComponent> outputs = ((ConnectorPipe) currentComponent).getOutputs();
				if (outputs.size() > 1) {
					/* Shit! We've got some serious shit going on here D:
					 * 
					 * For us, this is no problem since we don't have any crazy
					 * pipe networks but if this algorithm is to be generalised for
					 * any networks support for recursively tracing all paths out of 
					 * a connector pipe will be required.
					 */
					return null;
				} else {
					currentComponent = outputs.get(0);
				}
			} else {
				currentComponent = currentComponent.getOutput();
			}
		}
		return currentComponent;
	}
	
	/**
	 * Track backwards along the inputs of components from the starting point, 
	 * startComponent, iteratively until a pressurised component is found.
	 * 
	 * @param startComponent Component to start from.
	 * @return first pressurised component found.
	 */
	private PlantComponent findPressurePointBackwards(PlantComponent startComponent) {
		PlantComponent currentComponent = startComponent;
		while(!currentComponent.isPressurised()) {
			if (currentComponent instanceof ConnectorPipe) {
				List<PlantComponent> inputs = ((ConnectorPipe) currentComponent).getInputs();
				if (inputs.size() > 1) {
					/* Shit! We've got some serious shit going on here D:
					 * 
					 * For us, this is no problem since we don't have any crazy
					 * pipe networks but if this algorithm is to be generalised for
					 * any networks support for recursively tracing all paths into 
					 * a connector pipe will be required.
					 */
					return null;
				} else {
					currentComponent = inputs.get(0);
				}
			} else {
				currentComponent = currentComponent.getInput();
			}
		}
		return currentComponent;
	}
	
	/**
	 * Update the Flow out of a connector to reflect it's inputs and outputs.
	 * @param connector the connector to update.
	 */
	/*
	private void calcConnectorFlowOut(ConnectorPipe connector) {
		ArrayList<PlantComponent> inputs = connector.getInputs();
		int totalFlow = 0;
		int numOutputs = connector.numOutputs();
		for (PlantComponent input : inputs) {
			totalFlow += input.getFlowOut().getRate();
		}
		totalFlow = (numOutputs != 0) ? totalFlow / numOutputs : 0; // average the flow across all active outputs.
		connector.getFlowOut().setRate(totalFlow);
	}*/
	
}

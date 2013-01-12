package simulator;

public class PlantPresenter {

	private Plant model;
	
	public PlantPresenter(Plant model)
	{
		this.model = model;
	}
	
	public void saveState(String filename){
		// write serialised Plant to file?
	}
	
	public void loadState(String filename) {
		// read plant object from file
	}
	
	public void updatePlant() {
		// Go through all components and call updateState()
		// This will do things in Reactor and Condenser objects etc.
	}
	
	public void repairComponent() {
		// 
	}
	
	public void checkFailures() {
		
	}
	
	public void togglePaused() {
		
	}
	
	public void calcSystemFlow() {
		// Complex shit!
	}
	
}

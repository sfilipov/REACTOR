package simulator;

class Valve extends PlantComponent {
	private final static double VALVE_FAILURE_RATE = 0.0;
	private final static int VALVE_REPAIR_RATE = 0;
	private PlantComponentPair connectedTo; 
	private boolean open;
	
	Valve (PlantComponent first, PlantComponent second) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(first, second);
		this.open = true;
	}
	
	Valve (PlantComponent first, PlantComponent second, boolean open) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(first, second);
		this.open = open;
	}
	
	/**
	 * @return the first plant component that the valve is connected to
	 */
	PlantComponent getFirstComponent() {
		return connectedTo.getFirstComponent();
	}

	/**
	 * @return the second plant component that the valve is connected to
	 */	
	PlantComponent getSecondComponent() {
		return connectedTo.getSecondComponent();
	}
	
	boolean isOpen() {
		return open;
	}
	
	void open() {
		open = true;
	}
	
	void close() {
		open = false;
	}
	
	void updateState() {
		//Insert implementation
	}
	
	
	/*
	 * This class is used internally to represent the pair of components
	 * that the particular valve is connected to. Once created, it only
	 * provides methods to access the fields but not to change them.
	 */
	private final class PlantComponentPair {
		/*
		 * I assume a Valve doesn't change it's connections once it is created.
		 * If you latter need to make the pair first, second changeable
		 * create a setter.
		 */
		private PlantComponent first, second;
		
		PlantComponentPair(PlantComponent first, PlantComponent second) {
			this.first = first;
			this.second = second;
		}
		
		PlantComponent getFirstComponent() {
			return first;
		}
		
		PlantComponent getSecondComponent() {
			return second;
		}
	}
}

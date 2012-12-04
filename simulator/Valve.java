package simulator;

class Valve extends PlantComponent {
	private final static double VALVE_FAILURE_RATE = 0.0;
	private final static int VALVE_REPAIR_RATE = 0;
	private PlantComponentPair connectedTo; 
	private boolean open;
	
	/**
	 * Creates a new valve that is connected to the specified
	 * two plant components.
	 * @param first the first plant component
	 * @param second the second plant component
	 */
	Valve (PlantComponent first, PlantComponent second) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(first, second);
		this.open = true;
	}
	
	/**
	 * Creates a new valve that is connected to the specified
	 * two plant components and sets it's position (open/closed).
	 * @param first the first plant component
	 * @param second the second plant component
	 * @param open true if the valve is open
	 */
	Valve (PlantComponent first, PlantComponent second, boolean open) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(first, second);
		this.open = open;
	}
	
	/**
	 * Returns the first plant component that the valve is connected to.
	 * @return the first plant component that the valve is connected to
	 */
	PlantComponent getFirstComponent() {
		return connectedTo.getFirstComponent();
	}

	/**
	 * Returns the second plant component that the valve is connected to.
	 * @return the second plant component that the valve is connected to
	 */	
	PlantComponent getSecondComponent() {
		return connectedTo.getSecondComponent();
	}
	
	/**
	 * Returns true if the valve is open.
	 * @return true if the valve is open
	 */
	boolean isOpen() {
		return open;
	}
	
	/**
	 * Sets the position of the valve.
	 * @param open true if the valve is open
	 */
	void setOpen(boolean open) {
		this.open = open;
	}
	
	void updateState() {
		//Insert implementation
	}
	
	void checkFailure() {
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
		 * If you latter need to make the pair changeable then
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

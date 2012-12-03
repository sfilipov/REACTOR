package simulator;

class Valve extends PlantComponent {
	private final static double VALVE_FAILURE_RATE = 0.0;
	private final static int VALVE_REPAIR_RATE = 0;
	private PlantComponentPair connectedTo; 
	private boolean open;
	
	Valve (PlantComponent a, PlantComponent b) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(a, b);
		this.open = true;
	}
	
	Valve (PlantComponent a, PlantComponent b, boolean open) {
		super(VALVE_FAILURE_RATE, VALVE_REPAIR_RATE);
		this.connectedTo = new PlantComponentPair(a, b);
		this.open = open;
	}
	
	PlantComponent getA() {
		return connectedTo.getA();
	}
	
	PlantComponent getB() {
		return connectedTo.getB();
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
		//I assume a Valve doesn't change it's connections once it is created.
		//If you latter need to make the pair a, b changeable, just create a setter.
		private PlantComponent a, b;
		
		PlantComponentPair(PlantComponent a, PlantComponent b) {
			this.a = a;
			this.b = b;
		}
		
		PlantComponent getA() {
			return a;
		}
		
		PlantComponent getB() {
			return b;
		}
	}
}

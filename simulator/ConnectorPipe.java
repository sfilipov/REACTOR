package simulator;

import java.util.ArrayList;

public class ConnectorPipe extends PlantComponent {
	/* Since these objects will effectively be invisible to the user,
	 * they should never fail!
	 */
	private final static double FAILURE_RATE = 0.0d;
	private final static int REPAIR_TIME = 0;
	
	private ArrayList<PlantComponent> additionalInputs;
	private ArrayList<PlantComponent> additionalOutputs;
	private int numBlockedOutputs;
	private int numBlockedInputs;
	
	public ConnectorPipe() {
		super(FAILURE_RATE, REPAIR_TIME);
		this.additionalInputs = new ArrayList<PlantComponent>();
		this.additionalOutputs = new ArrayList<PlantComponent>();
		this.numBlockedOutputs = 0;
		this.numBlockedInputs = 0;
	}
	
	/**
	 * Adds an input to this ConnectorPipe. Use this method 
	 * instead of setInput().
	 * @param input Component to connect as an input to this
	 * 			    ConnectorPipe.
	 */
	public void addInput(PlantComponent input) {
		if (this.getInput() != null) {
			this.setInput(input);
		} else {
			this.additionalInputs.add(input);
		}
	}
	
	/**
	 * Adds an output to this ConnectorPipe. Use this method
	 * instead of setOutput().
	 * @param output Component to connect to the output of this
	 * 				 ConnectorPipe.
	 */
	public void addOutput(PlantComponent output) {
		if (this.getOutput() != null) {
			this.setOutput(output);
		} else {
			this.additionalOutputs.add(output);
		}
	}
	
	/**
	 * Returns an ArrayList of all PlantComponents connected as inputs 
	 * to this ConnectorPipe. Use this method instead of getInput().
	 * Note: This method will return ALL outputs, even if the object is null.
	 * @return an ArrayList of all PlantComponents connected as inputs 
	 * 		   to this ConnectorPipe.
	 */
	public ArrayList<PlantComponent> getInputs() {
		ArrayList<PlantComponent> inputs = new ArrayList<PlantComponent>(additionalInputs);
		inputs.add(this.getInput());
		return inputs;
	}

	/**
	 * Returns an ArrayList of all PlantComponents connected to the output 
	 * of this ConnectorPipe. Use this method instead of getOutput().
	 * Note: This method will return ALL outputs, even if the object is null.
	 * @return an ArrayList of all PlantComponents connected to the output 
	 * 		   of this ConnectorPipe.
	 */
	public ArrayList<PlantComponent> getOutputs() {
		ArrayList<PlantComponent> outputs = new ArrayList<PlantComponent>(additionalOutputs);
		outputs.add(this.getOutput());
		return outputs;
	}

	/**
	 * @return the number of paths out of this ConnectorPipe that are not 
	 * 		   blocked by closed valves.
	 */
	public int numOutputs() {
		return numActualOutputs() - numBlockedOutputs;
	}
	
	/**
	 * @return number of actual components connected to the outputs of this
	 * 		   ConnectorPipe.
	 */
	private int numActualOutputs() {
		int numOuts = 0;
		if (this.getOutput() != null) numOuts = 1;
		for (PlantComponent pc : this.additionalOutputs) {
			if (pc != null) numOuts += 1;
		}
		return numOuts;
	}
	
	/**
	 * @return the number of paths into this ConnectorPipe that are not  
	 * 		   blocked by closed valves.
	 */
	public int numInputs() {
		return numActualInputs() - numBlockedInputs;
	}

	/**
	 * @return number of actual components connected to the inputs of this
	 * 		   ConnectorPipe.
	 */
	private int numActualInputs() {
		int numIns = 0;
		if (this.getInput() != null) numIns = 1;
		for (PlantComponent pc : this.additionalInputs) {
			if (pc != null) numIns += 1;
		}
		return numIns;
	} 
	
	/**
	 * Increments the number of blocked inputs to this ConnectorPipe.
	 * @throws IllegalStateException if the number of blocked inputs
	 * 								 exceeds the number of actual inputs. 
	 */
	public void incBlockedInputs() throws IllegalStateException {
		if (++numBlockedInputs > numActualInputs()) {
			throw new IllegalStateException("ConnectorPipe: Number of " + 
											"blocked inputs should " +
											"never exceed the number " +
											"of actual inputs!");
		}
	}
	
	/**
	 * Increments the number of blocked outputs from this ConnectorPipe.
	 * @throws IllegalStateException if the number of blocked outputs
	 * 								 exceeds the number of actual outputs. 
	 */
	public void incBlockedOutputs() throws IllegalStateException {
		if (++numBlockedOutputs > numActualOutputs()) {
			throw new IllegalStateException("ConnectorPipe: Number of " + 
											"blocked outputs should " +
											"never exceed the number " +
											"of actual outputs!");
		}
	}
	
	@Override
	public void updateState() {
		// TODO sum active inputs & distribute to outputs.
	}

	@Override
	public boolean checkFailure() {
		return false; // a ConnectorPipe will never fail.
	}

}

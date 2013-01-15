package simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

/**
 * ConnectorPipe class only really extends PlantComponent for consistency
 * in the model.
 * It is effectively completely invisible to the user and is used to connect
 * up multiple paths between components in the system.
 * 
 * @author WillFrew
 *
 */
public class ConnectorPipe extends PlantComponent {
	/* Since these objects will effectively be invisible to the user,
	 * they should never fail!
	 */
	private final static double FAILURE_RATE = 0.0d;
	private final static int REPAIR_TIME = 0;
	
	private List<PlantComponent> inputs;
	private Map<PlantComponent, Boolean> outputs; // Boolean is true if that path is blocked.
	
	public ConnectorPipe() {
		super(FAILURE_RATE, REPAIR_TIME);
		this.inputs = new ArrayList<PlantComponent>();
		this.outputs = new HashMap<PlantComponent, Boolean>();
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
			this.inputs.add(input);
		}
	}
	
	/**
	 * Adds an output to this ConnectorPipe. Use this method
	 * instead of setOutput().
	 * @param output Component to connect to the output of this
	 * 				 ConnectorPipe.
	 */
	public void addOutput(PlantComponent output) {
		this.outputs.put(output, false);
	}
	
	/**
	 * Returns an ArrayList of all PlantComponents connected as inputs 
	 * to this ConnectorPipe. Use this method instead of getInput().
	 * Note: This method will return ALL outputs, even if the object is null.
	 * @return an ArrayList of all PlantComponents connected as inputs 
	 * 		   to this ConnectorPipe.
	 */
	public ArrayList<PlantComponent> getInputs() {
		ArrayList<PlantComponent> inputs = new ArrayList<PlantComponent>(this.inputs);
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
		return new ArrayList<PlantComponent>(this.outputs.keySet());
	}
	
	/**
	 * Returns the outputs map of component & blocked boolean.
	 * @return the outputs map of component & blocked boolean.
	 */
	public Map<PlantComponent, Boolean> getOutputsMap() {
		return this.outputs;
	}
	
	public void setComponentBlocked(PlantComponent blockedComponent) {
		if (this.outputs.containsKey(blockedComponent)) {
			this.outputs.put(blockedComponent, true);
		} else {
			throw new IllegalArgumentException("Attempt to block an output with a reference to a "
											  +"component that is not an output to this connector "
											  +"pipe.");
		}
	}
	
	/**
	 * Reset's all the outputs to not-blocked.
	 */
	public void resetState() {
		for (Entry<PlantComponent, Boolean> entry : outputs.entrySet()) {
			entry.setValue(false);
		}
	}
	
	@Override
	public void updateState() {
		// Do nothing.
	}

	@Override
	public boolean checkFailure() {
		return false; //Never breaks.
	}

}

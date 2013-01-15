package simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * ReactorUtils contains utilities for the game such as:
 * 		- Return a fresh Plant Object, fully initialised.
 * @author WillFrew
 *
 */
public abstract class ReactorUtils
{
	private static Plant newPlant;
	private static Reactor reactor;
	private static Condenser condenser;
	//private static Turbine turbine; 
	private static Valve steamValve1;
	private static Valve steamValve2;
	private static Valve waterValve1;
	private static Valve waterValve2;
	private static Pump pump1;
	private static Pump pump2;
	private static ConnectorPipe connectorPipe1;
	private static ConnectorPipe connectorPipe2;
	private static ConnectorPipe connectorPipe3;
	private static ConnectorPipe connectorPipe4;
	
	/**
	 * Instantiates and returns a new Plant object.
	 * @return new Plant object.
	 */
	public static Plant createNewPlant() {
		newPlant = new Plant();
		instantiateComponents();
		setupComponentReferences();
		newPlant.setPlantComponents(makeComponentList());
		return newPlant;
	}

	private static void instantiateComponents() {
		reactor = new Reactor();
		condenser = new Condenser();
		//turbine = new Turbine(); 
		steamValve1 = new Valve(1, FlowType.Steam);
		steamValve2 = new Valve(2, FlowType.Steam);
		waterValve1 = new Valve(3, FlowType.Water);
		waterValve2 = new Valve(4, FlowType.Water);
		pump1 = new Pump(1);
		pump2 = new Pump(2);
		connectorPipe1 = new ConnectorPipe();
		connectorPipe2 = new ConnectorPipe();
		connectorPipe3 = new ConnectorPipe();
		connectorPipe4 = new ConnectorPipe();
	}
	
	private static void setupComponentReferences() {
		setupInputOutputReferences(reactor, connectorPipe1);
		setupInputOutputReferences(connectorPipe1, steamValve1);
		setupInputOutputReferences(connectorPipe1, steamValve2);
		
		setupInputOutputReferences(steamValve1, connectorPipe2);
		setupInputOutputReferences(steamValve2, connectorPipe2);
		/* Replace previous 2 lines with the following when Turbine class
		 * is finished.
		 * 
		 * setupInputOutput(steamValve1, turbine);
		 * setupInputOutput(turbine, connectorPipe2);
		 * setupInputOutput(steamValve2, connectorPipe2);
		 */
		setupInputOutputReferences(connectorPipe2, condenser);
		setupInputOutputReferences(condenser, connectorPipe3);
		setupInputOutputReferences(connectorPipe3, pump1);
		setupInputOutputReferences(connectorPipe3, pump2);
		setupInputOutputReferences(pump1, waterValve1);
		setupInputOutputReferences(pump2, waterValve2);
		setupInputOutputReferences(waterValve1, connectorPipe4);
		setupInputOutputReferences(waterValve2, connectorPipe4);
		setupInputOutputReferences(connectorPipe4, reactor);
	}
	
	private static List<PlantComponent> makeComponentList()
	{
		List<PlantComponent> plantComponents = new ArrayList<PlantComponent>();
		plantComponents.add(reactor);
		plantComponents.add(condenser);
		//plantComponents.add(turbine); 
		plantComponents.add(steamValve1);
		plantComponents.add(steamValve2);
		plantComponents.add(waterValve1);
		plantComponents.add(waterValve2);
		plantComponents.add(pump1);
		plantComponents.add(pump2);
		plantComponents.add(connectorPipe1);
		plantComponents.add(connectorPipe2);
		plantComponents.add(connectorPipe3);
		plantComponents.add(connectorPipe4);
		return plantComponents;
	}
	
	/**
	 * Takes two PlantComponents and creates the input/output references between them.
	 * 
	 * @param from PlantComponent that flow is coming out of.
	 * @param to PlantComponent that flow is moving into.
	 */
	private static void setupInputOutputReferences(PlantComponent from, PlantComponent to) {
		if (from instanceof ConnectorPipe) {
			((ConnectorPipe) from).addOutput(to);
		} else {
			from.setOutput(to);
		}
		if (to instanceof ConnectorPipe) {
			((ConnectorPipe) to).addInput(from);
		} else {
			to.setInput(from);
		}
	}
	
}

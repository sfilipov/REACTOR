package simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * ReactorUtils contains utilities for the game such as:
 * 		- Return a fresh Plant Object, fully initialised.
 * @author WillFrew
 *
 */
public class ReactorUtils
{
	private Plant newPlant;
	private Reactor reactor;
	private Condenser condenser;
	private Turbine turbine;
	private Generator generator;
	private Valve steamValve1;
	private Valve steamValve2;
	private Valve steamValve3;
	private Pump pump1;
	private Pump pump2;
	private ConnectorPipe connectorPipe1;
	private ConnectorPipe connectorPipe2;
	private ConnectorPipe connectorPipe3;
	private ConnectorPipe connectorPipe4;
	private ConnectorPipe connectorPipe5;
	private ConnectorPipe connectorPipe6;
	
	public Plant createNewPlant() {
		newPlant = new Plant();
		instantiateComponents();
		setupComponentReferences();
		newPlant.setPlantComponents(makeComponentList());
		return newPlant;
	}
	
	public Plant createNewAlternativePlant() {
		newPlant = new Plant();
		instantiateComponents();
		setupComponentReferencesTesting();
		newPlant.setPlantComponents(makeComponentListTesting());
		return newPlant;
	}

	private void instantiateComponents() {
		reactor = new Reactor();
		condenser = new Condenser();
		turbine = new Turbine(newPlant.getMaxSteamFlowRate());
		generator = new Generator(turbine);
		steamValve1 = new Valve(1, FlowType.Steam);
		steamValve2 = new Valve(2, FlowType.Steam);
		steamValve3 = new Valve(3, FlowType.Steam);
		pump1 = new Pump(1);
		pump2 = new Pump(2);
		connectorPipe1 = new ConnectorPipe();
		connectorPipe2 = new ConnectorPipe();
		connectorPipe3 = new ConnectorPipe();
		connectorPipe4 = new ConnectorPipe();
		connectorPipe5 = new ConnectorPipe();
		connectorPipe6 = new ConnectorPipe();
	}
	
	private void setupComponentReferences() {
		setupInputOutputReferences(reactor, connectorPipe1);
		setupInputOutputReferences(connectorPipe1, steamValve1);
		setupInputOutputReferences(connectorPipe1, steamValve2);
		setupInputOutputReferences(steamValve1, turbine);
		setupInputOutputReferences(turbine, connectorPipe2);
		setupInputOutputReferences(steamValve2, connectorPipe2); 
		setupInputOutputReferences(connectorPipe2, condenser);
		setupInputOutputReferences(condenser, connectorPipe3);
		setupInputOutputReferences(connectorPipe3, pump1);
		setupInputOutputReferences(connectorPipe3, pump2);
		setupInputOutputReferences(pump1, connectorPipe4);
		setupInputOutputReferences(pump2, connectorPipe4);
		setupInputOutputReferences(connectorPipe4, reactor);
	}
	
	private void setupComponentReferencesTesting() {
		setupInputOutputReferences(reactor, connectorPipe1);
		setupInputOutputReferences(connectorPipe1, steamValve1);
		setupInputOutputReferences(connectorPipe1, connectorPipe5);
		setupInputOutputReferences(steamValve1, turbine);
		
		setupInputOutputReferences(connectorPipe5, steamValve2);
		setupInputOutputReferences(connectorPipe5, steamValve3);
		setupInputOutputReferences(steamValve2, connectorPipe6);
		setupInputOutputReferences(steamValve3, connectorPipe6);
		
		setupInputOutputReferences(turbine, connectorPipe2);
		setupInputOutputReferences(connectorPipe6, connectorPipe2);
		
		setupInputOutputReferences(connectorPipe2, condenser);
		setupInputOutputReferences(condenser, connectorPipe3);
		setupInputOutputReferences(connectorPipe3, pump1);
		setupInputOutputReferences(connectorPipe3, pump2);
		setupInputOutputReferences(pump1, connectorPipe4);
		setupInputOutputReferences(pump2, connectorPipe4);
		setupInputOutputReferences(connectorPipe4, reactor);
	}
	
	private List<PlantComponent> makeComponentList()
	{
		List<PlantComponent> plantComponents = new ArrayList<PlantComponent>();
		plantComponents.add(reactor);
		plantComponents.add(condenser);
		plantComponents.add(turbine); 
		plantComponents.add(generator);
		plantComponents.add(steamValve1);
		plantComponents.add(steamValve2);
		plantComponents.add(pump1);
		plantComponents.add(pump2);
		plantComponents.add(connectorPipe1);
		plantComponents.add(connectorPipe2);
		plantComponents.add(connectorPipe3);
		plantComponents.add(connectorPipe4);
		return plantComponents;
	}
	
	private List<PlantComponent> makeComponentListTesting()
	{
		List<PlantComponent> plantComponents = new ArrayList<PlantComponent>();
		plantComponents.add(reactor);
		plantComponents.add(condenser);
		plantComponents.add(turbine); 
		plantComponents.add(generator);
		plantComponents.add(steamValve1);
		plantComponents.add(steamValve2);
		plantComponents.add(steamValve3);
		plantComponents.add(pump1);
		plantComponents.add(pump2);
		plantComponents.add(connectorPipe1);
		plantComponents.add(connectorPipe2);
		plantComponents.add(connectorPipe3);
		plantComponents.add(connectorPipe4);
		plantComponents.add(connectorPipe5);
		plantComponents.add(connectorPipe6);
		return plantComponents;
	}
	
	/**
	 * Takes two PlantComponents and creates the input/output references between them.
	 * 
	 * @param from PlantComponent that flow is coming out of.
	 * @param to PlantComponent that flow is moving into.
	 */
	private void setupInputOutputReferences(PlantComponent from, PlantComponent to) {
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

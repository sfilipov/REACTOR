package test;

import simulator.*;
import model.*;
import pcomponents.*;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;

import org.junit.Test;

public class PlantControllerTest {
	
	private TextUI view;
	private PlantController presenter; 
	private ReactorUtils utils;
	private Plant plant;

	@Before
	public void setUp() {
		GameInit game = new GameInit();
		utils = new ReactorUtils();
		presenter = new PlantController(utils);
		view = new TextUI(presenter);
		presenter.newGame("Bob");
		plant = presenter.getPlant();
	}
	
	@Test
	public void testLoadGame() {
		
		File f = new File("save.ser");
		if(f.exists()) {
			f.delete();
		}
		
		// no saved game file so should return false
		assertEquals("Result", false, presenter.loadGame());
		
		presenter.saveGame();
		
		assertEquals("Result", true, presenter.loadGame());
		
	}
	
	@Test
	public void testOperatorName() {
		
		assertEquals("Result", "Bob", this.plant.getOperatorName());
		
	}
	
	@Test
	public void testTogglePaused() {
		
		boolean isPaused = plant.isPaused();
		
		presenter.togglePaused();
		
		assertEquals("Result", !isPaused, plant.isPaused());
		
	}
	
	@Test
	public void testAddHighScore() {
		
		plant.setHighScores(new ArrayList<HighScore>());
		
		HighScore newHighScore = new HighScore("Bob", 2000);
		
		presenter.addHighScore(newHighScore);
		
		List<HighScore> highScores = plant.getHighScores();
		
		//expected
		List<HighScore> expected = new ArrayList<HighScore>();
		expected.add(newHighScore);
		
		assertEquals("Result", expected, highScores);
		
	}
	
	@Test
	public void testSetValve() {
		
		presenter.setValve(1, false);
		
		List<Valve> valves = plant.getValves();
		
		assertEquals("Result", false, valves.get(0).isOpen());
		
	}
	
	@Test
	public void testSetPumpOnOff() {
		
		presenter.setPumpOnOff(1, false);
		
		List<Pump> pumps = plant.getPumps();
		
		assertEquals("Result", false, pumps.get(0).isOn());
		
	}
	
	@Test
	public void testSetPumpRpm() {
		
		presenter.setPumpRpm(1, 127);
		
		List<Pump> pumps = plant.getPumps();
		
		assertEquals("Result", 127, pumps.get(0).getRpm());
		
	}
	
	@Test
	public void testSetControlRods() {
		
		presenter.setControlRods(57);
		
		assertEquals("Result", 57, plant.getReactor().getPercentageLowered());
		
	}
	
	@Test
	public void testRepairTurbine() {
		
		assertEquals("Result", false, presenter.repairTurbine()); // the turbine hasn't failed so repairTurbine() should return false
		
		// break the turbine
		List<PlantComponent> failedComponents = plant.getFailedComponents();
		failedComponents.add(plant.getTurbine());
		
		assertEquals("Result", true, presenter.repairTurbine()); // the turbine is now broken so repairTurbine() should return true
		
		assertEquals("Result", false, presenter.repairTurbine()); // the turbine is already being repaired so repairTurbine() should return false again
		
	}
	
	@Test
	public void testRepairPump() {
		
		assertEquals("Result", false, presenter.repairPump(1));
		
		// break the pump
		List<PlantComponent> failedComponents = plant.getFailedComponents();
		failedComponents.add(plant.getPumps().get(0));
		
		assertEquals("Result", true, presenter.repairPump(1));
		
		assertEquals("Result", false, presenter.repairPump(1));
		
	}

}

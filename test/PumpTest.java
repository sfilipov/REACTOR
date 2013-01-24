package test;

import simulator.*;
import model.*;
import pcomponents.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PumpTest {

	private TextUI view;
	private PlantController presenter; 
	private ReactorUtils utils;
	private Plant plant;
	
	private Pump pump;
	private int pumpID = 4;

	@Before
	public void setUp() {
		GameInit game = new GameInit();
		utils = new ReactorUtils();
		presenter = new PlantController(utils);
		view = new TextUI(presenter);
		plant = presenter.getPlant();
		
		this.pump = new Pump(pumpID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRpm() {
		
		this.pump.setRpm(261777); // argument above MAX_RPM
		
	}
	
	@Test
	public void testSetGetRpm() {
		
		pump.setRpm(450);
		
		assertEquals("Result", 450, this.pump.getRpm());
		
	}
	
	@Test
	public void testGetID() {
		
		assertEquals("Result", this.pumpID, pump.getID());
		
	}
	
	@Test
	public void testSetOn() {
		
		this.pump.setOn(false);
		
		assertEquals("Result", false, this.pump.isOn());
		
		this.pump.setOn(true);
		
		assertEquals("Result", true, this.pump.isOn());
		
	}

}

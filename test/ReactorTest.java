package test;

import simulator.*;
import model.*;
import pcomponents.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReactorTest {

	private TextUI view;
	private PlantController presenter; 
	private ReactorUtils utils;
	private Plant plant;
	private Reactor reactor;

	@Before
	public void setUp() {
		GameInit game = new GameInit();
		utils = new ReactorUtils();
		presenter = new PlantController(utils);
		view = new TextUI(presenter);
		plant = presenter.getPlant();
		reactor = plant.getReactor();
	}
	
	@Test
	public void testUpdateWaterVolume() {
		
		int waterVolume = reactor.getWaterVolume();
		
		reactor.updateWaterVolume(300);
		
		assertEquals("Result", waterVolume+300, reactor.getWaterVolume());
		
	}
	
	@Test
	public void testUpdateSteamVolume() {
		
		int steamVolume = reactor.getSteamVolume();
		
		reactor.updateSteamVolume(300);
		
		assertEquals("Result", steamVolume+300, reactor.getSteamVolume());
		
	}
	
	@Test
	public void testUpdatePressure() {
		
		int expectedPressure = (int) Math.round(new Double(reactor.getSteamVolume()) * 0.15);
		
		assertEquals("Result", expectedPressure, reactor.getPressure());
		
	}

}

package test;

import simulator.*;
import model.*;
import pcomponents.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HighScoreTest {

	private TextUI view;
	private PlantController presenter; 
	private ReactorUtils utils;
	private Plant plant;
	
	private HighScore highScore;

	@Before
	public void setUp() {
		GameInit game = new GameInit();
		utils = new ReactorUtils();
		presenter = new PlantController(utils);
		view = new TextUI(presenter);
		plant = presenter.getPlant();
		
		this.highScore = new HighScore("Bob", 1233);
	}
	
	@Test
	public void testGetName() {
		
		assertEquals("Result", "Bob", this.highScore.getName());
		
	}
	
	@Test
	public void testGetHighScore() {
		
		assertEquals("Result", 1233, this.highScore.getHighScore());
		
	}
	
	@Test
	public void testCompareTo() {
		
		HighScore anotherHighScore = new HighScore("George", 540);
		
		assertEquals("Result", (1233 - 540), this.highScore.compareTo(anotherHighScore));
		
	}

}

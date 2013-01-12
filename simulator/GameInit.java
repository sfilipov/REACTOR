package simulator;

/**
 * GameInit class bootstraps the entire game.
 * 
 * It creates the Plant object and populates it
 * with the system model defined in the JSON config file.
 * It also instantiates the GUI and gives it a reference to 
 * the presenter. 
 * The presenter is given a reference to the Plant (Model)
 * 
 * @author WillFrew
 *
 */
public class GameInit {
	
	private Plant model;
	private Interface view;
	// private PlantPresenter presenter; 
	
	public GameInit() {
		model = new Plant("");
		view = new Interface();
	}
	
	public static void main(String[] args) {
		GameInit game = new GameInit();
	}
	
}
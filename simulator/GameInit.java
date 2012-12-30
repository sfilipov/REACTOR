package simulator;

/**
 * GameInit class bootstraps the entire game.
 * 
 * It creates the Plant object and populates it
 * with the system model defined in the JSON config file.
 * It also instantiates the GUI and gives it a reference to 
 * the presenter, which in turn is given a reference to the
 * Plant (Model)
 * 
 * @author WillFrew
 *
 */
public class GameInit {
	
	public GameInit() {
		// Init Game! :D
	}
	
	public static void main(String[] args) {
		GameInit game = new GameInit();
	}
	
}
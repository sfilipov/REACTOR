package simulator;

public class Highscore {
	private String name;
	private int highscore;
	
	Highscore(String name, int highscore) {
		this.name = name;
		this.highscore = highscore;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHighscore() {
		return highscore;
	}
}

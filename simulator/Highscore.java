package simulator;

public class HighScore implements Comparable<HighScore> {
	private String name;
	private int highScore;
	
	HighScore(String name, int highScore) {
		this.name = name;
		this.highScore = highScore;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	@Override
	public int compareTo(HighScore h) {
		return this.highScore - h.highScore;
	}
}

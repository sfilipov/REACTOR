package simulator;

import java.io.Serializable;

public class HighScore implements Comparable<HighScore>, Serializable {
	/**
	 * serialVersionUID: http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 3713046493639284784L;
	
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

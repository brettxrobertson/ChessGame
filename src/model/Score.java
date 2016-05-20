package model;

/**
 * Handles score
 * 
 * @author Brett Robertson (s3437164)
 *
 */
public class Score {
	private int score = 0;

	public void setScore(int updateValue) {
		score = updateValue;
	}

	public int updateScore(int updateValue) {
		score += updateValue;
		return score;
	}

	public void resetScore() {
		score = 0;
	}

	public int getScore() {
		// TODO return the actual score value
		return score;
	}

}

package model;

import java.io.Serializable;

/**
 * Handles id, name and score of player
 * 
 * @author Brett Robertson (s3437164)
 *
 */
public class Player implements Serializable{

	private int id;
	private String name;
	private int score;
	private boolean usedUndo = false;

	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		score = 0;
	}

	/**
	 * @return the score
	 */

	public int getScore() {
		return score;
	}

	public void setScore(int addedScore) {
		this.score = addedScore;
	}

	public void addScore(int addedScore) {
		this.score += addedScore;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the usedUndo
	 */
	public boolean isUsedUndo() {
		return usedUndo;
	}

	/**
	 * @param usedUndo the usedUndo to set
	 */
	public void setUsedUndo(boolean usedUndo) {
		this.usedUndo = usedUndo;
	}
}

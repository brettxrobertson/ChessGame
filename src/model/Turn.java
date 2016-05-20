package model;

import java.io.Serializable;

/**
 * Handles currentTurn of the player
 * 
 * @author Brett Robertson (s3437164)
 *
 */
public class Turn implements Serializable{
	private int currentTurn = 0;

	/**
	 * @return the currentTurn
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}

	private int totalTurns = 10;

	public Turn() {

	}

	public Turn(int totalTurns) {
		this.totalTurns = totalTurns;
	}

	/**
	 * @param totalTurns
	 *            the totalTurns to set
	 */
	public void setTotalTurns(int totalTurns) {
		this.totalTurns = totalTurns;
		this.currentTurn = this.totalTurns;
	}

	public int incrementTurn() {
		currentTurn++;
		return currentTurn;
	}

	public int decrementTurn() {
		currentTurn--;
		return currentTurn;
	}

	public void resetTurn() {
		currentTurn = 0;
	}

	public boolean isEndGame() {
		if (currentTurn < totalTurns) {
			return false;
		}

		return true;
	}
}

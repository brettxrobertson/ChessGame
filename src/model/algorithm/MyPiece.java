package model.algorithm;

import model.Constants;

public abstract class MyPiece
{
	protected String name;
	protected int type;
	protected int point = Constants.POINT_PIECE;
	protected int playerId;
	
	/**
	 * Constructor - initialize a piece
	 * @param type - type of piece
	 * @param initial - initial of type of piece (used for name)
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID 
	 */
	public MyPiece(int type, String initial, int id, int playerId) {
		this.name = initial + (id == 1 ? "A" : "B") + playerId;
		this.type = type;
		this.playerId = playerId;
	}
	
	/**
	 * Get the next position of the piece give current x and y positions
	 * @param direction - direction in which to move the piece (select from 0, 1, 2 and 3)
	 * @param x - x position
	 * @param y - y position
	 * @return int[] - new x and y position at index 0 and 1 respectively
	 */
	public int[] getNextPosition(int direction, int x, int y) {
		return new int[]{-1, -1};
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
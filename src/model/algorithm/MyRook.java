package model.algorithm;

import model.Constants;

public class MyRook extends MyPiece
{
	private final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
	
	/**
	 * 
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID
	 */
	public MyRook(int id, int playerId) {
		super(Constants.PIECE_TYPE_ROOK, "R", id, playerId);
	}
	
	@Override
	public int[] getNextPosition(int direction, int x, int y) {
		
		int new_x = x, new_y = y;
		switch (direction) {
			case TOP:
				new_y = y - 1;
				break;
	
			case RIGHT:
				new_x = x + 1;
				break;
			
			case BOTTOM:
				new_y = y + 1;
				break;
				
			case LEFT:
				new_x = x - 1;
				break;
		}
		return new int[]{new_x, new_y};
	}
}
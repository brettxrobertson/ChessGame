package model.algorithm;

import model.Constants;

public class MyBishop extends MyPiece 
{
	private final int TOP_RIGHT = 0, BOTTOM_RIGHT = 1, BOTTOM_LEFT = 2, TOP_LEFT = 3;
	
	/**
	 * 
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID
	 */
	public MyBishop(int id, int playerId) {
		super(Constants.PIECE_TYPE_BISHOP, "B", id, playerId);
	}
	
	@Override
	public int[] getNextPosition(int direction, int x, int y) {
		
		int new_x = x, new_y = y;
		switch (direction) {
			case TOP_RIGHT:
				new_x = x + 1;
				new_y = y - 1;
				break;
	
			case BOTTOM_RIGHT:
				new_x = x + 1;
				new_y = y + 1;
				break;
			
			case BOTTOM_LEFT:
				new_x = x - 1;
				new_y = y + 1;
				break;
				
			case TOP_LEFT:
				new_x = x - 1;
				new_y = y - 1;
				break;
		}
		return new int[]{new_x, new_y};
	}
}
package model.pieces;


import java.util.ArrayList;

import model.Constants;

public class King extends AbstractPiece {
	
	private final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
	private final int TOP_RIGHT = 4, BOTTOM_RIGHT = 5, BOTTOM_LEFT = 6, TOP_LEFT = 7;
	
	/**
	 * 
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID
	 */
	public King(int id, int playerId) {
		super(Constants.PIECE_TYPE_KING, "K", id, playerId, false);
		this.setTotalDirections(8);
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

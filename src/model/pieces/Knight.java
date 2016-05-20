package model.pieces;


import model.Constants;

public class Knight extends AbstractPiece {

	private final int TOP_LEFT = 0, TOP_RIGHT = 1;
	private final int RIGHT_TOP = 2, RIGHT_BOTTOM = 3;
	private final int BOTTOM__RIGHT = 4, BOTTOM_LEFT = 5;
	private final int LEFT_BOTTOM = 6, LEFT_TOP = 7;
	
	/**
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID
	 */
	public Knight(int id, int playerId) {
		super(Constants.PIECE_TYPE_KNIGHT, "K", id, playerId, false);
		this.setTotalDirections(8);
	}
	
	@Override
	public int[] getNextPosition(int direction, int x, int y) {
		
		int new_x = x, new_y = y;
		switch (direction) {
			case TOP_LEFT:
				new_x = x - 1;
				new_y = y - 2;
				break;
			case TOP_RIGHT:
				new_x = x + 1;
				new_y = y - 2;
				break;
	
			case RIGHT_TOP:
				new_y = y - 1;
				new_x = x + 2;
				break;
			case RIGHT_BOTTOM:
				new_y = y + 1;
				new_x = x + 2;
				break;
			
			case BOTTOM__RIGHT:
				new_x = x + 1;
				new_y = y + 2;
				break;
			case BOTTOM_LEFT:
				new_x = x - 1;
				new_y = y + 2;
				break;
				
			case LEFT_BOTTOM:
				new_y = y + 1;
				new_x = x - 2;
				break;
			case LEFT_TOP:
				new_y = y - 1;
				new_x = x - 2;
				break;
		}
		return new int[]{new_x, new_y};
	}

}

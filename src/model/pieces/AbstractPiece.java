package model.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Constants;

public abstract class AbstractPiece implements Serializable {

	final private int POINTS_VALUE = Constants.POINT_PIECE;
	private int playerId;
	private int type;
	private String name;
	//private int point = Constants.POINT_PIECE;
	private int totalDirections = 0;
	private boolean isMoveContinuous;

	/**
	 * Constructor - initialize a piece
	 * 
	 * @param type - type of piece
	 * @param initial - initial of type of piece (used for name)
	 * @param id - 1 or 2 for left and right side pieces
	 * @param playerId - player ID
	 * @param isMoveContinuous - true if piece moves continuously (like Rook), false otherwise (like Knight)
	 */
	public AbstractPiece(int type, String initial, int id, int playerId,
			boolean isMoveContinuous) {
		this.name = initial + (id == 1 ? "A" : "B") + playerId;
		this.type = type;
		this.setPlayerId(playerId);
		this.isMoveContinuous = isMoveContinuous;
	}

	/**
	 * @return id - int the id
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type - piece type
	 * @pre.condition type >= 0
	 * @post.condition 
	 * @invariant type != null
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the pointsValue
	 */
	public int getPointsValue() {
		return POINTS_VALUE;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name - string
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return total directions of piece
	 */
	public int getTotalDirections() {
		return totalDirections;
	}

	/**
	 * @param totalDirections
	 *            - total directions of piece
	 */
	public void setTotalDirections(int totalDirections) {
		this.totalDirections = totalDirections;
	}

	/**
	 * Returns true if piece moves continuously (Rook) or only one step (Knight)
	 * 
	 * @return boolean
	 */
	public boolean isMoveContinuous() {
		return isMoveContinuous;
	}

	/**
	 * Get the next position of the piece give current x and y positions
	 * 
	 * @param direction
	 *            - direction in which to move the piece (select from 0, 1, 2
	 *            and 3)
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 * @return int[] - new x and y position at index 0 and 1 respectively
	 * 
	 * @pre.condition x < tiles[0].length()
	 * <br> y < tiles[1].length()
	 * 
	 */
	public abstract int[] getNextPosition(int direction, int x, int y);


	
	// COMPOSITE METHODS
	public void addPiece(AbstractPiece piece) {
		
	}
	public void removeAllPieces() {
		
	}
	public void addPieces(List<AbstractPiece> pieces) {
		
	}
	public void removePieces(List<AbstractPiece> pieces) {
		
	}
	
	public ArrayList<AbstractPiece> getPieces() {
		
		if(this.playerId == -2) {
			return null;
		}
		
		ArrayList<AbstractPiece> list = new ArrayList<AbstractPiece>();
		list.add(this);
		return list;
	}

	/*public AbstractPiece getFirstPiece() {
		if(this.playerId == -2) {
			return null;
		}
		
		return this;
	}*/

	public int getPiecesCount() {
		if(this.playerId == -2) {
			return 0;
		}
		
		return 1;
	}
	
	public boolean isComposite() {
		if (this.getPiecesCount() > 1) {
			return true;
		}
		return false;
	}
	
	public boolean isPlayerPiece(int playerId) {
		//System.out.println("piece = " + this.toString() + ", player = " + this.playerId + ", given = " + playerId);
		return this.playerId == playerId;
	}
	
	public int getPieceValue() {
		if(this.playerId == -2) {
			return 0;
		}
		return getPointsValue();
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}

}

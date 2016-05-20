package model.pieces;

import java.util.ArrayList;
import java.util.List;

import model.Constants;

public class CompositePiece extends AbstractPiece {

	private ArrayList<AbstractPiece> pieces;
	
	public CompositePiece(int type, String initial, int id, int playerId,
			boolean isMoveContinuous) {
		super(type, initial, id, playerId, isMoveContinuous);
		
		
		pieces = new ArrayList<AbstractPiece>();
	}

	@Override
	public int[] getNextPosition(int direction, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * @return the pieces
	 */
	@Override
	public ArrayList<AbstractPiece> getPieces() {
		return pieces;
	}
	
	
	/**
	 * Get the first Piece in this tile
	 * 
	 * @return Piece - an object of Piece
	 */
	//@Override
	public AbstractPiece getFirstPiece() {
		return this.pieces.size() > 0 ? this.pieces.get(0) : null;
	}

	/**
	 * Get total number of pieces in this tile
	 * 
	 * @return int - count of pieces
	 */
	@Override
	public int getPiecesCount() {
		return this.pieces.size();
	}
/*
	@Override
	public boolean hasPieces() {

		if (pieces.size() > 0) {
			return true;
		}
		return false;
	}*/

	/**
	 * Get a piece by type
	 * 
	 * @param type
	 *            - type of a Piece
	 * @return Piece - an object of Piece if found, otherwise null
	 */
	public AbstractPiece getPieceByType(int type) {

		if (type == Constants.PIECE_TYPE_DEFAULT) {
			return this.getFirstPiece();
		} else {
			for (AbstractPiece piece : this.pieces) {
				if (type == piece.getType()) {
					return piece;
				}
			}
			return null;
		}
	}
	
	@Override
	public boolean isPlayerPiece(int playerId) {
		//return this.pieces.get(0).getPlayerId() == playerId;
		AbstractPiece p = this.getFirstPiece();
		if(p != null && p.getPlayerId() == playerId) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getPieceValue() {
		
		return (getPointsValue() * getPiecesCount());
	}
	
	
	

	@Override
	public void addPiece(AbstractPiece piece) {
		pieces.add(piece);

	}
	
	/**
	 * 
	 */
	@Override
	public void removeAllPieces() {
		pieces.removeAll(pieces);
	}

	/**
	 * Add many pieces to this tile
	 * 
	 * @param pieces
	 *            - ArrayList<Piece> of Piece
	 */
	@Override
	public void addPieces(List<AbstractPiece> pieces) {
		this.pieces.addAll(pieces);
	}

	/**
	 * 
	 */
	@Override
	public void removePieces(List<AbstractPiece> pieces) {

		for (int i = 0; i < pieces.size(); i++) {
			this.pieces.remove(pieces.get(i));
		}
	}
	
	@Override
	public String toString() {
		StringBuffer strbuff = new StringBuffer();
		for (AbstractPiece piece : pieces) {
			strbuff.append((strbuff.length() > 0 ? ", " : "")
					+ piece.toString());
		}

		return strbuff.toString();
	}
}

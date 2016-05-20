package model.algorithm;

import java.util.ArrayList;

import model.Constants;

/**
 * @author Dolly
 *
 */
public class MyTile {
	
	protected int point = 0;
	private ArrayList<MyPiece> pieces = new ArrayList<MyPiece>();
	
	/**
	 * Constructor - initialize tile
	 */
	public MyTile() {
		
	}
	
	/**
	 * Constructor - initialize tile with a piece
	 * @param piece - an object of Piece
	 */
	public MyTile(MyPiece piece) {
		if(piece != null) {
			this.pieces.add(piece);
		}
	}
	
	/**
	 * Add a piece to this tile
	 * @param piece - an object of Piece
	 */
	public void addPiece(MyPiece piece) {
		this.pieces.add(piece);
	}
	
	/**
	 * Add many pieces to this tile
	 * @param pieces - ArrayList<Piece> of Piece
	 */
	public void addAllPieces(ArrayList<MyPiece> pieces) {
		this.pieces.addAll(pieces);
	}
	
	/**
	 * Remove all the pieces from this tile
	 */
	public void removeAllPieces() {
		this.pieces.removeAll(this.pieces);
	}
	
	/**
	 * Get all the pieces of this tile
	 * @return ArrayList<Piece> - list of pieces
	 */
	public ArrayList<MyPiece> getAllPieces() {
		return this.pieces;
	}
	
	/**
	 * Get the first Piece in this tile
	 * @return Piece - an object of Piece
	 */
	public MyPiece getFirstPiece() {
		return this.pieces.size() > 0 ? this.pieces.get(0) : null;
	}
	
	/**
	 * Get total number of pieces in this tile
	 * @return int - count of pieces
	 */
	public int getPiecesCount() {
		return this.pieces.size();
	}
	
	/**
	 * Get a piece by type
	 * @param type - type of a Piece
	 * @return Piece - an object of Piece if found, otherwise null
	 */
	public MyPiece getPieceByType(int type) {
		
		if(type == Constants.PIECE_TYPE_DEFAULT) {
			return this.getFirstPiece();
		} else {
			for (MyPiece piece : this.pieces) {
				if(type == piece.type) {
					return piece;
				}
			}
			return null;
		}
	}
	
	/**
	 * Print this tile
	 */
	public void printData() {
		
		if(true) {
			return;
		}
		
		System.out.println("Tile point = " + this.point + ", total pieces = " + this.pieces);
		for (MyPiece piece : this.pieces) {
			System.out.println("p = " + piece.name);
		}
	}
	
	@Override
	public String toString() {
		if(this.pieces.size() == 0) {
			return "#";
		} else {
			StringBuffer strbuff = new StringBuffer();
			for (MyPiece piece : this.pieces) {
				strbuff.append((strbuff.length() > 0 ? ", " : "") + piece.toString());
			}
			return strbuff.toString();
		}
	}
}

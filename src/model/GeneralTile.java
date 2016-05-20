package model;

import model.pieces.AbstractPiece;

/**
 * Type of Tile - GeneralTile
 * 
 * @author Brett Robertson (s3437164)
 * @author Dolly Shah (s3399503)
 */
public class GeneralTile extends Tile {

	public GeneralTile() {
		
	}
	
	public GeneralTile(AbstractPiece piece) {
		super(piece);
	}
	
	public int getTileValue() {

		//int totalValue = AbstractPiece.getPointsValue() * piece.getPiecesCount();
		int totalValue = piece.getPieceValue();
		System.out.println(">>>>>>>>>>>GENERAL tile value = " + totalValue);
		return totalValue;
	}	
}

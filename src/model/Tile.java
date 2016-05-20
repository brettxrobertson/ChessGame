package model;

/**
 * Handles information about a Tile - pieces, image, etc.
 * 
 * @author Brett Robertson (s3437164)
 * @author Dolly Shah (s3399503)
 *
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.pieces.AbstractPiece;

public abstract class Tile implements Serializable {

	public AbstractPiece piece;
	
	private String image;

	public Tile() {
		//piece = new BlankPiece(0, -2);
	}
	
	public Tile(AbstractPiece piece) {
		this.piece = piece;
	}

	public abstract int getTileValue();

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public boolean isEmpty() {
		if(this.piece != null && this.piece.getPiecesCount() > 0) {
			return false;
		}
		
		return true;
	}
	

	@Override
	public String toString() {
		if (piece.getPiecesCount() == 0) {
			return "#";
		} else {
			return piece.toString();
		}
	}
}

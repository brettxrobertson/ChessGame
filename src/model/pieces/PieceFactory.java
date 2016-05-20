package model.pieces;


public class PieceFactory {
	
	public static AbstractPiece getPiece(char ch, int id, int playerId) {
		
		AbstractPiece piece = null;
		CompositePiece compiece = new CompositePiece(-1, "", 0, 0, true);
		switch (ch) {
			// Rook
			case 'R':
				piece = new Rook(id, playerId);
				break;
			// Bishop
			case 'B':
				piece = new Bishop(id, playerId);
				break;
			// Knight
			case 'H':
				piece = new Knight(id, playerId);
				break;
			// Queen
			case 'Q':
				piece = new Queen(id, playerId);
				break;
			// King
			case 'K':
				piece = new King(id, playerId);
				break;
		}
		if(piece != null) {
			compiece.addPiece(piece);
		}
		return compiece;
		//return piece;
	}
}

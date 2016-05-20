package model;

import java.util.ArrayList;

import model.pieces.AbstractPiece;

public class Move {

	GameModel gameModel;
	ArrayList<AbstractPiece> selectedPieces;
	int x1, x2, y1, y2;
	AbstractPiece takenPiece;
	boolean barrierTaken;
	int playerId;

	public Move(GameModel gameModel,ArrayList<AbstractPiece> selectedPieces, int x1, int x2,
			int y1, int y2, AbstractPiece takenPiece, int playerId,
			boolean barrierTaken) {
		this.gameModel = gameModel;
		this.selectedPieces = selectedPieces;

		this.x1 = x1;
		this.y1 = y1;

		this.x2 = x2;
		this.y2 = y2;

		this.takenPiece = takenPiece;
		this.playerId = playerId;
		this.barrierTaken = barrierTaken;
	}

	public void undo() {
		Tile tiles[][] = gameModel.getBoard().getTiles();
		int score =0;
		
		// Move selected pieces
		tiles[x2][y2].piece.removePieces(selectedPieces);
		tiles[x1][y1].piece.addPieces(selectedPieces);
		
		if(barrierTaken == true){
			tiles[x2][y2] = new BarrierTile();
			score += Constants.POINT_BARRIER;
		}
		gameModel.getBoard().setTiles(tiles);
		
		
		//Update the score with the negative score
		score += tiles[x2][y2].getTileValue();
		gameModel.getActivePlayer().setScore(0-score);
		
	}
}

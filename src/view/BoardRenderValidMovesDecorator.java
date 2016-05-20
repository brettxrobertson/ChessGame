package view;


import java.awt.Color;
import java.util.ArrayList;

public class BoardRenderValidMovesDecorator extends BoardAbstractDecorator {
	private static int[][] validMoves;
	
	BoardRenderValidMovesDecorator(DnDJLabel[][] chessBoardSquares){
		super(chessBoardSquares);
	}
	
	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
	
		if (BoardView.getInstance().getValidMoves() != null){
			//validMoves = BoardView.getInstance().getValidMoves();
		} else {
			//validMoves = new int[GameView.getNewGameData().boardSize][GameView.getNewGameData().boardSize];
			//_initialiseTiles();
		}
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				if (validMoves[i][j] == 1){
					//chessBoardSquares[i][j].setBorder(matteGreen);
					chessBoardSquares[i][j].setBackground(new Color(100, 255, 100));
					chessBoardSquares[i][j].setEnabled(true);
				}
				else if (chessBoardSquares[i][j] != BoardView.getInstance().getSelectedFromButton()){
					chessBoardSquares[i][j].setEnabled(false);
				}
 			}
		}
		return chessBoardSquares;
	}
	
	public void setValidMoves(ArrayList<Integer[]> validMovesIn) {
		// performing a quick sort so that renderValidMoves is a simple int[][] assignment
		int[][] validMovesOut = new int[GameView.getNewGameData().boardSize][GameView.getNewGameData().boardSize];
		
		for (int i = 0; i < validMovesIn.size(); i++){
			validMovesOut[validMovesIn.get(i)[0]][validMovesIn.get(i)[1]] = 1;
		}
		validMoves = validMovesOut;
	}
	
	
	private void _initialiseTiles(){
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				validMoves[i][j] = 0;
			}
		}
	}
}


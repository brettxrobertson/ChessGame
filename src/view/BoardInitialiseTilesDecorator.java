package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardInitialiseTilesDecorator extends BoardAbstractDecorator {

	BoardInitialiseTilesDecorator(DnDJLabel[][] chessBoardSquares) {
		super(chessBoardSquares);
	}

	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
	
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				BoardView.getInstance().getTiles()[i][j] = new model.GeneralTile();
				BoardView.getInstance().getValidMoves()[i][j] = 0;
			}
		}
		return chessBoardSquares;
	}
}

package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class BoardAbstractDecorator implements BoardViewInterface{
	protected BoardView boardViewInterface; 
	protected JPanel chessBoard;
	protected DnDJLabel[][] chessBoardSquares;
	
	BoardAbstractDecorator(DnDJLabel[][] chessBoardSquares){
		this.chessBoardSquares = chessBoardSquares;

	}
	
	public DnDJLabel[][] renderBoard(){
		if (boardViewInterface != null){
			boardViewInterface.renderBoard();
		}
		return chessBoardSquares;
	}
}

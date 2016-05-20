package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BoardClearValidMovesDecorator extends BoardAbstractDecorator {
	
	BoardClearValidMovesDecorator(DnDJLabel[][] chessBoardSquares){
		super(chessBoardSquares);
	//BoardClearValidMovesDecorator(BoardView boardViewInterface){
	//	super(boardViewInterface);
	}
	
	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				chessBoardSquares[i][j].setEnabled(false);
				chessBoardSquares[i][j].setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1),new LineBorder(Color.BLACK)));
			}
		}
		return chessBoardSquares;
	}
}

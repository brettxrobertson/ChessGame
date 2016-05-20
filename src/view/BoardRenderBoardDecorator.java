package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardRenderBoardDecorator extends BoardAbstractDecorator {

	BoardRenderBoardDecorator(DnDJLabel[][] chessBoardSquares){
		super(chessBoardSquares);
	//BoardRenderBoardDecorator(BoardView boardViewInterface){
	//	super(boardViewInterface);
	}
	
	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
		
		Color lastSquareColour = new Color(0);
		lastSquareColour = Color.BLACK;
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			// If the next row is an even number we want to force the last colour so that the alternating
			// test below keeps step. The modulus test is generic to work with rows 0 and 1.
			if ((i + 2) % 2 == 0){
				lastSquareColour = Color.BLACK;
			} else {
				lastSquareColour = Color.WHITE;
			}
			
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				if (lastSquareColour == Color.BLACK){
					chessBoardSquares[i][j].setBackground(Color.WHITE);
				} else {
					chessBoardSquares[i][j].setBackground(Color.BLACK);
				}
				lastSquareColour = chessBoardSquares[i][j].getBackground();
			}
		}
		return chessBoardSquares;
	}
}

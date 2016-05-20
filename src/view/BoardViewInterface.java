package view;

import javax.swing.JButton;
import javax.swing.JPanel;

public interface BoardViewInterface {
	
	//public abstract JPanel getChessBoard();
	//public abstract DnDJLabel[][] getChessBoardSquares();
	//public abstract DnDJLabel getSelectedFromButton();
	
	//public abstract void initialiseTiles();
	//public abstract void initialiseBoardButtons();
	public abstract DnDJLabel[][] renderBoard();
	//public abstract void renderTiles();
	//public abstract void renderValidMoves();
	//public abstract void clearValidMoves();
	//public abstract void clearBoard();
	//public abstract void clearAllSelected();
}

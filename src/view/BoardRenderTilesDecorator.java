package view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardRenderTilesDecorator extends BoardAbstractDecorator {
	
	private int boardSize = 2;
	private int chessboardPreferredSize = 768;
	private model.Tile[][] tiles;
	
	BoardRenderTilesDecorator(DnDJLabel[][] chessBoardSquares){
		super(chessBoardSquares);
		
		if (GameView.getNewGameData() != null){
			this.boardSize = GameView.getNewGameData().boardSize;
			this.chessboardPreferredSize = GameView.getNewGameData().chessboardPreferredSize;
		}
	}
	
	public DnDJLabel[][] renderBoard(){
		super.renderBoard();
		
		BoardView.getInstance();
		if (BoardView.getInstance().getTiles() != null){
			tiles = BoardView.getInstance().getTiles();
		} else {
			tiles = new model.Tile[GameView.getNewGameData().boardSize][GameView.getNewGameData().boardSize];
			_initialiseTiles();
		}
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				chessBoardSquares[i][j].setEnabled(false);
				
				if (!tiles[i][j].isEmpty()) {
                    chessBoardSquares[i][j].setIcon(new ImageIcon(
                        PieceVisualDecorator.getPieceVisual(tiles[i][j].piece.getPieces())));
                    if(tiles[i][j].piece.isPlayerPiece(GameView.getGameData().getCurrentPlayer() + 1)) {
                        chessBoardSquares[i][j].setEnabled(true);
                        
                        ((ImageIcon)chessBoardSquares[i][j].getIcon()).setDescription("icon:" + chessBoardSquares[i][j].getName());;
                      
                    }          
	
				} else if (tiles[i][j] instanceof model.BarrierTile){
					// Our barrier tile icons are coloured/transparent to specific background so test.
					if (chessBoardSquares[i][j].getBackground() == Color.BLACK){
						chessBoardSquares[i][j].setIcon(new ImageIcon(
							PieceVisualDecorator.getPieceVisual(Constants.BARRIER,
								(Constants.BLACK))));
					} else {
						chessBoardSquares[i][j].setIcon(new ImageIcon(
							PieceVisualDecorator.getPieceVisual(Constants.BARRIER,
								(Constants.WHITE))));
					}
				} else { //if (tiles[i][j] instanceof model.GeneralTile){
							chessBoardSquares[i][j].setIcon(new ImageIcon(
									new BufferedImage(
											(chessboardPreferredSize / boardSize),
											(chessboardPreferredSize / boardSize), 
											BufferedImage.TYPE_INT_ARGB)));
				}
			}
		}
		return chessBoardSquares;
	}
	
	private void _initialiseTiles(){
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				tiles[i][j] = new model.GeneralTile();
				//validMoves[i][j] = 0;
			}
		}
	}
	
	public void setTiles(model.Tile[][] tiles) {
		this.tiles = tiles;
		this.renderBoard();
	}
	
	public model.Tile[][] getTiles() {
		return tiles;
	}
}

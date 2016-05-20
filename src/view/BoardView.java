/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



/**
 * The main chessboard panel. Creates the visual board and renders the tiles.
 * Also handles originating mouse events to pass onto the controller. 
 * 
 * @author keltie
 *
 */
public class BoardView implements BoardViewInterface {
//public class BoardView {
	private static class InstanceHolder {
		private static BoardView instance = new BoardView(controllerEventHandler); 
	}
	
	private static final long serialVersionUID = 1L;
	private static int boardSize;
	private static int openingBoardSize = 2;
	private static int chessboardPreferredSize = 768; //used to keep button icons scaled correctly 
		
	private static JLayeredPane chessBoardDragLayer;
	private static JPanel chessBoard;
    
	private static DnDJLabel[][] chessBoardSquares;
	private static String alphabetChar = new String("ABCDEFGHIJKLMNOPQRSTUVXYZ");
	
	private static ArrayList<model.pieces.AbstractPiece> pieces = new ArrayList<model.pieces.AbstractPiece>();
		
	private static model.Tile selectedFromTile;
	private static model.Tile selectedToTile;
	private static DnDJLabel selectedFromButton;
	private static DnDJLabel selectedToButton;
	
	private static DnDJLabel dragLabel = new DnDJLabel();
			
	private static Border matteRed = BorderFactory.createMatteBorder(8, 8, 8, 8, Color.red);
	private static Border matteGreen = BorderFactory.createMatteBorder(8, 8, 8, 8, Color.green);
	
	private static model.Tile[][] tiles;
	private static model.pieces.AbstractPiece activePiece;
	
	private static int[][] validMoves;
	
	private static eventHandling.Subject controllerEventHandler;
		

	BoardView(eventHandling.Subject controllerEventHandler){
		if (GameView.getNewGameData() != null){
			BoardView.boardSize = GameView.getNewGameData().boardSize;
		}else{
			BoardView.boardSize = openingBoardSize;
		}
	}

	public static BoardView getInstance(){
		return InstanceHolder.instance;
	}
	
	public void initialiseBoard(eventHandling.Subject controllerEventHandler){
		setControllerEventHandler(controllerEventHandler);
		
		tiles = new model.Tile[boardSize][boardSize];
		validMoves  = new int[boardSize][boardSize];
		
		initialiseBoardButtons();
		renderBoard();
		createChessBoard();
		initialiseTiles();
		
		//In case the panel gets munted by a small display/window resize, reset the button scaling
		if (chessBoard != null){
			//chessboardPreferredSize = chessBoard.getHeight();
		}
		
		PieceVisualDecorator.getInstance(boardSize, chessboardPreferredSize);
		PieceVisualDecorator.createPieceVisuals(boardSize, chessboardPreferredSize);
		
		PieceVisualSelector.getInstance();
		PieceVisualSelector.setControllerEventHandler(controllerEventHandler);
	}
	
	private JPanel createChessBoard(){
		chessBoardDragLayer = GUILayoutFactory.createChessDragLayerPanel();
		chessBoard = GUILayoutFactory.createChessPanel();
		
		chessBoardDragLayer.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				chessBoard.add(chessBoardSquares[i][j]);
			}
		}
		return chessBoard;
	}
	
	
	public DnDJLabel[][] renderBoard(){
		BoardRenderBoardDecorator boardRenderBoardDecorator = new BoardRenderBoardDecorator(chessBoardSquares);
		chessBoardSquares = boardRenderBoardDecorator.renderBoard();

		return chessBoardSquares;
	}
	

	public static void setTiles(model.Tile[][] tiles) {
		BoardView.tiles = tiles;
		getInstance().renderTiles();
	}
	
	public model.Tile[][] getTiles() {
		return tiles;
	}
	
	public int[][] getValidMoves() {
		return validMoves;
	}
	
	public void setValidMoves(ArrayList<Integer[]> validMovesIn) {
		BoardRenderValidMovesDecorator boardRenderValidMovesDecorator = new BoardRenderValidMovesDecorator(chessBoardSquares);
		boardRenderValidMovesDecorator.setValidMoves(validMovesIn);
		chessBoardSquares = boardRenderValidMovesDecorator.renderBoard();
	}

	public static void setSelectedFromButton(DnDJLabel chessBoardSquares) {
		BoardView.selectedFromButton = chessBoardSquares;
	}
	
	public static void setSelectedToButton(DnDJLabel selectedToButton) {
		BoardView.selectedToButton = selectedToButton;
	}
	
	public static model.Tile getSelectedFromTile() {
		return selectedFromTile;
	}
	
	public static model.Tile getSelectedToTile() {
		return selectedToTile;
	}
	
	public static void setSelectedFromTile(int x, int y) {
		//chessBoardSquares[x][y].setBorder(matteRed);
		chessBoardSquares[x][y].setBackground(new Color(255, 100, 100));
		//chessBoardSquares[x][y].setSelected(true);
		chessBoardSquares[x][y].setForeground(Color.GREEN); //stand in attribute for setSelected(true).
		setSelectedFromButton(chessBoardSquares[x][y]);
	}
	
	public static void clearToSelected() {
		if (BoardView.selectedToButton != null){
			//BoardView.selectedToButton.setSelected(false);
			BoardView.selectedToButton.setForeground(Color.BLUE); //stand in attribute for setSelected(false).
			BoardView.selectedToButton.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1),new LineBorder(Color.BLACK)));
			BoardView.selectedToButton = null;
		}
	}	

	/**
	 * @param controllerEventHandler the controllerEventHandler to set
	 */
	public static void setControllerEventHandler(eventHandling.Subject controllerEventHandler) {
		BoardView.controllerEventHandler = controllerEventHandler;
	}

	/**
	 * @return the controllerEventHandler
	 */
	public eventHandling.Subject getControllerEventHandler() {
		return controllerEventHandler;
	}
	
	public JPanel getChessBoard() {
		return chessBoard;
	}

	public DnDJLabel[][] getChessBoardSquares() {
		return chessBoardSquares;
	}

	public void initialiseTiles() {
		BoardInitialiseTilesDecorator boardInitialiseTilesDecorator = new BoardInitialiseTilesDecorator(chessBoardSquares);
		chessBoardSquares = boardInitialiseTilesDecorator.renderBoard();
	}

	public void initialiseBoardButtons() {
		BoardInitialiseBoardButtonsDecorator boardInitialiseBoardButtonsDecorator = new BoardInitialiseBoardButtonsDecorator(chessBoardSquares);
		chessBoardSquares = boardInitialiseBoardButtonsDecorator.renderBoard();
	}

	public void renderTiles() {
		BoardRenderTilesDecorator boardRenderTilesDecorator = new BoardRenderTilesDecorator(chessBoardSquares);
		chessBoardSquares = boardRenderTilesDecorator.renderBoard();
	}


	public void _renderValidMoves() {
		BoardRenderValidMovesDecorator boardRenderValidMovesDecorator = new BoardRenderValidMovesDecorator(chessBoardSquares);
		chessBoardSquares = boardRenderValidMovesDecorator.renderBoard();
	}

	public void clearValidMoves() {
		BoardClearValidMovesDecorator boardClearValidMovesDecorator = new BoardClearValidMovesDecorator(chessBoardSquares);
		chessBoardSquares = boardClearValidMovesDecorator.renderBoard();

		renderBoard();
		renderTiles();
	}

	public void _clearBoard() {
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
                ImageIcon pieceProxyIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                chessBoardSquares[i][j].setIcon(pieceProxyIcon);
			}
		}
	}

	public void _clearAllSelected() {
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				//chessBoardSquares[i][j].setSelected(false);
				chessBoardSquares[i][j].setEnabled(true);
				chessBoardSquares[i][j].setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1),new LineBorder(Color.BLACK)));
				BoardView.selectedFromTile = null;
				BoardView.selectedToTile = null;
				
				chessBoardSquares[i][j].setForeground(Color.BLUE); //stand in attribute for setSelected(false).
			}
		}	
	}

	public DnDJLabel getSelectedFromButton() {
		for (int i = 0; i < chessBoardSquares.length; i++){
			for (int j = 0; j < chessBoardSquares[i].length; j++){
				if (chessBoardSquares[i][j].getForeground() == Color.GREEN){
				//if (chessBoardSquares[i][j].isSelected()){
					return chessBoardSquares[i][j];
				}
			}
		}
		return null;
	}
	
}

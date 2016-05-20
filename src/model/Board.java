package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import model.pieces.AbstractPiece;
import model.pieces.PieceFactory;

/**
 * To handle interaction with Board - tiles and pieces
 * 
 * @author Brett Robertson (s3437164)
 * @author Dolly Shah (s3399503)
 */
public class Board implements Serializable {

	GameModel gameModel;
	/**
	 * Store information on tiles
	 */
	private Tile[][] tiles;
	/**
	 * Size of the board
	 */
	private int boardSize;
	/**
	 * For temporary use, to find piece belongs to which player
	 */
	private int currentPlayer = 1;

	/**
	 * Initialize Board class
	 */
	public Board(GameModel gameModel) {
		this.gameModel = gameModel; //Possible use IoC instead
	}

	/**
	 * Initialize board with board size - tiles and pieces
	 * 
	 * @return the rows
	 * 
	 * @pre.condition empty board
	 * @post.condition board with tiles, barriers and pieces
	 */

	public void initBoard(int boardSize) {
		
		this.createBoard();
		/*if(true)
		return;
		
		this.boardSize = boardSize;
		int barrierPosition = boardSize / 2;
		tiles = new Tile[boardSize][boardSize];
		// Initialize all tiles
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {

				// if this is the middle two rows add barriers
				if ((j == barrierPosition - 1) || (j == barrierPosition)) {
					tiles[i][j] = new BarrierTile();
				}
				// else add general tiles
				else {
					tiles[i][j] = new GeneralTile();
				}
			}
		}

		// Initialize pieces
		// Rook
		this.tiles[0][0].addPiece(new Rook(1, 1));
		this.tiles[boardSize - 1][0].addPiece(new Rook(2, 1));
		this.tiles[0][boardSize - 1].addPiece(new Rook(1, 2));
		this.tiles[boardSize - 1][boardSize - 1].addPiece(new Rook(2, 2));

		// Knight - horse
		int sk = 1, ek = 6;
		this.tiles[sk][0].addPiece(new Knight(1, 1));
		this.tiles[ek][0].addPiece(new Knight(2, 1));
		this.tiles[sk][boardSize - 1].addPiece(new Knight(1, 2));
		this.tiles[ek][boardSize - 1].addPiece(new Knight(2, 2));
		
		// Bishop - camel
		int sb = 2, eb = 5;
		this.tiles[sb][0].addPiece(new Bishop(1, 1));
		this.tiles[eb][0].addPiece(new Bishop(2, 1));
		this.tiles[sb][boardSize - 1].addPiece(new Bishop(1, 2));
		this.tiles[eb][boardSize - 1].addPiece(new Bishop(2, 2));

		// King
		this.tiles[4][0].addPiece(new King(1, 1));
		this.tiles[4][boardSize - 1].addPiece(new King(1, 2));
		
		// Queen
		this.tiles[3][0].addPiece(new Queen(1, 1));
		this.tiles[3][boardSize - 1].addPiece(new Queen(1, 2));*/
	}
	
	public void createBoard() {
		
		String filename = "input.txt";
		BufferedReader buffer = null;
		FileReader reader = null;
		ArrayList<String> arrStr = new ArrayList<String>();
		try {
			reader = new FileReader(new File(filename));
			buffer = new BufferedReader(reader);
			
			String str = "";
			String validstr = "RBHQK*-";
			while((str = buffer.readLine()) != null) {
				if(!str.matches("[RBHQK*-]+")) {
					System.out.println("ERROR: invalid characters in file. Valid characters are RBHQK*-");
					buffer.close();
					reader.close();
					return;
				} else {
					arrStr.add(str);
				}
			}
			
			// Invert values and add for player2 
			ArrayList<String> arrStr2 = new ArrayList<String>(arrStr);
			Collections.reverse(arrStr2);
			arrStr.addAll(arrStr2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// Initialize all tiles
		this.boardSize = arrStr.get(0).length();
		tiles = new Tile[boardSize][boardSize];
		
		char ch;
		String str;
		//PieceFactory factoryPiece = new PieceFactory();
		int p = 1;
		for (int i = 0; i < arrStr.size(); i++) {
			
			if(i == (arrStr.size()/2)) {
				p++;
			}
			
			str = arrStr.get(i);
			for (int j = 0; j < boardSize; j++) {
				ch = str.charAt(j);
				//System.out.print(i + "," + j + " = " + ch);
				switch (ch) {
					case '*':
						tiles[i][j] = new BarrierTile();
						break;
						
					case '-':
						tiles[i][j] = new GeneralTile();
						break;
						
					default:
						tiles[i][j] = new GeneralTile(PieceFactory.getPiece(ch, i * j, p));
						//tiles[i][j].addPiece(factoryPiece.getPiece(ch, i * j, p));
						//tiles[i][j].piece.addPiece(factoryPiece.getPiece(ch, i * j, p));
						//tiles[i][j].piece = factoryPiece.getPiece(ch, i * j, p);
						break;
				}
				//System.out.println(" =>>> " + tiles[i][j]);
			}
		}
		
		//this.printBoard();
	}
	
	/**
	 * Print board
	 */
	public void printBoard() {
		
		System.out.println("\n**********************************************************************************************"); 
		//System.out.println("\nPlayer 1: " + score[1] + ", Player 2: " + score[2] + " --- Current Player: " + currentPlayer);
		System.out.println("\n----------------------------------------------------------------------------------------------");
		System.out.print(" \t");
		for (int j = 0; j < boardSize; j++) {
			System.out.format("%-15s", j);
		}
		System.out.println("\n----------------------------------------------------------------------------------------------");
		//System.out.println("\n______________________________________________________________________");
		for (int i = 0; i < boardSize; i++) {
			System.out.print(i + "  |\t");
			for (int j = 0; j < boardSize; j++) {
				
				//System.out.print("" + tiles[j][i] + "\t|\t");
				//System.out.print("\t" + tiles[j][i]);
				System.out.format("%-15s", tiles[j][i]);
			}
			System.out.println();
			
			//System.out.println("\n--------------------------------------------------------------------");
		}
	}

	/**
	 * Return size of the board
	 * 
	 * @return the size
	 * 
	 * @pre.condition
	 * @post.condition
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * Return specific tile at x and y
	 * 
	 * @param x
	 *            - row of Tile
	 * @param y
	 *            - column of Tile
	 * @return Tile
	 * 
	 * @pre.condition
	 * @post.condition
	 */
	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	/**
	 * Return all tiles
	 * 
	 * @return Tile[][]
	 * 
	 * @pre.condition
	 * @post.condition
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Temporarily used only in this class
	 * 
	 * @param currentPlayer
	 *            - the currentPlayer to set
	 * 
	 * @pre.condition
	 * @post.condition
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Find all valid moves for a piece
	 * 
	 * @param selectedPieces
	 *            - selected pieces
	 * @param x
	 *            - Source x position
	 * @param y
	 *            - Source y position
	 * @return ArrayList<Integer[]> -
	 *         <p>
	 *         index 0: x position,
	 *         </p>
	 *         <p>
	 *         index 1: y position,
	 *         </p>
	 *         <p>
	 *         index 2: special benefit (barrier, player's piece or opponent's
	 *         piece)
	 *         </p>
	 *         <p>
	 *         null - when source is invalid
	 *         </p>
	 * 
	 * @pre.condition selectedPieces exists on board
	 * @post.condition ArrayList of valid moves for pieces
	 */
	public ArrayList<Integer[]> findValidMoves(
			ArrayList<AbstractPiece> selectedPieces, int x, int y) {

		if (!validateSourcePiece(selectedPieces, x, y)) {
			return null;
		}

		int tmpx = x, tmpy = y, res, direction = 0;
		int[] pos;
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();

		for (AbstractPiece tmpPiece : selectedPieces) {

			direction = 0;
			tmpx = x;
			tmpy = y;
			while (true) {
				pos = tmpPiece.getNextPosition(direction, tmpx, tmpy);
				tmpx = pos[0];
				tmpy = pos[1];
				res = checkDestinationTile(tiles[x][y], tmpx, tmpy);
				if (res > 0) {
					result.add(new Integer[] { tmpx, tmpy, res });
				}

				// Only continue if the piece is a Tile
				// But Special Case: stop and change direction when piece
				// doesn't move continuously like Knight
				if (res != Constants.PIECE_TO_ADVANTAGE_TILE
						|| !tmpPiece.isMoveContinuous()) {
					direction++;
					if (direction == tmpPiece.getTotalDirections()) {
						break;
					}
					tmpx = x;
					tmpy = y;
				}
			}
		}

		return result;
	}

	/**
	 * Move from source (x1, y1) to destination (x2, y2) Returns: -1: not a
	 * valid source (x1, y1) -2: not a valid move/destination -3: Cannot
	 * composite pieces as same type of piece already exists in destination 1:
	 * Successful
	 * 
	 * @param selectedPieces
	 *            - selected pieces
	 * @param x1
	 *            - Source x position
	 * @param y1
	 *            - Source y position
	 * @param x2
	 *            - Destination x position
	 * @param y2
	 *            - Destination y position
	 * @return int - returns integer
	 * 
	 * @pre.condition selectedPieces exists at source (x1, y1) on board
	 * @post.condition selectedPieces are located at destination (x2, y2) on
	 *                 board
	 */
	public int move(ArrayList<AbstractPiece> selectedPieces, int x1, int y1,
			int x2, int y2) {
		
		//Used to keep note of taken pieces (for Move History)
		AbstractPiece removedPieces = null;
		boolean barrierTaken = false;
		
		if (!validateSourcePiece(selectedPieces, x1, y1)) {
			return Constants.PIECE_INVALID_MOVE;
		}

		if (!validatePieceMove(selectedPieces, x1, y1, x2, y2)) {
			return Constants.PIECE_INVALID_MOVE;
		}

		int score = 0;

		// When destination piece is null then it is a Tile
		//if (tiles[x2][y2].piece.getPiecesCount() == 0) {
		if (tiles[x2][y2].isEmpty()) {

			// When Tile is a Barrier
			if (tiles[x2][y2] instanceof BarrierTile) {

				// Upate score
				//score = Constants.POINT_BARRIER;
				score = tiles[x2][y2].getTileValue();
				
				//Set barrierTaken for Move history
				barrierTaken = true;
				
				// To delete the Barrier, initialize Tile
				tiles[x2][y2] = null;
				tiles[x2][y2] = new GeneralTile(PieceFactory.getPiece(' ', 0, 0));
			}
			// When it is a Tile
			else {
				// This is a tile
				
				// For empty tile, piece will be null
				tiles[x2][y2].piece = PieceFactory.getPiece(' ', 0, 0);
			}

		} else {
			// Composite piece
			//if (tiles[x2][y2].piece.getFirstPiece().getPlayerId() == currentPlayer) {
			if (tiles[x2][y2].piece.isPlayerPiece(currentPlayer)) {
				// This is a current player's piece
			}
			// Remove opponent's piece
			else {

				// Upate score
				//score = (Constants.POINT_PIECE * tiles[x2][y2].piece.getPiecesCount());
				score = tiles[x2][y2].getTileValue();
				
				//Before removing pieces keep a copy of them (For move history)
				removedPieces = tiles[x2][y2].piece;
				
				// Remove opponent's pieces
				tiles[x2][y2].piece.removeAllPieces();
			}
		}
		//Add successful move to the historyManager (used for undo)
		HistoryManager.addMoveHistory(new Move(gameModel,selectedPieces, x1, y1,
				 x2, y2, removedPieces, currentPlayer,barrierTaken));
		
		// Move selected pieces
		tiles[x2][y2].piece.addPieces(selectedPieces);
		tiles[x1][y1].piece.removePieces(selectedPieces);

		return score;
	}

	/**
	 * @param tiles the tiles to set
	 */
	void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * Validate source piece Check the boundaries, piece exists and piece
	 * belongs to player
	 * 
	 * @param selectedPieces
	 *            - Selected pieces
	 * @param x
	 *            - Source x position
	 * @param y
	 *            - Source y position
	 * @return boolean - true if the piece is valid, false otherwise
	 * 
	 * @pre.condition
	 * @post.condition
	 */
	private boolean validateSourcePiece(
			ArrayList<AbstractPiece> selectedPieces, int x, int y) {

		// Given pieces are not null and size is > 0
		if (selectedPieces == null || selectedPieces.size() == 0) {
			return false;
		}

		// Check the boundaries
		if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
			System.out.println("Error: Invalid x or y");
			return false;
		}

		// Check that pieces exists
		//if (tiles[x][y].piece.getPiecesCount() == 0) {
		if(tiles[x][y].isEmpty()) {
			System.out.println("Error: Piece doesn't exists");
			return false;
		}

		// Piece belongs to player
		//if (tiles[x][y].piece.getFirstPiece().getPlayerId() != currentPlayer) {
		if (!tiles[x][y].piece.isPlayerPiece(currentPlayer)) {
			System.out
					.println("Error: Piece doesn't belong to current player = "
							+ currentPlayer);
			return false;
		}

		// Check that Piece exists
		for (AbstractPiece piece : tiles[x][y].piece.getPieces()) {
			for (AbstractPiece selectedPiece : selectedPieces) {
				if (selectedPiece.getName() == piece.getName()) {
					// Piece exists
					return true;
				}
			}
		}
		// Piece doesn't exists
		return false;
	}

	/**
	 * Only used with move() Validate moving piece(s) from source to destination
	 * 
	 * @param selectedPieces
	 *            - selected pieces
	 * @param x1
	 *            - Source x position
	 * @param y1
	 *            - Source y position
	 * @param x2
	 *            - Destination x position
	 * @param y2
	 *            - Destination y position
	 * @return boolean - true if the piece is valid, false otherwise
	 * 
	 * @pre.condition x1 < tile[0].length <br>
	 *                y1 < tile[1].length <br>
	 *                x2 < tile[0].length <br>
	 *                y2 < tile[1].length
	 * @post.condition
	 */
	private boolean validatePieceMove(ArrayList<AbstractPiece> selectedPieces,
			int x1, int y1, int x2, int y2) {

		int tmpx = x1, tmpy = y1, res, direction = 0;
		int[] pos;

		for (AbstractPiece tmpPiece : selectedPieces) {

			// Reset values
			direction = 0;
			tmpx = x1;
			tmpy = y1;

			while (true) {

				// Get next position for piece
				pos = tmpPiece.getNextPosition(direction, tmpx, tmpy);
				tmpx = pos[0];
				tmpy = pos[1];

				// Check if the position is valid from source
				res = checkDestinationTile(tiles[x1][y1], tmpx, tmpy);
				if (res > 0) {
					if (x2 == tmpx && y2 == tmpy) {
						return true;
					}
				}
				// Special Case: Change direction when piece is a Knight as it
				// doesn't move continuously
				if (res != 1
						|| tmpPiece.getType() == Constants.PIECE_TYPE_KNIGHT) {
					direction++;
					if (direction == tmpPiece.getTotalDirections()) {
						break;
					}
					tmpx = x1;
					tmpy = y1;
				}
			}
		}
		return false;
	}

	/**
	 * Check the destination Tile Returns:
	 * <p>
	 * PIECE_INVALID_MOVE: not a valid destination (new_x, new_y) or cannot
	 * composite pieces as same type of piece already exists in destination
	 * </p>
	 * <p>
	 * PIECE_TO_ADVANTAGE_TILE: It is a Tile
	 * </p>
	 * <p>
	 * PIECE_TO_ADVANTAGE_BARRIER: It is a Barrier
	 * </p>
	 * <p>
	 * PIECE_TO_ADVANTAGE_COMPOSITE: It is a Player's piece (composite/split)
	 * </p>
	 * <p>
	 * PIECE_TO_ADVANTAGE_OPPONENT: It is Opponent's piece
	 * </p>
	 * 
	 * @param tileFrom
	 *            - Source Tile
	 * @param new_x
	 *            - Destination x position
	 * @param new_y
	 *            - Destination y position
	 * @return int - returns integer
	 * 
	 * @pre.condition new_x < tile[0].length <br>
	 *                new_y < tile[1].length
	 * @post.condition
	 */
	private int checkDestinationTile(Tile tileFrom, int new_x, int new_y) {

		// Check the boundaries
		if (new_x < 0 || new_y < 0 || new_x >= boardSize || new_y >= boardSize) {
			return Constants.PIECE_INVALID_MOVE;
		}

		Tile tileTo = tiles[new_x][new_y];
		//if (tileTo.piece.getPiecesCount() == 0) {
		if(tileTo.isEmpty()) {
			// Barrier
			if (tileTo instanceof BarrierTile) {
				return Constants.PIECE_TO_ADVANTAGE_BARRIER;
			}
			// Tile
			else {
				return Constants.PIECE_TO_ADVANTAGE_TILE;
			}
		} else {

			// Composite piece
			//if (tileTo.piece.getFirstPiece().getPlayerId() == currentPlayer) {
			if (tileTo.piece.isPlayerPiece(currentPlayer)) {

				// same piece cannot be composite - check all pieces
				ArrayList<AbstractPiece> toPieces = tileTo.piece.getPieces();
				for (AbstractPiece fromPiece : tileFrom.piece.getPieces()) {
					for (AbstractPiece toPiece : toPieces) {
						if (fromPiece.getType() == toPiece.getType()) {
							return Constants.PIECE_INVALID_MOVE;
						}
					}
				}
				// Type of piece in fromtile doesn't exists in totile
				return Constants.PIECE_TO_ADVANTAGE_COMPOSITE;
			}
			// Opponent's piece
			else {
				return Constants.PIECE_TO_ADVANTAGE_OPPONENT;
			}
		}
	}
}

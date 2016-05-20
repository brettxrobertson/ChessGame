package model.algorithm;

import java.util.ArrayList;

import model.Constants;

public class MyBoard {
	int boardSize = 6;
	MyTile[][] tiles = new MyTile[boardSize][boardSize];
	int[] score = {0, 0, 0};
	int currentPlayer = 1;
	
	/**
	 * Initialize board
	 * @param boardSize - size of the board
	 */
	public void initBoard(int boardSize) {
		
		this.boardSize = boardSize;
		
		// Initialize board with Tile, Barrier and Pieces
		int barrier_position = boardSize / 2;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				//System.out.println( i + ", " + j );
				tiles[i][j] = (j == (barrier_position - 1) || j == barrier_position) ? new MyBarrier() : new MyTile();
			}
			//System.out.println();
		}
		
		// Rook - elephant
		this.tiles[0][0].addPiece(new MyRook(1, 1));
		this.tiles[5][0].addPiece(new MyRook(2, 1));
		this.tiles[0][5].addPiece(new MyRook(1, 2));
		this.tiles[5][5].addPiece(new MyRook(2, 2));

		// Bishop - camel
		this.tiles[1][0].addPiece(new MyBishop(1, 1));
		this.tiles[4][0].addPiece(new MyBishop(2, 1));
		this.tiles[1][5].addPiece(new MyBishop(1, 2));
		this.tiles[4][5].addPiece(new MyBishop(2, 2));
		
		// Knight - horse
		this.tiles[2][0].addPiece(new MyKnight(1, 1));
		this.tiles[3][0].addPiece(new MyKnight(2, 1));
		this.tiles[2][5].addPiece(new MyKnight(1, 2));
		this.tiles[3][5].addPiece(new MyKnight(2, 2));
		
		printBoard();
	}
	
	/**
	 * Print board
	 */
	public void printBoard() {
		
		System.out.println("\n**********************************************************************************************"); 
		System.out.println("\nPlayer 1: " + score[1] + ", Player 2: " + score[2] + " --- Current Player: " + currentPlayer);
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
	 * Find all valid moves for a piece
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x - Source x position
	 * @param y - Source y position
	 * @return ArrayList<Integer[]> - index 0: x position, index 1: y position, index 2: special benefit
	 */
	/*
	public ArrayList<Integer[]> findValidMoves(int type, int x, int y) {
		
		if(!validateSourcePiece(type, x, y)) {
			return null;
		}
		
		System.out.println("\nFinding valid moves for (" + x + ", " + y + "): " + tiles[x][y]);
		int tmpx = x, tmpy = y, res;
		int[] pos;
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		
		for (MyPiece tmpPiece : tiles[x][y].getAllPieces()) {
				
			switch (type) {
				case Constants.PIECE_SELECTED_TYPE_DEFAULT:
					break;
				case Constants.PIECE_SELECTED_TYPE_ROOK:
					if(tmpPiece.type != Constants.PIECE_TYPE_ROOK) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_BISHOP:
					if(tmpPiece.type != Constants.PIECE_TYPE_BISHOP) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_KNIGHT:
					if(tmpPiece.type != Constants.PIECE_TYPE_KNIGHT) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_ROOK_BISHOP:
					//System.out.println("piece type = " + tmpPiece.type + ", cond = " + !(tmpPiece.type == Constants.PIECE_TYPE_ROOK || tmpPiece.type == Constants.PIECE_TYPE_BISHOP));
					if(!(tmpPiece.type == Constants.PIECE_TYPE_ROOK || tmpPiece.type == Constants.PIECE_TYPE_BISHOP)) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_ROOK_KNIGHT:
					if(!(tmpPiece.type == Constants.PIECE_TYPE_ROOK || tmpPiece.type == Constants.PIECE_TYPE_KNIGHT)) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_BISHOP_KNIGHT:
					if(!(tmpPiece.type == Constants.PIECE_TYPE_BISHOP || tmpPiece.type == Constants.PIECE_TYPE_KNIGHT)) {
						continue;
					}
					break;
				case Constants.PIECE_SELECTED_TYPE_ROOK_BISHOP_KNIGHT:
					break;
			}
			
			// 0 = top, 1 = right, 2 = bottom, 3 = left
			for (int d = 0; d < 4; d++) {
				
				//System.out.println("Direction: " + directions[d]);
				tmpx = x;
				tmpy = y;
				
				while(true) {
					//System.out.println("moves for (" + tmpx + ", " + tmpy + "):");
					pos = tmpPiece.getNextPosition(d, tmpx, tmpy);
					tmpx = pos[0];
					tmpy = pos[1];
					res = checkDestinationTile(tiles[x][y], tmpx, tmpy);
					if(res > 0) {
						result.add(new Integer[]{tmpx, tmpy, res});
					}
					//if(res > 1 || res <= 0) 
					if(res != 1) {
						break;
					}
				}
			}
		}
		
		
		/*
		MyPiece tmpPiece = tiles[x][y].getPieceByType(type);
		
		// 0 = top, 1 = right, 2 = bottom, 3 = left
		for (int d = 0; d < 4; d++) {
			
			//System.out.println("Direction: " + directions[d]);
			tmpx = x;
			tmpy = y;
			
			while(true) {
				//System.out.println("moves for (" + tmpx + ", " + tmpy + "):");
				pos = tmpPiece.getNextPosition(d, tmpx, tmpy);
				tmpx = pos[0];
				tmpy = pos[1];
				res = checkDestinationTile(tiles[x][y], tmpx, tmpy);
				if(res > 0) {
					result.add(new Integer[]{tmpx, tmpy, res});
				}
				//if(res > 1 || res <= 0) 
				if(res != 1) {
					break;
				}
			}
		}
		*//*
		
		return result;
	}
*/
	
	/**
	 * Find all valid moves for a piece
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x - Source x position
	 * @param y - Source y position
	 * @return ArrayList<Integer[]> - index 0: x position, index 1: y position, index 2: special benefit
	 */
	public ArrayList<Integer[]> findValidMovesOfPieces(ArrayList<Integer> type, int x, int y) {
		
		if(!validateSourcePiece(type.get(0), x, y)) {
			return null;
		}
		
		System.out.println("\nFinding valid moves for (" + x + ", " + y + "): " + tiles[x][y]);
		int tmpx = x, tmpy = y, res;
		int[] pos;
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		
		//for (MyPiece tmpPiece : tiles[x][y].getAllPieces()) 
		for (Integer piecetype : type) {
			
			MyPiece tmpPiece = tiles[x][y].getPieceByType(piecetype);
			
			// 0 = top, 1 = right, 2 = bottom, 3 = left
			for (int d = 0; d < 4; d++) {
				
				//System.out.println("Direction: " + directions[d]);
				tmpx = x;
				tmpy = y;
				
				while(true) {
					//System.out.println("moves for (" + tmpx + ", " + tmpy + "):");
					pos = tmpPiece.getNextPosition(d, tmpx, tmpy);
					tmpx = pos[0];
					tmpy = pos[1];
					res = checkDestinationTile(tiles[x][y], tmpx, tmpy);
					if(res > 0) {
						result.add(new Integer[]{tmpx, tmpy, res});
					} 
					if(res != 1) {
						break;
					}
				}
			}
		}
		
		
		/*
		MyPiece tmpPiece = tiles[x][y].getPieceByType(type);
		
		// 0 = top, 1 = right, 2 = bottom, 3 = left
		for (int d = 0; d < 4; d++) {
			
			//System.out.println("Direction: " + directions[d]);
			tmpx = x;
			tmpy = y;
			
			while(true) {
				//System.out.println("moves for (" + tmpx + ", " + tmpy + "):");
				pos = tmpPiece.getNextPosition(d, tmpx, tmpy);
				tmpx = pos[0];
				tmpy = pos[1];
				res = checkDestinationTile(tiles[x][y], tmpx, tmpy);
				if(res > 0) {
					result.add(new Integer[]{tmpx, tmpy, res});
				}
				//if(res > 1 || res <= 0) 
				if(res != 1) {
					break;
				}
			}
		}
		*/
		
		return result;
	}
	
	
	/**
	 * Move from source (x1, y1) to destination (x2, y2)
	 * Returns:
	 *  	-1: not a valid source (x1, y1)
	 *  	-2: not a valid move/destination
	 *  	-3: Cannot composite pieces as same type of piece already exists in destination
	 *  	 1:	Successful
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x1 - Source x position
	 * @param y1 - Source y position
	 * @param x2 - Destination x position
	 * @param y2 - Destination y position
	 * @return int - returns integer
	 */
	public int move(int type, int x1, int y1, int x2, int y2) {
		
		if(!validateSourcePiece(type, x1, y1)) {
			return -1;
		}
		
		if(!validatePieceMove(type, x1, y1, x2, y2)) {
			return -2;
		}
		
		// When destination piece is null then it is a Tile
		if(tiles[x2][y2].getPiecesCount() == 0) {
			
			// When Tile is a Barrier
			if(tiles[x2][y2] instanceof MyBarrier) {
				
				// Upate score
				score[currentPlayer] += Constants.POINT_BARRIER;
				
				tiles[x2][y2] = null;	// To delete the Barrier
				tiles[x2][y2] = new MyTile();
			} 
			// When it is a Tile
			else {
				
			}
			
			// Move all the pieces from source to the destination
			tiles[x2][y2].addAllPieces(tiles[x1][y1].getAllPieces());
			tiles[x1][y1].removeAllPieces();
			
		} else {
			// Composite piece
			if(tiles[x2][y2].getFirstPiece().playerId == currentPlayer) {
				
				// TODO: check that this never happens as this condition is checked in validatePieceMove()
				/*
				// Cannot composite same pieces, has to be unique piece type
				for (MyPiece pieceFrom : tiles[x1][y1].getAllPieces()) {
					for (MyPiece pieceTo : tiles[x2][y2].getAllPieces()) {
						if(pieceFrom.type == pieceTo.type) {
							//isValidComposite = false;
							System.out.println("ERROR: A piece of this type already exists in destination");
							return -3;
							//break;
						}
					}
				}*/
				
				// Move the piece
				tiles[x2][y2].addAllPieces(tiles[x1][y1].getAllPieces());
				tiles[x1][y1].removeAllPieces();
			}
			// Remove opponent's piece
			else {
				
				// Upate score
				score[currentPlayer] += (Constants.POINT_PIECE * tiles[x2][y2].getPiecesCount());
				
				// Move all the pieces from source to the destination
				tiles[x2][y2].removeAllPieces();
				tiles[x2][y2].addAllPieces(tiles[x1][y1].getAllPieces());
				tiles[x1][y1].removeAllPieces();
			}
		}
		
		// Update turn
		currentPlayer = (currentPlayer == 1) ? 2 : 1;
		return 1;
	}
	
	/**
	 * Validate source piece
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x - Source x position
	 * @param 1 - Source y position
	 * @return boolean - true if the piece is valid, false otherwise
	 */
	private boolean validateSourcePiece(int type, int x, int y) {
		
		// Check the boundaries
		if(x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
			return false;
		}
		
		// Check that pieces exists
		if(tiles[x][y].getPiecesCount() == 0) {
			System.out.println("Error: Piece doesn't exists");
			return false;
		}
		
		// Piece belongs to player 
		if(tiles[x][y].getFirstPiece().playerId != currentPlayer) {
			System.out.println("Error: Piece doesn't belong to current player = " + currentPlayer);
			return false;
		}
		
		// piece type exists
		// TODO: cannot composite same type of piece
		/*if(type != Constants.PIECE_TYPE_DEFAULT) {
			for (MyPiece piece : tiles[x][y].getAllPieces()) {
				if(piece.type == type) {
					return true;
				}
			}
			return false;
		}*/
		
		return true;
	}
	
	/**
	 * Only used with move()
	 * Validate moving piece(s) from source to destination
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x1 - Source x position
	 * @param y1 - Source y position
	 * @param x2 - Destination x position
	 * @param y2 - Destination y position
	 * @return boolean - true if the piece is valid, false otherwise
	 */
	private boolean validatePieceMove(int type, int x1, int y1, int x2, int y2) {
		
		System.out.println("\nValidating move from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
		int tmpx = x1, tmpy = y1, res;
		int[] pos;
		
		MyPiece tmpPiece = tiles[x1][y1].getPieceByType(type);
		
		// 0 = top, 1 = right, 2 = bottom, 3 = left
		for (int d = 0; d < 4; d++) {
			
			//System.out.println("Direction: " + directions[d]);
			tmpx = x1;
			tmpy = y1;
			
			while(true) {
				//System.out.println("moves for (" + tmpx + ", " + tmpy + "):");
				pos = tmpPiece.getNextPosition(d, tmpx, tmpy);
				tmpx = pos[0];
				tmpy = pos[1];
				res = checkDestinationTile(tiles[x1][y1], tmpx, tmpy);
				//System.out.println("+++++++++++++++++++ validate move = " + res);
				if(res > 0) {
					//System.out.println("+++++++++++++++++++ validate move = " + res);
					if(x2 == tmpx && y2 == tmpy) {
						System.out.println("VALID MOVE...");
						return true;
					}
				}
				if(res != 1) {
					break;
				}
			}
		}
		System.out.println("INVALID MOVE...");
		return false;
	}
	
	/**
	 * Check the destination Tile
	 * Returns:
	 *  	-1: not a valid destination (new_x, new_y)
	 *  	 0: not a valid move - Cannot composite pieces as same type of piece already exists in destination
	 *  	 1: It is a Tile
	 *  	 2:	It is a Barrier (defined as Constants.PIECE_TO_ADVANTAGE_BARRIER)
	 *  	 3:	It is a Composite piece (defined as Constants.PIECE_TO_ADVANTAGE_COMPOSITE)
	 *  	 4:	It is a Opponent's piece (defined as Constants.PIECE_TO_ADVANTAGE_OPPONENT)
	 * @param tileFrom - Source Tile
	 * @param new_x - Destination x position
	 * @param new_y - Destination y position
	 * @return int - returns integer
	 */
	private int checkDestinationTile(MyTile tileFrom, int new_x, int new_y) {
		
		// Check the boundaries
		if(new_x < 0 || new_y < 0 || new_x >= boardSize || new_y >= boardSize) {
			return -1;
		}
		
		MyTile tileTo = tiles[new_x][new_y];
		if(tileTo.getPiecesCount() == 0) {
			// Barrier
			if(tileTo instanceof MyBarrier) {
				return Constants.PIECE_TO_ADVANTAGE_BARRIER;
			}
			// Tile
			else {
				return 1;
			}
		} else {
			
			// Composite piece
			if(tileTo.getFirstPiece().playerId == currentPlayer) {
				
				// same piece cannot be composite - check all pieces
				ArrayList<MyPiece> toPieces = tileTo.getAllPieces();
				for (MyPiece fromPiece : tileFrom.getAllPieces()) {
					for (MyPiece toPiece : toPieces) {
						if(fromPiece.type == toPiece.type) {
							return 0;
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
	
	/**
	 * Get array[type] = name for all the pieces at the tile
	 * @param x - Tile x position
	 * @param y - Tile y position
	 * @return String[]
	 */
	public String[] getPiecesCountForTile(int x, int y) {
		
		if(tiles[x][y].getPiecesCount() <= 1) {
			return null;
		}
		
		// TODO: make size of array dynamic
		String[] result = new String[4];
		for (MyPiece piece : tiles[x][y].getAllPieces()) {
			//result.put(piece.type, piece.name);
			result[piece.type] = piece.name;
		}
		return result;
	}
	
	public int getPiecesInfoForTile(int x, int y) {
		if(tiles[x][y].getPiecesCount() < 1) {
			return -1;
		}
		
		if(tiles[x][y].getPiecesCount() == 1) {
			return Constants.PIECE_SELECTED_TYPE_DEFAULT;
		}
		
		StringBuffer str = new StringBuffer("0000");
		for (MyPiece piece : tiles[x][y].getAllPieces()) {
			switch (piece.type) {
			case Constants.PIECE_TYPE_ROOK:
				str.setCharAt(3, '1');
				break;
			case Constants.PIECE_TYPE_BISHOP:
				str.setCharAt(2, '1');
				break;
			case Constants.PIECE_TYPE_KNIGHT:
				str.setCharAt(1, '1');
				break;
			}
		}
		return Integer.parseInt(str.toString(), 2);
	}
	
	/**
	 * Print result
	 */
	public void showResult() {
		System.out.println("Player 1 score: " + score[1]);
		System.out.println("Player 2 score: " + score[2]);
		System.out.println("********************************");
		System.out.println("WINNER: " + (score[1] > score[2] ? "Player 1" : (score[1] == score[2] ? "Tie" : "Player 2")));
		System.out.println("********************************");
	}
}

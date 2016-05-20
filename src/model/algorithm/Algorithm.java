package model.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.Constants;

/****************************************************************************
 * Application 		: 	ChessGame
 * <p>Description 	: 	Simplified Chess Game</p> 
 * <p>Author 		:	DOLLY SHAH<br />
 * Student No		:	s3399503
 ***************************************************************************/
 
/****************************************************************************
* <p>ALGORITHM CLASS</p>
* <p><b>testing...</b></p>
* <p>Algorithm basic testing...</p>
***************************************************************************/
public class Algorithm {

	public static void main(String[] args) {
		
		MyBoard board = new MyBoard();
		board.initBoard(6);
		
		
		// Predefined moves for testing
		
		//board.findValidMoves(0, 0);
		board.move(Constants.PIECE_TYPE_DEFAULT, 0, 0, 1, 0);
		board.move(Constants.PIECE_TYPE_DEFAULT, 0, 5, 1, 5);
		board.move(Constants.PIECE_TYPE_DEFAULT, 4, 0, 2, 2);
		board.move(Constants.PIECE_TYPE_ROOK, 1, 5, 2, 5);
		board.move(Constants.PIECE_TYPE_DEFAULT, 3, 0, 2, 2);
		//board.move(Constants.PIECE_TYPE_ROOK, 1, 0, 2, 0);
		//board.move(Constants.PIECE_TYPE_ROOK, 1, 5, 2, 5);
		board.printBoard();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int input, x1, y1, x2, y2, type;
		//HashMap<Integer, String> types;
		String[] types;
		
		System.out.println("\n##############################################################################################");
		System.out.println("Please select:\n");
		System.out.println("1) Show all valid moves: \n2) Move: \n3) Print Board: \n4) Exit: ");
		try {
			while((str = in.readLine()) != null) {
				
				input = Integer.parseInt(str);
				//System.out.println("input = " + input);
				if(input == 4) {
					board.showResult();
					System.out.println("Exiting the game");
					break;
				}
				
				switch (input) {
					case 1:
						System.out.print("Enter x: ");
						x1 = Integer.parseInt(in.readLine());
						System.out.print("Enter y: ");
						y1 = Integer.parseInt(in.readLine());
						
						/*type = board.getPiecesInfoForTile(x1, y1);
						if(type == -1) {
							System.out.println("No pieces found at this tile");
							continue;
						} else {
							switch (type) {
								case Constants.PIECE_SELECTED_TYPE_DEFAULT:
									break;
								case Constants.PIECE_SELECTED_TYPE_ROOK:
									//System.out.println("1: ROOK");
									break;
								case Constants.PIECE_SELECTED_TYPE_BISHOP:
									break;
								case Constants.PIECE_SELECTED_TYPE_KNIGHT:
									break;
								case Constants.PIECE_SELECTED_TYPE_ROOK_BISHOP:
									System.out.println("1: ROOK");
									System.out.println("2: BISHOP");
									System.out.println("3: ROOK and BISHOP");
									type = Integer.parseInt(in.readLine());
									break;
								case Constants.PIECE_SELECTED_TYPE_ROOK_KNIGHT:
									System.out.println("1: ROOK");
									System.out.println("3: KNIGHT");
									System.out.println("5: ROOK and KNIGHT");
									type = Integer.parseInt(in.readLine());
									break;
								case Constants.PIECE_SELECTED_TYPE_BISHOP_KNIGHT:
									System.out.println("2: BISHOP");
									System.out.println("3: KNIGHT");
									System.out.println("6: BISHOP and KNIGHT");
									type = Integer.parseInt(in.readLine());
									break;
								case Constants.PIECE_SELECTED_TYPE_ROOK_BISHOP_KNIGHT:
									System.out.println("1: ROOK");
									System.out.println("2: BISHOP");
									System.out.println("3: KNIGHT");
									System.out.println("7: ROOK, BISHOP and KNIGHT");
									type = Integer.parseInt(in.readLine());
									break;
							}
						}
						ArrayList<Integer[]> result = board.findValidMoves(type, x1, y1);
						*/
						
						/*types = board.getPiecesCountForTile(x1, y1);
						if(types != null) {
							System.out.println("Multiple pieces available on this tile. Please select one:");
							for (int i = 0; i < types.length; i++) {
								if(types[i] != null) {
									System.out.println(i + ": " + types[i]);
								}
							}
							type = Integer.parseInt(in.readLine());
						} else {
							type = Constants.PIECE_TYPE_DEFAULT;
						}
						
						ArrayList<Integer[]> result = board.findValidMoves(type, x1, y1);*/
						
						ArrayList<Integer> arrChoices = new ArrayList<Integer>();;
						types = board.getPiecesCountForTile(x1, y1);
						if(types != null) {
							System.out.println("Multiple pieces available on this tile. Please enter comma separated number to select piece(s):");
							for (int i = 0; i < types.length; i++) {
								if(types[i] != null) {
									System.out.println(i + ": " + types[i]);
								}
							}
							String inputchoice = in.readLine();
							String[] choices = inputchoice.split(",");
							for (String string : choices) {
								arrChoices.add(Integer.parseInt(string));
							}
							
						} else {
							arrChoices.add(0);
							type = Constants.PIECE_TYPE_DEFAULT;
						}
						
						ArrayList<Integer[]> result = board.findValidMovesOfPieces(arrChoices, x1, y1);
						
						if(result != null) {
							System.out.println("VALID MOVES:");
							for (Integer[] pos: result) {
								String s = "";
								switch (pos[2]) {
									case Constants.PIECE_TO_ADVANTAGE_BARRIER:
										s = "Star - 1 point";
										break;
									case Constants.PIECE_TO_ADVANTAGE_COMPOSITE:
										s = "Player's piece - Composite";
										break;
									case Constants.PIECE_TO_ADVANTAGE_OPPONENT:
										s = "Opponent's piece - 5 points min";
										break;
								}
								System.out.println("(" + pos[0] + ", " + pos[1] + "): " + s);
							}
						} else {
							System.out.println("VALID MOVES: no valid moves found");
						}
						board.printBoard();
						break;
	
					case 2:
						System.out.print("Enter x1: ");
						x1 = Integer.parseInt(in.readLine());
						System.out.print("Enter y1: ");
						y1 = Integer.parseInt(in.readLine());
						System.out.print("Enter x2: ");
						x2 = Integer.parseInt(in.readLine());
						System.out.print("Enter y2: ");
						y2 = Integer.parseInt(in.readLine());
						
						types = board.getPiecesCountForTile(x1, y1);
						if(types != null) {
							System.out.println("Multiple pieces available on this tile. Please select one:");
							for (int i = 0; i < types.length; i++) {
								if(types[i] != null) {
									System.out.println(i + ": " + types[i]);
								}
							}
							type = Integer.parseInt(in.readLine());
						} else {
							type = Constants.PIECE_TYPE_DEFAULT;
						}
						
						int res = board.move(type, x1, y1, x2, y2);
						System.out.println("MOVE RESULT: " + (res == 1 ? "SUCCESS" : "FAILURE"));
						board.printBoard();
						break;
						
					case 3:
						board.printBoard();
						break;
						
					default:
						System.out.println("Invalid seletion. Please try again.");
						break;
				}
				
				System.out.println("\n##############################################################################################");
				System.out.println("Please select:");
				System.out.println("1) Show all valid moves: \n2) Move: \n3) Print Board: \n4) Exit: ");
				
				
			}
		} catch (IOException e) {
			System.out.println("Ex: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
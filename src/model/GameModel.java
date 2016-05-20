package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.pieces.AbstractPiece;
import eventHandling.Subject;

/**
 * To handle interaction with the Controller and other classes in Model
 * 
 * @author Brett Robertson (s3437164)
 */
public class GameModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player1;
	private Player player2;
	private Player activePlayer;
	private Board board;
	private transient GameTimer timer;
	private int gameTime;
	private Turn turn;
	Subject eventHandler;
	private HistoryManager historyManager;

	public GameModel(Subject eventHandler) {
		this.eventHandler = eventHandler;
		turn = new Turn();
		this.board = new Board(this);
		timer = new GameTimer(this.eventHandler);
		historyManager = new HistoryManager();
	}

	/**
	 * Initialize the game with board size and set the timer length
	 * 
	 * @param boardSize
	 * @param gameTime
	 */
	public void initGame(int boardSize, int gameTime) {
		board.initBoard(boardSize);
		this.setGameTime(gameTime);
	}

	/**
	 * Set local players and set player 1 to first active player by default
	 * 
	 * @param player1Name
	 * @param player2Name
	 * @post.condition player1 != null <br>
	 *                 player2 != null <br>
	 *                 activePlayer == player1
	 */
	// TODO: BRETT - if we are only using player ID in activePlayer then why not
	// just use int instead of entire object
	public void createPlayers(String player1Name, String player2Name) {

		player1 = new Player(1, player1Name);
		player2 = new Player(2, player2Name);
		activePlayer = new Player(1, "ACTIVE PLAYER");

	}

	/**
	 * @param totalTurns
	 * @post.condition turn.totalTurns == totalTurns
	 */
	// TODO pull this logic from controller
	public void setTurns(int totalTurns) {
		turn.setTotalTurns(totalTurns);
	}

	/**
	 * @return 2D array of tiles representing the current state of the board
	 * @pre.condition board != null
	 */
	public Tile[][] getTiles() {
		return board.getTiles();
	}

	/**
	 * @param timerSubject
	 * @return GameTimer
	 */
	public GameTimer getTimer() {
		return timer;
	}

	/**
	 * Starts the game timer which will count down from timer length
	 * timerSubject is the observer to notify of events
	 * 
	 * @param timerSubject
	 * @post.condition timer.time == time
	 */

	/**
	 * resets the game timer back to zero and calls cancel on the timer object
	 * 
	 * @pre.condition timer != null
	 * @post.condition gamerTime.time == time
	 */

	/**
	 * Sets score for active player before changing active players
	 * 
	 * @pre.condition activePlayer == 1 || activePlayer == 2
	 * @post.condition @pre.condition activePlayer == 1 || activePlayer == 2 <br>
	 *                 activePlayer.score += score
	 */
	// TODO: BRETT - if we are only using player ID in activePlayer then why not
	// just use int instead of entire object
	public void swapActivePLayer() {
		System.out.println(">>>>>>>SWAP ACTIVE PLAYER = "
				+ activePlayer.getId() + ", active score = "
				+ activePlayer.getScore()); // player1 =
											// " + player1.getId() + ", score1 =
											// " + player1.getScore() + ",
											// player2 =
											// " + player2.getId() + ", score2 =
											// " + player2.getScore());
		try {
			if (activePlayer.getId() == 1) {
				player1.addScore(activePlayer.getScore());
				board.setCurrentPlayer(2);
				activePlayer.setId(2);

			} else {
				player2.addScore(activePlayer.getScore());
				board.setCurrentPlayer(1);
				activePlayer.setId(1);
			}
		} catch (Exception e) {
			System.out.println("Swapping players issue: "
					+ e.getLocalizedMessage());
		}
		System.out.println(">>>>>>>AFTER player1 score = " + player1.getScore()
				+ ", player2 score = " + player2.getScore());
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @return the turn
	 */
	public Turn getTurn() {
		return turn;
	}

	/**
	 * @return activePlayer
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @return the score
	 * @pre.condition activePlayer != null
	 */
	public int getScore() {
		if (activePlayer.getId() == 1) {
			return player1.getScore();
		} else
			return player2.getScore();
	}

	/**
	 * @return the gameTime
	 */
	public int getGameTime() {
		return gameTime;
	}

	/**
	 * Sets the time the game will run to -
	 * 
	 * @param gameTime
	 * 
	 * @post.condition gameTime == gameTime
	 */
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	/**
	 * @param row
	 *            - board position
	 * @param column
	 *            - board position
	 * @return boolean
	 */
	public boolean isMyPiece(int row, int column) {

		Tile tile = board.getTile(row, column);
		return (!tile.isEmpty() ? tile.piece
				.isPlayerPiece(activePlayer.getId()) : false);

		/*
		 * //if (tile.piece.hasPieces()) { if(!tile.isEmpty()) { // must be like
		 * this, if there is no pieces get first piece throws // exception
		 * System.out.print("tile id: ");
		 * System.out.print(tile.piece.getFirstPiece().getPlayerId());
		 * System.out.print(" active id: ");
		 * System.out.println(activePlayer.getId()); if
		 * (tile.piece.getFirstPiece().getPlayerId() == activePlayer.getId())
		 * return true; }
		 * 
		 * return false;
		 */
	}

	/**
	 * @param row
	 *            - board position
	 * @param column
	 *            - board position
	 * @return boolean
	 * @pre.condition board.length == 2 <br>
	 *                board[0] >= row <br>
	 *                board.[1] >= column
	 */
	public boolean isComposite(int row, int column) {

		Tile tile = board.getTile(row, column);
		return tile.piece.isComposite();
	}

	/**
	 * @param selectedPieces
	 * @param int row - board position
	 * @param int column - board position
	 * @return ArrayList<Integer[]>
	 */
	public ArrayList<Integer[]> getValidMoves(
			ArrayList<AbstractPiece> selectedPieces, int row, int column) {
		ArrayList<Integer[]> moves = board.findValidMoves(selectedPieces, row,
				column);
		return moves;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public ArrayList<AbstractPiece> getPieces(int row, int column) {
		return board.getTile(row, column).piece.getPieces();
	}

	/**
	 * @param selectedPieces
	 * @param xSelextedPiece
	 * @param ySelectedPiece
	 * @param xMoveTile
	 * @param yMoveTile
	 * @return int
	 * @pre.condition <br>
	 *                xSelextedPiece < tile[0].length <br>
	 *                ySelextedPiece < tile[1].length
	 * @post.condition score = score + boardMove <br>
	 *                 xMoveTile < tile[0].length <br>
	 *                 yMoveTIle < tile[1].length
	 */
	public int moveSelectedPiece(ArrayList<AbstractPiece> selectedPieces,
			int xSelextedPiece, int ySelectedPiece, int xMoveTile, int yMoveTile) {

		int score = board.move(selectedPieces, xSelextedPiece, ySelectedPiece,
				xMoveTile, yMoveTile);
		// System.out.println(">>>>>>>>>>>>>>>>>>MOVE = " + score +
		// " for player = " + activePlayer.getId());
		activePlayer.setScore(score);
		// System.out.println(">>>>>>>>>>>>>>>>>>AFTER player = " +
		// activePlayer.getId());
		return 1;
	}

	public String getWinner() {
		if (getScores().get(0) > getScores().get(1)) {
			return "Player: " + player1.getName() + " is the WINNER";
		} else if (getScores().get(0) < getScores().get(1)) {
			return "Player: " + player2.getName() + " is the WINNER";
		} else
			return "GAME DRAWN";
	}

	public List<Integer> getScores() {
		return new ArrayList<Integer>(Arrays.asList(player1.getScore(),
				player2.getScore()));
	}

	public int getCurrentTurn() {
		return turn.getCurrentTurn();
	}

	public void startTimer() {
		timer.startTimer(false);

	}

	public void resetGameTimer() {
		timer.startTimer(true);

	}

	/**
	 * Call to static method save game
	 * saves game in current game location with name (SaveGame.saveLocation)
	 * @throws IOException
	 */
	public void SaveGame() throws IOException {
		try {
			SaveGame.saveGame(this);
		} catch (IOException e) {
			throw new IOException();

		} catch (Exception e) {
			System.out
					.println("Something went wrong" + e.getLocalizedMessage());

		}
	}
	
	/**
	 * deserializes gameModel from saved game (SaveGame.saveLocation)
	 * @return GameModel
	 * @throws IOException
	 */
	public GameModel  RestartGame() throws IOException{
		GameModel model = SaveGame.restartGame();
		return model;
	}
	
	/**
	 * Tests to see if there is a saved game 
	 * @return boolean
	 */
	public boolean isSavedGame(){
		File file = new File(SaveGame.saveLocation);
		if(file.exists()){
			return true;
		}
		return false;
	}
}

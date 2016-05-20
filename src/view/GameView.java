package view;

import java.util.ArrayList;
import java.util.Arrays;
import view.BoardView;
import view.NewGameDialogue;
import view.PlayerStatusView;

/**
 * The View 'API' interface for other components. Most methods are direct wrappers.
 * Delegate pattern. Ready for RMI.
 * 
 * @author keltie
 * @param <ChessGame>
 *
 */
public class GameView {
	private static class InstanceHolder {
		private static final GameView instance = new GameView(); 
	}
	
	public static GameView getInstance(){
		return InstanceHolder.instance;
	}

	// variables needed to throw around non-static methods
	private static int openingBoardSize = 2;
	static view.GameData gameData;
	static BoardView boardView;
	
	//new game dialogue test
	private static ArrayList<String> gameConfigList = new ArrayList<String>(Arrays.asList(
			"(from GameView) Game Config 1", "Game Config 2", "Game Config 3", "Game Config 4"));
	private static ArrayList<String> gamePendingList = new ArrayList<String>(Arrays.asList(
			"(from GameView) Game Waiting 1", "Game Waiting 2", "Game Waiting 3"));
	private final static int hostPlayer = 0;
	private final static int joinPlayer = 1;
	
	/*
	static private Context initialContext;
	private ChessGame chessGame;
	
	private GameView(){
		try {
			String remoteJNDIName = ChessGame.JNDI_NAME;
 
			Object objRef = initialContext.lookup(remoteJNDIName);
 
			Object obj = PortableRemoteObject.narrow(objRef,
					ChessGame.class);
 
			chessGame = (ChessGame) obj;
 
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	*/
	
	
	/*
	 * GUILayoutFactory public methods
	 */
	/*
	 * @precontion: none.
	 * @postcontion: The view gui widgets are assembled with singleton classes instantiated.
	 */
	/**
	 * @param eventHandler
	 * @return
	 */
	public static GUILayoutFactory initGUI(eventHandling.Subject eventHandler){
		//first call to initialise gui!
		return new GUILayoutFactory(eventHandler, BoardView.getInstance());
	}
	
	public static boolean isInstanceRunning(){
		return GUILayoutFactory.isInstanceRunning();
	}
	public static void showGeneralPurposeDialogue(String input){
		GeneralPurposeDialogue.getInstance();
		GeneralPurposeDialogue.launchDialogue(boardView.getChessBoard(), input);
	}
	
	/*
	 * NewGameDialogue public methods
	 */
	/*
	 * @precontion: The GUILayoutFactory has to have been called.
	 * @postcontion: The welcome dialogue is instantiaed, initialised and drawn ready for input.
	 */
	/**
	 * @param eventHandler
	 * @return
	 */
	public static boolean initWelcomeDialog(eventHandling.Subject eventHandler, int playerType){
		//eventHandler This method is called early so we can init the gameData object here for now.
		//if the user cancels the dialogue, we don't also want to void the current gamedata. 
		NewGameDialogue.getInstance(eventHandler);
		
		setGamePlayerType(playerType);
		setGameConfigList(gameConfigList);
		setGamePendingList(gamePendingList);
		
		if (NewGameDialogue.getInstance(eventHandler) == null){
			return false;
		}
		return true;
	}
	
	public static boolean initWelcomeDialog(eventHandling.Subject eventHandler, int playerType,
			ArrayList<String> gameConfigList, ArrayList<String> gamePendingList){
		//eventHandler This method is called early so we can init the gameData object here for now.
		//if the user cancels the dialogue, we don't also want to void the current gamedata. 
		NewGameDialogue.getInstance(eventHandler);
		
		setGamePlayerType(playerType);
		setGameConfigList(gameConfigList);
		setGamePendingList(gamePendingList);
		
		if (NewGameDialogue.getInstance(eventHandler) == null){
			return false;
		}
		return true;
	}
	
	public static NewGameData drawWelcomeDialog(){
		
		NewGameData newGameData = NewGameDialogue.launchDialogue();
		gameData = initGameData();

		if (newGameData == null){
			return null;
		}
		return newGameData;
	}
	
	/*
	 * @invariant: 
	 * @precontion: The new game dialogue has been submitted with success.
	 * @postcontion: The new game fields are returned as a NewGameData object.
	 */
	/**
	 * @return NewGameData The new game dialogue fields as Java (final) struct.
	 * 
	 *	int sessionID;
	 *	ArrayList<String> playerNames;
	 *	int totalTurns;
	 *	int boardSize;
	 *	int timeoutTurns;
	 *	boolean playMachine;
	 *	String gameHostConfiguration;
	 *	String gameJoinAvailable;
	 */
	public static NewGameData getNewGameData(){
		return NewGameDialogue.getNewGameData();
	}
	
	/*
	 * @invariant: boardSize > 0
	 * @precontion: The gui spinner has set a value either by default or submitting the welcome dialogue.
	 * @postcontion: The boardsize (X=Y) is returned as an integer.
	 */
	/**
	 * @return
	 */
	public static int getBoardSize(){
		return NewGameDialogue.getNewGameData().boardSize;
	}

	/*
	 * @invariant: 10 < totalTurns > 50
	 * @precontion: The gui spinner has set a value either by default or submitting the welcome dialogue.
	 * @postcontion: The number of turns each players has for the game duration is returned as an integer.
	 */
	/**
	 * @return
	 */
	public static int getTotalTurns(){
		return NewGameDialogue.getNewGameData().totalTurns;
	}	
	
	/*
	 * @invariant: 10 < timeout > 50
	 * @precontion: The gui spinner has set a value either by default or submitting the welcome dialogue.
	 * @postcontion: The timeout value that a player has on each turn is returned as an integer.
	 */
	/**
	 * @return
	 */
	public static int getTimeoutTurns(){
		return NewGameDialogue.getNewGameData().timeoutTurns;
	}
	
	/*
	 * @precontion: The gui textfield has set a value either by default or submitting the welcome dialogue.
	 * @postcontion: The two player names are are returned as strings in an  ArrayList.
	 */
	/**
	 * @return
	 */
	public static ArrayList<String> getPlayerNames(){
		return NewGameDialogue.getNewGameData().playerNames;
	}	

	/*
	 * @precontion: The welcome dialogue has closed successfully marking the start of a new game.
	 * @postcontion: The game started/finished status is returned as true or false.
	 */
	/**
	 * @return
	 */
	public static Boolean getGameStarted(){
		return NewGameDialogue.getGameStarted();
	}
	
	/*
	 * @precontion:
	 * @postcontion: A message printed on the console's text area with the calling class name. 
	 */
	/**
	 * @param object
	 * @param output
	 */
	public static void setGameConfigList(ArrayList<String> gameConfigList){
		NewGameDialogue.setGameConfigList(gameConfigList);
	}
	public static int getSelectedGameConfig(){
		return NewGameDialogue.getSelectedGameConfig();
	}
	
	public static void setGamePendingList(ArrayList<String> gamePendingList){
		NewGameDialogue.setGamePendingList(gamePendingList);
	}
	public static int getSelectedGamePending(){
		return NewGameDialogue.getSelectedGamePending();
	}
	public static void setGamePlayerType(int playerType){
		NewGameDialogue.setPlayerType(playerType);
	}	
	
	
	/*
	 * PlayerStatusView public methods
	 */
	
	/**
	 * @return
	 */
	public static void updateStatus(){
		// Will call the PlayerStatusView to read from the current GameView.gameData object
		// and update each field. This is the same as calling the individual setters below 
		// (assuming that the controller updates the gameData object. 
		PlayerStatusView.update();
	}	

	/*
	 * @precontion: The current game player has changed and the view requires an update. 
	 * @precontion: The current player in passed in as an integer 0 or 1. 
	 * @postcontion: The current player is shown in the status as active, the current players pieces are rendered enabled.
	 */
	/**
	 * @param currentPlayer
	 */
	public static void setCurrentPlayer(int currentPlayer){
		//System.out.println("GameData init: player names: " + 
		//		gameData.getPlayerNames().get(0) + ", " + 
		//		gameData.getPlayerNames().get(1) + ", " +
		//		gameData.getCurrentPlayer() + ", " +
		//		gameData.getScore().get(0) + ":" + gameData.getScore().get(1) + ", " +
		//		gameData.getTurnsRemaining().get(0) + ":" + gameData.getTurnsRemaining().get(1) + ", " +
		//		gameData.getTimeRemaining().get(0) + ":" + gameData.getTimeRemaining().get(1)
		//		);
	
		gameData.setCurrentPlayer(currentPlayer);
		//PlayerStatusView.setCurrentPlayer(currentPlayer);
		PlayerStatusView.update();
	}

	/*
	 * @precontion: 
	 * @postcontion: T
	 */
	/**
	 * @return
	 */
	public static void setPlayerNames(ArrayList<String> playerNames){
		gameData.setPlayerNames(playerNames);
		//System.out.println("setPlayerNames: player names: " + 
		//		gameData.getPlayerNames().get(0) + ", " + 
		//		gameData.getPlayerNames().get(1));
		//PlayerStatusView.setPlayerNames(playerNames);
		PlayerStatusView.update();
	}	
	
	/*
	 * @invariant: 10 < totalTurns > 50
	 * @invariant: turnCount >= totalTurns
	 * @precontion: The number of remaining turns for a player has changed and the view needs an update.
	 * @precontion: The number of remaining turns for the current player is passed in as an integer.
	 * @postcontion: The current player's remaining turns are shown in the status.
	 */
	/**
	 * @param turn
	 */
	public static void setTurnCount(int turn){
		gameData.setTurnsRemaining(turn);
		PlayerStatusView.update();
	}
	
	/*
	 * @precontion: The score has changed for a player and the view needs an update.
	 * @precontion: The score for the current player is passed in as an integer.
	 * @postcontion: The current player's score is shown in the status.
	 */
	/**
	 * @param score
	 */
	public static void setScore(int newScore){
		gameData.setScore(gameData.getCurrentPlayer(), newScore);
		//System.out.println("setScore: scores: " + 
		//		gameData.getScore().get(0) + ", " + 
		//		gameData.getScore().get(1));
		//PlayerStatusView.setScore(newScore);
		PlayerStatusView.update();
	}
	
	/*
	 * @precontion: The timer's countdown has changed for a player and the view needs an update.
	 * @precontion: The timer's current value for the current player is passed in as an integer.
	 * @postcontion: The current player's remaining time for the turn is shown in the status.
	 */
	/**
	 * @param seconds
	 */
	public static void updateTime(int seconds){
		gameData.setTimeRemaining(seconds);
		PlayerStatusView.update();
	}
	

	/*
	 * BoardView public methods
	 */
	/**
	 * @param boardSize
	 */
	public static BoardView initBoardView() {
		boardView = BoardView.getInstance();
		boardView.initialiseBoard(BoardView.getInstance().getControllerEventHandler());
		boardView.renderTiles();

		GUILayoutFactory.updateChessBoardComponent(boardView.getChessBoard(), 
				java.awt.BorderLayout.LINE_START, GUILayoutFactory.BOARDVIEW);

		return boardView;
	}
	
	
	/*
	 * @invariant: tiles[n][m] == tile[coord x=n][coord y=m]
	 * @precontion: BoardView needs to have been previously instantiated. 
	 * @precontion: A 2D array of size boardSize of valid Tile objects are input.
	 * @postcontion: The tiles are parsed for their position, player and piece value(s) and rendered on the chessboard. 
	 */
	/**
	 * @param tiles
	 */
	public static void setTiles(model.Tile[][] tiles) {
		boardView.setTiles(tiles);
	}

	/*
	 * @invariant: tiles[n][m] == tile[coord x=n][coord y=m]  
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @postcontion: A 2D array of size boardSize of the current BoardView Tile objects are returned.
	 */
	/**
	 * @return
	 */
	public static model.Tile[][] getTiles() {
		return boardView.getTiles();
	}
	
	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @precontion: An ArrayList of type Integer[] is passed in with the valid value only tiles their position as {X,Y}
	 * @postcontion: The tiles are parsed for their position, and rendered on the chessboard.
	 * @postcontion: All other tiles are disabled from user input until the current piece tile is unselected or a move occurs.
	 */
	/**
	 * @param validMoves
	 */
	public static void setValidMoves(ArrayList<Integer[]> validMoves) {
		boardView.setValidMoves(validMoves);
	}
	
	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @precontion: 
	 * @postcontion: The user selected tile is rendered with a highlighting style.
	 */
	/**
	 * @param x
	 * @param y
	 */
	public static void drawSelectedPiece(int x, int y){
		BoardView.setSelectedFromTile(x, y);
	}

	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @precontion: 
	 * @postcontion: The tiles are cleared of their icons, set unselected and set enabled for input.
	 */
	/**
	 * 
	 */
	public static void clearAllSelected(){
		boardView.clearValidMoves();
	}

	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @precontion: 
	 * @postcontion: The valid moves selection tiles are cleared of their highlighting, set unselected.
	 */
	/**
	 * 
	 */
	public static void clearValidMoves(){
		boardView.clearValidMoves();
	}

	/*
	 * PieceVisualSelector public methods
	 */
	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @precontion: A tile is selected to move from which contains more than one piece.
	 * @precontion: The piece options are passed in as an ArrayList of type model.pieces.AbstractPiece. 
	 * @postcontion: A dialogue is drawn with checkbox widgets offering the possible selections.
	 * @postcontion: An ArrayList of type model.pieces.AbstractPiecets containing the composite selection is returned.
	 */
	/**
	 * @param pieces
	 * @return
	 */
	public static ArrayList<model.pieces.AbstractPiece> drawCompositeDialog(final ArrayList<model.pieces.AbstractPiece> pieces){
		return PieceVisualSelector.launchDialogue(boardView.getSelectedFromButton(), pieces);
	}
	
	/*
	 * @precontion: BoardView needs to have been previously instantiated.
	 * @postcontion: An ArrayList of type model.pieces.AbstractPiecets containing the last composite selection is returned.
	 * @postcontion: if no selection has been made or has been cleared, the list is empty.
	 */
	/**
	 * @return
	 */
	public static ArrayList<model.pieces.AbstractPiece> getCompositePieces(){
		return PieceVisualSelector.getCompositePieces();
	}
	
	/*
	 * TimeoutDialogue public methods
	 */
	/*
	 * @precontion:
	 * @postcontion: A message dialogue is drawn with the player's name alerting of a missed turn. 
	 */
	/**
	 * @param eventHandler
	 * @param player
	 * @return
	 */
	public static int drawTimeoutDialog(String player){
		TimeoutDialogue.getInstance();
		return TimeoutDialogue.launchDialogue(boardView.getChessBoard(), player);
	}
	
	/*
	 * WinnerDialogue public methods
	 */
	/*
	 * @precontion:
	 * @postcontion: A message dialogue is drawn with the player's name alerting of the winner. 
	 */
	/**
	 * @param eventHandler
	 * @param player
	 * @return
	 */
	public static int drawWinnerDialog(eventHandling.Subject eventHandler, String player){
		WinnerDialogue.getInstance(eventHandler);
		return WinnerDialogue.launchDialogue(boardView.getChessBoard(), player);
	}

	
	/*
	 * ConsoleView public methods
	 */
	/*
	 * @precontion:
	 * @postcontion: A message printed on the console's text area with the calling class name. 
	 */
	/**
	 * @param object
	 * @param output
	 */
	public static void writeDebugToGUIConsole(Class object, String output){
		ConsoleView.writeDebugToGUIConsole(object, output);
	}
	
	/*
	 * @precontion:
	 * @postcontion: A message printed on the console's text area. 
	 */
	/**
	 * @param output
	 */
	public static void writeToGUIConsole(String output){
		ConsoleView.writeToGUIConsole(output);
	}
	
	
	/*
	 * @precontion: One or more properties has changed for a player and the view needs an update.
	 * @precontion: The changed properties are passed in per the ArrayList encoding.
	 * @postcontion: The current player's updated properties are shown in the status.
	 */
	public static void setGameData(GameData gameData){
		GameView.gameData = gameData;
	}
	
	public static GameData getGameData(){
		return gameData;
	}
	
	public static view.GameData initGameData(){
		//quick init of our update struct
		ArrayList<String> names = new ArrayList<String>(Arrays.asList("init 1", "init 2"));
		ArrayList<Integer> scores = new ArrayList<Integer>(Arrays.asList(0,0));
		int turns = 0;
		int times = 0;
		
		view.GameData gameData = new view.GameData(0, 0, names,
				scores, turns, times);
		return gameData; 
	}
	
	

}

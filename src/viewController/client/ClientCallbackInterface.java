package viewController.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import view.NewGameData;

public interface ClientCallbackInterface extends Remote  {
	
	//*******************************************
	// current support until new i/f
	public int getBoardSize() throws RemoteException;
	public int getTotalTurns() throws RemoteException;
	public int getTimeoutTurns() throws RemoteException;
	public ArrayList<String> getPlayerNames() throws RemoteException;
	
	//*******************************************

	
	// retVal; 0 == success, 1 == fail
	public int drawWelcomeDialog(int playerNumber) throws RemoteException;
	public void showGeneralPurposeDialogue(String input)throws RemoteException;
	
	public void deregisterClient()throws RemoteException;
	public void playerLeftGame()throws RemoteException;
	public void waitingForPlayer1Dialogue()throws RemoteException;
	/*
	 * NewGameData =
	 * 	int sessionID;
	 *	ArrayList<String> playerNames;
	 *	int totalTurns;
	 *	int boardSize;
	 *	int timeoutTurns;
	 *	boolean playMachine;
	 *	boolean showValidMoves;
	 *	boolean showConsole;
	 *	boolean showGraveyard;
	 *	boolean showToolBar;
	 *	boolean showPlayerStatus; 
	 * */
	public void initBoardView() throws RemoteException;
	public boolean isInstanceRunning()throws RemoteException;
	public void initGUI()throws RemoteException;
	
	public NewGameData getNewGameData()throws RemoteException;
	// true if game has started
	public  Boolean getGameStarted()throws RemoteException;
	
	public void setCurrentPlayer(int currentPlayer)throws RemoteException;
	
	public void setPlayerNames(ArrayList<String> playerNames)throws RemoteException;
	
	public void setTurnCount(int turn)throws RemoteException;
	
	void setScore(int newScore)throws RemoteException;
	
	void updateTime(int seconds)throws RemoteException;
	
	public void setTiles(model.Tile[][] tiles)throws RemoteException;
	
	public model.Tile[][] getTiles() throws RemoteException;
	
	public void setValidMoves(ArrayList<Integer[]> validMoves)throws RemoteException;
	
	public void drawSelectedPiece(int x, int y)throws RemoteException;
	
	public void clearAllSelected() throws RemoteException;
	
	public int resetPlayerStatus(int player)throws RemoteException;
	
	public ArrayList<model.pieces.AbstractPiece> drawCompositeDialog(final ArrayList<model.pieces.AbstractPiece> pieces)throws RemoteException;
			  
	public ArrayList<model.pieces.AbstractPiece> getCompositePieces()throws RemoteException;
	
	public int drawTimeoutDialog(String player)throws RemoteException;
	
	int drawWinnerDialog( String player)throws RemoteException;
	
	public void registerPlayer(int playerNumber)throws RemoteException;
	public void opponentRegistered()throws RemoteException;
	public void unregisterPlayer()throws RemoteException;
	
}

package viewController.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.Tile;
import model.pieces.AbstractPiece;
import view.NewGameData;
import viewController.ViewController;

public class ClientCallback extends UnicastRemoteObject implements ClientCallbackInterface {
	private static final long serialVersionUID = 1L;
	private int playerNumber;
	private ViewController controller;
	Client client;
	
	protected ClientCallback(Client client) throws RemoteException {
		super();
		this.client = client;

	}
	
	
	//*******************************************
	// current support until new i/f
	@Override
	public int getBoardSize() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getBoardSize();
	}



	@Override
	public int getTotalTurns() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getTotalTurns();
	}



	@Override
	public int getTimeoutTurns() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getTimeoutTurns();
	}



	@Override
	public ArrayList<String> getPlayerNames() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getPlayerNames();
	}
	
	//*******************************************
	
	@Override
	public void setTiles(model.Tile[][] tiles) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setTiles(tiles);
	}

	@Override
	public int resetPlayerStatus(int player) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.resetPlayerStatus(player);
		return 0;
	}

	@Override
	public int drawWelcomeDialog(int playerNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.drawWelcomeDialog(playerNumber);
	}

	@Override
	public NewGameData getNewGameData() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getNewGameData();
	}

	@Override
	public Boolean getGameStarted() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getGameStarted();
	}

	@Override
	public void setCurrentPlayer(int currentPlayer) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setCurrentPlayer(currentPlayer);
	}

	@Override
	public void setPlayerNames(ArrayList<String> playerNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setPlayerNames(playerNames);
	}

	@Override
	public void setTurnCount(int turn) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setTurnCount(turn);
	}

	@Override
	public void setScore(int newScore) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setScore(newScore);
	}

	@Override
	public void updateTime(int seconds) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.updateTime(seconds); 
	}

	@Override
	public Tile[][] getTiles() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getTiles();
	}

	@Override
	public void setValidMoves(ArrayList<Integer[]> validMoves)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.client.setValidMoves(validMoves);
	}

	@Override
	public void drawSelectedPiece(int x, int y) throws RemoteException {
		// TODO Auto-generated method stub
		this.client.drawSelectedPiece( x, y);
	}

	@Override
	public void clearAllSelected() throws RemoteException {
		// TODO Auto-generated method stub
		this.client.clearAllSelected();
	}

	@Override
	public ArrayList<AbstractPiece> drawCompositeDialog(
			ArrayList<AbstractPiece> pieces) throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.drawCompositeDialog(pieces);
	}

	@Override
	public ArrayList<AbstractPiece> getCompositePieces() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.getCompositePieces();
	}

	@Override
	public int drawTimeoutDialog(String player) throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.drawTimeoutDialog(player) ;
	}

	@Override
	public int drawWinnerDialog( String player)
			throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.drawWinnerDialog(player);
	}
	
	@Override
	public boolean isInstanceRunning() throws RemoteException {
		// TODO Auto-generated method stub
		return this.client.isInstanceRunning();
	}

	@Override
	public void initGUI() throws RemoteException {
		// TODO Auto-generated method stub
		this.client.initGUI();
	}

	@Override
	public void initBoardView() throws RemoteException {
		// TODO Auto-generated method stub
		this.client.initBoardView();
	}


	// Register methods
	
	public void registerPlayer(int playerNumber)throws RemoteException{
		if(playerNumber == 1){
			this.client.registerPlayer(playerNumber);
			
		}else if(playerNumber == 2){
			System.out.println("player2");
			this.client.registerPlayer(playerNumber);
		}
		this.playerNumber = playerNumber;
		
	}
	public void opponentRegistered()throws RemoteException{
		System.out.println("player2 Online");
		this.client.opponentRegistered();
	}
	
	public void unregisterPlayer()throws RemoteException{
		System.out.println("player " + this.playerNumber + " unregistered");
		this.playerNumber = 0;
		
	}

	@Override
	public void waitingForPlayer1Dialogue() throws RemoteException {
		// TODO Auto-generated method stub
		this.controller.getEventHandler().setEvent(Constant.Constants.P1_WAIT);
	}

	@Override
	public void playerLeftGame() throws RemoteException {
		// TODO Auto-generated method stub
		this.controller.getEventHandler().setEvent(Constant.Constants.LEFT_GAME);
	}

	@Override
	public void deregisterClient() throws RemoteException {
		// TODO Auto-generated method stub
		this.controller.getClient().unrgegisterFromServer();
	}


	@Override
	public void showGeneralPurposeDialogue(String input) throws RemoteException {
		// TODO Auto-generated method stub
		this.controller.getClient().showGeneralPurposeDialogue(input);
	}

}

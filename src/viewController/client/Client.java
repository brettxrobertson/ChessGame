package viewController.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import model.Tile;
import model.pieces.AbstractPiece;
import RMIInterface.ServerRemote;
import view.GameView;
import view.NewGameData;
import viewController.ViewController;

public class Client {
	private Registry reg;
	private ServerRemote server;
	private ClientCallbackInterface callback;
	int playerNumber;
	boolean registered;
	boolean opponentResgistered;
	ViewController controller;
	
	public Client(ViewController controller) {
		this.controller = controller;
		// get the registry to the Server on 127.0.0.1 for the server port
		try {
			System.out.println("Registering Server");
			registerServer();
			System.out.println("Server Registered");
			if(connectCallback() == false){
				System.out.println("Game Already Has 2 Players");
				System.out.println("Exiting");
				System.exit(0);
			}
						
			System.out.println("Callback Registered");
			
		} catch (RemoteException | NotBoundException e ) {
			
			System.out.println("Create Client Failed!");
			System.exit(0);
			e.printStackTrace();
		}

	}
	
	private void registerServer() throws RemoteException, NotBoundException{
		reg = LocateRegistry.getRegistry("localhost",modelController.server.Constants.CONTROLLER_PORT);
		// create a local copy of the server's interface
		server =  (ServerRemote)reg.lookup(modelController.server.Constants.CONTROLLER_ID);
	}
	
	private boolean connectCallback() throws RemoteException{

		// register the clients call backs with the Server for asynchronous calling
		callback = (ClientCallbackInterface) new ClientCallback(this);
		return server.registerForCallback(callback);
	}
	
	public void SendServerEvent(String event){
		 try {
			server.sendServerEvent(event);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unrgegisterFromServer() {
		 try {
			server.unregisterForCallback(callback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getPlayerNumber(){return this.playerNumber;}
	public boolean playerRegister(){return this.registered;}
	
	public void registerPlayer(int in){
		playerNumber = in; 
		registered = true;
	}
	
	public void unregisterPlayer(int in){
		playerNumber = 0; 
		registered = false;
	}
	
	
	public void opponentRegistered(){
		opponentResgistered = true; 
	}
	
	
	public void finalize()  {
		try {
			server.unregisterForCallback(callback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int drawWelcomeDialog(int playerType){
		GameView.initWelcomeDialog(this.controller.getEventHandler(), playerType);
		GameView.drawWelcomeDialog();
		System.out.println("Welcome");
		return 0;
	}
	
	
	//*******************************************
	// current support until new i/f
	public int getBoardSize() {
		// TODO Auto-generated method stub
		return GameView.getBoardSize();
	}


	public int getTotalTurns(){
		// TODO Auto-generated method stub
		return GameView.getTotalTurns();
	}

	public int getTimeoutTurns() {
		// TODO Auto-generated method stub
		return GameView.getTimeoutTurns();
	}

	public ArrayList<String> getPlayerNames() {
		// TODO Auto-generated method stub
		return GameView.getPlayerNames() ;
	}
	
	//*******************************************
	
	public void setTiles(model.Tile[][] tiles) {
		// TODO Auto-generated method stub
		GameView.setTiles(tiles);
	}

	public int resetPlayerStatus(int player) {
		// TODO Auto-generated method stub
		GameView.setCurrentPlayer(player);
		GameView.setScore(0);
		GameView.updateTime(30);
		return 0;
	}


	public NewGameData getNewGameData() {
		// TODO Auto-generated method stub
		return GameView.getNewGameData();
	}

	public Boolean getGameStarted() {
		// TODO Auto-generated method stub
		return GameView.getGameStarted();
	}

	public void setCurrentPlayer(int currentPlayer) {
		// TODO Auto-generated method stub
		GameView.setCurrentPlayer(currentPlayer);
	}

	public void setPlayerNames(ArrayList<String> playerNames){
		// TODO Auto-generated method stub
		GameView.setPlayerNames(playerNames);
	}

	public void setTurnCount(int turn) {
		// TODO Auto-generated method stub
		GameView.setTurnCount(turn);
	}

	public void setScore(int newScore){
		// TODO Auto-generated method stub
		GameView.setScore(newScore);
	}

	public void updateTime(int seconds){
		// TODO Auto-generated method stub
		GameView.updateTime(seconds); 
	}

	public Tile[][] getTiles(){
		// TODO Auto-generated method stub
		return GameView.getTiles();
	}

	public void setValidMoves(ArrayList<Integer[]> validMoves){
		// TODO Auto-generated method stub
		GameView.setValidMoves(validMoves);
	}

	public void drawSelectedPiece(int x, int y) {
		// TODO Auto-generated method stub
		GameView.drawSelectedPiece( x, y);
	}

	public void clearAllSelected() {
		// TODO Auto-generated method stub
		GameView.clearAllSelected();
	}

	public ArrayList<AbstractPiece> drawCompositeDialog(
			ArrayList<AbstractPiece> pieces) {
		// TODO Auto-generated method stub
		return GameView.drawCompositeDialog(pieces);
	}

	public ArrayList<AbstractPiece> getCompositePieces() {
		// TODO Auto-generated method stub
		return GameView.getCompositePieces();
	}

	public int drawTimeoutDialog(String player){
		// TODO Auto-generated method stub
		return GameView.drawTimeoutDialog(player) ;
	}

	public int drawWinnerDialog(String player) {
		// TODO Auto-generated method stub
		return GameView.drawWinnerDialog(this.controller.getEventHandler(),player);
	}


	public boolean isInstanceRunning() {
		// TODO Auto-generated method stub
		return GameView.isInstanceRunning();
	}


	public void initGUI() {

		//GK - add, check that gui is not already running
	//// if the new game dialogue returns 1 however (cancel) we should leave the current game alone.
		if (!GameView.isInstanceRunning()){
			GameView.initGUI(this.controller.getEventHandler());
		}
/////////GK - add
		// refresh the board
		GameView.initBoardView();
		
	}

	public void initBoardView() {
		// TODO Auto-generated method stub
		 GameView.initBoardView();
	}

	public void showGeneralPurposeDialogue(String input) {
		// TODO Auto-generated method stub
		GameView.showGeneralPurposeDialogue(input);
	}
}

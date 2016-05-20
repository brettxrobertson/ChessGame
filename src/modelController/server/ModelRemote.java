package modelController.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import viewController.client.ClientCallbackInterface;
import eventHandling.Subject;
import RMIInterface.ServerRemote;

public class ModelRemote extends UnicastRemoteObject implements ServerRemote {
	private static final long serialVersionUID = 1L;

	private ClientCallbackInterface player1Callback;
	private ClientCallbackInterface player2Callback;
	private boolean player1_Registered;
	private boolean player2_Registered;
	private int playersRegistered = 0;
	
	private Subject eventHandler;
	
	protected ModelRemote(Subject eventHandler) throws RemoteException {
		super();
		this.eventHandler = eventHandler;
		playersRegistered = 0;
	}
	
	public ClientCallbackInterface getCallbackP1(){
		return player1Callback;
	}

	public ClientCallbackInterface getCallback(int player){
		if(player == 1){
			return player1Callback;
		}else if(player == 2){
			return player2Callback;
		}
		return null;
	}
	
	public boolean getPlayer1_registered(){return player1_Registered;}
	public boolean getPlayer2_registered(){return player1_Registered;}
	public int getRegisteredPlayers(){return playersRegistered;}
	
	
	@Override
	public void sendServerEvent(String event)throws RemoteException{
		this.eventHandler.setEvent(event);
	}

	
	// implementation to allow clients to register callbacks
	public synchronized  boolean registerForCallback(ClientCallbackInterface callbackClientObject)
			throws RemoteException {
			     // store the callback object into the vector
		
		if(playersRegistered < 2){
			 if (playersRegistered == 0){
				 player1_Registered = true;
				 player1Callback = callbackClientObject;
				 playersRegistered++;
				 handlePlayerRegistration(1);
			}else if(playersRegistered == 1){
				player2_Registered = true;
				player2Callback = callbackClientObject;
				 playersRegistered++;
				 handlePlayerRegistration(2);
			}
			
			 return true;
		}
		return false;
	}
	@Override
	public synchronized  void unregisterForCallback(ClientCallbackInterface callbackClientObject)
			throws RemoteException {
		if(callbackClientObject.equals(player1Callback)){
			player1Callback.unregisterPlayer();
			player1Callback = null;
		}else if(callbackClientObject.equals(player2Callback)){
			player2Callback.unregisterPlayer();
			playersRegistered--;
			player2Callback = null;
		}
	      System.out.println("Unregistered players - nothing actually done ");
	}
	
	
	private synchronized void handlePlayerRegistration(int player) throws RemoteException{
		
		if(player == 1){
			player1Callback.registerPlayer(1);
			if(player2_Registered == true)
				player2Callback.opponentRegistered();
		}else if(player == 2){
			if(player1_Registered == true)
				player1Callback.opponentRegistered();
			player2Callback.registerPlayer(2);
		}
		System.out.println( playersRegistered + " Players Informed of Callback Registration");
			
	}




	
}

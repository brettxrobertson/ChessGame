package RMIInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import viewController.client.ClientCallbackInterface;


public interface ServerRemote extends Remote {

	// allow the client to register their callbacks
	  public boolean registerForCallback(ClientCallbackInterface callbackClientObject) throws RemoteException;
	  public void unregisterForCallback( ClientCallbackInterface callbackClientObject) throws RemoteException;
	// Client methods to call either to update the server or request data.
	  public void sendServerEvent(String event)throws RemoteException;
}
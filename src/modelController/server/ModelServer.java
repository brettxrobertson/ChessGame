package modelController.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import eventHandling.Subject;
import server.server;
import viewController.client.ClientCallbackInterface;

public class ModelServer extends server{
	private ModelRemote impl;
	
	
	
	public ModelServer(Subject eventHandler){
		super();
		
		try {
			impl = new ModelRemote(eventHandler);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void startServer(int port, String RMI_ID){

		try {
			this.setRegistry(LocateRegistry.createRegistry(port));
			this.getRegistry().bind(RMI_ID , impl);
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("start server");
		
	}
	
	public ClientCallbackInterface callClient(int player){
			return this.impl.getCallback(player);
	}
	
	
	public ClientCallbackInterface callPlayer1(){
		return this.impl.getCallbackP1();
}
	
	public boolean playerRegistered(int player){
		if(player == 1 && this.impl.getRegisteredPlayers() > 0){
			return true;
		}else if(player == 2 && this.impl.getRegisteredPlayers() > 1){
			return true;
		}
			
		return false;
	}
		

}

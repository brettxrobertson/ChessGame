package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public abstract class server {
	private Registry reg;
	
	public Registry getRegistry(){return reg;}
	public void setRegistry(Registry in){reg = in;}
	
	public abstract void startServer(int port, String RMI_ID)throws RemoteException,
		java.rmi.AlreadyBoundException;
}



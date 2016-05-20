package controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import eventHandling.*;


// SRP - The single responsibility is to create the required instances (model, event handler and state machine) and initialise the GUI
// OCP - The implements instances of other classes. The class is open for extension using other closed classes.
// by closing the other classes the high level controller can implement tested and closed classes to control the 
// initialisation of the classes it creates.
// LSP -
// ISP -
// DIP - 

public abstract class Controller {
	
	private Subject eventHandler;
	
	/**
	 * Controller Constructor 
	 * <p> 
	 * Post-condition: <br>
	 * 1. Creates an instance of the eventHandler and attaches the new game, tile and timeout observers<br>
	 * 2. Creates an instance of the model<br>
	 * 3. Creates an instance of the view (currently view is static) and initialises the GUI<br>
	 * 4. Set the state machine to state zero (start state) and unlocks the state machine.<br>
	 * 5. To be implemented, create an instance of the state machine and connect states.<br>
	 * @author Troy
	 */
	public Controller(){
		
		eventHandler = new Subject();
		
	}
	
	public Subject getEventHandler(){ return eventHandler;}
	
	/**
	 * startGame
	 * <p>
	 * Pre-Condition: the constructor has correctly initialise view, created model and setup the state machine and event handler
	 * @param void
	 * <p>
	 * Invariant: the controller will be forced to start the state machine in state zero
	 * <p>
	 * Pre-Condition: State machine is started in state zero
	 * @return void
	 * @author Troy
	 * @throws RemoteException 
	 * @throws InterruptedException 
	 * @throws NotBoundException 
	 */
	public abstract void start() throws RemoteException, InterruptedException, NotBoundException;
		
}

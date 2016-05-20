package viewController;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class StartViewController {
	
	private static ViewController controller;
	
	public static void main(String[] args) throws NotBoundException, RemoteException, InterruptedException {
		
		int errorCode = 0;
		initialise();
		
		System.out.println("View Controller Started");
		controller.start();

		System.out.println("View Controller Closed");	
		System.out.println(errorCode);
		
		System.exit(0);
	}
	
	public static void initialise() {
		
		controller = new ViewController();	
		System.out.println("View Controller Initialised");
		
	}

}

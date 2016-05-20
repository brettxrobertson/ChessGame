package modelController;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;


public class StartModelController {
	
	private static ModelController controller;
	
	/**
	 * startGame (start of program execution) or newGame Observer called, this state calls the welcome dialog
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x - Source x position
	 * @param y - Source y position
	 * @return ArrayList<Integer[]> - index 0: x position, index 1: y position, index 2: special benefit
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
		
		int errorCode = 0;
		initialise();
		
		System.out.println("Model Started");
		controller.start();
		System.out.println("Model Closed");	
		System.out.println(errorCode);
		
		System.exit(0);
	}
	
	/**
	 * startGame (start of program execution) or newGame Observer called, this state calls the welcome dialog
	 * @param type - Type of piece for a composite piece, otherwise Constants.PIECE_TYPE_DEFAULT
	 * @param x - Source x position
	 * @param y - Source y position
	 * @return ArrayList<Integer[]> - index 0: x position, index 1: y position, index 2: special benefit
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 */
	public static void initialise() throws RemoteException, AlreadyBoundException {
		
		controller = new ModelController();	
		System.out.println("Model Initialised");
		
	}

	
}

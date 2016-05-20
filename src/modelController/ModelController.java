package modelController;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import viewController.client.ClientCallbackInterface;
import model.GameModel;
import modelController.eventHandling.Player1InitObserver;
import modelController.eventHandling.Player2InitObserver;
import modelController.eventHandling.TileObserver;
import modelController.server.ModelServer;
import modelController.stateMachine.ModelStateEight;
import modelController.stateMachine.ModelStateFive;
import modelController.stateMachine.ModelStateFour;
import modelController.stateMachine.ModelStateMachine;
import modelController.stateMachine.ModelStateOne;
import modelController.stateMachine.ModelStatePlayer1Init;
import modelController.stateMachine.ModelStatePlayer2Init;
import modelController.stateMachine.ModelStateSeven;
import modelController.stateMachine.ModelStateSix;
import modelController.stateMachine.ModelStateThree;
import modelController.stateMachine.ModelStateTwo;
import controller.Controller;


public class ModelController extends Controller {
	
	private GameModel chessGameModel;
	private ModelStateMachine stateMachine;
	private ModelServer serverC;
	
	public ModelController() throws RemoteException, AlreadyBoundException{
		super();
		
		this.serverC = new ModelServer(this.getEventHandler());
		new Player1InitObserver(this.getEventHandler(), this);
		new Player2InitObserver(this.getEventHandler(), this);
		//	new TimeoutObserver(this.getEventHandler(), this);
		//	new TickObserver(this.getEventHandler());
		//	new NewGameObserver(this.getEventHandler() , this);
		//	new ExitGameObserver(this.getEventHandler());
		new TileObserver(this.getEventHandler() , this);
		
		this.chessGameModel = new GameModel(this.getEventHandler());
		stateMachine = new ModelStateMachine();
		new ModelStatePlayer1Init(this);
		new ModelStateOne(this);
		new ModelStateTwo(this);
		new ModelStateThree(this);
		new ModelStateFour(this);
		new ModelStateFive(this);
		new ModelStateSix(this);
		new ModelStateSeven(this);
		new ModelStateEight(this);
		new ModelStatePlayer2Init(this);
	
		this.serverC.startServer(modelController.server.Constants.CONTROLLER_PORT, modelController.server.Constants.CONTROLLER_ID);
	}
	public boolean playerRegistered(int player){return this.serverC.playerRegistered(player); }
	public GameModel getModel(){ return chessGameModel;}
	public ModelStateMachine getStateMachine(){return this.stateMachine;}
	public ModelServer getServer(){return this.serverC;}
	
	
	public ClientCallbackInterface getClient(int player){
		return this.serverC.callClient(player);
		}
	
	public void start(){
		this.getStateMachine().stateInit();
	}	
	
}

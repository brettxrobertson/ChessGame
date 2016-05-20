package modelController.stateMachine;

import java.rmi.RemoteException;

import modelController.ModelController;
import stateMachine.ModelStates;
import view.NewGameData;


public class ModelStatePlayer1Init extends ModelStates {
	private ModelController controller;
	public ModelStatePlayer1Init(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}

	@Override
	public void run(String event) {
		int player = Character.getNumericValue(event.charAt(Constant.Constants.PLAYER));
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_P1INIT);
		boolean checkEvent =  (event.equals(Constant.Constants.P1_INT)||event.equals(Constant.Constants.NEW_GAME));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		if(checkState && checkEvent && checkRegistration ){

			System.out.println("Model State P1INIT");
			System.out.println(event);

			try {
				if(this.controller.getClient(player).drawWelcomeDialog(player)== Constant.Constants.SUBMIT_CLICKED ){
					
					NewGameData data = 	controller.getClient(player).getNewGameData();
						
					controller.getModel().initGame(data.boardSize, Constant.Constants.DEFAULT_TIME);
					controller.getModel().createPlayers(data.playerNames.get(player-Constant.Constants.VIEW_PLAYER_OFFSET),"");//controller.getClient(1).getPlayerNames().get(0), "");
					controller.getModel().setTurns(data.totalTurns);
					
					this.controller.getClient(1).initGUI();
					this.controller.getClient(1).setTiles(controller.getModel().getTiles()); 
					controller.getClient(player).resetPlayerStatus(player-Constant.Constants.VIEW_PLAYER_OFFSET);
					
					// need to  track registered players
					if( controller.playerRegistered(2)){
						// P2 is waiting
						stateMachine.setEvent(Constant.Constants.P2_INT);
						stateMachine.setNextState(Constant.Constants.STATE_P2INIT);
						stateMachine.stateUnlock();
					}else{
						// wait for p2
						stateMachine.setEvent("nil");
						stateMachine.setNextState(Constant.Constants.STATE_P2INIT);
						stateMachine.stateLock();
					}
						
				}else{
					stateMachine.setEvent("nil");
					stateMachine.setNextState(Constant.Constants.STATE_P1INIT);
					stateMachine.stateLock();
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
		

}
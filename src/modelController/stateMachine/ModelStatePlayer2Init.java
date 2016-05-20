package modelController.stateMachine;

import java.rmi.RemoteException;

import modelController.ModelController;
import stateMachine.ModelStates;

public class ModelStatePlayer2Init extends ModelStates {
	private ModelController controller;
	public ModelStatePlayer2Init(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}

	@Override
	public void run(String event) {
		int player = Character.getNumericValue(event.charAt(Constant.Constants.PLAYER));
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_P2INIT);
		boolean checkEvent =  (event.equals(Constant.Constants.P2_INT)||event.equals(Constant.Constants.NEW_GAME));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		if(checkState && checkEvent && checkRegistration ){
			System.out.println("Model State P2INIT");
			System.out.println(event);
			
			try {
				
				if(this.controller.getClient(player).drawWelcomeDialog(player)
						== Constant.Constants.SUBMIT_CLICKED){
					
					controller.getModel().getPlayer2().setName(controller.getClient(player).getPlayerNames().get(player-Constant.Constants.VIEW_PLAYER_OFFSET));
					controller.getClient(player).initGUI();
					controller.getClient(player).setTiles(controller.getModel().getTiles()); 
					controller.getClient(player).resetPlayerStatus(player-Constant.Constants.VIEW_PLAYER_OFFSET);
					stateMachine.setEvent("new game");
					stateMachine.setNextState(Constant.Constants.STATE_ONE);
					stateMachine.stateUnlock();
					
				}else{
					controller.getClient(1).playerLeftGame();
					
					stateMachine.setEvent("nil");
					stateMachine.setNextState(Constant.Constants.STATE_P1INIT);
					stateMachine.stateUnlock();
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		
	}

}

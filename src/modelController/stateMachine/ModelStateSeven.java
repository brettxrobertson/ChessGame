package modelController.stateMachine;

import stateMachine.ModelStates;
import view.GameView;
import modelController.ModelController;

public class ModelStateSeven extends ModelStates {
	private ModelController controller;
	public ModelStateSeven(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event){
		if(stateMachine.getState() == Constant.Constants.STATE_SEVEN && (event == "timeout")){
			System.out.println("state seven00");
			controller.getModel().getTimer().stopTimer();
			
			stateMachine.clearAll(controller);
			
			if(controller.getModel().getActivePlayer().getId() == 2){
				GameView.drawTimeoutDialog(controller.getModel().getPlayer2().getName());
				controller.getModel().getTurn().decrementTurn();
				GameView.updateTime(30);
				GameView.setTurnCount(controller.getModel().getTurn().getCurrentTurn());
				GameView.setCurrentPlayer(0); // temp fix
			}else{
				GameView.drawTimeoutDialog(controller.getModel().getPlayer1().getName());
				GameView.updateTime(30);
				GameView.setTurnCount(controller.getModel().getTurn().getCurrentTurn()-1);
				GameView.setCurrentPlayer(1); 
			}
			
			
			int turn = controller.getModel().getTurn().decrementTurn();
			
			if(turn > 0){
				controller.getModel().getActivePlayer().setScore(0);
				controller.getModel().swapActivePLayer();
				GameView.setTiles(controller.getModel().getTiles()); 				
				
			
				stateMachine.setNextState(Constant.Constants.STATE_THREE);
				controller.getModel().getTimer().startTimer(true);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();

			}else{
				stateMachine.setNextState(Constant.Constants.STATE_EIGHT);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();
				
			}
		}

		
	}
}

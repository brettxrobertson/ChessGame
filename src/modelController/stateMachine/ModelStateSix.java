package modelController.stateMachine;

import stateMachine.ModelStates;
import view.GameView;
import modelController.ModelController;

public class ModelStateSix extends ModelStates {
	private ModelController controller;
	public ModelStateSix(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event){
		boolean first;
		boolean second;
		if(event.length() >  Constant.Constants.LEN){
			first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
			second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		}else{
			first = second = false;
		}

		if(stateMachine.getState() == Constant.Constants.STATE_SIX && (first && second)){
			System.out.println("state sixOO");
			
			if(controller.getModel().getActivePlayer().getId() == 2){
				controller.getModel().getTurn().decrementTurn();
				GameView.setTurnCount(controller.getModel().getTurn().getCurrentTurn());
			}else{
				GameView.setTurnCount(controller.getModel().getTurn().getCurrentTurn()-1);
			}
			
		
			int turn = controller.getModel().getTurn().getCurrentTurn();
			controller.getModel().swapActivePLayer();
			if(turn > 0){
				
					
				GameView.updateTime(30);
				
				if(controller.getModel().getActivePlayer().getId() == 1){
					GameView.setScore(controller.getModel().getPlayer1().getScore());
					GameView.setCurrentPlayer(1);
				}else{
					GameView.setScore(controller.getModel().getPlayer2().getScore());
					GameView.setCurrentPlayer(0);
				}
				
				GameView.setTiles(controller.getModel().getTiles()); 
				stateMachine.setNextState(Constant.Constants.STATE_THREE);
				controller.getModel().getTimer().startTimer(true);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();
				
			}else{
				
				GameView.setScore(controller.getModel().getPlayer2().getScore());
				stateMachine.setNextState(Constant.Constants.STATE_EIGHT);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();
				
			}

		}

		
	}
}

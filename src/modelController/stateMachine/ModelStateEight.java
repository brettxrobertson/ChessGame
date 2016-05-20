package modelController.stateMachine;

import stateMachine.ModelStates;
import view.GameView;
import modelController.ModelController;

public class ModelStateEight extends ModelStates {
	private ModelController controller;
	public ModelStateEight(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event) {
		// currently limit to a board size less than 9
		// need to build a method to convert the event to numbers if a tile is clicked

		boolean first;
		boolean second;
		if(event.length() >  Constant.Constants.LEN){
			first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
			second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		}else{
			first = second = false;
		}

		if(stateMachine.getState() == Constant.Constants.STATE_EIGHT &&
				(first && second || event == "timeout")){
			System.out.println("state eightOO");
			// will return 0

			controller.getModel().getTimer().stopTimer();

			GameView.drawWinnerDialog(controller.getEventHandler(), controller.getModel().getWinner());

			stateMachine.setNextState(Constant.Constants.STATE_NEWGAME);
			stateMachine.setEvent(event);
			stateMachine.stateUnlock();
		}
		
	}
}

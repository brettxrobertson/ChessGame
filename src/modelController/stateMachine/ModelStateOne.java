package modelController.stateMachine;

import stateMachine.ModelStates;
import modelController.ModelController;

public class ModelStateOne extends ModelStates {
	private ModelController controller;
	
	public ModelStateOne(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event)  {
		// currently limit to a board size less than 9
		// need to build a method to convert the event to numbers if a tile is clicked
		int player = Character.getNumericValue(event.charAt(Constant.Constants.PLAYER));
		this.controller.getModel().getActivePlayer().getId();
		boolean first;
		boolean second;
		if(event.length() > Constant.Constants.LEN){
			first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
			second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		}else{
			first = second = false;
		}
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_ONE);
		boolean checkEvent =  ((first && second)||event.equals("timeout"));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		boolean checkPlayer = (this.controller.getModel().getActivePlayer().getId() == player);

		if(checkState && checkEvent && checkRegistration && checkPlayer){
			System.out.println("Model State ONE");
			System.out.println(event);	
			//controller.getModel().getTimer().startTimer(true);
			controller.getStateMachine().setNextState(Constant.Constants.STATE_TWO);
			stateMachine.setEvent(event);
			stateMachine.stateUnlock();
			System.out.println("Model State ONE End");
		}
	
	}
	
}

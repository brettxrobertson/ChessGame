package modelController.eventHandling;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class Player1InitObserver extends Observer{
	ModelController controller;
	public Player1InitObserver(Subject subject, ModelController controller) {
		super(subject);
		this.controller = controller;
	}

	@Override
	public void update(String event) {
		if(event.equals(Constant.Constants.P1_INT)){	
			this.controller.getStateMachine().setEvent(event);
			this.controller.getStateMachine().setNextState(Constant.Constants.STATE_P1INIT);
			this.controller.getStateMachine().stateUnlock();
		}		
	}
}

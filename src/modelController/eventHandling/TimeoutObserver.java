package modelController.eventHandling;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class TimeoutObserver extends Observer {
	
	ModelController controller;
	
	public TimeoutObserver(Subject subject, ModelController controller) {
		super(subject);
		this.controller = controller;
	}

	@Override
	public void update(String event) {
		if(event == "timeout"){
			System.out.println("timeout");
			controller.getStateMachine().setNextState(Constant.Constants.STATE_SEVEN);
			controller.getStateMachine().setEvent(event);
			controller.getStateMachine().stateUnlock();
		}
	}

}

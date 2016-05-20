package modelController.eventHandling;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class NewGameObserver extends Observer{
	ModelController controller;
	public NewGameObserver(Subject subject, ModelController controller) {
		super(subject);
		this.controller = controller;
	}

	@Override
	public void update(String event) {
		if(event == "new game"){

			System.out.println("new game");
			this.controller.getStateMachine().setEvent("new game");
			this.controller.getStateMachine().setNextState(Constant.Constants.STATE_NEWGAME);
			this.controller.getStateMachine().stateUnlock();

		}	
		
	}

}

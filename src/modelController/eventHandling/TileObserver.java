package modelController.eventHandling;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class TileObserver extends Observer{
	private ModelController controller;
	public TileObserver(Subject subject, ModelController controller) {
		super(subject);
		this.controller = controller;
	}

	@Override
	public void update(String event) {
		boolean first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
		boolean second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		if(first && second){
			
			controller.getStateMachine().setEvent(event);
			controller.getStateMachine().stateUnlock();
		}
		
	}

}
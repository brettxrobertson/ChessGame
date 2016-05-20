package viewController.eventHandling;

import viewController.ViewController;
import eventHandling.Observer;
import eventHandling.Subject;


public class ViewObserver extends Observer{
	ViewController controller;
	public ViewObserver(Subject subject,ViewController controller) {
		super(subject);
		this.controller = controller;
	}
	@Override
	public void update(String event) {
		if(!(event.equals(Constant.Constants.P1_WAIT) || event.equals(Constant.Constants.LEFT_GAME))){
			if(this.controller.getClient().playerRegister() == true)
				this.controller.getClient().SendServerEvent(this.controller.getClient().getPlayerNumber() + event);
		}
	}

}

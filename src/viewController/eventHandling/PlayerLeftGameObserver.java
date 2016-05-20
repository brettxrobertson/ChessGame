package viewController.eventHandling;

import viewController.ViewController;
import eventHandling.Observer;
import eventHandling.Subject;


public class PlayerLeftGameObserver extends Observer{
	ViewController controller;
	public PlayerLeftGameObserver(Subject subject,ViewController controller) {
		super(subject);
		this.controller = controller;
	}
	@Override
	public void update(String event) {
		if(event.equals(Constant.Constants.LEFT_GAME)){
			System.out.println("playerLeftGame()");
		}
	}

}

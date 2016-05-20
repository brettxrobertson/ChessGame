package viewController.eventHandling;

import viewController.ViewController;
import eventHandling.Observer;
import eventHandling.Subject;


public class WaitingPlayer1Observer extends Observer{
	ViewController controller;
	public WaitingPlayer1Observer(Subject subject,ViewController controller) {
		super(subject);
		this.controller = controller;
	}
	@Override
	public void update(String event) {
		if(event.equals(Constant.Constants.P1_WAIT)){
			System.out.println("Player1 Making Game Selections");
		}
	}

}

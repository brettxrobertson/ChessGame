package modelController.eventHandling;

import eventHandling.Observer;
import eventHandling.Subject;

public class ExitGameObserver extends Observer{

	public ExitGameObserver(Subject subject) {
		super(subject);
	}

	@Override
	public void update(String event) {
		if(event == "exit game"){
			System.out.println(event);
			System.exit(0);
		}
		
	}
	
	

}

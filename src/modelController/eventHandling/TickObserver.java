package modelController.eventHandling;

import java.rmi.RemoteException;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class TickObserver extends Observer{

	private ModelController controller;
	public TickObserver(Subject subject,ModelController controller) {
		super(subject);
		this.controller = controller;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(String event) {
		if(event.contains("tick")){
			
			Integer i = Integer.valueOf(event.substring(4, event.length()));
			try {
				this.controller.getClient(1).updateTime(i);
				this.controller.getClient(2).updateTime(i);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		
	}

}

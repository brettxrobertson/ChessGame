package modelController.eventHandling;

import java.rmi.RemoteException;

import modelController.ModelController;
import eventHandling.Observer;
import eventHandling.Subject;

public class Player2InitObserver extends Observer{
	ModelController controller;
	public Player2InitObserver(Subject subject, ModelController controller) {
		super(subject);
		this.controller = controller;
	}

	@Override
	public void update(String event) {
		if(event.equals(Constant.Constants.P2_INT)){
			if(this.controller.getStateMachine().getState() == Constant.Constants.STATE_P1INIT){
				try {
					this.controller.getClient(2).showGeneralPurposeDialogue("Waiting for player 1");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(this.controller.getStateMachine().getState() == Constant.Constants.STATE_P2INIT){
				this.controller.getStateMachine().setEvent(event);
				this.controller.getStateMachine().setNextState(Constant.Constants.STATE_P2INIT);
				this.controller.getStateMachine().stateUnlock();
			}

		}		
	}
}

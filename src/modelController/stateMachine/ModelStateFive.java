package modelController.stateMachine;

import java.rmi.RemoteException;

import stateMachine.ModelStates;
import view.GameView;
import modelController.ModelController;

public class ModelStateFive extends ModelStates{
	private ModelController controller;
	public ModelStateFive(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event) {
		// currently limit to a board size less than 9
		// need to build a method to convert the event to numbers if a tile is clicked

		int player = Character.getNumericValue(event.charAt(0));
		boolean first;
		boolean second;
		if(event.length() >  Constant.Constants.LEN){
			first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
			second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		}else{
			first = second = false;
		}
		
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_FIVE);
		boolean checkEvent =  ((first && second));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		boolean checkPlayer = (this.controller.getModel().getActivePlayer().getId() == player);

		if(checkState && checkEvent && checkRegistration && checkPlayer){
			System.out.println("Model State FIVE");
			System.out.println(event);	

			if(controller.getModel().getActivePlayer().getId() == 2){
				controller.getModel().getTurn().decrementTurn();
				try {
					controller.getClient(2).setTurnCount(controller.getModel().getTurn().getCurrentTurn());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					controller.getClient(1).setTurnCount(controller.getModel().getTurn().getCurrentTurn()-1);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
			int turn = controller.getModel().getTurn().getCurrentTurn();
			controller.getModel().swapActivePLayer();
			if(turn > 0){
				
					
				try {
					controller.getClient(1).updateTime(30);
					controller.getClient(2).updateTime(30);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(controller.getModel().getActivePlayer().getId() == 1){
					GameView.setScore(controller.getModel().getPlayer1().getScore());
					try {
						controller.getClient(1).setScore(controller.getModel().getPlayer1().getScore());
						controller.getClient(2).setScore(controller.getModel().getPlayer1().getScore());
						controller.getClient(1).setCurrentPlayer(1); 
						controller.getClient(2).setCurrentPlayer(1); 
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else{
					try {
						controller.getClient(1).setScore(controller.getModel().getPlayer2().getScore());
						controller.getClient(2).setScore(controller.getModel().getPlayer2().getScore()); 
						controller.getClient(1).setCurrentPlayer(0); 
						controller.getClient(2).setCurrentPlayer(0); 
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

				}
				
				try {
					controller.getClient(1).setTiles(controller.getModel().getTiles());
					controller.getClient(2).setTiles(controller.getModel().getTiles()); 
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				stateMachine.setNextState(Constant.Constants.STATE_TWO);
				controller.getModel().getTimer().startTimer(true);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();
				
			}else{
				
				try {
					controller.getClient(1).setScore(controller.getModel().getPlayer2().getScore());
					controller.getClient(2).setScore(controller.getModel().getPlayer2().getScore());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				stateMachine.setNextState(Constant.Constants.STATE_SEVEN);
				stateMachine.setEvent(event);
				stateMachine.stateUnlock();
				
			}	
		}
	}
}

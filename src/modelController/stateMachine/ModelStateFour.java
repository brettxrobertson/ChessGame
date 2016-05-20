package modelController.stateMachine;

import java.rmi.RemoteException;

import stateMachine.ModelStates;
import modelController.ModelController;

public class ModelStateFour extends ModelStates {
	private ModelController controller;
	public ModelStateFour(ModelController controller) {
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
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_FOUR);
		boolean checkEvent =  ((first && second));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		boolean checkPlayer = (this.controller.getModel().getActivePlayer().getId() == player);

		if(checkState && checkEvent && checkRegistration && checkPlayer){
			System.out.println("Model State FOUR");
			System.out.println(event);	
				// currently limit to a board size less than 9
			// need to build a method to convert the event to numbers if a tile is clicked

			int x = Character.getNumericValue(event.charAt(Constant.Constants.FIRST));
			int y = Character.getNumericValue(event.charAt(Constant.Constants.SECOND));
			
			if(stateMachine.getSelectedPieceXY()[0]== x && stateMachine.getSelectedPieceXY()[1] == y){
				// clear valid moves and selected pieces
				stateMachine.clearAll(controller);

			}else{
				// maybe able to ignore if tiles are locked, only non valid tile is selected tile

				if(stateMachine.isClickValidMove(x,y)){
					// NEED IMPLEMENTATION
					//chessGameModel.resetGameTimer(); 
					controller.getModel().getTimer().stopTimer();
					try {
						controller.getClient(1).clearAllSelected();
						controller.getClient(2).clearAllSelected();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//controller.getView().clearAllSelected();

					controller.getModel().moveSelectedPiece(stateMachine.getSelectedPieces(),stateMachine.getSelectedPieceXY()[0], stateMachine.getSelectedPieceXY()[1], x, y);
					try {
						controller.getClient(1).setTiles(controller.getModel().getTiles());
						controller.getClient(2).setTiles(controller.getModel().getTiles());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					 
					//controller.getView().setTiles(controller.getModel().getTiles()); 
					stateMachine.setNextState(Constant.Constants.STATE_FIVE);
					stateMachine.setEvent(event);
					stateMachine.stateUnlock();
					//stateMachine.runStateMachine(event);

				}else{
					// invalid click remain in state 4
					stateMachine.setEvent("nil");
					stateMachine.stateLock();
					stateMachine.setNextState(Constant.Constants.STATE_FOUR);
				}
			}		
		}
	
	}
}

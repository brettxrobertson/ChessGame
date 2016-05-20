package modelController.stateMachine;

import java.rmi.RemoteException;
import java.util.ArrayList;

import stateMachine.ModelStates;
import model.pieces.AbstractPiece;
import modelController.ModelController;

public class ModelStateThree extends ModelStates {
	private ModelController controller;
	public ModelStateThree(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event){
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
		
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_THREE);
		boolean checkEvent =  ((first && second));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		boolean checkPlayer = (this.controller.getModel().getActivePlayer().getId() == player);

		if(checkState && checkEvent && checkRegistration && checkPlayer){
			System.out.println("Model State THREE");
			System.out.println(event);	
			// only supports size of 9
			int x = Character.getNumericValue(event.charAt(Constant.Constants.FIRST));
			int y = Character.getNumericValue(event.charAt(Constant.Constants.SECOND));

			stateMachine.getSelectedPieces().clear();

			ArrayList<AbstractPiece> Pieces = null;
			try {
				Pieces = this.controller.getClient(player).drawCompositeDialog(controller.getModel().getPieces(x, y));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			if(stateMachine.getState() != Constant.Constants.STATE_THREE) return ;

			stateMachine.getValidMoves().clear();
			stateMachine.getSelectedPieces().clear();

			if(Pieces.size() == 0){
				stateMachine.clearAll(controller);
				return ;

			}

			stateMachine.getSelectedPieces().addAll(Pieces);

			ArrayList<Integer[]> validMoves = controller.getModel().getValidMoves(stateMachine.getSelectedPieces(), x, y);
			if(validMoves == null){
				stateMachine.clearAll(controller);
				return ;
			}
			stateMachine.setValidMoves(validMoves);
			try {
				this.controller.getClient(1).setValidMoves(stateMachine.getValidMoves());
				this.controller.getClient(2).setValidMoves(stateMachine.getValidMoves());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stateMachine.setEvent("nil");
			stateMachine.stateLock();
			stateMachine.setNextState(Constant.Constants.STATE_FOUR);
	
		}		
		
	}
}

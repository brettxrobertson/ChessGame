package modelController.stateMachine;

import java.rmi.RemoteException;
import java.util.ArrayList;
import modelController.ModelController;
import stateMachine.ModelStates;


public class ModelStateTwo extends ModelStates {
	private ModelController controller;
	public ModelStateTwo(ModelController controller) {
		super(controller.getStateMachine());
		this.controller = controller;
	}
	@Override
	public void run(String event){
		// currently limit to a board size less than 9
		// need to build a method to convert the event to numbers if a tile is clicked
		int player = Character.getNumericValue(event.charAt(Constant.Constants.PLAYER));
		boolean first;
		boolean second;
		if(event.length() >  Constant.Constants.LEN){
			first = (event.charAt(Constant.Constants.FIRST) >= '0' && event.charAt(Constant.Constants.FIRST) <= '9');
			second = (event.charAt(Constant.Constants.SECOND) >= '0' && event.charAt(Constant.Constants.SECOND) <= '9');
		}else{
			first = second = false;
		}
		
		boolean checkState = (stateMachine.getState() == Constant.Constants.STATE_TWO);
		boolean checkEvent =  ((first && second)||event.equals("timeout"));
		boolean checkRegistration = (this.controller.playerRegistered(player));
		boolean checkPlayer = (this.controller.getModel().getActivePlayer().getId() == player);

		if(checkState && checkEvent && checkRegistration && checkPlayer){
			System.out.println("Model State TWO");
			System.out.println(event);	
			// only supports size of 9
			int x = Character.getNumericValue(event.charAt(Constant.Constants.FIRST));
			int y = Character.getNumericValue(event.charAt(Constant.Constants.SECOND));

			if(controller.getModel().isMyPiece(x,y)){
				// this is the active players piece, draw the selection
				stateMachine.setSelectedPieceXY(x,y);		
			
				try {
					this.controller.getClient(1).drawSelectedPiece(x,y);
					this.controller.getClient(2).drawSelectedPiece(x,y);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//						controller.getView().drawSelectedPiece(x,y);

				if(controller.getModel().isComposite(x,y)){
					// piece is composite, draw composite selection dialog using state 4
					stateMachine.setEvent(event);
					stateMachine.setNextState(Constant.Constants.STATE_THREE); 
					stateMachine.stateUnlock();
					///stateMachine.runStateMachine(event);
				}else{

					// piece isn't composite, draw the valid moves
					if(stateMachine.getValidMoves() != null )
						stateMachine.getValidMoves().clear();

					ArrayList<model.pieces.AbstractPiece> pieces = controller.getModel().getPieces(x, y);

					if(stateMachine.getSelectedPieces() != null )
						stateMachine.getSelectedPieces().clear();

					stateMachine.getSelectedPieces().addAll(pieces);

					ArrayList<Integer[]> validMoves = controller.getModel().getValidMoves(stateMachine.getSelectedPieces(), x, y);

					if(validMoves == null){
						stateMachine.clearAll(this.controller);
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

			}else{
				// not active players piece, ignore click
				stateMachine.setEvent("nil");
				stateMachine.stateLock();
				stateMachine.setNextState(Constant.Constants.STATE_TWO);
			}

		}

	}
}

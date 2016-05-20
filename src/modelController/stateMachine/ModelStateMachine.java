package modelController.stateMachine;

import java.rmi.RemoteException;
import java.util.ArrayList;
import stateMachine.StateMachine;
import model.pieces.AbstractPiece;
import modelController.ModelController;

public class ModelStateMachine extends StateMachine{
	// required to support model for the time being
	private int selectedPieceX;
	private int selectedPieceY;
	private  ArrayList<Integer[]> validMoves;
	private  ArrayList<AbstractPiece> selectedPieces;
	
	public ModelStateMachine(){
		// required to support model for the time being
		super();
		selectedPieces = new ArrayList<AbstractPiece>();
		validMoves = new ArrayList<Integer[]>();
	}
	
	@Override
	public void stateInit() {
		this.setNextState(Constant.Constants.STATE_P1INIT);
		this.setEvent("nil");
		this.stateLock();
		this.runStateMachine();
	}
	
	public void clearAll(ModelController controller){
		setEvent("nil");
		stateLock();
		setNextState(Constant.Constants.STATE_TWO);
		validMoves.clear();
		selectedPieces.clear();
		setSelectedPieceXY(Constant.Constants.ERROR,Constant.Constants.ERROR);
		try {
			controller.getClient(1).clearAllSelected();
			controller.getClient(2).clearAllSelected();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// required to support model for the time being
	
	public ArrayList<Integer[]> getValidMoves(){
		return  validMoves;
	}
	
	public void setValidMoves(ArrayList<Integer[]> in){
		validMoves = in;
	}
	
	public ArrayList<AbstractPiece> getSelectedPieces(){
		return selectedPieces;
	}
	
	public void getSelectedPieces(ArrayList<AbstractPiece>  in){
		selectedPieces = in;
	}
	
	
	public int[] getSelectedPieceXY() {
		int arr[] = {selectedPieceX,selectedPieceY};
		return arr;
	}
	
	public void setSelectedPieceXY(int selectedPieceX, int selectedPieceY) {
		this.selectedPieceX = selectedPieceX;
		this.selectedPieceY = selectedPieceY;
	}
		
	public boolean isClickValidMove(int x, int y){
		for(int i = 0; i < validMoves.size(); i++ ){
			if(validMoves.get(i)[0] == x){
				if(validMoves.get(i)[1] == y){
					return true;
				}
			}
				
		}
		
		return false;
	}
	
}

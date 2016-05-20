package stateMachine;

import viewController.stateMachine.ViewStateMachine;

//SRP - Each state has the repsonsibility for responding when its conditions are meet to perform
//its implemented functional and safely meeting program flow control requirements 
//OCP - The abstract class has been implemented so that is it closed for modification, and open for
// extension by creating a sub-class
//LSP - This method is replacable by all sub-types
//ISP - 
//DIP - All sub-classes depend on States and not the other way around

public abstract class ViewStates extends States {
	
	protected ViewStateMachine stateMachine;
	
	public ViewStates(ViewStateMachine stateMachine){
		super();
		this.setStateID(stateMachine.getStateSize()+1);
		this.stateMachine = stateMachine;
		this.stateMachine.attachState(this);
	}
	
	public abstract void run(String event);
	

}

package viewController.stateMachine;

import stateMachine.StateMachine;


public class ViewStateMachine extends StateMachine {
	private int registeredPlayer;

	public ViewStateMachine(){
		// required to support model for the time being
		super();
	
		registeredPlayer = 0;
	}
	
	public void setRegisteredPlayer(int in){
		registeredPlayer = in;
	}
	
	public int getRegisteredPlayer(){ return registeredPlayer;}
	

	@Override
	public void stateInit() {
		this.setNextState(Constant.Constants.STATE_ZERO);
		this.setEvent("connect");
		this.stateUnlock();
		this.runStateMachine();
	}
	public void registerPlayer1(){
		System.out.println("Player1 Registered");
		this.setRegisteredPlayer(1);
	}
	
	public void registerPlayer2(){
		System.out.println("Player2 Registered");
		setRegisteredPlayer(2);
	}
	
}

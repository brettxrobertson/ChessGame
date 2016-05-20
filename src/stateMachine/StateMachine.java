package stateMachine;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class StateMachine {
	
	private List<States> states;
	private int state;
	private String event;
	private int stateLock;
	
	/**
	 * StateMachine Constructor <p>
	 * 
	 * Pre-condition: Called by the constructor of the controller to create an instance of 
	 * it state machine to handle program flow
	 * <p>
	 * Post-condition: <br> 
	 * 1. Creates an instance of states for new states to be dynamically attached [see attachState(States state)] <br>
	 * 2. Set the state machine to state zero (start state) and unlock it.
	 * @author Troy
	 */
	public StateMachine(){
		// required to support model for the time being
		event = new String();
		
		states = new ArrayList<States>();
		state = Constant.Constants.STATE_ZERO;
		stateLock = Constant.Constants.STATEMACHINE_UNLOCK;
		
		
	}

	public abstract void stateInit() ;
	
	/**
	 * StateMachine Constructor <p>
	 * 
	 * Pre-condition: State Machine runStateMachine(String event) is called with a valid event String
	 * <p>
	 * Post-condition: <br> 
	 * 1. if the state machine is unlocked, obtain the lock, else the state machine is already in use and the event is ignored <br> 
	 * @param void   
	 * @return boolean True: lock obtained, false: lock not obtained 
	 * @author Troy
	 */
	public boolean stateLock(){ 
		if(stateLock == Constant.Constants.STATEMACHINE_UNLOCK) {
			stateLock = Constant.Constants.STATEMACHINE_LOCK;
			return true;
		}
		return false;
	}
	
	/**
	 * StateMachine Constructor <p>
	 * 
	 * Pre-condition: State Machine runStateMachine(String event) is called with a valid event String
	 * <p>
	* Post-condition: <br> 
	 * 1. State machine is forced unlocked<br>
	 * @param void   
	 * @return void 
	 * @author Troy
	 */
	public void stateUnlock(){ stateLock = Constant.Constants.STATEMACHINE_UNLOCK;}
	
	/**
	 * getStateSize() <p>
	 * 
	 * Pre-condition: State Machine constructor called
	 * <p>
	 * @return int: the number of states attached to the state machine as an   
	 * @author Troy
	 */
	public int getStateSize(){
		return this.states.size();
	}
	
	/**
	 *  getEvent() <p>
	 * 
	 * Pre-condition: State Machine constructor called
	 * <p>
	 * @return String: the most recent event of the state machine, null if no event
	 * @author Troy
	 */
	public String getEvent(){
		return this.event;
	}
	
	public void setEvent(String event){
		this.event = event;
	}
	
	/**
	 *  getState() <p>
	 * 
	 * Pre-condition: State Machine constructor called
	 * <p>
	 * @return int: gets the current state of the state machine
	 * @author Troy
	 */
	public int getState(){return state;}
	
	/**
	 *  setNextState(int in) <p>
	 * 
	 * Pre-condition: State Machine constructor called
	 * <p>
	 * Post-Condition: State is set for State Machine to move to the required next state 
	 * IAW the required program flow. 
	 * @param int in: input to attempt to set the next state to.
	 * @return boolean: true: next state set successfully, false: desired state doesn't exist 
	 * @author Troy
	 */
	public boolean setNextState(int in){ 
		
			if( in < states.size()){
				state = in;
				return true;
			}
			return false;
	}
	
	/**
	 *  runStateMachine(String event) <p>
	 * 
	 * Pre-condition: state machine is in the required state for the event, else event is ignored
	 * <p>
	 * Post-Condition: the execution of the desired state for the associate event 
	 * @param String event: event required to execute the current state.
	 * @return void 
	 * @author Troy
	 * @throws RemoteException 
	 */
	public void runStateMachine() {
		while(true){
			if(stateLock == Constant.Constants.STATEMACHINE_UNLOCK){
				stateLock = Constant.Constants.STATEMACHINE_LOCK;
			}
			else{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
				//return;
			for (States state : states) {
				state.run(event);
			}
					
		}
	     
	}
	/**
	 * attachState(States state) <p>
	 * 
	 * Pre-condition: state machine constructor has been caller
	 * <p>
	 * Post-Condition: a new state is attached to the state machine and available for execution
	 * by adding it the states arraylist
	 * @param States state: The new state to be attached
	 * @return void 
	 * @author Troy
	 */
	public void attachState(States state){
		this.states.add(state);
	}
	/**
	 * detachState(States state) <p>
	 * 
	 * Pre-condition: state instance exists
	 * <p>
	 * Post-Condition: state removed from the states arraylist
	 * @param States state: The new state to be detached
	 * @return void 
	 * @author Troy
	 */
	public void detachState(States state){
		this.states.remove(state.getStateID());
	}
	
}


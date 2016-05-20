package eventHandling;


import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private List<Observer> observers;
	private String event;
	
	/**
	 * Constructor
	 * <p>
	 * Post-condition: the observer list is initialised
	 * @return void
	 */
	public Subject(){
		observers = new ArrayList<Observer>();
	}
	/**
	 * getObserversSize 
	 * @param void
	 * <p>
	 * Invariant: output equal the number of observers in the observers list.
	 * <p>
	 * Post-condition: 
	 * @return int: the number of attached observers
	 */
	public int getObserversSize(){
		return this.observers.size();
	}
	/**
	 * getEvent
	 * <p>
	 * Pre-condition: event must have been set previously
	 * @param void
	 * <p>
	 * Invariant: output is the value of event
	 * <p>
	 * Post-condition: 
	 * @return String: the string value of event
	 */
	public String getEvent(){
		return this.event;
	}
	/**
	 * notifyObservers
	 * <p>
	 * Pre-condition: 
	 * @param String event: a string value to send to all observers
	 * <p>
	 * Invariant: all attached observer will get the event
	 * <p>
	 * Post-condition: call update for all attached observers
	 * @return void
	 */
	private void notifyObservers(String event){
		
	     for (Observer observer : observers) {
	    	 observer.update(event);
	     }
	}
	/**
	 * attachObserver
	 * <p>
	 * Pre-condition: 
	 * @param Observer observer: the instance of an observer
	 * <p>
	 * Invariant: observers appends the new observer to the end of the array list
	 * <p>
	 * Post-condition: observers appends the new observer to the end of the array list
	 * @return void
	 */
	public void attachObserver(Observer observer){
		this.observers.add(observer);
	}
	/**
	 * detachObserver
	 * <p>
	 * Pre-condition: 
	 * @param Observer observer: observer to be detached
	 * <p>
	 * Invariant: for a valid observer it will be detached from the observers array list
	 * <p>
	 * Post-condition: the observe is removed from the subject
	 * @return void
	 */
	public void detachObserver(Observer observer){
		
	    	 if(observer.getObserverId() == this.observers.get(observer.getObserverId()).getObserverId())
	    		 this.observers.remove(observer.getObserverId());
	}
	
	/**
	 * setEvent
	 * <p>
	 * Pre-condition: the subject is passed to another class who calls set event passing a string
	 * @param String event: a string value identifying a possible observer
	 * <p>
	 * Invariant: the event will be passed to all observers
	 * <p>
	 * Post-condition: all observers of the subject recieve the event
	 * @return void
	 */
	public void setEvent(String event){
		this.event = event;
		notifyObservers(event);
	}
	
}

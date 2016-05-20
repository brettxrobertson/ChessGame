package eventHandling;

public abstract class Observer {
	
	private int id;
	protected Subject subject;
	/**
	 * Constructor
	 * <p>
	 * Pre-condition: Subject and controller instances exist
	 * @param Subject subject: 
	 * @param Controller controller: 
	 * <p>
	 * Invariant: the observe is attached the the Subject parameter
	 * <p>
	 * Post-condition: 
	 * 1. the observe is attached the the Subject parameter
	 * 2. the observer id is set
	 * @return void
	 */
	public Observer(Subject subject){
		id = subject.getObserversSize()+1;
		this.subject = subject;
		this.subject.attachObserver(this);
	}
	
	public abstract void update(String event);
	/**
	 * getObserverId
	 * <p>
	 * Pre-condition: 
	 * @param String event: is the Cartesian coordinate location of the tile in the form "X,Y"
	 * <p>
	 * Invariant: 
	 * <p>
	 * Post-condition: 
	 * @return void
	 */
	public int getObserverId(){
		return id;
	}
}

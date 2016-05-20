package viewController;

import controller.Controller;
import viewController.client.Client;
import viewController.eventHandling.ViewObserver;

public class ViewController extends Controller{
//	private ViewStateMachine stateMachine;

	private Client client;
	
	public static final boolean ENABLE_VIEW = true;

	public ViewController(){
		super();
		
		new ViewObserver(this.getEventHandler(), this);
		
		createClient();		
	
		System.out.println("Client Created");
	}
	

	public Client getClient(){return this.client;}
//	public ViewStateMachine getStateMachine(){return this.stateMachine;}

	@Override
	public void start() {
	//	this.getStateMachine().stateInit();
		this.getEventHandler().setEvent("Reg");
		//GameView.drawWelcomeDialog(this.getEventHandler());
		boolean test = true;
		while(test){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createClient(){
		this.client = new Client(this); 
	}
	


}

package stateMachine;


//SRP - Each state has the repsonsibility for responding when its conditions are meet to perform
//its implemented functional and safely meeting program flow control requirements 
//OCP - The abstract class has been implemented so that is it closed for modification, and open for
// extension by creating a sub-class
//LSP - This method is replacable by all sub-types
//ISP - 
//DIP - All sub-classes depend on States and not the other way around

public abstract class States {
	
	private int id;
	
	
	public States(){
		
	}
	
	public abstract void run(String event);
	
	public int getStateID(){
		return id;
	}
	
	public void setStateID(int in){
		id = in;
	}
	
	
}

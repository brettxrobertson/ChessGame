package view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * The popup dialogue to alert the game is over and announce the winning player
 * 
 * @author keltie
 *
 */
public class GeneralPurposeDialogue {
	private static GeneralPurposeDialogue generalPurposeDialogue;
	

	GeneralPurposeDialogue(){
		createWinnerDialogue();
	}

	public static synchronized GeneralPurposeDialogue getInstance(){
		if (generalPurposeDialogue == null){
			generalPurposeDialogue = new GeneralPurposeDialogue();
		}
		return generalPurposeDialogue;
	}
	
	public static int launchDialogue(Component component, String input){
			
		JOptionPane.showMessageDialog(component, input);
		
		return 0;
	}
			
	private static void createWinnerDialogue(){
		//TODO
	}
}

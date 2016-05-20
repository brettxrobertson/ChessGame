package view;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The ancillary console panel. Used by any class wishing to print text.
 * 
 * @author keltie
 *
 */
public class ConsoleView {
	private static ConsoleView consoleView;
	
	private static JPanel consolePanel = new JPanel(new FlowLayout());
	private static ArrayList<String> consoleOutput = new ArrayList<String>();
	private static JTextArea textArea = new JTextArea(100,80);
	
	ConsoleView(){
		createStatusPanel();
	}

	public static synchronized ConsoleView getInstance(){
		if (consoleView == null){
			consoleView = new ConsoleView();
		}
		return consoleView;
	}
	
	public static void writeDebugToGUIConsole(Class object, String output){
		consoleOutput.add(output);
		textArea.setText(textArea.getText() + "Console: (" + object.getSimpleName() + ") " + consoleOutput.get((consoleOutput.size() - 1)) + "\n");	
	}
	
	public static void writeToGUIConsole(String output){
		consoleOutput.add(output);
		textArea.setText(textArea.getText() + "Console: " + consoleOutput.get((consoleOutput.size() - 1)) + "\n");	
	}
	
	private static void createStatusPanel(){
		consolePanel = GUILayoutFactory.createConsolePanel();
	}	
	
	public static JPanel getStatusPanel() {
		return consolePanel;
	}
}

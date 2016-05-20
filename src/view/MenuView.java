package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * The basic top gui menu panel. Home for interaction buttons as required.
 * 
 * @author keltie
 *
 */
public class MenuView {
	private static class InstanceHolder {
		private static MenuView instance = new MenuView(); 
	}

	private static JToolBar menuBar; // = new JToolBar();
	
	private static JButton newGame = new JButton("New Game");
	private static JButton exitGame = new JButton("Exit");

	private static JButton loadGame = new JButton("Load Game");
	private static JButton saveGame = new JButton("Save Game");
	
	//private static JButton doMove = new JButton("Do Move");
	private static JButton undoLastMove = new JButton("Undo Last Move");
	private static JButton redoLastMove = new JButton("Redo Last Move");
	
	private static JButton timeoutDialogue = new JButton("Timeout Dialogue test");
	private static JButton winnerDialogue = new JButton("Winner Dialogue test");
	
	private static eventHandling.Subject controllerEventHandler;
	
	MenuView(){
		setControllerEventHandler(new eventHandling.Subject());
		createMenuBar();
	}


	public static MenuView getInstance(){
		return InstanceHolder.instance;
	}
	
	private static void createMenuBar(){
		menuBar = GUILayoutFactory.createMenuBar();
		
		//menuBar.setName("Menu Bar");
		//menuBar.setFloatable(false);
		//menuBar.setPreferredSize(new Dimension(600,50));
		
        menuBar.add(newGame);
        newGame.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("new game");
				//NewGameDialogue.launchDialogue();
			}
        });
        

        
        /*
         * Commented out buttons below for future functionality/testing.
         */
        menuBar.add(loadGame);
        loadGame.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        loadGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("load game");
				//TODO load from file
			}
        });
        menuBar.add(saveGame);
        saveGame.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        saveGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("save game");
				//TODO save to file.
			}
        });

        /*
        menuBar.add(doMove);
        doMove.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        doMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("move");
				highlightMoveButton(false);
				//BoardView.clearAllSelected();
				
				//TODO do current player's move 
				//TODO push last command onto undo stack
			}
        });
        */
        menuBar.add(undoLastMove);
        undoLastMove.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        undoLastMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("undo");
				//TODO pop undo current player's last move 
			}
        });        
        /*
	    menuBar.add(timeoutDialogue);
	    timeoutDialogue.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
	    timeoutDialogue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//controllerEventHandler.setEvent("timeout dialogue");
				//TODO pop undo current player's last move 
				TimeoutDialogue.getInstance(controllerEventHandler);
				TimeoutDialogue.launchDialogue(menuBar.getParent(), "Player 1");
			}
	    });   
	    */
	    /*
	    menuBar.add(winnerDialogue);
	    winnerDialogue.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
	    winnerDialogue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//controllerEventHandler.setEvent("winner dialogue");
				//TODO pop undo current player's last move 
				WinnerDialogue.getInstance(controllerEventHandler);
				WinnerDialogue.launchDialogue(menuBar.getParent(), "Player 1");
			}
	    });
	    */   
        menuBar.add(exitGame);
        exitGame.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
        exitGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controllerEventHandler.setEvent("exit game");
				
				// do we want to catch this and prompt ask are you sure or save game state?
				GUILayoutFactory.destroyAndCloseGUI();
			}
        });
	}
	
	/*
	public static void highlightMoveButton(boolean value){
		if (value){
			doMove.setBorder(new CompoundBorder(BorderFactory.createRaisedBevelBorder(),new EmptyBorder(10,10,10,10)));
		} else {
			doMove.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new EmptyBorder(0,0,0,0)));
		}
	}
	*/

	
	
	public static JToolBar getMenuBar() {
		return menuBar;
	}
	
	/**
	 * @param controllerEventHandler the controllerEventHandler to set
	 */
	public static void setControllerEventHandler(eventHandling.Subject controllerEventHandler) {
		MenuView.controllerEventHandler = controllerEventHandler;
	}
}

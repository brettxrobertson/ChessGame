/**
 * referencing:
 * https://docs.oracle.com/javase/tutorial/uiswing/
 * http://www.dreamincode.net/forums/topic/209966-java-drag-and-drop-tutorial-part-1-basics-of-dragging/
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * The gui intialiser and assembler. Called by the controller when starting up.
 * Most panel creation code is with the component classes which are finally assembled here. 
 * 
 * @author keltie
 *
 */
//public class GUILayoutFactory extends JFrame {
public class GUILayoutFactory {
	private static class InstanceHolder {
		private static GUILayoutFactory instance = new GUILayoutFactory(controllerEventHandler, boardView); 
	}
	
	private static final long serialVersionUID = 1L;
	private static boolean isInstanceRunning = false;
	private static int openingBoardSize = 2;

	private static BoardView boardView; // = BoardView.getInstance();;
	private static eventHandling.Subject controllerEventHandler;
		
	private static JFrame frame; // = new JFrame("ChessGame");
	//private static JLayeredPane basePanel; // = new JPanel(new BorderLayout(3, 3));
	//private static JPanel chessBoardPanel;
	private static JPanel basePanel; // = new JPanel(new BorderLayout(3, 3));
	private static JPanel chessBoardPanel;
	private static JLayeredPane chessBoardDragLayerPanel;
	private static JPanel playerStatusPanel;
	private static JPanel newGamePanel;
	private static JPanel newNewGamePanel;
	private static JPanel consolePanel;
	private static JToolBar menuBar;
	
	private static final int TITLE = 0;
	private static final int MENUBAR = 1;
	public static final int BOARDVIEW = 2;
	private static final int PLAYERSTATUSVIEW = 3;
	private static final int CONSOLEVIEW = 4;

	
	public GUILayoutFactory(eventHandling.Subject eventHandler, BoardView boardView){ 
		setControllerEventHandler(eventHandler);
		PlayerStatusView.getInstance();
		GUILayoutFactory.boardView = boardView;
		boardView = BoardView.getInstance();
		boardView.initialiseBoard(eventHandler);
		ConsoleView.getInstance();
		MenuView.getInstance();
		MenuView.setControllerEventHandler(eventHandler);

		initialiseBoard();
		
		isInstanceRunning = true;
	}

	public static GUILayoutFactory getInstance(){
		return InstanceHolder.instance;
	}
	
	public static boolean isInstanceRunning(){
		return isInstanceRunning;
	}
	
	/**
	 * @param controllerEventHandler the controllerEventHandler to set
	 */
	public static void setControllerEventHandler(eventHandling.Subject controllerEventHandler) {
		GUILayoutFactory.controllerEventHandler = controllerEventHandler;
	}
	
	private void initialiseBoard() {
		createBasePanel();
		createAndShowGUI();
	}
	
	private static JPanel createBasePanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		basePanel = factory.createJPanel("basePanel", new BorderLayout(3, 3), 
				new Dimension(1024,960), BorderFactory.createEmptyBorder(), 
				JComponent.LEFT_ALIGNMENT);
		return basePanel;
	}
	
	public static JLayeredPane createChessDragLayerPanel() {
		GUIConcreteFactory factory = new GUIConcreteFactory();
		chessBoardDragLayerPanel = factory.createJLayeredPane("ChessBoardDragLayerPanel", 
				new FlowLayout(), 
				new Dimension(GameView.getNewGameData().chessboardPreferredSize, 
						GameView.getNewGameData().chessboardPreferredSize), 
				new CompoundBorder(new EmptyBorder(0,0,0,0), new LineBorder(Color.BLACK)), 
				JComponent.LEFT_ALIGNMENT);
		chessBoardDragLayerPanel.setBounds(0,0,
				GameView.getNewGameData().chessboardPreferredSize, 
				GameView.getNewGameData().chessboardPreferredSize);
		return chessBoardDragLayerPanel;
	}
	
	public static JPanel createChessPanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		chessBoardPanel = factory.createJPanel("ChessBoardPanel", 
				new GridLayout(GameView.getNewGameData().boardSize, 
						GameView.getNewGameData().boardSize), 
				new Dimension(GameView.getNewGameData().chessboardPreferredSize, 
						GameView.getNewGameData().chessboardPreferredSize), 
				new CompoundBorder(new EmptyBorder(0,0,0,0), new LineBorder(Color.BLACK)), 
				JComponent.LEFT_ALIGNMENT);
		return chessBoardPanel;
	}
	
	public static JPanel createStatusPanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		playerStatusPanel = factory.createJPanel("PlayerStatusPanel", 
				new FlowLayout(), 
				new Dimension(250,600), 
				BorderFactory.createEmptyBorder(), 
				JComponent.LEFT_ALIGNMENT);

		return playerStatusPanel;
	}
	
	public static JPanel createNewGamePanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		newGamePanel = factory.createJPanel("NewGamePanel", 
				new FlowLayout((FlowLayout.LEFT)), 
				new Dimension(150,700), 
				BorderFactory.createEmptyBorder(), 
				JComponent.LEFT_ALIGNMENT);

		return newGamePanel;
	}
	
	public static JPanel createNewNewGamePanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		newNewGamePanel = factory.createJPanel("NewGamePanel", 
				new GridLayout(0,1), 
				new Dimension(200,250), 
				BorderFactory.createEmptyBorder(), 
				JComponent.LEFT_ALIGNMENT);

		return newNewGamePanel;
	}
	
	public static JPanel createConsolePanel(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		consolePanel = factory.createJPanel("ConsolePanel", 
				new FlowLayout(), 
				new Dimension(1000,90), 
				new TitledBorder("Console"), 
				JComponent.LEFT_ALIGNMENT);

		JTextArea textArea = new JTextArea(100,80);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(1000,60));
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		scrollPane.setLocation(0, 0);
		scrollPane.setName("ScrollConsole");
		textArea.setName("TextConsole");
		textArea.setEditable(false);
		textArea.setText("Console: ");
		
		consolePanel.add(scrollPane);
		return consolePanel;
	}
	
	public static JToolBar createMenuBar(){
		GUIConcreteFactory factory = new GUIConcreteFactory();
		menuBar = factory.createJToolBar("MenuBar", new Dimension(600,50));

		return menuBar;
	}
	
    private static void addComponentsToBasePanel() {
        
       	basePanel.add(new JLabel("ChessGame", GUILayoutFactory.TITLE));
       	
        basePanel.add(menuBar, BorderLayout.PAGE_START, GUILayoutFactory.MENUBAR);
        //basePanel.add(chessBoardPanel, BorderLayout.LINE_START, GUILayoutFactory.BOARDVIEW);
        basePanel.add(chessBoardDragLayerPanel, BorderLayout.LINE_START, GUILayoutFactory.BOARDVIEW);
        basePanel.add(playerStatusPanel, BorderLayout.CENTER, GUILayoutFactory.PLAYERSTATUSVIEW);
        basePanel.add(consolePanel, BorderLayout.PAGE_END, GUILayoutFactory.CONSOLEVIEW);
    }	
    
    public static void updateChessBoardComponent(Component component, Object contraints, int index){
    	basePanel.remove(index);
    	basePanel.add(component, contraints, index);
    	basePanel.revalidate();
    	basePanel.repaint();
    }
    
    private static void createAndShowGUI() {
		GUIConcreteFactory factory = new GUIConcreteFactory();
		frame = factory.createJFrame("ChessGame");
		
        addComponentsToBasePanel();
        frame.add(basePanel);
        frame.pack();
    }
    
    public static JPanel getBasePanel(){
    	return basePanel;
    }
    
    public static JFrame getFrame(){
    	return frame;
    }
    
    public static void destroyAndCloseGUI() {
    	frame.dispose();
    	//System.exit(0);
    }


}


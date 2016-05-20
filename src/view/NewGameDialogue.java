/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.Constants;

/**
 * The introduction panel. Allow the user input of new game variables.
 * Reports the change back to the controller. 
 * 
 * @author keltie
 *
 */
public class NewGameDialogue implements ActionListener{
	private static class InstanceHolder {
		private static NewGameDialogue instance = new NewGameDialogue(controllerEventHandler); 
	}
	private static class InstanceHolderJoin {
		private static NewGameDialogue instance = new NewGameDialogue(controllerEventHandler); 
	}

	private static JPanel newGamePanel;

	public static NewGameData newGameData;
	public static NewGameViewData newGameViewData;
	
	private static int sessionID;
	private static ArrayList<String> playerNames = new ArrayList<String>();
	private static int totalTurns;
	private static int boardSize;
	private static int timeoutTurns;
	private static Boolean gameStarted = false;
	private static boolean hostGame = false;
	//private static boolean joinGame = false;
	private static boolean playMachine = false;
	private static boolean playLocal = false;
	private static String gameHostConfiguration;
	private static String gameJoinAvailable;
	
	private static boolean showValidMoves = true;
	private static boolean showConsole = true;
	private static boolean showGraveyard = false;
	private static boolean showToolBar = true;
	private static boolean showPlayerStatus = true;	
	
	private static Map<String, Component> NewGameGUIComponents = new HashMap<String, Component>();
	//private static String[] gameConfigList = {"Select a board configurations...", "Game Config 1", "Game Config 2", "GameConfig 3", "GameConfig 4"};
	//private static String[] gamePendingList; // = {"Select from pending games...", "Game 1", "Game 2", "Game 3"};
	private static String[] gameConfigList = {"Select a board configurations..."};
	private static String[] gamePendingList = {"Select from pending games..."};
	private static eventHandling.Subject controllerEventHandler;
	
	NewGameDialogue(eventHandling.Subject eventHandler){
		controllerEventHandler = eventHandler;
		createNewGamePanel();
	}
	
	NewGameDialogue(eventHandling.Subject eventHandler, int player){
		controllerEventHandler = eventHandler;
		if (player == 0){hostGame = true;}
		createNewGamePanel();
	}
	
	NewGameDialogue(eventHandling.Subject eventHandler, int player, ArrayList<String> gameConfigList, ArrayList<String> pendingGameList){
		controllerEventHandler = eventHandler;
		if (player == 0){hostGame = true;}
		setGameConfigList(gameConfigList);
		setGamePendingList(pendingGameList);
		createNewGamePanel();
	}
	
	public static NewGameDialogue getInstance(eventHandling.Subject eventHandler){
		return InstanceHolder.instance;
	}
	
	public static NewGameData launchDialogue(){
		resetNewGamePanel();
		
		Object[] dialogOptions = {"Submit", "Cancel"};
		
		int NewGameOptionDialogue  = JOptionPane.showOptionDialog(GUILayoutFactory.getFrame(), //BoardView.getChessBoard(), 
					newGamePanel, "Start a new game", 
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, dialogOptions, dialogOptions[0]);
		
		if (NewGameOptionDialogue == 0 ){
			return submitNewGamePanel();
		}
		return null;
	}
	
	private static void createNewGamePanel(){
		newGamePanel = GUILayoutFactory.createNewGamePanel();

		GUIConcreteFactory factory = new GUIConcreteFactory();
		
		ActionListener actionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				
				//TODO - recursively check for (container) JPanel components that can be enabled/disabled

				NewGameDialogue iClass = NewGameDialogue.getInstance(controllerEventHandler);
				 Method method = null;
				try {
					method = iClass.getClass().getDeclaredMethod(e.getActionCommand());
				} catch (NoSuchMethodException | SecurityException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			    try {
					method.invoke(iClass);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		};

		ItemListener itemListener = new ItemListener(){
			public void itemStateChanged(ItemEvent arg0) {}

		};
		
		NewGameGUIComponents.put("Padding1", factory.createJLabel("", "Padding1", 220, factory.paddedBorder1));
		
		NewGameGUIComponents.put("HostGame", factory.createJRadioButton("Host New Game", "HostGame", 
				factory.simpleBorder, true, actionListener, "hostGame"));
		NewGameGUIComponents.put("JoinGame", factory.createJRadioButton("Join Game", "JoinGame", 
				factory.simpleBorder, true, actionListener, "joinGame"));

		NewGameGUIComponents.put("LocalGame", factory.createJRadioButton("Play A Local Game", "LocalGame", 
				factory.simpleBorder, true, actionListener, "localGame"));
		NewGameGUIComponents.put("RemoteGame", factory.createJRadioButton("Play A Remote Game", "RemoteGame", 
				factory.simpleBorder, true, actionListener, "remoteGame"));
		
		NewGameGUIComponents.put("PlayHuman", factory.createJRadioButton("Play Against Human", "PlayHuman", 
				factory.simpleBorder, true, actionListener, "playHuman"));
		NewGameGUIComponents.put("PlayMachine", factory.createJRadioButton("Play Against Machine", "PlayMachine", 
				factory.simpleBorder, true, actionListener, "playMachine"));

		NewGameGUIComponents.put("NewGameConfig", factory.createJComboBox(gameConfigList, "Board Configurations", 
				new Dimension(216,35), factory.paddedBorder1, true, "selectFromNewGameConfig"));
		NewGameGUIComponents.put("GamePendingList", factory.createJComboBox(gamePendingList, "Game Pending List", 
				new Dimension(216,35), factory.paddedBorder1, true, "selectFromGamePendingList"));			
		
		NewGameGUIComponents.put("Player1", factory.createJLabel("Player 1 Name:", "Player1", 100, factory.simpleBorder));
		NewGameGUIComponents.put("Player1Name", factory.createJTextField("White", "Player1Name", 110));
		NewGameGUIComponents.put("Player2", factory.createJLabel("Player 2 Name:", "Player2", 100, factory.simpleBorder));
		NewGameGUIComponents.put("Player2Name", factory.createJTextField("Black", "Player2Name", 110));
		
		NewGameGUIComponents.put("NumberOfTurns", factory.createJLabel("Number of Turns:", "NumberOfTurns", 100, factory.simpleBorder));
		NewGameGUIComponents.put("TurnsValue",factory.createJSpinner("TurnsValue", 110, Constants.TURNS_INITIAL_VALUE, 
				Constants.TURNS_LOWER_BOUND, Constants.TURNS_UPPER_BOUND));
		NewGameGUIComponents.put("Timeout", factory.createJLabel("Timeout:", "Timeout", 100, factory.simpleBorder));
		NewGameGUIComponents.put("TimeCounter", factory.createJSpinner("TimeCounter", 110, Constants.TIME_INITIAL_VALUE, 
				Constants.TIME_LOWER_BOUND, Constants.TIME_UPPER_BOUND));
		NewGameGUIComponents.put("BoardSize", factory.createJLabel("Board Size:", "BoardSize", 100, factory.simpleBorder));
		NewGameGUIComponents.put("BoardSizeValue", factory.createJSpinner("BoardSizeValue", 110, Constants.BOARDSIZE_INITIAL_VALUE, 
				Constants.BOARDSIZE_LOWER_BOUND, Constants.BOARDSIZE_UPPER_BOUND));
		
		NewGameGUIComponents.put("LocalRemotePanel", factory.createJPanel("LocalRemotePanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,80), 
				new TitledBorder("Play a local or remote instance"), JComponent.LEFT_ALIGNMENT));
		NewGameGUIComponents.put("HostJoinPanel", factory.createJPanel("HostJoinPanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,80), 
				new TitledBorder("Host a game or join existing game"), JComponent.LEFT_ALIGNMENT));
		NewGameGUIComponents.put("NewGameConfigPanel", factory.createJPanel("NewGameConfigPanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,200), 
				new TitledBorder("New Game Configuration"), JComponent.LEFT_ALIGNMENT));
		NewGameGUIComponents.put("JoinGameConfigPanel", factory.createJPanel("JoinGameConfigPanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,80), 
				new TitledBorder("Available Game List"), JComponent.LEFT_ALIGNMENT));
		NewGameGUIComponents.put("PlayPanel", factory.createJPanel("PlayPanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,80), 
				new TitledBorder("Play a person or the machine"), JComponent.LEFT_ALIGNMENT));
		NewGameGUIComponents.put("ContestantPanel", factory.createJPanel("ContestantPanel", new FlowLayout((FlowLayout.LEFT)), new Dimension(235,80), 
				new TitledBorder("Contestants"), JComponent.LEFT_ALIGNMENT));
		
		NewGameGUIComponents.put("ShowValidMoves", factory.createJCheckBox("Valid Moves", "ShowValidMoves", new Dimension(100,20), 
				factory.emptyBorder, false, true, itemListener, "showValidMoves"));
		NewGameGUIComponents.put("Graveyard", factory.createJCheckBox("Graveyard", "Graveyard", new Dimension(100,20), 
				factory.emptyBorder, false, false, itemListener, "showGraveyard"));
		NewGameGUIComponents.put("Toolbar", factory.createJCheckBox("Toolbar", "Toolbar", new Dimension(100,20), 
				factory.emptyBorder, false, true, itemListener, "showToolbar"));
		NewGameGUIComponents.put("Console", factory.createJCheckBox("Console", "Console", new Dimension(100,20), 
				factory.emptyBorder, false, true, itemListener, "showConsole"));
		
		try {
			ButtonGroup localRemoteGroup = new ButtonGroup();
			localRemoteGroup.add((JRadioButton)NewGameGUIComponents.get("LocalGame"));
			localRemoteGroup.add((JRadioButton)NewGameGUIComponents.get("RemoteGame"));
			
			((JPanel)NewGameGUIComponents.get("LocalRemotePanel")).add(NewGameGUIComponents.get("LocalGame"));
			((JPanel)NewGameGUIComponents.get("LocalRemotePanel")).add(NewGameGUIComponents.get("RemoteGame"));
			//NewGameGUIComponents.get("LocalGame").setEnabled(false);
			//NewGameGUIComponents.get("RemoteGame").setEnabled(false);
			//NewGameGUIComponents.get("LocalRemotePanel").setEnabled(false);
			newGamePanel.add(NewGameGUIComponents.get("LocalRemotePanel"));
			
			ButtonGroup hostJoinGroup = new ButtonGroup();
			hostJoinGroup.add((JRadioButton)NewGameGUIComponents.get("HostGame"));
			hostJoinGroup.add((JRadioButton)NewGameGUIComponents.get("JoinGame"));
	
			((JPanel)NewGameGUIComponents.get("HostJoinPanel")).add(NewGameGUIComponents.get("HostGame"));
			((JPanel)NewGameGUIComponents.get("HostJoinPanel")).add(NewGameGUIComponents.get("JoinGame"));
			NewGameGUIComponents.get("HostGame").setEnabled(false);
			NewGameGUIComponents.get("JoinGame").setEnabled(false);
			NewGameGUIComponents.get("HostJoinPanel").setEnabled(false);
			newGamePanel.add(NewGameGUIComponents.get("HostJoinPanel"));
	
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("NewGameConfig"));
			
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("NumberOfTurns"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("TurnsValue"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("Timeout"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("TimeCounter"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("BoardSize"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("BoardSizeValue"));
			
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("ShowValidMoves"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("Toolbar"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("Graveyard"));
			((JPanel)NewGameGUIComponents.get("NewGameConfigPanel")).add(NewGameGUIComponents.get("Console"));
			newGamePanel.add(NewGameGUIComponents.get("NewGameConfigPanel"));
			
			((JPanel)NewGameGUIComponents.get("JoinGameConfigPanel")).add(NewGameGUIComponents.get("GamePendingList"));
			NewGameGUIComponents.get("GamePendingList").setEnabled(false);
			NewGameGUIComponents.get("JoinGameConfigPanel").setEnabled(false);
			newGamePanel.add(NewGameGUIComponents.get("JoinGameConfigPanel"));
	
			ButtonGroup playGroup = new ButtonGroup();
			playGroup.add((JRadioButton)NewGameGUIComponents.get("PlayHuman"));
			playGroup.add((JRadioButton)NewGameGUIComponents.get("PlayMachine"));
			
			((JPanel)NewGameGUIComponents.get("PlayPanel")).add(NewGameGUIComponents.get("PlayHuman"));
			((JPanel)NewGameGUIComponents.get("PlayPanel")).add(NewGameGUIComponents.get("PlayMachine"));
			NewGameGUIComponents.get("PlayHuman").setEnabled(false);
			NewGameGUIComponents.get("PlayMachine").setEnabled(false);
			NewGameGUIComponents.get("PlayPanel").setEnabled(false);
			newGamePanel.add(NewGameGUIComponents.get("PlayPanel"));
			
			((JPanel)NewGameGUIComponents.get("ContestantPanel")).add(NewGameGUIComponents.get("Player1"));
			((JPanel)NewGameGUIComponents.get("ContestantPanel")).add(NewGameGUIComponents.get("Player1Name"));
			((JPanel)NewGameGUIComponents.get("ContestantPanel")).add(NewGameGUIComponents.get("Player2"));
			((JPanel)NewGameGUIComponents.get("ContestantPanel")).add(NewGameGUIComponents.get("Player2Name"));
			
			newGamePanel.add(NewGameGUIComponents.get("ContestantPanel"));

		}catch(ClassCastException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void resetNewGamePanel(){
		try{
			NewGameGUIComponents.get("Player1Name").setEnabled(true);
			NewGameGUIComponents.get("Player2Name").setEnabled(true);
			
			((JComboBox<?>)NewGameGUIComponents.get("NewGameConfig")).setSelectedIndex(0);
			((JComboBox<?>)NewGameGUIComponents.get("GamePendingList")).setSelectedIndex(0);
			
			NewGameGUIComponents.get("TurnsValue").setEnabled(true);
			NewGameGUIComponents.get("TimeCounter").setEnabled(true);
			NewGameGUIComponents.get("BoardSizeValue").setEnabled(true);
			
			((JCheckBox)NewGameGUIComponents.get("ShowValidMoves")).setSelected(true);
			((JCheckBox)NewGameGUIComponents.get("Toolbar")).setSelected(true);
			((JCheckBox)NewGameGUIComponents.get("Graveyard")).setSelected(false);
			((JCheckBox)NewGameGUIComponents.get("Console")).setSelected(true);
			
			((JTextField)NewGameGUIComponents.get("Player1Name")).setText("White");
			((JTextField)NewGameGUIComponents.get("Player2Name")).setText("Black");
			((JSpinner)NewGameGUIComponents.get("TurnsValue")).setValue(Constants.TURNS_INITIAL_VALUE);
			
			((JSpinner)NewGameGUIComponents.get("TimeCounter")).setValue(Constants.TIME_INITIAL_VALUE);
			((JSpinner)NewGameGUIComponents.get("BoardSizeValue")).setValue(Constants.BOARDSIZE_INITIAL_VALUE);
			
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static NewGameData submitNewGamePanel(){
		try {
			playerNames.add(0, ((JTextField)NewGameGUIComponents.get("Player1Name")).getText());
			playerNames.add(1, ((JTextField)NewGameGUIComponents.get("Player2Name")).getText());
			
			totalTurns = (int)((JSpinner)NewGameGUIComponents.get("TurnsValue")).getValue();
			timeoutTurns = (int)((JSpinner)NewGameGUIComponents.get("TimeCounter")).getValue();
			boardSize = (int)((JSpinner)NewGameGUIComponents.get("BoardSizeValue")).getValue();				
	
			NewGameGUIComponents.get("Player1Name").setEnabled(false);
			NewGameGUIComponents.get("Player2Name").setEnabled(false);
			NewGameGUIComponents.get("TurnsValue").setEnabled(false);
			NewGameGUIComponents.get("TimeCounter").setEnabled(false);
			NewGameGUIComponents.get("BoardSizeValue").setEnabled(false);
		
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
			
		}
		
		newGameData = new NewGameData(sessionID, 
				playerNames.get(0), playerNames.get(1), totalTurns, boardSize, timeoutTurns, 
				playMachine, playLocal, 
				((JComboBox<?>)NewGameGUIComponents.get("NewGameConfig")).getSelectedItem().toString(), 
				((JComboBox<?>)NewGameGUIComponents.get("GamePendingList")).getSelectedItem().toString());
		
		newGameViewData = new NewGameViewData(sessionID, 
				showValidMoves, showConsole, 
				showGraveyard, showToolBar, showPlayerStatus);
		
		return newGameData;
	}
	
	protected static void hostGame(){
		resetNewGamePanel();
		
		NewGameGUIComponents.get("NewGameConfig").setEnabled(true);
		NewGameGUIComponents.get("NewGameConfigPanel").setEnabled(true);
		
		NewGameGUIComponents.get("NumberOfTurns").setEnabled(true);
		NewGameGUIComponents.get("TurnsValue").setEnabled(true);
		NewGameGUIComponents.get("Timeout").setEnabled(true);
		NewGameGUIComponents.get("TimeCounter").setEnabled(true);
		NewGameGUIComponents.get("BoardSize").setEnabled(true);
		NewGameGUIComponents.get("BoardSizeValue").setEnabled(true);
		
		NewGameGUIComponents.get("GamePendingList").setEnabled(false);
		NewGameGUIComponents.get("JoinGameConfigPanel").setEnabled(false);
		
		NewGameGUIComponents.get("Player1").setEnabled(true);
		NewGameGUIComponents.get("Player1Name").setEnabled(true);
		NewGameGUIComponents.get("Player2").setEnabled(false);
		NewGameGUIComponents.get("Player2Name").setEnabled(false);	
	}
	protected static void joinGame(){
		resetNewGamePanel();
		
		NewGameGUIComponents.get("NewGameConfig").setEnabled(false);
		NewGameGUIComponents.get("NewGameConfigPanel").setEnabled(false);
		
		NewGameGUIComponents.get("NumberOfTurns").setEnabled(false);
		NewGameGUIComponents.get("TurnsValue").setEnabled(false);
		NewGameGUIComponents.get("Timeout").setEnabled(false);
		NewGameGUIComponents.get("TimeCounter").setEnabled(false);
		NewGameGUIComponents.get("BoardSize").setEnabled(false);
		NewGameGUIComponents.get("BoardSizeValue").setEnabled(false);
		
		NewGameGUIComponents.get("GamePendingList").setEnabled(true);
		NewGameGUIComponents.get("JoinGameConfigPanel").setEnabled(false);
		
		NewGameGUIComponents.get("Player1").setEnabled(false);
		NewGameGUIComponents.get("Player1Name").setEnabled(false);
		NewGameGUIComponents.get("Player2").setEnabled(true);
		NewGameGUIComponents.get("Player2Name").setEnabled(true);	
	}
	protected static void localGame(){
		resetNewGamePanel();
		
		NewGameGUIComponents.get("HostGame").setEnabled(false);
		NewGameGUIComponents.get("JoinGame").setEnabled(false);
		NewGameGUIComponents.get("HostJoinPanel").setEnabled(false);
		
		resetNewGamePanel();
		hostGame();
		
		NewGameGUIComponents.get("Player2").setEnabled(true);
		NewGameGUIComponents.get("Player2Name").setEnabled(true);
	}
	protected static void remoteGame(){
		resetNewGamePanel();
		
		NewGameGUIComponents.get("HostGame").setEnabled(true);
		NewGameGUIComponents.get("JoinGame").setEnabled(true);
		NewGameGUIComponents.get("HostJoinPanel").setEnabled(true);
		
		if (((JRadioButton)(NewGameGUIComponents.get("HostGame"))).isSelected()){
			resetNewGamePanel();
			hostGame();
		} else {
			resetNewGamePanel();
			joinGame();
		}
	}
	
	protected static void setHostGame(){
		((JRadioButton)(NewGameGUIComponents.get("RemoteGame"))).setSelected(true);
		remoteGame();
		((JRadioButton)(NewGameGUIComponents.get("HostGame"))).setSelected(true);
		hostGame();
	}
	protected static void setJoinGame(){
		((JRadioButton)(NewGameGUIComponents.get("RemoteGame"))).setSelected(true);
		remoteGame();
		((JRadioButton)(NewGameGUIComponents.get("JoinGame"))).setSelected(true);
		joinGame();
	}
	
	public static JPanel getWelcomePanel() {
		return newGamePanel;
	}

	public static Map<String, Component> getWelcomeGUIFields() {
		return NewGameGUIComponents;
	}

	public static int getBoardSize(){
		return boardSize;
	}
	
	public static int getTotalTurns(){
		return totalTurns;
	}	

	static int getTimeoutTurns(){
		return timeoutTurns;
	}
	
	static ArrayList<String> getPlayerNames(){
		return playerNames;
	}	

	static Boolean getGameStarted(){
		return gameStarted;
	}

	public static NewGameData getNewGameData(){
		return newGameData;
	}
	
	public static void setGameConfigList(ArrayList<String> gameConfigList){
		NewGameDialogue.gameConfigList = new String[gameConfigList.size()];
		
		for (int i = 0; i < gameConfigList.size(); i++){
			System.out.println("read from GameConfigList " + i + ": " + gameConfigList.get(i));
			((JComboBox<String>)NewGameGUIComponents.get("NewGameConfig")).addItem(gameConfigList.get(i));			
		}
	}
	public static int getSelectedGameConfig(){
			return ((JComboBox<String>)NewGameGUIComponents.get("NewGameConfig")).getSelectedIndex();			
	}
	
	public static void setGamePendingList(ArrayList<String> gamePendingList){
		NewGameDialogue.gamePendingList = new String[gamePendingList.size()];
		
		for (int i = 0; i < gamePendingList.size(); i++){
			System.out.println("read from GamePendingList " + i + ": " + gamePendingList.get(i));
			((JComboBox<String>)NewGameGUIComponents.get("GamePendingList")).addItem(gamePendingList.get(i));			
		}
	}
	public static int getSelectedGamePending(){
			return ((JComboBox<String>)NewGameGUIComponents.get("GamePendingList")).getSelectedIndex();			
	}
	
	public static void setPlayerType(int playerType){
		if (playerType == 0){
			//default local game
		} else if (playerType == 1){
			setHostGame();

		} else if (playerType == 2){
			setJoinGame();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
}

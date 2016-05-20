/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import eventHandling.Subject;

/**
 * The player status dashboard. Updates the text based statistics 
 * of the game state as passed on from the controller. 
 * 
 * @author keltie
 *
 */
public class PlayerStatusView {
	private static class InstanceHolder {
		private static PlayerStatusView instance = new PlayerStatusView(); 
	}
	
	private static final long serialVersionUID = 1L;
	private static JPanel playerStatusPanel;
	private static Map<String, Component> playerStatusGUIComponents = new HashMap<String, Component>();
	private static int currentPlayer = 0;
	private Subject eventHandler = new Subject();
	
	public static GameData gameData;
	
	PlayerStatusView(){
		createPlayerStatusPanel();
		new TimeObserver(eventHandler);
	}
	
	public static PlayerStatusView getInstance(){
		return InstanceHolder.instance;
	}
	
	private static JPanel createPlayerStatusPanel(){
		playerStatusPanel = GUILayoutFactory.createStatusPanel();
		
		GUIConcreteFactory factory = new GUIConcreteFactory();
		
		playerStatusGUIComponents.put("Player-padding1", factory.createJLabel("", "Player-padding1", new Dimension(200,25), factory.paddedBorder3));
		playerStatusGUIComponents.put("Player-padding2", factory.createJLabel("", "Player-padding2", new Dimension(200,25), factory.paddedBorder3));
	
		playerStatusGUIComponents.put("Player1Graveyard", factory.createJLabel("", "Player1Graveyard", new Dimension(200,200), new TitledBorder("Player 1")));
		playerStatusGUIComponents.put("Player2Graveyard", factory.createJLabel("", "Player2Graveyard", new Dimension(200,200), new TitledBorder("Player 2")));
		
		playerStatusGUIComponents.put("Player1-Score", factory.createJLabel("0", "Player1-Score", new Dimension(200,100),  new TitledBorder("Player 1 Score")));
		playerStatusGUIComponents.put("Player2-Score", factory.createJLabel("0", "Player2-Score", new Dimension(200,100),  new TitledBorder("Player 2 Score")));
		
		playerStatusGUIComponents.put("Time", factory.createJLabel("", "Time", new Dimension(100,100), new TitledBorder("Time")));	
		playerStatusGUIComponents.put("Turn", factory.createJLabel("", "Turn", new Dimension(100,100), new TitledBorder("Turn")));

		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player1Graveyard"));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player1-Score"));
		((JLabel)playerStatusGUIComponents.get("Player1-Score")).setHorizontalAlignment(JLabel.CENTER);
		playerStatusGUIComponents.get("Player1-Score").setFont(new Font("font1", 1, 48));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player-padding1"));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Time"));
		((JLabel)playerStatusGUIComponents.get("Time")).setHorizontalAlignment(JLabel.CENTER);
		playerStatusGUIComponents.get("Time").setFont(new Font("font1", 1, 48));
		((JLabel)playerStatusGUIComponents.get("Turn")).setHorizontalAlignment(JLabel.CENTER);
		playerStatusPanel.add(playerStatusGUIComponents.get("Turn"));
		playerStatusGUIComponents.get("Turn").setFont(new Font("font1", 1, 48));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player-padding2"));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player2-Score"));
		((JLabel)playerStatusGUIComponents.get("Player2-Score")).setHorizontalAlignment(JLabel.CENTER);
		playerStatusGUIComponents.get("Player2-Score").setFont(new Font("font1", 1, 48));
		
		playerStatusPanel.add(playerStatusGUIComponents.get("Player2Graveyard"));
	
		return playerStatusPanel;
	}
	
	public static void resetPlayerStatusPanel(){
		//for (String : playerStatusGUIComponents){
		//	JLabel label = (JLabel)playerStatusGUIComponents.get(key);
		//	label.setText("");
		//}
	}

	public static GameData getGameData(){
		return gameData;
	}
	
	public static void setGameData(GameData gameData){
		PlayerStatusView.gameData = gameData;
	}
	
	public static JPanel getPlayerStatusPanel() {
		return playerStatusPanel;
	}

	public static void update(){
		setCurrentPlayer();
		updateTime();
		setTurnCount();
		setScore();
		setPlayerNames();
	}
	
	public static void updateTime(){
		try {
			((JLabel)playerStatusGUIComponents.get("Time")).setText(String.valueOf(GameView.gameData.getTimeRemaining()));
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void setCurrentPlayer(){
		PlayerStatusView.currentPlayer = GameView.getGameData().getCurrentPlayer();
	}

	public static void setTurnCount(){
		//if (turn > 50) turn = Constants.TURNS_UPPER_BOUND;
		try {
			((JLabel)playerStatusGUIComponents.get("Turn")).setText(String.valueOf(GameView.gameData.getTurnsRemaining()));
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
		}
	}

	public static void setScore(){
		try {
			((JLabel)playerStatusGUIComponents.get("Player1-Score")).setText(String.valueOf(GameView.gameData.getScore().get(0)));
			((JLabel)playerStatusGUIComponents.get("Player2-Score")).setText(String.valueOf(GameView.gameData.getScore().get(1)));
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void setPlayerNames(){
		try {
			((JLabel)playerStatusGUIComponents.get("Player1-Score")).setBorder(new TitledBorder(String.valueOf(GameView.gameData.getPlayerNames().get(0)) + " Score"));
			((JLabel)playerStatusGUIComponents.get("Player2-Score")).setBorder(new TitledBorder(String.valueOf(GameView.gameData.getPlayerNames().get(1)) + " Score"));
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
		}
	}
}
package view;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of game state data to pass
 * There are ((WILL BE, open for now)) no get/setters as this class is a convenience data object/struct 
 * with final variables once constructed.
 */
public class GameData implements Serializable{
	private int sessionID;
	private int currentPlayer;
	private ArrayList<String> playerNames = new ArrayList<String>();
	private ArrayList<Integer> score = new ArrayList<Integer>();
	private Integer turnsRemaining;
	private Integer timeRemaining;
		
	public GameData(int sessionID, int currentPlayer, 
			ArrayList<String> playerNames, ArrayList<Integer> score, 
			int turnsRemaining, int timeRemaining){
		this.sessionID = sessionID;
		this.currentPlayer = currentPlayer;
		this.playerNames = playerNames;
		this.score = score;
		this.turnsRemaining = turnsRemaining;
		this.timeRemaining = timeRemaining;
	}
	
	public void setCurrentPlayer(int currentPlayer){this.currentPlayer = currentPlayer;}
	public void setPlayerNames(ArrayList<String> playerNames){this.playerNames = playerNames;}
	public void setScore(int currentPlayer, int score){this.score.set(currentPlayer, score);}
	public void setTurnsRemaining(int  turnsRemaining){this.turnsRemaining = turnsRemaining;}
	public void setTimeRemaining(int  timeRemaining){this.timeRemaining = timeRemaining;}
	
	public int getCurrentPlayer(){return currentPlayer;}
	public ArrayList<String> getPlayerNames(){return playerNames;}
	public ArrayList<Integer> getScore(){return score;}
	public int getTurnsRemaining(){return turnsRemaining;}
	public int getTimeRemaining(){return timeRemaining;}

}

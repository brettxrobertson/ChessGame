package view;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of new game configuration data to pass
 * There are no get/setters as this class is a convenience data object/struct 
 * with final variables once constructed.
 */
public class NewGameData implements Serializable{
	public final int sessionID; //could be game config ID from list.
	public final ArrayList<String> playerNames = new ArrayList<String>();
	public final int totalTurns;
	public final int boardSize;
	public final int chessboardPreferredSize = 768; //used to keep button icons scaled correctly 
	public final int timeoutTurns;
	
	public final String gameHostConfiguration;
	public final String gameJoinAvailable;
	
	public final boolean playMachine;
	public final boolean playLocal;
	
	public NewGameData(int sessionID, 
			String player1Name, String player2Name, int totalTurns, int boardSize, int timeoutTurns, 
			boolean playMachine, boolean playLocal, 
			String gameHostConfiguration, String gameJoinAvailable){
		this.sessionID = sessionID;
		this.playerNames.add(player1Name);
		this.playerNames.add(player2Name);
		this.totalTurns = totalTurns;
		this.boardSize = boardSize;
		this.timeoutTurns = timeoutTurns;
		this.playMachine = playMachine;
		this.playLocal = playLocal;
		this.gameHostConfiguration = gameHostConfiguration;
		this.gameJoinAvailable = gameJoinAvailable;
	}
	public NewGameData newGameData;
}

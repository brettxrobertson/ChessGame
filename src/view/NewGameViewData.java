package view;

/**
 * class of new game configuration data to pass
 * There are no get/setters as this class is a convenience data object/struct 
 * with final variables once constructed.
 */
public class NewGameViewData {
	public final int sessionID; //could be game config ID from list.
	public final boolean showValidMoves;
	public final boolean showConsole;
	public final boolean showGraveyard;
	public final boolean showToolBar;
	public final boolean showPlayerStatus;
	
	public NewGameViewData(int sessionID, 
			boolean showValidMoves, boolean showConsole, 
			boolean showGraveyard, boolean showToolBar, boolean showPlayerStatus){
		this.sessionID = sessionID;
		this.showValidMoves = showValidMoves;
		this.showConsole = showConsole;
		this.showGraveyard = showGraveyard;
		this.showToolBar = showToolBar;
		this.showPlayerStatus = showPlayerStatus;
	}
	public NewGameViewData newGameViewData;
}

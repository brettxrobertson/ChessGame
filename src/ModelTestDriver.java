import java.io.IOException;

import eventHandling.Subject;
import model.Board;
import model.Constants;
import model.GameModel;
import model.GameTimer;
import model.Player;
import model.SaveGame;
import model.Tile;
import model.UndoCommand;
import model.algorithm.*;

public class ModelTestDriver {

	public static void main(String[] args) throws IOException {

		

		GameModel game = new GameModel(null);

		// get board with default size (6x6)
		Board board = game.getBoard();

		// initialize the board
		board.initBoard(6);
		
//		board.printBoard();
		Subject timerSubject = new Subject();
		//game.startGame(timerSubject);
		game.createPlayers("Rosie", "Nanna");
		
		
		Tile tile = board.getTile(3,2);
		
		Tile[][] tiles = board.getTiles();
		
		board.move(tiles[0][0].piece.getPieces(), 0, 0, 3, 0);
//		board.move(tiles[0][0].piece.getPieces(), 3, 0, 4, 0);
		board.move(tiles[3][0].piece.getPieces(), 3, 0, 4, 0);
//		board.move(Constants.PIECE_TYPE_ROOK, 1, 5, 2, 5);
//		board.printBoard();
		//SaveGame.saveGame(game);
//		board.move(tiles[0][0].getPieces(), 0, 0, 0, 0);
		//game = SaveGame.restartGame();
//		board.printBoard();
		UndoCommand undo = new UndoCommand();
		undo.undo();
//		board.printBoard();
		System.out.println("hello");
	}

}

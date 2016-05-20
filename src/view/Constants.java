package view;

import java.io.File;

public class Constants {

	static final int WHITE				= 0;
	static final int BLACK				= 1;
	
	static final String[] leftRightID = {"left", "right"};
	
	static final String[] pieceName = {"Default", "Rook", "Bishop",
		"Knight", "Queen", "King", "Pawn", "Barrier"};
	
	static final int NONE 				= 0;
	static final int ROOK 				= 1;
	static final int BISHOP 			= 2;
	static final int KNIGHT 			= 3;
	static final int QUEEN 				= 4;
	static final int KING 				= 5;
	static final int PAWN 				= 6;
	static final int BARRIER 			= 7;	

	
	
	static final int FROM				= 0;
	static final int TO					= 1;
	
	static final int TURNS_INITIAL_VALUE 	= 10; 
	static final int TURNS_LOWER_BOUND 		= 10;
	static final int TURNS_UPPER_BOUND 		= 50;
	static final int BOARDSIZE_INITIAL_VALUE = 8;
	static final int BOARDSIZE_LOWER_BOUND 	= 6; // fixed  at until model can handle it.
	static final int BOARDSIZE_UPPER_BOUND 	= 999; //arbitrary for now
	static final int TIME_INITIAL_VALUE 	= 60;
	static final int TIME_LOWER_BOUND 	= 0; // arbitrary for now
	static final int TIME_UPPER_BOUND 	= 600; //arbitrary for now
	
	static final File ICON_SET = new File("chessIcons_2048x512_unique.png");
}

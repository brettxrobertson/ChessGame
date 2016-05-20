package model;

import java.io.Serializable;
import java.util.LinkedList;

public class HistoryManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6716212943700052548L;
	static LinkedList<Move> historyList = new LinkedList<Move>();

	public static void addMoveHistory(Move move) {
		historyList.add(move);
	}

	/**
	 * @return the historyList
	 */
	public static LinkedList<Move> getHistoryList() {
		return historyList;
	}
}

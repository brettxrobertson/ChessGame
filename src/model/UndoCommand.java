package model;

import java.util.NoSuchElementException;

public class UndoCommand implements Command {
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		Move lastMove;
		
		try{
			lastMove = HistoryManager.historyList.getLast();
			lastMove.undo();
		}catch(NoSuchElementException e){
			System.out.println(e.getLocalizedMessage());
		}
		
	}

}

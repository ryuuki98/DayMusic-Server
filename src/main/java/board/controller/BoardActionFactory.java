package board.controller;

import board.controller.action.CreateBoardAction;
import board.controller.action.DeleteBoardAction;
import board.controller.action.SearchBoardAction;
import board.controller.action.UpdateBoardAction;

public class BoardActionFactory {
	private BoardActionFactory() {
		
	}
	
	private static BoardActionFactory instance = new BoardActionFactory();
	
	public static BoardActionFactory getInstance() {
		return instance;
	}
	
	public BoardAction getAction(String command) {
		BoardAction action = null;
		
		if (command.equals("write")) 
			action = new CreateBoardAction();
		else if(command.equals("delete"))
			action = new DeleteBoardAction();
		else if(command.equals("search"))
			action = new SearchBoardAction();
		else if(command.equals("update"))
			action = new UpdateBoardAction();
		
		return action;
		
	}
}
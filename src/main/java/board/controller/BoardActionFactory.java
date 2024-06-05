package board.controller;

import board.controller.action.*;
import follow.controller.FollowAction;

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
		else if(command.equals("detail"))
			action = new DetailBoardAction();
		else if(command.equals("myBoard"))
			action = new MyBoardAction();
		else if(command.equals("myMusicBoard"))
			action = new MyBoardMusicAction();
		else if(command.equals("follow"))
			action = new FollowBoardAction();
		return action;
		
	}
}
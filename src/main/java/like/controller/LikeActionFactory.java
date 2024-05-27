	package like.controller;

import like.controller.action.LikeAction;
import like.controller.action.LikeListAction;
import like.controller.action.LikeAddAction;

public class LikeActionFactory {
	private LikeActionFactory() {
		
	}
	
	private static LikeActionFactory instance = new LikeActionFactory();
	
	public static LikeActionFactory getInstance() {
		return instance;
	}
	
	public LikeAction getAction(String command) {
		LikeAction action = null;
		if(command.equals("like")) {
			action = new LikeListAction();
		}else if(command.equals("likeAdd")) {
			action = new LikeAddAction();
		}
		return action;
		
	}
}
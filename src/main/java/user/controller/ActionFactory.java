package user.controller;

import like.action.LikeAction;
import user.controller.action.LoginAction;

public class ActionFactory {
	private ActionFactory() {
		
	}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if (command.equals("login")) {
			action = new LoginAction();
		}else if(command.equals("like")) {
			action = new LikeAction();
		}
		return action;
		
	}
}
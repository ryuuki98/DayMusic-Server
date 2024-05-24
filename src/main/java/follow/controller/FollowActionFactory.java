package follow.controller;

import follow.controller.action.FollowListAction;

public class FollowActionFactory {
	private FollowActionFactory() {}
	
	private static FollowActionFactory instance = new FollowActionFactory();
	
	public static FollowActionFactory getInstance() {
		return instance;
	}
	
	public FollowAction getAction(String command) {
		FollowAction action = null;
		
		if(command.equals("follow")) {
			action = new FollowListAction();
		}
		return action;
	}
}

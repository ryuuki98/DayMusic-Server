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
		System.out.println("command : " + command);
		
		if(command.equals("/follow")) {
			action = new FollowListAction(command);
		}
		return action;
	}
	
	public FollowAction getAction(String command, String id) {
		FollowAction action = null;
		System.out.println("FollowAction command : " + command);
		System.out.println("FollowAction id : " + id);
		
		if(command.equals("/followed_list")) {
			System.out.println("/followed_list/ 실행");
			action = new FollowListAction(id);
		}
		else if(command.equals("/follower_list")) {
			action = new FollowListAction(id);
		}
		return action;
	}
}

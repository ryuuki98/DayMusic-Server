package follow.controller;

import follow.controller.action.FollowAddAction;
import follow.controller.action.FollowDeleteAction;
import follow.controller.action.FollowedListAction;
import follow.controller.action.FollowerListAction;

public class FollowActionFactory {
	private FollowActionFactory() {}
	
	private static FollowActionFactory instance = new FollowActionFactory();
	
	public static FollowActionFactory getInstance() {
		return instance;
	}
	
	public FollowAction getAction(String command) {
		FollowAction action = null;
		System.out.println("command : " + command);
		
		if(command == null) {
			action = new FollowAddAction();
		}
		action = new FollowDeleteAction();
		return action;
	}
	
	public FollowAction getAction(String command, String id) {
		FollowAction action = null;
		System.out.println("FollowAction command : " + command);
		System.out.println("FollowAction id : " + id);
		
		if(command.equals("/followed_list")) {
			System.out.println("/followed_list/ 실행");
			action = new FollowedListAction(id);
		}
		else if(command.equals("/follower_list")) {
			System.out.println("/follower_list/ 실행");
			action = new FollowerListAction(id);
		}
		return action;
	}
}

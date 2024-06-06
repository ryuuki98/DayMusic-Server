package follow.controller;

import follow.controller.action.FollowAddAction;
import follow.controller.action.FollowDeleteAction;
import follow.controller.action.FollowListAction;

public class FollowActionFactory {
	private FollowActionFactory() {}
	
	private static FollowActionFactory instance = new FollowActionFactory();
	
	public static FollowActionFactory getInstance() {
		return instance;
	}
	
	public FollowAction getAction(String command) {
		FollowAction action = null;
		
		
		if(command.equals("add")) {
			action = new FollowAddAction();
			
		}
		else if(command.equals("delete")) {
			action = new FollowDeleteAction();
			
		}
		return action;
	}
	
	public FollowAction getAction(String command, String id) {
		FollowAction action = null;
		
		
		
		if(command.equals("/follow_list")) {
			
			action = new FollowListAction(id);
		}
		return action;
	}
}

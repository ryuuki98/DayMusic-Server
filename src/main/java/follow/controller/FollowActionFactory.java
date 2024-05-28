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
		System.out.println("command : " + command);
		
		if(command.equals("add")) {
			action = new FollowAddAction();
			System.out.println("add로들어오나?");
		}
		else if(command.equals("delete")) {
			action = new FollowDeleteAction();
			System.out.println("delete로들어오나?");
		}
		return action;
	}
	
	public FollowAction getAction(String command, String id) {
		FollowAction action = null;
		System.out.println("FollowAction command : " + command);
		System.out.println("FollowAction id : " + id);
		
		if(command.equals("/follow_list")) {
			System.out.println("/follow_list/ 실행");
			action = new FollowListAction(id);
		}
		return action;
	}
}

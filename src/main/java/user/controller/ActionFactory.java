package user.controller;

import follow.controller.FollowAction;
import user.controller.action.JoinAction;
import user.controller.action.LoginAction;
import user.controller.action.UpdateNicknameAction;
import user.controller.action.UpdatePasswordAction;

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
		} else if (command.equals("join")) {
			action = new JoinAction();
		} else if (command.equals("updateNickname")) {
			action = new UpdateNicknameAction();
		} else if (command.equals("updatePassword")) {
			action = new UpdatePasswordAction();
		}
		return action;

	}
}
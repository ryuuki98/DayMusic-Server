package user.controller;

import user.controller.action.DeleteUserAction;
import user.controller.action.DuplicateEmail;
import user.controller.action.DuplicateId;
import user.controller.action.DuplicateNickname;
import user.controller.action.DuplicatePhone;
import user.controller.action.JoinAction;
import user.controller.action.LoginAction;
import user.controller.action.UpdateInformation;
import user.controller.action.UpdateNicknameAction;
import user.controller.action.UpdatePasswordAction;
import user.controller.action.UpdateProfileImage;

public class UserActionFactory {
	private UserActionFactory() {
		
	}
	
	private static UserActionFactory instance = new UserActionFactory();
	
	public static UserActionFactory getInstance() {
		return instance;
	}
	
	public UserAction getAction(String command) {
		UserAction action = null;
		
		if (command.equals("login")) {
			action = new LoginAction();
		}else if (command.equals("join")) {
			action = new JoinAction();
		}else if (command.equals("updateNickname")) {
			action = new UpdateNicknameAction();
		}else if (command.equals("updatePassword")) {
			action = new UpdatePasswordAction();
		}else if (command.equals("updateProfileImage")) {
			action = new UpdateProfileImage();
		}else if (command.equals("updateInformation")) {
			action = new UpdateInformation();
		}else if (command.equals("deleteUser")) {
			action = new DeleteUserAction();
		}else if (command.equals("duplicateId")) {
			action = new DuplicateId();
		}else if (command.equals("duplicateEmail")) {
			action = new DuplicateEmail();
		}else if (command.equals("duplicatePhone")) {
			action = new DuplicatePhone();
		}else if (command.equals("duplicateNickname")) {
			action = new DuplicateNickname();
		}
		return action;
		
	}
}
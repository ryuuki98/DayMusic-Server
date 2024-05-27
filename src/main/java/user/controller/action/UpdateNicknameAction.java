package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

public class UpdateNicknameAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();

		String id = request.getParameter("id");
		String newNickname = request.getParameter("nickname");
		
		System.out.println(id);
		System.out.println(newNickname);

		if (userDao.duplicateNickname(id,newNickname)) {
			System.out.println("닉네임이 중복됩니다.");
		} else {
			UserResponseDto user = userDao.updateNickname(id, newNickname);
		}

	}

}

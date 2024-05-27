package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

public class UpdateProfileImage implements UserAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		
		String id = request.getParameter("id");
		String profile_img_url =request.getParameter("profile_img_url");
		
		UserResponseDto user = userDao.updateProfileImage(id,profile_img_url);
		if (user == null) {
			System.out.println("사진 변경 안됨");
		}else {
			System.out.println("사진 변경 완료");
		}
	}

}

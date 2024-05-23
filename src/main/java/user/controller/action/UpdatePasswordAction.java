package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;
import util.PasswordCrypto;

public class UpdatePasswordAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		
		String id =request.getParameter("id");
		String newPassword = request.getParameter("password");
		
		UserResponseDto user = userDao.updatePassword(id, newPassword);
		
		System.out.println("db에 바뀐 새 비밀번호 : " +user.getPassword());
		
		System.out.println("바뀌었다면 true가 출력되어야 함 : " + PasswordCrypto.decrypt(newPassword, user.getPassword()) );
	}

}

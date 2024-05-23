package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인 처리 로직!");
		UserDao userDao = UserDao.getInstance();
		UserRequestDto userRequestDto = new UserRequestDto();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println(id);
		System.out.println(password);
		
		userRequestDto.setId(id);
		userRequestDto.setPassword(password);
		
		UserResponseDto user = userDao.validateUser(userRequestDto);
		
		if (user == null) {
			System.out.println("아이디 혹은 비밀번호 불일치");
		}else {
			System.out.println("로그인 성공");
			
		}
		
		
		
	}

}

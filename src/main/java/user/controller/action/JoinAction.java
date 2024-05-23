package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class JoinAction  implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("가입 처리 로직");
		UserDao userDao = UserDao.getInstance();
		UserRequestDto userRequestDto = new UserRequestDto();
		
		//id, password, name, gender, email, phone, telecom, nickname, profile_img_url, is_staff
		String id =request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String telecom = request.getParameter("telecom");
		String nickname = request.getParameter("nickname");
		String profile_img_url = request.getParameter("profile_img_url");
		
		userRequestDto = new UserRequestDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url, false);
		
		UserResponseDto user = userDao.joinUser(userRequestDto);
		
		if (user == null) {
			System.out.println("가입에 실패 하였습니다.");
		}else {
			System.out.println("가입에 성공 하셨습니다.");
		}
		
	}

}

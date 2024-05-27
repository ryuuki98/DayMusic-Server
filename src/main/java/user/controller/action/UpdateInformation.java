package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class UpdateInformation implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String telecom = request.getParameter("telecom");
		
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setId(id);
		userRequestDto.setName(name);
		userRequestDto.setGender(gender);
		userRequestDto.setEmail(email);
		userRequestDto.setPhone(phone);
		userRequestDto.setTelecom(telecom);
		
		UserResponseDto user = userDao.UpdateInformation(userRequestDto);
		
		if (user == null) {
			System.out.println("기타정보 업데이트 실패");
		}else {
			System.out.println("정보 업데이트 성공");
		}
		
	}

}

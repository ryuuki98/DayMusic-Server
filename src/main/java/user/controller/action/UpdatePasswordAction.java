package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.PasswordCrypto;

public class UpdatePasswordAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();

		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
		
		String id = jsonObject.getString("id");
		String currentPassword = jsonObject.getString("currentPassword");
		String newPassword = jsonObject.getString("newPassword");

		
		
		
		UserRequestDto userrequestDto = new UserRequestDto();
		userrequestDto.setId(id);
		userrequestDto.setPassword(currentPassword);

		UserResponseDto user = userDao.validateUser(userrequestDto);
		if (user == null) {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("{\"updatePassword\":\"false\"}");
		} else {
			// 비밀번호 업데이트 처리
			UserResponseDto updateUser = userDao.updatePassword(id, newPassword);
			if (updateUser != null) {
				
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("{\"updatePassword\":\"true\"}");
			} else {
				
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"updatePassword\":\"false\"}");
			}
		}
	}

}

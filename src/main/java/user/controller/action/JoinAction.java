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

public class JoinAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = UserDao.getInstance();
		UserRequestDto userRequestDto = new UserRequestDto();

		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

		// id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
		// is_staff
		String id = jsonObject.getString("id");
		String password = jsonObject.getString("password");
		String name = jsonObject.getString("name");
		String gender = jsonObject.getString("gender");
		String email = jsonObject.getString("email");
		String phone = jsonObject.getString("phone");
		String telecom = jsonObject.getString("telecom");
		String nickname = jsonObject.getString("nickname");
		String profile_img_url = jsonObject.optString("profile_img_url");
		userRequestDto = new UserRequestDto(id, password, name, gender, email, phone, telecom, nickname,
				profile_img_url, false);

		UserResponseDto user = userDao.joinUser(userRequestDto);

		if (user == null) {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("{\"message\":\"Join failure\"}");
		} else {
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("{\"message\":\"Join successful\"}");
		}

	}

}

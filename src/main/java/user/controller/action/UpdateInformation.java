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

public class UpdateInformation implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();

		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

		

		String id = jsonObject.getString("id");
		String name = jsonObject.getString("name");
		String gender = jsonObject.getString("gender");
		String email = jsonObject.getString("email");
		String phone = jsonObject.getString("phone");
		String telecom = jsonObject.getString("telecom");

		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setId(id);
		userRequestDto.setName(name);
		userRequestDto.setGender(gender);
		userRequestDto.setEmail(email);
		userRequestDto.setPhone(phone);
		userRequestDto.setTelecom(telecom);
		
		UserResponseDto user = userDao.updateInformation(userRequestDto);
		
		if (user == null) {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}else {
			
			response.setStatus(HttpServletResponse.SC_OK);

		}
		
	}

}

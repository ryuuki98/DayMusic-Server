package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

public class UpdateNicknameAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();

		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

		

		String id = jsonObject.getString("id");
		String newNickname = jsonObject.getString("nickname");



		if (userDao.duplicateNickname(id,newNickname)) {
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("{\"message\":\"Nickname update failure\"}");
		} else {
			UserResponseDto user = userDao.updateNickname(id, newNickname);
			
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("{\"message\":\"Nickname update successful\"}");
		}

	}

}

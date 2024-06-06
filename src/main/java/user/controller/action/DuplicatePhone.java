package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

public class DuplicatePhone implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserDao userDao = UserDao.getInstance();
		
		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
		
		String phone = jsonObject.getString("phone");
		UserResponseDto user = userDao.findUserByPhone(phone);
		
		if (user == null) {
			
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"false\"}");

			
		}else {
			
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"true\"}");
		}
	}

}

package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;

public class DeleteUserAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserDao userDao = UserDao.getInstance();

		JSONObject jsonObject = (JSONObject)request.getAttribute("jsonRequest");
		String id = jsonObject.getString("id");
		boolean isDeleted = userDao.deleteUserById(id);
		
		if (isDeleted) {
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("{\"message\":\"delete success\"}");

		}else {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("{\"message\":\"delete fail\"}");
		}
		
	}

}

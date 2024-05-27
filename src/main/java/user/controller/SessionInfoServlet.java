package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

//SessionInfoServlet.java
@WebServlet("/user/session")
public class SessionInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String nickname = (String) session.getAttribute("nickname");

		response.setContentType("application/json;charset=UTF-8");
		JSONObject jsonResponse = new JSONObject();

		if (userId != null && nickname != null) {
			jsonResponse.put("userId", userId);
			jsonResponse.put("nickname", nickname);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			jsonResponse.put("message", "User not logged in");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(jsonResponse.toString());
	}

}

package follow.controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FollowServiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();			// / 뒤에 마지막 주소를 가져옴
		
		
		FollowActionFactory af = FollowActionFactory.getInstance();
		FollowAction action = null;
		
		String id = "";	// 현재 로그인한 유저 아이디
		if(request.getParameter("id") != null) {
			id = request.getParameter("id");
			
			
			action = af.getAction(command, id);
		} else {
			action = af.getAction(command);
		}
		
		if(action != null) {
			action.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		JSONObject jsonObject = new JSONObject(sb.toString());

		// JSON 데이터 추출
		String command = jsonObject.getString("command");
		
		FollowActionFactory af = FollowActionFactory.getInstance();
		FollowAction action = af.getAction(command);

		String followerId = jsonObject.getString("followerId");
		String followedId = jsonObject.getString("followedId");

		if(action != null) {
			request.setAttribute("jsonRequest", jsonObject);
			action.execute(request, response);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

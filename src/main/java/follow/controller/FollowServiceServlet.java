package follow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FollowServiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();			// / 뒤에 마지막 주소를 가져옴
		System.out.println("ServiceServlet command : " + command);
		
		FollowActionFactory af = FollowActionFactory.getInstance();
		FollowAction action = null;
		
		String id = "";	// 현재 로그인한 유저 아이디
		if(request.getParameter("id") != null) {
			id = request.getParameter("id");
			System.out.println("ServiceServlet id: "+id);
			
			action = af.getAction(command, id);
		} else {
			action = af.getAction(command);
		}
		
		if(action != null) {
			action.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package like.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.controller.action.LikeAction;


public class LikeServiceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GoF 디자인패턴
		// ㄴ 생성패턴 싱글톤 , 팩토리메소드 , 커맨드 
		//input hidden 으로 넘겨도 되고 경로에 ?command = "ddd" 로 넘겨도 되고 
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		
		LikeActionFactory af = LikeActionFactory.getInstance();
		LikeAction action = af.getAction(command);
		
		if(action != null) {
			action.execute(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String command = request.getParameter("command");
		
		LikeActionFactory af = LikeActionFactory.getInstance();
		LikeAction action = af.getAction(command);
		
		if(action != null) {
			action.execute(request, response);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
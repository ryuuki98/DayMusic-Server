package board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class BoardServiceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GoF 디자인패턴
		// ㄴ 생성패턴 싱글톤 , 팩토리메소드 , 커맨드 
		
		//input hidden 으로 넘겨도 되고 경로에 ?command = "ddd" 로 넘겨도 되고 
//		String command = request.getParameter("command");
//		String contents = request.getParameter("contents");
//        String isPublic = request.getParameter("isPublic");
//		System.out.println("command : " + command);
//		System.out.println("contents : " + contents);
//		System.out.println("ispublic : " + isPublic);
		
		request.setCharacterEncoding("UTF-8");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
//		System.out.println("sb.toString() : " + sb.toString());
		JSONObject jsonObject = new JSONObject(sb.toString());
		
		
		// JSON 데이터 추출
		String command = jsonObject.getString("command");
		System.out.println("command: " + command);
		
		if(command != null) {
			BoardActionFactory af = BoardActionFactory.getInstance();
			BoardAction action = af.getAction(command);
			
			if(action != null) {
				request.setAttribute("data", jsonObject);
				action.execute(request, response);
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

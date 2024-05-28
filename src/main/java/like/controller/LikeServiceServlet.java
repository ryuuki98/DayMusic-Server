package like.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.controller.action.LikeAction;
import org.json.JSONObject;


public class LikeServiceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GoF 디자인 패턴
		// ㄴ 생성 패턴: 싱글톤, 팩토리 메소드, 커맨드
		// input hidden 으로 넘겨도 되고 경로에 ?command=ddd 로 넘겨도 됩니다
		System.out.println("LikeServiceServlet GET 실행");
		request.setCharacterEncoding("UTF-8");

		// URL 파라미터에서 command 값 추출
		String command = request.getParameter("command");
		if (command == null || command.isEmpty()) {
			System.out.println("Command가 null이거나 비어있습니다");
			return;
		}

		System.out.println("command: " + command);
		LikeActionFactory af = LikeActionFactory.getInstance();
		LikeAction action = af.getAction(command);

		if (action != null) {
			request.setAttribute("command", command);
			action.execute(request, response);
		} else {
			System.out.println("Command에 해당하는 액션을 찾을 수 없습니다: " + command);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST실행");
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
		System.out.println("command"+command);
		LikeActionFactory af = LikeActionFactory.getInstance();
		LikeAction action = af.getAction(command);

		if(action != null) {
			request.setAttribute("jsonRequest", jsonObject);
			action.execute(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println("PUT실행");
		String command = request.getParameter("command");
		System.out.println(command);
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

package user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class UserServiceServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		System.out.println(command);
		UserActionFactory af = UserActionFactory.getInstance();
		UserAction action = af.getAction(command);

		if (action != null) {
			request.setAttribute("jsonRequest", jsonObject);
			action.execute(request, response);
		}
	}


}

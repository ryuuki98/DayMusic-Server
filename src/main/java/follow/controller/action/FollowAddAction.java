package follow.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import follow.controller.FollowAction;
import follow.model.FollowDao;
import org.json.JSONObject;

public class FollowAddAction extends HttpServlet implements FollowAction {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		JSONObject jsonResponse = new JSONObject();
		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

		FollowDao followDao = FollowDao.getInstance();

		String followerId = jsonObject.getString("followerId");
		String followedId = jsonObject.getString("followedId");

		boolean isFollow = followDao.isFollowing(followerId, followedId);
		System.out.println("팔로우 추가 : " + isFollow);
		jsonObject.put("isFollow", isFollow);

		String message = followDao.followChange(followerId, followedId);
		jsonResponse.put("message", message);

		// JSON 응답으로 반환
		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());
	}
}

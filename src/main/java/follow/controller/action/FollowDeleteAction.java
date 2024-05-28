package follow.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import follow.controller.FollowAction;
import follow.model.FollowDao;
import org.json.JSONObject;

public class FollowDeleteAction extends HttpServlet implements FollowAction{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

		FollowDao followDao = FollowDao.getInstance();
		String followerId = request.getParameter("followerId");
		String followedId = request.getParameter("followedId");

		followDao.deleteFollow(followerId, followedId);

		String message = "팔로우 취소 성공";
		request.setAttribute("message", message);
	}
}

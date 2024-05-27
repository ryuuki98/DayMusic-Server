package follow.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import follow.controller.FollowAction;
import follow.model.FollowDao;

public class FollowAddAction extends HttpServlet implements FollowAction {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		FollowDao followDao = FollowDao.getInstance();
		String followedId = request.getParameter("followedId");
		String followerId = request.getParameter("followerId");

		followDao.addFollow(followedId, followerId);
	}
}

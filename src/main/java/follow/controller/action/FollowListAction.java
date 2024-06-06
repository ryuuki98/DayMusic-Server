package follow.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import follow.model.FollowResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;

import follow.controller.FollowAction;
import follow.model.FollowDao;
import user.model.UserDao;

public class FollowListAction extends HttpServlet implements FollowAction {
	private String id;
	
	public FollowListAction(String id) {
		this.id = id;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		System.out.println("내가 팔로우한 유저(팔로잉) 닉네임 읽어오기");
		FollowDao followDao = FollowDao.getInstance();
		UserDao userDao = UserDao.getInstance();

		String nickname = userDao.findNickNameById(id);

		List<FollowResponseDto> FollowingList = followDao.findFollowingList(id);
		List<FollowResponseDto> FollowerList = followDao.findFollowerList(id);
		System.out.println("FollowingList : " + FollowingList);
		System.out.println("FollowerList : " + FollowerList);
		System.out.println("nickname : " + nickname);

//		FollowResponseDto isFollowing = followDao.isFollowing()

		JSONArray result = new JSONArray();
		result.put(FollowingList);
		result.put(FollowerList);
		result.put(nickname);

		resObj.put("status", 200);
		resObj.put("result", result);
		System.out.println(resObj);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}

package like.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.model.LikeDao;
import org.json.JSONObject;

public class LikeAddAction implements LikeAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("좋아요 추가/제거로직");

		JSONObject jsonResponse = new JSONObject();
		LikeDao likeDao = LikeDao.getInstance();
		JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
		System.out.println("1");

		int board_code = jsonObject.getInt("board_code");
		String id = jsonObject.getString("id");
		System.out.println("id:"+ id);
		System.out.println("board_code:"+ board_code);


		String message = likeDao.likeChange(id, board_code);
		int count = likeDao.countLike(board_code);
		jsonResponse.put("count",count);

		// JSON 응답으로 반환
		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());
	}


}

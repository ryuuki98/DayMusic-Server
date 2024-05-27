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
		LikeDao likeDao = LikeDao.getInstance();

		// 요청 본문에서 JSON 데이터 읽어오기
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		String jsonString = stringBuilder.toString();

		// JSON 파싱
		JSONObject jsonObject = new JSONObject(jsonString);
		String id = jsonObject.getString("id");
		int board_code = jsonObject.getInt("board_code");
		String message = likeDao.likeChange(id, board_code);
		int likeCount = likeDao.countLike(board_code);

		// 응답 데이터 설정
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("message", message);
		jsonResponse.put("likeCount", likeCount);

		// 응답 전송
		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());
	}


}

package like.controller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.model.LikeDao;
import like.model.LikeResponseDto;
import org.json.JSONObject;

public class LikeListAction implements LikeAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		LikeDao likeDao = LikeDao.getInstance();

		JSONObject jsonObject = (JSONObject)request.getAttribute("jsonRequest");

		int board_code = jsonObject.getInt("board_code");

		List<LikeResponseDto> list = null;
		
		list = likeDao.printAllLikeList(board_code);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("list",list);

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());
	}

}

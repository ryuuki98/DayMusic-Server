package like.controller.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import like.model.LikeDao;
import like.model.LikeResponseDto;
import org.json.JSONObject;

public class LikeListAction implements LikeAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		LikeDao likeDao = LikeDao.getInstance();

		int board_code = Integer.parseInt(request.getParameter("board_code"));
		List<LikeResponseDto> list = null;

		list = likeDao.printAllLikeList(board_code);
		if(list == null || list.size() == 0){
			System.out.println("좋아요리스트가 비어있다");
		}else{
			System.out.println("좋아요리스트가 채워져있다");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}

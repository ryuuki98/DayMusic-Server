package like.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.model.LikeDao;
import like.model.LikeResponseDto;

public class LikeListAction implements LikeAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("실행됐다.");
		
		LikeDao likeDao = LikeDao.getInstance();
		
		int boardCode = Integer.parseInt(request.getParameter("board_code"));
		String id = request.getParameter("id");
		
		List<LikeResponseDto> list = null;
		
		list = likeDao.printAllLikeList(boardCode);
		
		request.setAttribute("likeList", list);
		
	}

}

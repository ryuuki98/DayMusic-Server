package like.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;

public class LikeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		LikeDao likeDao = LikeDao.getInstance();
		
		int boardCode = Integer.parseInt(request.getParameter("board_code"));
		String id = request.getParameter("id");
		
		List<LikeResponseDto> list = null;
		
		list = likeDao.printAllLikeList(boardCode);
		
		request.setAttribute("likeList", list);
		request.getRequestDispatcher("/like").forward(request, response);
		System.out.println("실행됐다.");
	}

}

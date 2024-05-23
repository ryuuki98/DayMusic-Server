package board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.BoardAction;
import board.module.BoardDao;

public class UpdateBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
//		HttpSession session = request.getSession();
		BoardDao boardDao = BoardDao.getInstance();
//
//		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		
//		if(boardDao.findBoardByCode())
		
		
	}

}

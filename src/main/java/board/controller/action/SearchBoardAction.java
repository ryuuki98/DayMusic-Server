package board.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;

public class SearchBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int publics = 0;
		
		BoardDao boardDao = BoardDao.getInstance();
		
		List<BoardResponseDto> boardList = boardDao.findBoardList(publics);
		
		for(int i = 0; i < boardList.size(); i++) {
			System.out.println(boardList);
		}
		
	}

}

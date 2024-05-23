package board.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardRequestDto;

public class DeleteBoardAction extends HttpServlet implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		String id = request.getParameter("userId");
//		String contents = request.getParameter("contents");
		int boardCode = Integer.parseInt(request.getParameter("boardCode"));
		
		System.out.println("boardCode : " + boardCode);
		
		boolean isValid = true;
		
		
		
		BoardDao boardDao = BoardDao.getInstance();
		
		
		if(boardDao.findBoardByCode(boardCode) == null )
			isValid = false;
		
		System.out.println(isValid);
		
		
		if(isValid) {
			BoardRequestDto boardDto = new BoardRequestDto();
			
			boardDto.setBoardCode(boardCode);
			
			boolean result = boardDao.deleteBoard(boardDto);
			
			if(result)
				System.out.println("게시글 삭제 완료");
			else
				System.out.println("게시글 삭제 실패");
				
		}
	}

}

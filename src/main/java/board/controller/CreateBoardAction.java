package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.module.BoardDao;
import board.module.BoardRequestDto;
import board.module.BoardResponseDto;

/**
 * Servlet implementation class CreateBoardAction
 */
public class CreateBoardAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String contents = request.getParameter("contents");
		String isPublic = request.getParameter("isPublic");
		// 음악코드 받아서 파싱해서 같이 사용
		System.out.println("public : " + isPublic);
		int publics = Integer.parseInt(isPublic);
		
		boolean isValid = true;
		
		if(contents == null || contents.equals(""))
			isValid = false;
		
		System.out.println(isValid);
		
		if(isValid) {
//			HttpSession session = request.getSession();
//			UserResponseDto user = (UserResponseDto) session.getAttribute("user");
			String userId = "user2";
			
			System.out.println("user : " + userId);
			System.out.println("contents : " + contents);
			System.out.println("public : " + publics);
			
			BoardRequestDto boardDto = new BoardRequestDto(userId, contents, publics);
			
			BoardDao boardDao = BoardDao.getInstance();
			BoardResponseDto board = boardDao.createBoard(boardDto);
		}
	}
 
}

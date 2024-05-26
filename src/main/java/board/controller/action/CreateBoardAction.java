package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardRequestDto;
import board.module.BoardResponseDto;
import user.model.UserResponseDto;

public class CreateBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 
		 JSONObject jsonObject = (JSONObject) request.getAttribute("data");
		 int publics = -1;
		 String contents = jsonObject.getString("contents");
		 System.out.println("contents : " + contents);
		 boolean isPublic = jsonObject.getBoolean("isPublic");
		 System.out.println("public : " + isPublic);
		 if(isPublic) {
			 publics = 0;
		 } else 
			 publics = 1;
		 
		 
//	        System.out.println("public : " + isPublic);
		 

	        boolean isValid = true;

	        if (contents == null || contents.equals(""))
	            isValid = false;

	        System.out.println(isValid);

	        response.setContentType("text/plain; charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        if (isValid) {
	             HttpSession session = request.getSession();
	             String user = (String) session.getAttribute("userId");
	        	 String nickname = (String)session.getAttribute("nickname");

	            System.out.println("user : " + user);
	            System.out.println("contents : " + contents);
	            System.out.println("public : " + publics);

	            BoardRequestDto boardDto = new BoardRequestDto(user, contents, publics);

	            BoardDao boardDao = BoardDao.getInstance();
	            BoardResponseDto board = boardDao.createBoard(boardDto);
	            System.out.println("게시글 작성 완료");
	            out.print("게시글 작성 완료");
	        } else {
	            System.out.println("게시글 작성 실패");
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
	            out.print("게시글 작성 실패");
	        }

	        out.flush();
	        out.close();
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		
//		String contents = request.getParameter("contents");
//		String isPublic = request.getParameter("isPublic");
//		// 음악코드 받아서 파싱해서 같이 사용
//		System.out.println("public : " + isPublic);
//		int publics = Integer.parseInt(isPublic);
//		
//		boolean isValid = true;
//		
//		if(contents == null || contents.equals(""))
//			isValid = false;
//		
//		System.out.println(isValid);
//		
//		if(isValid) {
////			HttpSession session = request.getSession();
////			UserResponseDto user = (UserResponseDto) session.getAttribute("user");
//			String userId = "user2";
//			
//			System.out.println("user : " + userId);
//			System.out.println("contents : " + contents);
//			System.out.println("public : " + publics);
//			
//			BoardRequestDto boardDto = new BoardRequestDto(userId, contents, publics);
//			
//			BoardDao boardDao = BoardDao.getInstance();
//			BoardResponseDto board = boardDao.createBoard(boardDto);
//		}
//	}

}

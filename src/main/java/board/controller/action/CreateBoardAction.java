package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardRequestDto;
import board.module.BoardResponseDto;

public class CreateBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		JSONObject jsonObject = (JSONObject) request.getAttribute("data");
		int publics = -1;
		String contents = jsonObject.getString("contents");
		
		boolean isPublic = jsonObject.getBoolean("isPublic");
		
		if (isPublic) {
			publics = 0;
		} else
			publics = 1;

		String userId = jsonObject.getString("id");
		
		String nickname = jsonObject.getString("nickname");
		
		String musicTrack = jsonObject.getString("musicTrack");
		String musicArtist = jsonObject.getString("musicArtist");
		String musicPreviewUrl = jsonObject.getString("musicPreviewUrl");
		String musicThumbnailUrl = jsonObject.getString("musicThumbnail");
		String musicUrl = jsonObject.getString("musicUrl");

		boolean isValid = true;

		if (contents == null || contents.equals(""))
			isValid = false;

		

		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (isValid) {
			BoardRequestDto boardDto = new BoardRequestDto(userId, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnailUrl, musicUrl,  publics);

			BoardDao boardDao = BoardDao.getInstance();
			BoardResponseDto board = boardDao.createBoard(boardDto);
			if(board != null) {
				response.setStatus(HttpServletResponse.SC_OK);
				

				
				jsonObject.put("boardCode", board.getBoardCode());
				out.print(jsonObject.toString());
			}
			else {
				
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
				out.print("게시글 작성 실패");
			}
		} else {
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
			out.print("게시글 작성 실패");
		}

		out.flush();
		out.close();
	}
}
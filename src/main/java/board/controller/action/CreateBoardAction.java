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
		System.out.println("contents : " + contents);
		boolean isPublic = jsonObject.getBoolean("isPublic");
		System.out.println("public : " + isPublic);
		if (isPublic) {
			publics = 0;
		} else {
			publics = 1;
		}

		String userId = jsonObject.getString("id");
		System.out.println("userId : " + userId);
		String nickname = jsonObject.getString("nickname");
		System.out.println("nickname : " + nickname);
		String musicTrack = jsonObject.getString("musicTrack");
		String musicArtist = jsonObject.getString("musicArtist");
		String musicPreviewUrl = jsonObject.getString("musicPreviewUrl");
		String musicThumbnailUrl = jsonObject.getString("musicThumbnail");

		boolean isValid = true;

		if (contents == null || contents.equals("")) {
			isValid = false;
		}

		System.out.println(isValid);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (isValid) {
			BoardRequestDto boardDto = new BoardRequestDto(userId, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnailUrl, publics);

			BoardDao boardDao = BoardDao.getInstance();
			BoardResponseDto board = boardDao.createBoard(boardDto);
			response.setStatus(HttpServletResponse.SC_OK);

			JSONObject responseJson = new JSONObject();
			responseJson.put("status", "success");
			responseJson.put("message", "게시글 작성 완료");
			responseJson.put("boardCode", board.getBoardCode());

			System.out.println("게시글 작성 완료");
			out.print(responseJson.toString());
		} else {
			System.out.println("게시글 작성 실패");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
			JSONObject responseJson = new JSONObject();
			responseJson.put("status", "fail");
			responseJson.put("message", "게시글 작성 실패");

			out.print(responseJson.toString());
		}

		out.flush();
		out.close();
	}
}

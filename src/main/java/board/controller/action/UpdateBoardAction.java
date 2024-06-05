package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardRequestDto;
import board.module.BoardResponseDto;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class UpdateBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
                JSONObject jsonObject = (JSONObject) request.getAttribute("data");

                System.out.println(jsonObject.toString());

                String id = jsonObject.getString("id");
                String nickname = jsonObject.getString("nickname");
                String contents = jsonObject.getString("contents");
                String musicTrack = jsonObject.getString("musicTrack");
                String musicArtist = jsonObject.getString("musicArtist");
                String musicPreviewUrl = jsonObject.getString("musicPreviewUrl");
                String musicThumbnailUrl = jsonObject.getString("musicThumbnailUrl");
                String musicUrl = jsonObject.getString("musicUrl");
                int boardCode = jsonObject.getInt("board_code");

                BoardRequestDto boardRequestDto = new BoardRequestDto();
                boardRequestDto.setId(id);
                boardRequestDto.setNickname(nickname);
                boardRequestDto.setContents(contents);
                boardRequestDto.setMusicTrack(musicTrack);
                boardRequestDto.setMusicArtist(musicArtist);
                boardRequestDto.setMusicPreviewUrl(musicPreviewUrl);
                boardRequestDto.setMusicThumbnail(musicThumbnailUrl);
                boardRequestDto.setMusicUrl(musicUrl);
                boardRequestDto.setBoardCode(boardCode);


                BoardDao boardDao = BoardDao.getInstance();
                BoardResponseDto board = boardDao.updateBoardContents(boardRequestDto);

                if (board == null) {
                        System.out.println("업데이트 실패");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                        System.out.println("업데이트 성공");
                        response.setStatus(HttpServletResponse.SC_OK);
                }
        } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"message\":\"서버 오류 발생\"}");
        }


	}

}

package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
        BoardDao boardDao = BoardDao.getInstance();

        JSONObject jsonObject = (JSONObject) request.getAttribute("data");

        System.out.println(jsonObject.toString());

        String id = jsonObject.getString("id");
        String contents = jsonObject.getString("contents");
        int boardCode = jsonObject.getInt("board_code");


        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setId(id);
        boardRequestDto.setContents(contents);
        boardRequestDto.setBoardCode(boardCode);


        BoardResponseDto board = boardDao.updateBoardContents(boardRequestDto);

        if (board == null) {
            System.out.println("업데이트 실패");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            System.out.println("업데이트 성공");
            response.setStatus(HttpServletResponse.SC_OK);

        }


	}

}

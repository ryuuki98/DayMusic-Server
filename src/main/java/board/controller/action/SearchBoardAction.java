package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        int publics = 0;

        BoardDao boardDao = BoardDao.getInstance();
        List<BoardResponseDto> boardList = boardDao.findBoardList(publics);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);

        JSONArray boardArray = new JSONArray();
        for (BoardResponseDto board : boardList) {
            JSONObject boardJson = new JSONObject();
            boardJson.put("id", board.getId());
            boardJson.put("contents", board.getContents());
            boardJson.put("board_code", board.getBoardCode());
            boardJson.put("music_code", board.getMusicCode());
            boardJson.put("is_public", board.isPublic());
            boardArray.put(boardJson);
        }

        jsonResponse.put("boardList", boardArray);

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
        out.close();
    }
}

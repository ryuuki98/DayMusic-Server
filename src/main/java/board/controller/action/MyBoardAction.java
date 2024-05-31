package board.controller.action;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class MyBoardAction extends HttpServlet implements BoardAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        System.out.println("Request attributes: " + request.getAttributeNames());
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");
        String userId = jsonObject.getString("id");
        String nickname = jsonObject.getString("nickname");

        System.out.println("userId : " + userId);
        System.out.println("nickname : " + nickname);
        BoardDao boardDao = BoardDao.getInstance();
        List<BoardResponseDto> boardList = boardDao.findBoardMyID(userId);

        System.out.println("boardList : " + boardList);
//        for(int i =0; i < boardList.size(); i++){
//            BoardResponseDto board = boardList.get(i);
//            System.out.println("board : " + board);
//        }

        System.out.println("Total boards fetched: " + boardList.size());

//        List<BoardResponseDto> filteredBoardList = boardList.stream()
//                .filter(board -> board.getId().equals(userId))
//                .collect(Collectors.toList());

//        System.out.println("Total public boards: " + filteredBoardList.size());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);

        JSONArray boardArray = new JSONArray();
        for (BoardResponseDto board : boardList) {
            JSONObject boardJson = new JSONObject();
            boardJson.put("id", board.getId());
            boardJson.put("contents", board.getContents());
            boardJson.put("board_code", board.getBoardCode());
            boardJson.put("music_track", board.getMusicTrack());
            boardJson.put("music_album", board.getMusicAlbum());
            boardJson.put("music_artist", board.getMusicArtist());
            boardJson.put("is_public", board.isPublic());
            boardArray.put(boardJson);
        }

        jsonResponse.put("boardList", boardArray);
        response.setStatus(HttpServletResponse.SC_OK);
        System.out.println("JSON Response: " + jsonResponse.toString());

        PrintWriter out = response.getWriter();

        out.print(jsonResponse.toString());
        out.flush();
    }
}

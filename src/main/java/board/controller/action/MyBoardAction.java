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

public class MyBoardAction  extends HttpServlet implements BoardAction  {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");
        String userId = jsonObject.getString("id");

        

        BoardDao boardDao = BoardDao.getInstance();
        List<BoardResponseDto> boardList = boardDao.findBoardMyID(userId);

        
//        for(int i =0; i < boardList.size(); i++){
//            BoardResponseDto board = boardList.get(i);
//            
//        }

//        

//        List<BoardResponseDto> filteredBoardList = boardList.stream()
//                .filter(board -> board.getId().equals(userId))
//                .collect(Collectors.toList());
////
//        

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);

        JSONArray boardArray = new JSONArray();
        for (BoardResponseDto board : boardList) {
            JSONObject boardJson = new JSONObject();
            boardJson.put("id", board.getId());
            boardJson.put("contents", board.getContents());
            boardJson.put("board_code", board.getBoardCode());
            boardJson.put("music_track", board.getMusicTrack());
            boardJson.put("music_artist", board.getMusicArtist());
            boardJson.put("music_preview_url", board.getMusicPreviewUrl());
            boardJson.put("music_thumbnail", board.getMusicThumbnail());
            boardJson.put("music_Url", board.getMusicUrl());
            boardJson.put("is_public", board.isPublic());
            boardJson.put("createdAt", board.getModDate());
            boardArray.put(boardJson);
        }

        jsonResponse.put("boardList", boardArray);
        response.setStatus(HttpServletResponse.SC_OK);
        

        PrintWriter out = response.getWriter();

        out.print(jsonResponse.toString());
        out.flush();
    }
}

package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import image.model.ImageDao;
import org.json.JSONObject;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;

public class DetailBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Parse the JSON request body to extract the board_code
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");
        int boardCode = jsonObject.getInt("board_code");
        ImageDao imageDao = ImageDao.getInstance();

        String imgPath = imageDao.getProfileImgUrl(boardCode);
        
        

        // Retrieve the board details from the database
        BoardDao boardDao = BoardDao.getInstance();
        BoardResponseDto board = boardDao.findBoardByCode(boardCode);

//        
//        
//        
        

        // Prepare the JSON response
        JSONObject jsonResponse = new JSONObject();
        if (board != null) {
            jsonResponse.put("board_code", board.getBoardCode());
            jsonResponse.put("id", board.getId());
            jsonResponse.put("imgPath", imgPath);
            jsonResponse.put("contents", board.getContents());
            jsonResponse.put("music_track", board.getMusicTrack());
            jsonResponse.put("music_artist", board.getMusicArtist());
            jsonResponse.put("music_PreviewUrl", board.getMusicPreviewUrl());
            jsonResponse.put("music_Thumbnail", board.getMusicThumbnail());
            jsonResponse.put("music_Url", board.getMusicUrl());
            jsonResponse.put("createdAt", board.getModDate());
            jsonResponse.put("is_public", board.isPublic());
            jsonResponse.put("nickname", board.getNickname());
            jsonResponse.put("status", 200);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            jsonResponse.put("status", 404);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("message", "Post not found");
        }

        // Write the JSON response to the output
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
        out.close();
    }
}

package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;
import image.model.ImageDao;
import like.model.LikeDao;
import org.json.JSONArray;
import org.json.JSONObject;

public class FollowBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("팔로우 리스트업 하는중");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        LikeDao likeDao = LikeDao.getInstance();
        BoardDao boardDao = BoardDao.getInstance();
        ImageDao imageDao = ImageDao.getInstance();


        JSONObject jsonObject = (JSONObject) request.getAttribute("data");

        String userId = jsonObject.getString("userId");
        System.out.println("id : " + userId);
        List<BoardResponseDto> boardList = boardDao.followBoardList(userId);

        System.out.println("Follow boards fetched[36]: " + boardList.size());

        Map<String, String> profileImages = imageDao.getAllProfileImages();
        Map<Integer, String> boardImages = imageDao.getAllBoardImage();

        response.setStatus(HttpServletResponse.SC_OK);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);

        JSONArray boardArray = new JSONArray();
        for (BoardResponseDto board : boardList) {
            JSONObject boardJson = new JSONObject();
            int likeCount = likeDao.countLike(board.getBoardCode());
            boardJson.put("id", board.getId());
            boardJson.put("profileImg", profileImages.get(board.getId())); // 프로필 이미지 URL을 맵에서 가져옴
            boardJson.put("image_url", boardImages.get(board.getBoardCode())); // 게시물 이미지 URL을 맵에서 가져옴
            boardJson.put("contents", board.getContents());
            boardJson.put("board_code", board.getBoardCode());
            boardJson.put("music_track", board.getMusicTrack());
            boardJson.put("music_artist", board.getMusicArtist());
            boardJson.put("music_preview_url", board.getMusicPreviewUrl());
            boardJson.put("music_thumbnail", board.getMusicThumbnail());
            boardJson.put("music_Url", board.getMusicUrl());
            boardJson.put("is_public", board.isPublic());
            boardJson.put("nickname", board.getNickname());
            boardJson.put("likeCount", likeCount);
            boardJson.put("createdAt", board.getModDate());
            boardArray.put(boardJson);
        }

        jsonResponse.put("boardList", boardArray);
        System.out.println("JSON Response: " + jsonResponse.toString());

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}

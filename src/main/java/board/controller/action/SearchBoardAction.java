package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardResponseDto;
import comment.module.CommentDao;
import image.model.ImageDao;
import like.model.LikeDao;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        LikeDao likeDao = LikeDao.getInstance();
        BoardDao boardDao = BoardDao.getInstance();
        ImageDao imageDao = ImageDao.getInstance();
        CommentDao commentDao = CommentDao.getInstance();

        List<BoardResponseDto> boardList = boardDao.findBoardList();

        

        List<BoardResponseDto> filteredBoardList = boardList.stream()
                .filter(board -> board.isPublic() == 0)
                .collect(Collectors.toList());

        

        Map<String, String> profileImages = imageDao.getAllProfileImages();
        Map<Integer, String> boardImages = imageDao.getAllBoardImage();



        response.setStatus(HttpServletResponse.SC_OK);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);

        JSONArray boardArray = new JSONArray();
        for (BoardResponseDto board : filteredBoardList) {
            JSONObject boardJson = new JSONObject();
            int likeCount = likeDao.countLike(board.getBoardCode());
            int commentCount = commentDao.countComment(board.getBoardCode());
            boardJson.put("id", board.getId());
            boardJson.put("profileImg", profileImages.get(board.getId())); // 프로필 이미지 URL을 맵에서 가져옴
            boardJson.put("image_url", boardImages.get(board.getBoardCode())); // 프로필 이미지 URL을 맵에서 가져옴
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
            boardJson.put("commentCount", commentCount);
            boardArray.put(boardJson);
        }

        jsonResponse.put("boardList", boardArray);
        

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}

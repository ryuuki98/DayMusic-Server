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

public class UpdateBoardAction extends HttpServlet implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        int boardCode = Integer.parseInt(request.getParameter("boardCode"));
        String id = request.getParameter("id");
        String newContents = request.getParameter("contents");
        PrintWriter out = response.getWriter();
        
        BoardDao boardDao = BoardDao.getInstance();
        BoardResponseDto boardResponse = boardDao.findBoardByCode(boardCode);

        JSONObject jsonResponse = new JSONObject();
        JSONObject meta = new JSONObject();
        JSONArray boardArray = new JSONArray();

        if (boardResponse != null) {
            String currentContents = boardResponse.getContents();
            if (!newContents.equals("") && !newContents.equals(currentContents)) {
                BoardRequestDto boardDto = new BoardRequestDto();
                boardDto.setBoardCode(boardCode);
                boardDto.setId(id);
                boardDto.setContents(newContents);

                boardResponse = boardDao.updateBoardContents(boardDto);
                meta.put("is_correct", true);

                JSONObject boardJson = new JSONObject();
                boardJson.put("contents", boardResponse.getContents());
                boardJson.put("board_code", boardResponse.getBoardCode());
                boardJson.put("music_code", boardResponse.getMusicCode());
                boardJson.put("is_public", boardResponse.isPublic());
                boardArray.put(boardJson);

                System.out.println("게시글 수정 완료");
            } else {
                meta.put("is_correct", false);
                System.out.println("게시글 수정 실패");
            }
        } else {
            meta.put("is_correct", false);
            System.out.println("게시글 수정 실패: 게시물을 찾을 수 없음");
        }

        jsonResponse.put("meta", meta);
        jsonResponse.put("board", boardArray);

        out.print(jsonResponse.toString());
        out.flush();
        out.close();
		
		
	}

}

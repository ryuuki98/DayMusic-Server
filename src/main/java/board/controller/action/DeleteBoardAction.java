package board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardAction;
import board.module.BoardDao;
import board.module.BoardRequestDto;
import org.json.JSONObject;

@WebServlet("/deleteBoard")
public class DeleteBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        int boardCode = Integer.parseInt(request.getParameter("boardCode"));
        System.out.println("boardCode : " + boardCode);

        BoardDao boardDao = BoardDao.getInstance();
        boolean isValid = boardDao.findBoardByCode(boardCode) != null;

        System.out.println(isValid);

        JSONObject jsonResponse = new JSONObject();
        PrintWriter out = response.getWriter();

        if (isValid) {
            BoardRequestDto boardDto = new BoardRequestDto();
            boardDto.setBoardCode(boardCode);

            boolean result = boardDao.deleteBoard(boardDto);

            if (result) {
                System.out.println("게시글 삭제 완료");
                jsonResponse.put("status", 200);
                jsonResponse.put("message", "Delete success.");
            } else {
                System.out.println("게시글 삭제 실패");
                jsonResponse.put("status", 404);
                jsonResponse.put("message", "Delete failed.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 상태 코드 설정
            }
        } else {
            System.out.println("게시글 삭제 실패: 게시물을 찾을 수 없음");
            jsonResponse.put("status", 404);
            jsonResponse.put("message", "Board not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 상태 코드 설정
        }

        out.print(jsonResponse.toString());
        out.flush();
        out.close();
    }
}

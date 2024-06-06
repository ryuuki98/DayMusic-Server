package comment.controller.action;

import comment.controller.CommentAction;
import comment.module.Comment;
import comment.module.CommentDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommentAddAction extends HttpServlet implements CommentAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // JSON 객체 가져오기
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");

        String userId = jsonObject.getString("id");
        String contents = jsonObject.getString("contents");
        int boardCode = jsonObject.getInt("boardCode");
        int parent = jsonObject.has("parent") ? jsonObject.getInt("parent") : 0;

        boolean isValid = contents != null && !contents.isEmpty();

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (isValid) {
            Comment comment = new Comment(userId, boardCode, contents, parent);

            CommentDao commentDao = CommentDao.getInstance();
            Comment isSuccess = commentDao.addComment(comment);

            if (isSuccess != null) {
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("message", "댓글 작성 완료");
                jsonResponse.put("comment", new JSONObject(isSuccess));
                response.setStatus(HttpServletResponse.SC_OK);
                
                out.print("댓글 작성 완료");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("message", "댓글 작성 실패");
                
                out.print("댓글 작성 실패");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
            
            out.print("유효하지 않은 입력");
        }

        out.flush();
        out.close();
    }
}

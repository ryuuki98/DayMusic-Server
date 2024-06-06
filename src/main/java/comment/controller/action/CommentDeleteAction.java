package comment.controller.action;

import comment.controller.CommentAction;
import comment.module.CommentDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommentDeleteAction extends HttpServlet implements CommentAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // JSON 객체 가져오기
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");

        int cmtCode = jsonObject.getInt("cmtCode");

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        CommentDao commentDao = CommentDao.getInstance();
        boolean isSuccess = commentDao.deleteComment(cmtCode);

        if (isSuccess) {
            response.setStatus(HttpServletResponse.SC_OK);
            
            out.print("댓글 삭제 완료");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
            out.print("댓글 삭제 실패");
        }

        out.flush();
        out.close();
    }
}

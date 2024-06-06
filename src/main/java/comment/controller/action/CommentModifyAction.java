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

public class CommentModifyAction extends HttpServlet implements CommentAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // JSON 객체 가져오기
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");

        int cmtCode = jsonObject.getInt("cmtCode");
        String newContents = jsonObject.getString("contents");
        
        

        boolean isValid = newContents != null && !newContents.isEmpty();

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (isValid) {
            CommentDao commentDao = CommentDao.getInstance();
            Comment isSuccess = commentDao.updateComment(cmtCode, newContents);

            if (isSuccess != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                
                out.print("댓글 수정 완료");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                
                out.print("댓글 수정 실패");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
            
            out.print("유효하지 않은 입력");
        }

        out.flush();
        out.close();
    }
}

package comment.controller.action;

import comment.controller.CommentAction;
import comment.module.Comment;
import comment.module.CommentDao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class GetCommentListAction extends HttpServlet implements CommentAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        JSONObject jsonObjects = (JSONObject) request.getAttribute("data");
        int boardCode = jsonObjects.getInt("boardCode");
        

        CommentDao commentDao = CommentDao.getInstance();
        List<Comment> comments = commentDao.getCommentsByBoardCode(boardCode);
        int count = commentDao.countComment(boardCode);

        Map<String, String> commentNickname = commentDao.getAllCommentNickname();

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        JSONArray jsonArray = new JSONArray();
        for (Comment comment : comments) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", comment.getId());
            jsonObject.put("cmtCode", comment.getCmtCode());
            jsonObject.put("nickname", commentNickname.get(comment.getId()));
            jsonObject.put("boardCode", comment.getBoardCode());
            jsonObject.put("contents", comment.getContents());
            jsonObject.put("parent", comment.getParent());
            jsonObject.put("regDate", comment.getRegDate().toString());
            jsonObject.put("modDate", comment.getModDate().toString());
            jsonObject.put("count", count);

            

            if (comment.getParent() == 0) {
                List<Comment> replies = commentDao.getRepliesByParentCode(comment.getCmtCode());
                JSONArray replyArray = new JSONArray();
                for (Comment reply : replies) {
                    JSONObject replyObject = new JSONObject();
                    replyObject.put("id", reply.getId());
                    replyObject.put("cmtCode", reply.getCmtCode());
                    replyObject.put("boardCode", reply.getBoardCode());
                    replyObject.put("contents", reply.getContents());
                    replyObject.put("parent", reply.getParent());
                    replyObject.put("regDate", reply.getRegDate().toString());
                    replyObject.put("modDate", reply.getModDate().toString());
                    replyArray.put(replyObject);
                }

                jsonObject.put("replies", replyArray);
                jsonObject.put("count", replies.size());
            }

            jsonArray.put(jsonObject);
        }

        out.print(jsonArray.toString());
        out.flush();
        out.close();
    }
}

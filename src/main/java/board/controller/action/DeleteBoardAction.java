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

public class DeleteBoardAction extends HttpServlet implements BoardAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = request.getReader().readLine()) != null) {
//            sb.append(line);
//        }
//        
//        String jsonString = sb.toString();
        JSONObject jsonObject = (JSONObject) request.getAttribute("data");
//        JSONObject jsonObject = new JSONObject(jsonString);

        String userId = jsonObject.getString("id");
        
        int boardCode = jsonObject.getInt("board_code");
        

        // Delete the board from the database
        BoardDao boardDao = BoardDao.getInstance();
        boolean success = boardDao.deleteBoard(userId, boardCode);
        
        // Prepare the JSON response
        JSONObject jsonResponse = new JSONObject();
        if (success) {
            jsonResponse.put("status", 200);
            response.setStatus(HttpServletResponse.SC_OK);
            jsonResponse.put("message", "Post deleted successfully");
        } else {
            jsonResponse.put("status", 400);
            jsonResponse.put("message", "Failed to delete post");
        }

        // Write the JSON response to the output
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
        out.close();
    }
}

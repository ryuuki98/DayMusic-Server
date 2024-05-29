package user.controller.action;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserInfo implements UserAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        UserDao userDao = UserDao.getInstance();

        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");

        String id = jsonObject.getString("id");
        System.out.println("[22] GetUserInfo Id : " + id);

        UserResponseDto user = userDao.findUserById(id);

        if (user == null){
            System.out.println("해당 유저를 찾을수 없음 ");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            response.setStatus(HttpServletResponse.SC_OK);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("name", user.getName());
            jsonResponse.put("gender", user.getGender());
            jsonResponse.put("email", user.getEmail());
            jsonResponse.put("phone", user.getPhone());
            jsonResponse.put("telecom", user.getTelecom());
            response.getWriter().write(jsonResponse.toString());


        }
    }
}

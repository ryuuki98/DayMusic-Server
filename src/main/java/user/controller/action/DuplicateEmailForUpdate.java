package user.controller.action;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DuplicateEmailForUpdate implements UserAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();
        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
        String id = jsonObject.getString("id");
        String email = jsonObject.getString("email");

        boolean isDuplicated = userDao.duplicateEmail(id,email);

        if (!isDuplicated) {
            System.out.println("이메일 사용 가능 ");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"false\"}");


        }else {
            System.out.println("이메일 사용 불가 ");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"true\"}");
        }

    }
}

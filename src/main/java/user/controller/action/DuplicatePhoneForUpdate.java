package user.controller.action;

import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DuplicatePhoneForUpdate implements UserAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();
        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
        String id = jsonObject.getString("id");
        String phone = jsonObject.getString("phone");

        boolean isDuplicated = userDao.duplicatePhone(id,phone);

        if (!isDuplicated) {
            

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"false\"}");


        }else {
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"exists\":\"true\"}");
        }
    }
}

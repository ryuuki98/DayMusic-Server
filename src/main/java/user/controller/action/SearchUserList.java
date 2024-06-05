package user.controller.action;

import org.json.JSONArray;
import org.json.JSONObject;
import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchUserList implements UserAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("유저검색 시작 ");



        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
        String nickname = jsonObject.getString("nickname");
        System.out.println("nickname" + nickname);


        UserDao userDao = UserDao.getInstance();
        List<UserResponseDto> userList = userDao.searchUsersByNickname(nickname);

        JSONArray jsonArray = new JSONArray();
        for (UserResponseDto user : userList) {
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.getId());
            userJson.put("nickname", user.getNickname());
            jsonArray.put(userJson);
        }

        JSONObject result = new JSONObject();
        result.put("results", jsonArray);

        response.getWriter().write(result.toString());
    }
}

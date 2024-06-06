package user.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
public class LoginAction implements UserAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        
        UserDao userDao = UserDao.getInstance();
       
        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonRequest");
        
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");

        // 유저 정보 설정
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(id);
        userRequestDto.setPassword(password);

        // 유저 로그인 검증
        UserResponseDto user = userDao.validateUser(userRequestDto);

        if (user == null) {
            
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"Invalid ID or password\"}");
        } else {
            

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("id", user.getId());
            jsonResponse.put("nickname", user.getNickname());

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonResponse.toString());

        }
    }
}

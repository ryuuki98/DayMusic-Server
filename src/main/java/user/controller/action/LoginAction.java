package user.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class LoginAction implements UserAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("로그인 처리 로직!");
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
            System.out.println("아이디 혹은 비밀번호 불일치");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"Invalid ID or password\"}");
        } else {
            System.out.println("로그인 성공");
            // 로그인 성공 처리 (예: 세션 설정 등)
            
            // 세션에 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("nickname", user.getNickname());
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Login successful\"}");
        }
    }
}

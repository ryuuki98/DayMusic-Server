package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;

public class DeleteUserAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원 삭제 로직");
		
		UserDao userDao = UserDao.getInstance();
		
		String id = request.getParameter("id");
		
		boolean isDeleted = userDao.deleteUserById(id);
		
		if (isDeleted) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		
	}

}

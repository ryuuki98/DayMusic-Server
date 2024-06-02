package rank.controller.action;

import rank.controller.RankAction;
import rank.model.RankDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RankListAction implements RankAction{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        RankDao rankDao = RankDao.getInstance();



    }
}

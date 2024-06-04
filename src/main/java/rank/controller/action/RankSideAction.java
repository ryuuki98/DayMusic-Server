package rank.controller.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import rank.controller.RankAction;
import rank.model.RankDao;
import rank.model.RankResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RankSideAction implements RankAction{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        RankDao rankDao = RankDao.getInstance();

        List<RankResponseDto> list = null;
        list = rankDao.sideList();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(list);

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }
}

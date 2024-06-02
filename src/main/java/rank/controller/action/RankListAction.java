package rank.controller.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import rank.controller.RankAction;
import rank.model.RankDao;
import rank.model.RankResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RankListAction implements RankAction{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        RankDao rankDao = RankDao.getInstance();

        List<RankResponseDto> list = null;

        list = rankDao.rankList();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

    }
}

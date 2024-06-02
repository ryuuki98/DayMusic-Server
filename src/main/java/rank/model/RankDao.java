package rank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RankDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private RankDao(){

    }

    private static RankDao instance =new RankDao();

    public static RankDao getInstance(){
        return instance;
    }

    public List<RankResponseDto> rankList (){
        List<RankResponseDto> list = new ArrayList<RankResponseDto>();

        String sql = "select * from rank";

        return list;
    }
}

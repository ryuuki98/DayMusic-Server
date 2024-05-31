package image.model;

import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private ImageDao() {
    }

    private static ImageDao instance = new ImageDao();

    public static ImageDao getInstance() {
        return instance;
    }

    public boolean updateProfile(String userId, String imageUrl) {
        boolean update = false;

        conn = DBManager.getConnection();
        String sql = "UPDATE users SET profile_img_url = ? WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, imageUrl);
            pstmt.setString(2, userId);
            update = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("duplicateEmail method 오류");
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return update;

    }


}

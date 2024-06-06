package image.model;

import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
            
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return update;

    }

    public String getProfileImgUrl(int boardCode) {
        String imgUrl = null;
        conn = DBManager.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT image_path FROM image WHERE board_code = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                imgUrl = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return imgUrl;
    }



    // ImageDao.java
    public Map<String, String> getAllProfileImages() {
        Map<String, String> profileImages = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT id, profile_img_url FROM users";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String userId = rs.getString(1);
                String profileImageUrl = rs.getString(2);
                profileImages.put(userId, profileImageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        

        return profileImages;
    }

    public Map<Integer, String> getAllBoardImage() {
        Map<Integer, String> boardImages = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT board_code,image_path FROM image";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int board_code = Integer.parseInt(rs.getString(1)) ;
                String boardImageUrl = rs.getString(2);
                boardImages.put(board_code, boardImageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        

        return boardImages;
    }


    public boolean saveBoardImage(int boardCode, String imagePath, String imageName, String imageType) {
        String sql = "INSERT INTO image (board_code, image_path, image_name, image_type) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardCode);
            pstmt.setString(2, imagePath);
            pstmt.setString(3, imageName);
            pstmt.setString(4, imageType);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}

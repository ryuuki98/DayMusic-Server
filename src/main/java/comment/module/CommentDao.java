package comment.module;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBManager;

public class CommentDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int count;

    private CommentDao() {
    }

    private static CommentDao instance = new CommentDao();

    public static CommentDao getInstance() {
        return instance;
    }

    // 댓글 추가
    public Comment addComment(Comment comment) {
        String sql = "INSERT INTO comment (board_code, id, contents, parent) VALUES (?, ?, ?, ?)";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, comment.getBoardCode());
            pstmt.setString(2, comment.getId());
            pstmt.setString(3, comment.getContents());
            if (comment.getParent() == 0) {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(4, comment.getParent());
            }

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int cmtCode = rs.getInt(1);
                    Timestamp regDate = new Timestamp(System.currentTimeMillis());
                    return new Comment(comment.getId(), cmtCode, comment.getBoardCode(), comment.getContents(), comment.getParent(), regDate, regDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return null;
    }

    public Map<String, String> getAllCommentNickname() {
        Map<String, String> commentNickname = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT id, nickname FROM users";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String userId = rs.getString(1) ;
                String userNickname = rs.getString(2);
                commentNickname.put(userId, userNickname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        

        return commentNickname;
    }

    // 특정 게시물의 모든 댓글 조회
    public List<Comment> getCommentsByBoardCode(int boardCode) {
        String sql = "SELECT * FROM comment WHERE board_code = ?";
        List<Comment> comments = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                int cmtCode = rs.getInt("cmt_code");
                String contents = rs.getString("contents");
                int parent = rs.getInt("parent");
                Timestamp regDate = rs.getTimestamp("reg_date");
                Timestamp modDate = rs.getTimestamp("mod_date");

                Comment comment = new Comment(id, cmtCode, boardCode, contents, parent, regDate, modDate);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return comments;
    }


    // 특정 댓글의 대댓글 조회
    public List<Comment> getRepliesByParentCode(int parentCode) {
        String sql = "SELECT * FROM comment WHERE parent = ?";
        List<Comment> comments = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, parentCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                int cmtCode = rs.getInt("cmt_code");
                int boardCode = rs.getInt("board_code");
                String contents = rs.getString("contents");
                int parent = rs.getInt("parent");
                Timestamp regDate = rs.getTimestamp("reg_date");
                Timestamp modDate = rs.getTimestamp("mod_date");

                Comment comment = new Comment(id, cmtCode, boardCode, contents, parent, regDate, modDate);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return comments;
    }

    public Integer countComment(int board_code){
        int count = 0;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT COUNT(*) FROM `comment` WHERE board_code=?";
            pstmt = conn.prepareStatement(sql);


            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,board_code);

            rs = pstmt.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt,rs);
        }

        return  count;
    }

    // 댓글 수정
    public Comment updateComment(int cmtCode, String newContent) {
        String sql = "UPDATE comment SET contents = ? WHERE cmt_code = ?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newContent);
            pstmt.setInt(2, cmtCode);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                return getCommentById(cmtCode); // 업데이트된 댓글 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return null;
    }

    // 특정 댓글 조회
    public Comment getCommentById(int cmtCode) {
        String sql = "SELECT * FROM comment WHERE cmt_code = ?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cmtCode);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                int boardCode = rs.getInt("board_code");
                String contents = rs.getString("contents");
                int parent = rs.getInt("parent");
                Timestamp regDate = rs.getTimestamp("reg_date");
                Timestamp modDate = rs.getTimestamp("mod_date");

                return new Comment(id, cmtCode, boardCode, contents, parent, regDate, modDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return null;
    }

    // 댓글 삭제
    public boolean deleteComment(int cmtCode) {
        String deleteRepliesSql = "DELETE FROM comment WHERE parent = ?";
        String deleteCommentSql = "DELETE FROM comment WHERE cmt_code = ?";

        try {
            conn = DBManager.getConnection();
            conn.setAutoCommit(false);

            // 덧글 삭제
            pstmt = conn.prepareStatement(deleteRepliesSql);
            pstmt.setInt(1, cmtCode);
            pstmt.executeUpdate();
            pstmt.close();

            // 원댓글 삭제
            pstmt = conn.prepareStatement(deleteCommentSql);
            pstmt.setInt(1, cmtCode);
            int rowsDeleted = pstmt.executeUpdate();

            conn.commit();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            DBManager.close(conn, pstmt);
        }
        return false;
    }
}

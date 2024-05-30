package comment.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private CommentDao() {}

    private static CommentDao instance = new CommentDao();

    public static CommentDao getInstance() { return instance; }

    // 댓글 리스트

    // 댓글 작성

    // 댓글 수정

    // 댓글 삭제



}

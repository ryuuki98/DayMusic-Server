package rank.model;

import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RankDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private RankDao() {
    }

    private static RankDao instance = new RankDao();

    public static RankDao getInstance() {
        return instance;
    }

    public List<RankResponseDto> rankList() {
        List<RankResponseDto> list = new ArrayList<RankResponseDto>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT COUNT(*) AS count, music_Track, music_Artist, music_Thumbnail, music_PreviewUrl,music_Url " +
                    "FROM board " +
                    "GROUP BY music_Track, music_Artist, music_Thumbnail, music_PreviewUrl, music_Url " +
                    "ORDER BY count DESC";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                if(!rs.getString("music_Track").equals("")) {
                    int count = rs.getInt(1);
                    String musicTrack = rs.getString(2);
                    String musicArtist = rs.getString(3);
                    String musicThumbnail = rs.getString(4);
                    String musicPreviewUrl = rs.getString(5);
                    String musicUrl = rs.getString(6);
                    list.add(new RankResponseDto(count, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl));
                }

            }

            System.out.println("rank 리스트 불러오기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<RankResponseDto> sideList() {
        List<RankResponseDto> list = new ArrayList<RankResponseDto>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT COUNT(*) AS count, music_Track, music_Artist, music_Thumbnail, music_PreviewUrl " +
                    "FROM board " +
                    "GROUP BY music_Track, music_Artist, music_Thumbnail, music_PreviewUrl " +
                    "ORDER BY count DESC LIMIT 6";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                if(!rs.getString("music_Track").equals("")) {
                    int count = rs.getInt(1);
                    String musicTrack = rs.getString(2);
                    String musicArtist = rs.getString(3);
                    String musicThumbnail = rs.getString(4);
                    String musicPreviewUrl = rs.getString(5);
                    list.add(new RankResponseDto(count, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail));
                    System.out.println(count);
                    System.out.println(musicTrack);
                    System.out.println(musicArtist);
                    System.out.println(musicThumbnail);
                    System.out.println(musicPreviewUrl);
                }

            }

            System.out.println("rank 리스트 불러오기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }
}

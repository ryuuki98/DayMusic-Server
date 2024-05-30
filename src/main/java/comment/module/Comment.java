package comment.module;

import java.sql.Timestamp;

public class Comment {

    private String id;
    private int cmtCode;
    private int boardCode;
    private String contents;
    private int parent;
    private Timestamp regDate;
    private Timestamp modDate;

    public Comment(String id, int cmtCode, int boardCode, String contents, int parent,  Timestamp regDate, Timestamp modDate) {
        super();
        this.id = id;
        this.cmtCode = cmtCode;
        this.boardCode = boardCode;
        this.contents = contents;
        this.parent = parent;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public String getId() {
        return id;
    }
    public int getCmtCode() {
        return cmtCode;
    }
    public int getBoardCode() {
        return boardCode;
    }
    public String getContents() {
        return contents;
    }
    public int getParent() {
        return parent;
    }
    public Timestamp getRegDate() {
        return regDate;
    }
    public Timestamp getModDate() {
        return modDate;
    }
}

package comment.module;

import java.sql.Timestamp;

public class CommentResponseDto {

    private String id;
    private int cmtCode;
    private int boardCode;
    private String contents;
    private int parent;
    private Timestamp regDate;
    private Timestamp modDate;

    public CommentResponseDto() {}

    public CommentResponseDto(String id, int cmtCode, int boardCode, String contents, int parent,
                             Timestamp regDate, Timestamp modDate) {
        super();
        this.id = id;
        this.cmtCode = cmtCode;
        this.boardCode = boardCode;
        this.contents = contents;
        this.parent = parent;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getCmtCode() { return cmtCode; }
    public void setCmtCode(int cmtCode) { this.cmtCode = cmtCode; }
    public int getBoardCode() { return boardCode; }
    public void setBoardCode(int boardCode) { this.boardCode = boardCode; }
    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }
    public int getParent() { return parent; }
    public void setParent(int parent) { this.parent = parent; }
    public Timestamp getRegDate() { return regDate; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
    public Timestamp getModDate() { return modDate; }
    public void setModDate(Timestamp modDate) { this.modDate = modDate; }
}

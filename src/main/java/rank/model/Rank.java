package rank.model;

public class Rank {

    private int count;
    private String musicTrack;
    private String musicArtist;
    private String musicPreviewUrl;
    private String musicThumbnail;
    private String musicUrl;

    public Rank(int count, String musicTrack, String musicArtist, String musicPreviewUrl, String musicThumbnail) {
        this.count = count;
        this.musicTrack = musicTrack;
        this.musicArtist = musicArtist;
        this.musicPreviewUrl = musicPreviewUrl;
        this.musicThumbnail = musicThumbnail;
    }

    public Rank(int count, String musicTrack, String musicArtist, String musicPreviewUrl, String musicThumbnail, String musicUrl) {
        this.count = count;
        this.musicTrack = musicTrack;
        this.musicArtist = musicArtist;
        this.musicPreviewUrl = musicPreviewUrl;
        this.musicThumbnail = musicThumbnail;
        this.musicUrl = musicUrl;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getMusicTrack() {
        return musicTrack;
    }

    public String getMusicUrl() {return musicUrl;}
}

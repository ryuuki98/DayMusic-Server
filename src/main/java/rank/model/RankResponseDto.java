package rank.model;

public class RankResponseDto {
    private int count;
    private String musicTrack;
    private String musicArtist;
    private String musicPreviewUrl;
    private String musicThumbnail;

    public RankResponseDto(int count, String musicTrack, String musicArtist, String musicPreviewUrl, String musicThumbnail) {
        this.count = count;
        this.musicTrack = musicTrack;
        this.musicArtist = musicArtist;
        this.musicPreviewUrl = musicPreviewUrl;
        this.musicThumbnail = musicThumbnail;
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
    public void setMusicTrack(String musicTrack) {
        this.musicTrack = musicTrack;
    }
    public String getMusicArtist() {
        return musicArtist;
    }
    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }
    public String getMusicPreviewUrl() {
        return musicPreviewUrl;
    }
    public void setMusicPreviewUrl(String musicPreviewUrl) {
        this.musicPreviewUrl = musicPreviewUrl;
    }
    public String getMusicThumbnail() {
        return musicThumbnail;
    }
    public void setMusicThumbnail(String musicThumbnail) {
        this.musicThumbnail = musicThumbnail;
    }
}

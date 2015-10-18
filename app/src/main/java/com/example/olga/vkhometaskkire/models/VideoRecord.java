package com.example.olga.vkhometaskkire.models;

/**
 * Created by Olga on 17.10.2015.
 */
public class VideoRecord {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_preview() {
        return photo_preview;
    }

    public void setPhoto_preview(String photo_preview) {
        this.photo_preview = photo_preview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String photo_preview;
    private String title, link;

    public VideoRecord(int id, String photo_preview, String title, String link) {
        this.id = id;
        this.photo_preview = photo_preview;
        this.title = title;
        this.link = link;
    }

    public boolean isMember(int[] videosId) {
        for (int i : videosId) {
            if (id == i) return true;
        }
        return false;
    }
}

package com.example.olga.vkhometaskkire.models;

/**
 * Created by Olga on 18.10.2015.
 */
public class AudioTreck {
    private int id;
    private String title, link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public AudioTreck(int id, String title, String link) {
        this.id = id;
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

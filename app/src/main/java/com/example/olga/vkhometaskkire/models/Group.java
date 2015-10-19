package com.example.olga.vkhometaskkire.models;

/**
 * Created by Olga on 18.10.2015.
 */
public class Group {
    private int id;
    private int [] membersId;
    private String title;
    private String icon;
    private boolean isClosed;

    public Group(int id, int[] membersId, String title, String icon, boolean isClosed) {
        this.id = id;
        this.membersId = membersId;
        this.title = title;
        this.icon = icon;
        this.isClosed = isClosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getMembersId() {
        return membersId;
    }

    public void setMembersId(int[] membersId) {
        this.membersId = membersId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isMember(int[] groupsId) {
        for (int i : groupsId) {
            if (id == i) return true;
        }
        return false;
    }
}

package com.project.acto_assignment_brandon.Models;

public class AlbumHolder {

    private int userId;
    private String title;
    private int id;

    public AlbumHolder(int userId, String title, int id) {
        this.userId = userId;
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public int getAlbumId() {
        return id;
    }
}

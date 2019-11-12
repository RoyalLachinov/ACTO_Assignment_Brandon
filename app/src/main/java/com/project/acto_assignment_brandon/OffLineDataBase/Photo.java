package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "photo_table")

public class Photo {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String title;
    private String url;


    public Photo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "album_name")

public class Album {

    @PrimaryKey(autoGenerate = true)

    private int id;

    private String title;

    public Album(String title) {
        this.title = title;
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
}

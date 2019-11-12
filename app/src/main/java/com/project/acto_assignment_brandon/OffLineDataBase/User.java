package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")

public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String userName;
    private String email;
    private String phone;
    private String webSite;

    public User(String name, String userName, String email, String phone, String webSite) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.webSite = webSite;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebSite() {
        return webSite;
    }



}

package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao

public interface UserDao {


    @Insert
    void insertToUser(User user);

    @Update
    void updateToUser(User user);

    @Delete
    void deleteToUser(User user);

    @Query("DELETE FROM user_table")
    void deleteAllFromUser();

    @Query("SELECT * FROM user_table OrDER BY id ASC")
    LiveData<List<User>> getAllUserFromChart();
}

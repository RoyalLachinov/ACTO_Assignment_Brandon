package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao

public interface PhotoDao {


    @Insert
    void insertToPhoto(Photo photo);

    @Update
    void updateToPhoto(Photo photo);

    @Delete
    void deleteToPhoto(Photo photo);

    @Query("DELETE FROM photo_table")
    void deleteAllFromPhoto();

    @Query("SELECT * FROM photo_table OrDER BY id DESC")
    LiveData<List<Photo>> getAllPhotoFromChart();
}

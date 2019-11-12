package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao

public interface AlbumDao {

    @Insert
    void insertToAlbum(Album album);

    @Update
    void updateToAlbum(Album album);

    @Delete
    void deleteToAlbum(Album album);

    @Query("DELETE FROM album_name")
    void deleteAllFromAlbum();

    @Query("SELECT * FROM album_name OrDER BY id DESC")
    LiveData<List<Album>> getAllAlbumFromChart();
}

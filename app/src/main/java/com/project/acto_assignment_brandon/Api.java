package com.project.acto_assignment_brandon;

import com.project.acto_assignment_brandon.Models.AlbumHolder;
import com.project.acto_assignment_brandon.Models.PhotoHolder;
import com.project.acto_assignment_brandon.Models.UserHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("photos")
    Call<List<PhotoHolder>> getPhotos(@Query("albumId") int albumId);

    @GET("users")
    Call<List<UserHolder>> getUser();

    @GET("albums")
    Call<List<AlbumHolder>> getAlbum(@Query("userId")int userId);
}

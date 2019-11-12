package com.project.acto_assignment_brandon;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.project.acto_assignment_brandon.Models.AlbumHolder;
import com.project.acto_assignment_brandon.Models.PhotoHolder;
import com.project.acto_assignment_brandon.Models.UserHolder;
import com.project.acto_assignment_brandon.OffLineDataBase.Album;
import com.project.acto_assignment_brandon.OffLineDataBase.Photo;
import com.project.acto_assignment_brandon.OffLineDataBase.User;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<User>> allChart;
    private LiveData<List<Album>> allAlbum;
    private LiveData<List<Photo>> allPhoto;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allChart = repository.getAllUser();
        allAlbum = repository.getAllAlbum();
        allPhoto = repository.getAllPhoto();
    }

    public LiveData<List<UserHolder>> getAllUsers(){
        return repository.getUserMutableLiveData();
    }
    public LiveData<List<AlbumHolder>> getAllAlbum(){
        return repository.getAlbumMutableLiveData();
    }
    public LiveData<List<PhotoHolder>> getAllPhoto(){
        return repository.getPhotoMutableLiveData();
    }

    public void insert(User user){
        repository.insertUser(user);
    }
    public void insert(Album album){
        repository.insertAlbum(album);
    }
    public void insert(Photo photo){
        repository.insertPhoto(photo);
    }
    public void update(User user){
        repository.updateUser(user);
    }
    public void update(Album album){
        repository.updateAlbum(album);
    }
    public void update(Photo photo){
        repository.updatePhoto(photo);
    }
    public void delete(User user){
        repository.deleteUser(user);
    }
    public void delete(Album album){
        repository.deleteAlbum(album);
    }
    public void delete(Photo photo){
        repository.deletePhoto(photo);
    }
    public void deleteAllUser(){
        repository.deleteAllUsers();
    }
    public void deleteAllAlbum(){
        repository.deleteAllAlbum();
    }
    public void deleteAllPhoto(){
        repository.deleteAllPhoto();
    }



    public LiveData<List<User>> getOffLineUsers(){
        return allChart;
    }
    public LiveData<List<Album>>getOffLineAlbum(){
        return allAlbum;
    }
    public LiveData<List<Photo>>getOffLinePhoto(){
        return allPhoto;
    }

}

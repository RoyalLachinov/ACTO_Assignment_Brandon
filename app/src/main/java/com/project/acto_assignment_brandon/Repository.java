package com.project.acto_assignment_brandon;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.project.acto_assignment_brandon.Activities_Adapters.AlbumActivity;
import com.project.acto_assignment_brandon.Models.AlbumHolder;
import com.project.acto_assignment_brandon.Models.PhotoHolder;
import com.project.acto_assignment_brandon.Models.UserHolder;
import com.project.acto_assignment_brandon.OffLineDataBase.Album;
import com.project.acto_assignment_brandon.OffLineDataBase.AlbumDao;
import com.project.acto_assignment_brandon.OffLineDataBase.Db;
import com.project.acto_assignment_brandon.OffLineDataBase.Photo;
import com.project.acto_assignment_brandon.OffLineDataBase.PhotoDao;
import com.project.acto_assignment_brandon.OffLineDataBase.User;
import com.project.acto_assignment_brandon.OffLineDataBase.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private MutableLiveData<List<UserHolder>> userMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<AlbumHolder>> albumMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<PhotoHolder>> photoMutableLiveData = new MutableLiveData<>();
    private Application application;

    private UserDao userDao;
    private AlbumDao albumDao;
    private PhotoDao photoDao;
    private LiveData<List<User>> allUser;
    private LiveData<List<Album>> allAlbum;
    private LiveData<List<Photo>> allPhoto;



    public Repository(Application application){
        this.application = application;
        Db db = Db.getInstance(application);
        userDao = db.userDao();
        albumDao = db.albumDao();
        photoDao = db.photoDao();
        allUser = userDao.getAllUserFromChart();
        allAlbum = albumDao.getAllAlbumFromChart();
        allPhoto = photoDao.getAllPhotoFromChart();
    }

    Api api = ApiClient.getClient().create(Api.class);


    public MutableLiveData<List<UserHolder>> getUserMutableLiveData() {
        Call<List<UserHolder>> call = api.getUser();
        call.enqueue(new Callback<List<UserHolder>>() {
            @Override
            public void onResponse(Call<List<UserHolder>> call, Response<List<UserHolder>> response) {
                List<UserHolder> userHolders = response.body();
                userMutableLiveData.setValue(userHolders);
            }

            @Override
            public void onFailure(Call<List<UserHolder>> call, Throwable t) {

            }
        });

        return userMutableLiveData;
    }

    public MutableLiveData<List<AlbumHolder>> getAlbumMutableLiveData(){
        Call<List<AlbumHolder>> albumCall = api.getAlbum(MainActivity.POSITION);
        albumCall.enqueue(new Callback<List<AlbumHolder>>() {
            @Override
            public void onResponse(Call<List<AlbumHolder>> call, Response<List<AlbumHolder>> response) {
                List<AlbumHolder> albumHolders = response.body();
                albumMutableLiveData.setValue(albumHolders);
            }

            @Override
            public void onFailure(Call<List<AlbumHolder>> call, Throwable t) {

            }
        });
        return albumMutableLiveData;

    }
    public MutableLiveData<List<PhotoHolder>> getPhotoMutableLiveData(){
        Call<List<PhotoHolder>> photoCall = api.getPhotos(AlbumActivity.USER_ID);
        photoCall.enqueue(new Callback<List<PhotoHolder>>() {
            @Override
            public void onResponse(Call<List<PhotoHolder>> call, Response<List<PhotoHolder>> response) {
                List<PhotoHolder> photoHolders = response.body();
                photoMutableLiveData.setValue(photoHolders);
            }

            @Override
            public void onFailure(Call<List<PhotoHolder>> call, Throwable t) {

            }
        });
        return photoMutableLiveData;
    }


    public void insertUser(User user){
        new InsertDataAsync(userDao).execute(user);
    }
    public void updateUser(User user){
        new UpdateDataAsync(userDao).execute(user);
    }
    public void deleteUser(User user){
        new DeleteDataAsync(userDao).execute(user);
    }
    public void deleteAllUsers(){
        new DeleteAll(userDao).execute();
    }
    public void deleteAllAlbum(){
        new DeleteAllAlbum(albumDao).execute();
    }
    public void deleteAllPhoto(){
        new DeleteAllPhoto(photoDao).execute();
    }
    
    public void insertAlbum(Album album){
        new InsertAlbumAsync(albumDao).execute(album);
    }
    public void updateAlbum(Album album){
        new UpdateAlbumAsync(albumDao).execute(album);
    }
    public void deleteAlbum(Album album){
        new DeleteAlbumAsync(albumDao).execute(album);
    }
    public void insertPhoto(Photo photo){
        new InsertPhotoAsync(photoDao).execute(photo);
    }
    public void updatePhoto(Photo photo){
        new UpdatePhotoAsync(photoDao).execute(photo);
    }
    public void deletePhoto(Photo photo){
        new DeletePhotoAsync(photoDao).execute(photo);
    }

    private static class InsertPhotoAsync extends AsyncTask<Photo, Void,Void>{
        private PhotoDao photoDao;
        private InsertPhotoAsync(PhotoDao photoDao){
            this.photoDao = photoDao;
        }
        @Override
        protected Void doInBackground(Photo... photos) {
            photoDao.insertToPhoto(photos[0]);
            return null;
        }
    }
    private static class UpdatePhotoAsync extends AsyncTask<Photo, Void,Void>{
        private PhotoDao photoDao;
        private UpdatePhotoAsync(PhotoDao photoDao){
            this.photoDao = photoDao;
        }
        @Override
        protected Void doInBackground(Photo... photos) {
            photoDao.updateToPhoto(photos[0]);
            return null;
        }
    }
    private static class DeletePhotoAsync extends AsyncTask<Photo, Void,Void>{
        private PhotoDao photoDao;
        private DeletePhotoAsync(PhotoDao photoDao){
            this.photoDao = photoDao;
        }
        @Override
        protected Void doInBackground(Photo... photos) {
            photoDao.deleteToPhoto(photos[0]);
            return null;
        }
    }
    private static class InsertAlbumAsync extends AsyncTask<Album, Void,Void>{
        private AlbumDao albumDao;
        private InsertAlbumAsync(AlbumDao albumDao){
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.insertToAlbum(albums[0]);
            return null;
        }
    }
    private static class UpdateAlbumAsync extends AsyncTask<Album, Void,Void>{
        private AlbumDao albumDao;
        private UpdateAlbumAsync(AlbumDao albumDao){
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.updateToAlbum(albums[0]);
            return null;
        }
    }
    private static class DeleteAlbumAsync extends AsyncTask<Album, Void,Void>{
        private AlbumDao albumDao;
        private DeleteAlbumAsync(AlbumDao albumDao){
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.deleteToAlbum(albums[0]);
            return null;
        }
    }
    private static class InsertDataAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertDataAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertToUser(users[0]);
            return null;
        }
    }
    private static class UpdateDataAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateDataAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateToUser(users[0]);
            return null;
        }
    }
    private static class DeleteDataAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteDataAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteToUser(users[0]);
            return null;
        }
    }
    private static class DeleteAll extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAll(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllFromUser();
            return null;
        }
    }
    private static class DeleteAllAlbum extends AsyncTask<Void, Void, Void> {
        private AlbumDao albumDao;

        private DeleteAllAlbum(AlbumDao albumDao){
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            albumDao.deleteAllFromAlbum();
            return null;
        }
    }
    private static class DeleteAllPhoto extends AsyncTask<Void, Void, Void> {
        private PhotoDao photoDao;

        private DeleteAllPhoto(PhotoDao photoDao){
            this.photoDao = photoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    

    public LiveData<List<User>> getAllUser(){
        return allUser;
    }
    public LiveData<List<Album>> getAllAlbum(){
        return allAlbum;
    }
    public LiveData<List<Photo>> getAllPhoto(){
        return allPhoto;
    }

    

}

package com.project.acto_assignment_brandon.Activities_Adapters;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.project.acto_assignment_brandon.MyViewModel;
import com.project.acto_assignment_brandon.OffLineDataBase.Photo;
import com.project.acto_assignment_brandon.R;
import com.project.acto_assignment_brandon.Models.PhotoHolder;

import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private RecyclerView photo_recyclerView;
    private PhotoAdapter adapter;
    private OffLinePhotoAdapter offLinePhotoAdapter;
    private MyViewModel viewModel;
    private Context context;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photo_recyclerView = findViewById(R.id.photo_recyclerView);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        photo_recyclerView.setLayoutManager(layoutManager);

        photo_recyclerView.setHasFixedSize(true);

        if(isNetworkAvailable()){
            viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

            viewModel.getAllPhoto().observe(this, new Observer<List<PhotoHolder>>() {
                @Override
                public void onChanged(@Nullable List<PhotoHolder> photoHolders) {

                    populatePhotoRecyclerView(photoHolders, context);
                    viewModel.deleteAllPhoto();
                    for (int i = 0; i < photoHolders.size(); i++) {

                        Photo photo = new Photo(photoHolders.get(i).getTitle(),photoHolders.get(i).getUrl());
                        viewModel.insert(photo);
                    }

                }
            });
        }else {
            viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
            viewModel.getOffLinePhoto().observe(this, new Observer<List<Photo>>() {
                @Override
                public void onChanged(@Nullable List<Photo> photos) {
                    offLinePhotoAdapter = new OffLinePhotoAdapter(photos,context);
                    photo_recyclerView.setAdapter(offLinePhotoAdapter);
                }
            });
        }


    }
    private void populatePhotoRecyclerView(List<PhotoHolder> photoHolders, Context context){
        adapter = new PhotoAdapter(photoHolders, context);
        photo_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }return networkInfo != null && networkInfo.isConnected();
    }

}

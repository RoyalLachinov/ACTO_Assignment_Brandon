package com.project.acto_assignment_brandon.Activities_Adapters;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.acto_assignment_brandon.Models.AlbumHolder;
import com.project.acto_assignment_brandon.MyViewModel;
import com.project.acto_assignment_brandon.OffLineDataBase.Album;
import com.project.acto_assignment_brandon.R;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private MyViewModel albumViewModel;
    private OffLineAlbumAdapter albumAdapter;
    public static int USER_ID;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        recyclerView = findViewById(R.id.album_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        if (isNetworkAvailable()){
            albumViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
            albumViewModel.getAllAlbum().observe(this, new Observer<List<AlbumHolder>>() {
                @Override
                public void onChanged(@Nullable List<AlbumHolder> albumHolders) {
                    populateAlbumRecyclerView(albumHolders);
                    albumViewModel.deleteAllAlbum();
                    for (int i = 0; i < albumHolders.size(); i++) {
                        Album album = new Album(albumHolders.get(i).getTitle());
                        albumViewModel.insert(album);
                    }
                }
            });
        }else {
            albumViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
            albumViewModel.getOffLineAlbum().observe(this, new Observer<List<Album>>() {
                @Override
                public void onChanged(@Nullable List<Album> albums) {
                    albumAdapter = new OffLineAlbumAdapter(albums);
                    recyclerView.setAdapter(albumAdapter);
                    albumAdapter.setOnAlbumItemClickListener(new OffLineAlbumAdapter.OffLineAlbumAdapterListener() {
                        @Override
                        public void onClick() {
                            Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        }



    }
    private void populateAlbumRecyclerView(List<AlbumHolder> albumHolders){
        adapter = new AlbumAdapter(albumHolders);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnAlbumItemClickListener(new AlbumAdapter.OnAlbumAdapterListener() {
            @Override
            public void onClick(int intId) {
                Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
                USER_ID = intId;
                startActivity(intent);
            }
        });

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

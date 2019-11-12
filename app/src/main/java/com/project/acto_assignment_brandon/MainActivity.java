package com.project.acto_assignment_brandon;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.project.acto_assignment_brandon.Activities_Adapters.AlbumActivity;
import com.project.acto_assignment_brandon.Activities_Adapters.OffLineUserAdapter;
import com.project.acto_assignment_brandon.Activities_Adapters.UserAdapter;
import com.project.acto_assignment_brandon.Models.UserHolder;
import com.project.acto_assignment_brandon.OffLineDataBase.User;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private UserAdapter adapter;
    private OffLineUserAdapter offLineUserAdapter;

    private MyViewModel viewModel;
    public static int POSITION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.user_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(layoutManager);

        recyclerview.setHasFixedSize(true);

        if (isNetworkAvailable()){
            Toast.makeText(this, "NetWork is working", Toast.LENGTH_LONG).show();

            viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
            viewModel.getAllUsers().observe(this, new Observer<List<UserHolder>>() {
                @Override
                public void onChanged(@Nullable List<UserHolder> userHolders) {

                    populateRecyclerView(userHolders);

                    viewModel.deleteAllUser();

                    for (int i = 0; i < userHolders.size(); i++) {
                        User user = new User(userHolders.get(i).getName(), userHolders.get(i).getUsername(),
                                userHolders.get(i).getEmail(), userHolders.get(i).getPhone(), userHolders.get(i).getWebsite());

                        viewModel.insert(user);

                    }
                }
            });
        }else {
            Toast.makeText(this, "No NetWork", Toast.LENGTH_LONG).show();

            viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
            viewModel.getOffLineUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable List<User> users) {
                    offLineUserAdapter = new OffLineUserAdapter(users);
                    recyclerview.setAdapter(offLineUserAdapter);
                    offLineUserAdapter.setOnUserItemClickListener(new OffLineUserAdapter.OnOffLineUserAdapterListener() {
                        @Override
                        public void actionClick() {
                            Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            });

        }

    }

    private void populateRecyclerView(List<UserHolder> userHolders) {
        adapter = new UserAdapter(userHolders);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnUserItemClickListener(new UserAdapter.OnUserAdapterListener() {
            @Override
            public void actionClick(int position) {
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                POSITION = position;
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
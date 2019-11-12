package com.project.acto_assignment_brandon.Activities_Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.acto_assignment_brandon.OffLineDataBase.Photo;
import com.project.acto_assignment_brandon.R;

import java.util.List;

public class OffLinePhotoAdapter extends RecyclerView.Adapter<OffLinePhotoAdapter.ViewHolder> {

    private List<Photo> photoList;
    private Context context;

    public OffLinePhotoAdapter(List<Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offline_photo_adapter,viewGroup,false);

        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(context)
                .load(photoList.get(i).getUrl()).apply(requestOptions)
                .into(viewHolder.offline_image);
        viewHolder.offline_image_name.setText(photoList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView offline_image;
        public TextView offline_image_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            offline_image_name = itemView.findViewById(R.id.offline_image_name);
            offline_image = itemView.findViewById(R.id.offline_image);

        }
    }
}

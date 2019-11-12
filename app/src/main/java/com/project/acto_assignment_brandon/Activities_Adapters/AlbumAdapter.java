package com.project.acto_assignment_brandon.Activities_Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.acto_assignment_brandon.R;
import com.project.acto_assignment_brandon.Models.AlbumHolder;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<AlbumHolder> list;

    private OnAlbumAdapterListener listener;

    public interface OnAlbumAdapterListener{
        void onClick( int intId);
    }
    public void setOnAlbumItemClickListener(OnAlbumAdapterListener listener){
        this.listener = listener;
    }

    public AlbumAdapter(List<AlbumHolder> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_adapter,viewGroup,false);
        return new AlbumViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.album_text.setText(list.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        public TextView album_text;
        public RelativeLayout album_layout;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            album_text = itemView.findViewById(R.id.album_text);
            album_layout = itemView.findViewById(R.id.album_layout);


            album_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idNum = getAdapterPosition()+1;
                    if (listener != null && idNum != RecyclerView.NO_POSITION){
                        listener.onClick(idNum);
                    }
                }
            });
        }
    }
}

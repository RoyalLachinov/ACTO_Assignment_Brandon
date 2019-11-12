package com.project.acto_assignment_brandon.Activities_Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.acto_assignment_brandon.OffLineDataBase.Album;
import com.project.acto_assignment_brandon.R;

import java.util.List;

public class OffLineAlbumAdapter extends RecyclerView.Adapter<OffLineAlbumAdapter.ViewHolder> {

    private List<Album>list;

    public OffLineAlbumAdapter(List<Album> list) {
        this.list = list;
    }

    private OffLineAlbumAdapterListener listener;

    public interface OffLineAlbumAdapterListener{
        void onClick( );
    }
    public void setOnAlbumItemClickListener(OffLineAlbumAdapterListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offline_album_adapter,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.offline_album_text.setText(list.get(i).getTitle());
        viewHolder.offline_album_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout offline_album_layout;
        public TextView offline_album_text;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            offline_album_layout = itemView.findViewById(R.id.offline_album_layout);
            offline_album_text = itemView.findViewById(R.id.offline_album_text);

        }
    }
}

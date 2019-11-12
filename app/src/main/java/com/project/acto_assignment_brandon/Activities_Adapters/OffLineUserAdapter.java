package com.project.acto_assignment_brandon.Activities_Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.acto_assignment_brandon.OffLineDataBase.User;
import com.project.acto_assignment_brandon.R;

import java.util.List;

public class OffLineUserAdapter extends RecyclerView.Adapter<OffLineUserAdapter.ViewHolder> {

    private List<User>users;

    public OffLineUserAdapter(List<User> users) {
        this.users = users;
    }


    private OnOffLineUserAdapterListener mListener;

    public interface OnOffLineUserAdapterListener{
        void actionClick();
    }
    public void setOnUserItemClickListener(OnOffLineUserAdapterListener listener){
        this.mListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offline_user_adapter,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.offline_name_txt.setText("Name: " + users.get(i).getName());
        viewHolder.offline_username_txt.setText("User name: " + users.get(i).getUserName());
        viewHolder.offline_email_txt.setText("Email: " + users.get(i).getEmail());
        viewHolder.offline_phone_txt.setText("Phone: " + users.get(i).getPhone());
        viewHolder.offline_web_txt.setText("Web: " + users.get(i).getWebSite());
        viewHolder.offline_user_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.actionClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offline_name_txt,offline_username_txt,offline_email_txt,offline_phone_txt,offline_web_txt;
        public LinearLayout offline_user_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            offline_name_txt = itemView.findViewById(R.id.offline_name_txt);
            offline_username_txt = itemView.findViewById(R.id.offline_username_txt);
            offline_email_txt = itemView.findViewById(R.id.offline_email_txt);
            offline_phone_txt = itemView.findViewById(R.id.offline_phone_txt);
            offline_web_txt = itemView.findViewById(R.id.offline_web_txt);
            offline_user_layout = itemView.findViewById(R.id.offline_user_layout);

        }
    }
}

package com.project.acto_assignment_brandon.Activities_Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.acto_assignment_brandon.R;
import com.project.acto_assignment_brandon.Models.UserHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private List<UserHolder>list;

    private OnUserAdapterListener mListener;

    public interface OnUserAdapterListener{
        void actionClick( int position);
    }
    public void setOnUserItemClickListener(OnUserAdapterListener listener){
        this.mListener = listener;
    }
    public UserAdapter(List<UserHolder> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_adapter,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name_txt.setText("Name: "+ list.get(i).getName());
        viewHolder.username_txt.setText("User name: "+ list.get(i).getUsername());
        viewHolder.email_txt.setText("Email: "+ list.get(i).getEmail());
        viewHolder.phone_txt.setText("Phone: "+ list.get(i).getPhone());
        viewHolder.web_txt.setText("Web: "+ list.get(i).getWebsite());
        viewHolder.company_txt.setText("Company: "+ list.get(i).getCompany().getCompanyName() + " '" + list.get(i).getCompany().getCatchPhrase()
        + "' " + " ("+ list.get(i).getCompany().getBs() +")");
        viewHolder.address_txt.setText("Address: "+ list.get(i).getAddress().getCity() + ", " + list.get(i).getAddress().getStreet() + ", " +
        list.get(i).getAddress().getSuite() + " " + list.get(i).getAddress().getZipcode());





    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name_txt, username_txt,email_txt,phone_txt,web_txt,address_txt,company_txt;
        public LinearLayout user_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_txt = itemView.findViewById(R.id.name_txt);
            username_txt = itemView.findViewById(R.id.username_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            phone_txt = itemView.findViewById(R.id.phone_txt);
            web_txt = itemView.findViewById(R.id.web_txt);
            user_layout = itemView.findViewById(R.id.user_layout);
            company_txt = itemView.findViewById(R.id.company_txt);
            address_txt = itemView.findViewById(R.id.address_txt);

            user_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition()+1;
                    if (mListener != null && position != RecyclerView.NO_POSITION){
                        mListener.actionClick(position);
                    }
                }
            });
        }
    }

}

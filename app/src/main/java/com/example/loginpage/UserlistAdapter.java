package com.example.loginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserlistAdapter extends RecyclerView.Adapter<UserlistAdapter.Viewholder> {

    ArrayList<ModelClass> modelClasses;
    private Context context;

    public UserlistAdapter(ArrayList<ModelClass> modelClassArrayList, Context context) {
        modelClasses = modelClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserlistAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item_list,parent,false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserlistAdapter.Viewholder holder, int position) {
        ModelClass modelClass = modelClasses.get(position);
        holder.pfp.setImageDrawable(context.getResources().getDrawable(R.drawable.user));
        holder.email.setText(modelClass.getEmail());
    }

    @Override
    public int getItemCount() {

        return modelClasses.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView pfp;
        TextView email;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
            pfp = itemView.findViewById(R.id.avatar);
            email = itemView.findViewById(R.id.tvv_email);
        }
    }
}

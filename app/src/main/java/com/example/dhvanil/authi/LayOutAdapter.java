package com.example.dhvanil.authi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

public class LayOutAdapter extends RecyclerView.Adapter<LayOutAdapter.ViewHolder> {
    private Context ctx;
    private List<User>USERS;

    public LayOutAdapter( Context ctx, List<User> user ) {
        this.ctx = ctx;
        this.USERS = user;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View v=LayoutInflater.from(ctx).inflate(R.layout.sampleview,parent,false);
        return new LayOutAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        final User user1 = USERS.get(position);
        holder.username.setText( user1.getname());
      //  holder.username.setText(user1.getname());
        if(user1.getImageUrl().equals( "Default" ))
        {
            holder.profilePhoto.setImageResource( R.mipmap.ic_launcher );
        }
        else
        {
            Glide.with( ctx ).load( user1.getImageUrl() ).into( holder.profilePhoto );
        }

        holder.profilePhoto.setImageResource( R.mipmap.ic_launcher );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent( ctx,MessageActivity.class);
                intent.putExtra( "ImageUrl",user1.getImageUrl());
                intent.putExtra( "Name",user1.getname() );
                intent.putExtra( "id",user1.getId() );
                ctx.startActivity(intent);
            }
        } );
    }

    public int getItemCount() {
        return USERS.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profilePhoto;

        public ViewHolder( View itemView ) {
            super( itemView );
            username = itemView.findViewById( R.id.Text3 );
            profilePhoto = itemView.findViewById( R.id.myImage );
        }
    }
}

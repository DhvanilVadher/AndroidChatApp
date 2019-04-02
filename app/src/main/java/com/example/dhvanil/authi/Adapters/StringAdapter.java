package com.example.dhvanil.authi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dhvanil.authi.Activities.ChatActivity;
import com.example.dhvanil.authi.Activities.GroupMessageActivity;
import com.example.dhvanil.authi.Activities.MainActivity;
import com.example.dhvanil.authi.R;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViiewHolder2> {

    private Context ctx2;
    private ArrayList<String> GrpChats;

    public StringAdapter( Context ctx2, ArrayList<String> grpChats ) {
        this.ctx2 = ctx2;
        GrpChats = grpChats;
    }

    public StringAdapter() {
    }

    @NonNull
    @Override
    public ViiewHolder2 onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
        View v= LayoutInflater.from( ctx2 ).inflate( R.layout.messagemodereceiever,viewGroup,false );
        return new StringAdapter.ViiewHolder2(v);
    }

    @Override
    public void onBindViewHolder( @NonNull ViiewHolder2 viiewHolder2, final int i ) {
            viiewHolder2.Message.setText( GrpChats.get( i ) );
            viiewHolder2.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    Intent intent = new Intent( ctx2,GroupMessageActivity.class);
                    ctx2.startActivity(intent);
                }
            } );
    }

    @Override
    public int getItemCount() {
        return  GrpChats.size();
    }

    public  class  ViiewHolder2 extends  RecyclerView.ViewHolder{
        public TextView Message;

        public ViiewHolder2( @NonNull View itemView ) {
            super( itemView );
            Message = itemView.findViewById( R.id.FinalMessage);
        }

    }
}

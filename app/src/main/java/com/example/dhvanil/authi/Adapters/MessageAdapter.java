package com.example.dhvanil.authi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dhvanil.authi.BasicClasses.ChatClass;
import com.example.dhvanil.authi.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder1>{
    private Context ctx;
    private List<ChatClass>MyChats;

    public MessageAdapter( Context ctx, List<ChatClass> myChats ) {
        this.ctx = ctx;
        MyChats = myChats;
    }
    @NonNull
    @Override
    public MessageAdapter.ViewHolder1 onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        switch (viewType) {
            case 0:
               View v = LayoutInflater.from( ctx ).inflate( R.layout.messagemodel,parent ,false);
               return new MessageAdapter.ViewHolder1( v );
            case 1:
               View v1 = LayoutInflater.from( ctx ).inflate( R.layout.messagemodereceiever,parent ,false);
               return new MessageAdapter.ViewHolder1( v1 );
               default:return null;
        }
    }

     @Override
    public void onBindViewHolder( @NonNull MessageAdapter.ViewHolder1 holder, int position ) {
            ChatClass chatClass = MyChats.get( position );
            holder.FinamMessage.setText(chatClass.getMessage());
    }

    @Override
    public int getItemCount() {
        return MyChats.size();
    }

    @Override
    public int getItemViewType( int position ) {
        if(MyChats.get( position ).isSenderOfNot()){
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public TextView FinamMessage;
        public ViewHolder1( View itemView ) {
            super( itemView );
            FinamMessage = itemView.findViewById( R.id.FinalMessage );
        }
    }
}

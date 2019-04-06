package com.example.dhvanil.authi.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhvanil.authi.Adapters.MessageAdapter;
import com.example.dhvanil.authi.BasicClasses.Chat;
import com.example.dhvanil.authi.BasicClasses.ChatClass;
import com.example.dhvanil.authi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    private static final String TAG ="TAG" ;
    ImageView imageView;
    EditText sendMessage;
    TextView textView;
    RecyclerView recycler1;
    MessageAdapter adapter1;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );
        textView = findViewById( R.id.MessageName );
        recycler1 = findViewById( R.id.recycler1 );
        recycler1.setHasFixedSize( true );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        layoutManager.setStackFromEnd( true );
        Intent intent =getIntent();
        String a=intent.getStringExtra( "Name" );
        if(a==""){
            textView.setText( "aaa" );
        }
        else
        {
            textView.setText(a);
        }
        sendMessage=findViewById( R.id.sendMessageText );
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String p=intent.getStringExtra( "id" );
        String q=firebaseUser.getUid();
        recycler1.setLayoutManager(layoutManager);
        receieveMessage(q,p);
    }

    public void SendMessageToReceiever( View view ) {
        String sender,receiever,message;
        message= sendMessage.getText().toString();
        if(message.equals(null) || message.equals("")){
            return;
        }
        else if(sendMessage.getText().toString().equals( null ) || sendMessage.getText().toString().equals( "" )){
            return;
        }
        FirebaseUser firebaseUser;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sender =firebaseUser.getUid();
        Intent intent = getIntent();
        receiever =intent.getStringExtra( "id");
        sendMessage(sender,receiever,message);
        sendMessage.setText("");
    }

    private void sendMessage( String sender, String receiever, String message ) {
        DatabaseReference dbr=FirebaseDatabase.getInstance().getReference();
        HashMap<String ,Object> hashMap = new HashMap<>(  );
        hashMap.put( "sender",sender );
        hashMap.put("message",message);
        hashMap.put( "receiever",receiever );
        dbr.child( "Chats" ).push().setValue(hashMap);
    }
    private void receieveMessage ( final String sender, final String receiever){
        final ArrayList<ChatClass> myChat= new ArrayList<>();
        DatabaseReference dbr= FirebaseDatabase.getInstance().getReference().child( "Chats" );
        dbr.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                myChat.clear();
                for(DataSnapshot dss: dataSnapshot.getChildren()){
                    Chat chat = dss.getValue(Chat.class);
                    if(chat==null){
                        Toast.makeText( MessageActivity.this,chat.getMessage()+" "+chat.getReceiever(),Toast.LENGTH_LONG ).show();
                        return;
                    }
                    if(chat.getReceiever().equals(receiever) && chat.getSender().equals(sender)){
                        ChatClass chatClass= new ChatClass( chat.getSender(),chat.getReceiever(),chat.getMessage(),true );
                        chatClass.setSender( chat.getSender());
                        chatClass.setMessage( chat.getMessage());
                        chatClass.setReceiever(chat.getReceiever());
                        myChat.add( chatClass );
                        Log.v(TAG, "Chats:"+chatClass.getMessage() +chatClass.getReceiever()+chatClass.getSender());
                        Log.v(TAG, "Chats1:"+chat.getMessage() +chat.getReceiever()+chat.getSender());

                    }
                    else if(chat.getReceiever().equals(sender) && chat.getSender().equals(receiever))
                    {
                        ChatClass chatClass= new ChatClass( chat.getSender(),chat.getReceiever(),chat.getMessage(),false );
                        chatClass.setSender( chat.getSender());
                        chatClass.setMessage( chat.getMessage());
                        chatClass.setReceiever(chat.getReceiever());
                        myChat.add(chatClass);

                        Log.v(TAG, "Chats:"+chatClass.getMessage() +chatClass.getReceiever()+chatClass.getSender());
                        Log.v(TAG, "Chats1:"+chat.getMessage() +chat.getReceiever()+chat.getSender());
                    }
                }
                adapter1 = new MessageAdapter(getApplicationContext(),myChat);
                recycler1.setAdapter( adapter1 );
            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        } );
    }
}

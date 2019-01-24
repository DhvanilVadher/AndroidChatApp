package com.example.dhvanil.authi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    ImageView imageView;
    EditText sendMessage;
    TextView textView;
    RecyclerView recycler1;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );
        textView = findViewById( R.id.MessageName );
        recycler1 = findViewById( R.id.recycler1 );
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
    }

    public void SendMessageToReceiever( View view ) {
        String sender,receiever,message;
        message= sendMessage.getText().toString();
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
        dbr.child( "Chats" ).push().setValue( hashMap );
    }
}

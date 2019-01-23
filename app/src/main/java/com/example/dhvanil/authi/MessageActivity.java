package com.example.dhvanil.authi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {
    ImageView imageView;
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
    }

    public void SendMessageToReceiever( View view ) {
    }
}

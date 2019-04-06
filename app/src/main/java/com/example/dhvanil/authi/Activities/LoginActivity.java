package com.example.dhvanil.authi.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhvanil.authi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    String User,pwd;
    DatabaseReference mdb;
    private FirebaseAuth auth;
    private EditText UserName,Password;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
    }

    public void submit1( View view ) {
        auth= FirebaseAuth.getInstance();
        UserName = (EditText)findViewById( R.id.email1 );
        Password = findViewById( R.id.password1);
        User = UserName.getText().toString();
        pwd = Password.getText().toString();
        if(User.equals( null  )|| pwd.equals( null )|| User.equals( "" )|| pwd.equals( "" )){
            return;
        }
        auth.signInWithEmailAndPassword( User,pwd ).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess( AuthResult authResult ) {
                Intent intent= new Intent( LoginActivity.this, ChatActivity.class );
                startActivity( intent );
                finish();
            }
        }).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {
                Toast.makeText( LoginActivity.this,"Enter Credentials Properly",Toast.LENGTH_LONG).show();
            }
        } );
    }
}

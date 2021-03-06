package com.example.dhvanil.authi.Activities;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhvanil.authi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity{
    DatabaseReference mdb;
    private FirebaseAuth Auth;
    private FirebaseUser firebaseUser;
    private EditText UserName,Password,Email ;

    protected void onCreate( Bundle savedInstanceState ) {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );
            Email = findViewById( R.id.email ) ;
            UserName = findViewById( R.id.name );
            Password = findViewById( R.id.password );
            mdb = FirebaseDatabase.getInstance().getReference();
            Auth = FirebaseAuth.getInstance();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if(firebaseUser!=null){
                String a=firebaseUser.getUid();
                Toast.makeText( MainActivity.this,a,Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( MainActivity.this, ChatActivity.class);
                startActivity( intent );
                finish();
            }
        }

    public void Login( View view ) {
        Intent intent = new Intent( MainActivity.this, LoginActivity.class);
        //intent.putExtra(  );
        startActivity( intent );
    }
    public void submit( View view ) {
        if(Email.getText().toString().equals("") || Password.getText().toString().equals( "" ) || UserName.getText().toString().equals( "" ) || Email.getText().toString().equals(null) || Password.getText().toString().equals( null ) || UserName.getText().toString().equals( null )){
            return;
        }
        Auth.createUserWithEmailAndPassword( Email.getText().toString(),Password.getText().toString()).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess( AuthResult authResult ) {
                final FirebaseUser user = authResult.getUser();
                final String userId= user.getUid();
                DatabaseReference reference = mdb.child( "User" ).child( userId );
                HashMap<String,String> hashMap = new HashMap<>(  );
                hashMap.put("id",userId);
                hashMap.put("name",UserName.getText().toString());
                hashMap.put("Email",Email.getText().toString());
                hashMap.put("ImageUrl","Default");
                hashMap.put("PassWord",Password.getText().toString());
                reference.setValue( hashMap ).addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid ) {
                        Intent intent = new Intent( MainActivity.this,ChatActivity.class );
                        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK );
                        EmailVerify();
                        if(Auth.getCurrentUser().isEmailVerified()==true) {
                            startActivity( intent );
                            finish();
                            Toast.makeText( MainActivity.this, "Hello" + UserName, Toast.LENGTH_SHORT ).show();
                        }
                        else
                        {
                            Log.v("Not VArified","not varified");
                        }
                    }

                    private void EmailVerify() {
                        final FirebaseUser user1 = Auth.getCurrentUser();
                        user1.sendEmailVerification().addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                Toast.makeText(MainActivity.this,"Verification Email sent to "+ user1.getDisplayName(),Toast.LENGTH_SHORT).show();
                            }
                        } ).addOnFailureListener( new OnFailureListener() {
                            @Override
                            public void onFailure( @NonNull Exception e ) {
                                Toast.makeText(MainActivity.this,"Try Again",Toast.LENGTH_SHORT).show();

                            }
                        } );
                }
                } ).addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure( @NonNull Exception e ) {
                        Toast.makeText( MainActivity.this,"You can't register",Toast.LENGTH_SHORT).show();
                    }
                } );
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {
                Log.v( "why",e.toString() );
                Toast.makeText( MainActivity.this,"Faild!Something went wrong",Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    public void submit1( View view ) {
    }


}

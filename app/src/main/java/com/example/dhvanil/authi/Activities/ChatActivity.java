package com.example.dhvanil.authi.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dhvanil.authi.Adapters.adapter;
import com.example.dhvanil.authi.BasicClasses.User;
import com.example.dhvanil.authi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {
    DatabaseReference reference1;
    ImageView imageView;
    TextView textView;
    TabLayout tabLayout;
    FirebaseUser firebaseUser;
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );
        Toast.makeText( this,"Yeah",Toast.LENGTH_LONG ).show();
        imageView= findViewById( R.id.ProfileImage );
        textView = findViewById( R.id.ProfileText );
        tabLayout = findViewById( R.id.tablayout );
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("User").child( firebaseUser.getUid() );
        reference1.addValueEventListener( new ValueEventListener() {
         @Override
        public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
        User user = dataSnapshot.getValue(User.class);

        if(user!=null)
            textView.setText( user.getname() );
        else
            Log.v("2222","2222");

        if(user.getImageUrl().equals( "Default" ))
            imageView.setImageResource( R.mipmap.ic_launcher );
        else
            Glide.with(ChatActivity.this).load(user.getImageUrl()).into(imageView);
    }

    @Override
    public void onCancelled( @NonNull DatabaseError databaseError ) {

    }
} );

        ViewPager pager = (ViewPager)findViewById( R.id.viewPager );
        adapter adapter1 = new adapter(  getSupportFragmentManager());
        adapter1.addFregment( new FirstFregment(),"User");
        adapter1.addFregment( new SecondFragment(),"Chats");
        adapter1.addFregment( new Grp_Fragment(),"Groups");
        pager.setAdapter(adapter1);
        tabLayout.setupWithViewPager( pager );
    }
    public void logOut( View view ) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ChatActivity.this, MainActivity.class );
        startActivity( intent );
        finish();
    }

    public void AddGroup( View view ){
        Intent intent = new Intent(ChatActivity.this, CheckerGrp.class  );
        startActivity(intent);
    }

}

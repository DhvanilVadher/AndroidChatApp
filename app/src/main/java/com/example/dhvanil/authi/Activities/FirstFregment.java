package com.example.dhvanil.authi.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhvanil.authi.BasicClasses.User;
import com.example.dhvanil.authi.Adapters.LayOutAdapter;
import com.example.dhvanil.authi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class FirstFregment extends Fragment {

    RecyclerView recyclerView;
    List<User> UserList;
    LayOutAdapter adapter;
    //Context context;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState ) {
        View v= inflater.inflate( R.layout.fragment_first_fregment,container ,false);
        recyclerView = v.findViewById( R.id.Recycler );
        UserList =new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        readUser();
       // context =getContext();
        return v;
    }

    private void readUser() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser==null){
            return ;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                UserList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue( User.class );
                    Log.v(TAG, "User="+user.getId()+" "+user.getImageUrl()+" "+user.getname());
                    if(!snapshot.getKey().equals( firebaseUser.getUid() ))
                    {UserList.add(user);}
                }
                adapter = new LayOutAdapter(getContext(),UserList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {
            }
        } );

    }
}

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

import com.example.dhvanil.authi.Adapters.LayOutAdapter;
import com.example.dhvanil.authi.BasicClasses.Chat;
import com.example.dhvanil.authi.BasicClasses.User;
import com.example.dhvanil.authi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SecondFragment extends Fragment {
    public SecondFragment() {
    }
    HashSet<String> hashSet;
    DatabaseReference reference ;
    RecyclerView recyclerView;
    FirebaseUser firebaseUser;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState ) {
        View v= inflater.inflate( R.layout.fragment_second,container,false);
        recyclerView = v.findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        hashSet = new HashSet<String>();
        reference  = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                hashSet.clear();

                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Chat chat = dataSnapshot1.getValue(Chat.class);
                if(chat.getReceiever().equals( firebaseUser.getUid())) {
                    hashSet.add( chat.getSender() );
                }
                    if(chat.getSender().equals( firebaseUser.getUid() )){
                    hashSet.add( chat.getReceiever());
                    }
                }
                Log.v("fd", "hehehe"+String.valueOf(hashSet.size()));
                readChats();
            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {
            }
        } );
        return v;
    }

    private void readChats() {
        final List<User>arrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            //Display Users with Chats
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                arrayList.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    for(String id:hashSet){
                        if(user.getId().equals(id)){
                          arrayList.add( user );
                        }
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                Log.v("fd", "hehehe"+String.valueOf(arrayList.size()));
                LayOutAdapter adapter = new LayOutAdapter(getContext(),arrayList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }
        } );
    }
}

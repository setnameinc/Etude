package com.setnameinc.etude.app;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataBase implements DB {
    private String text;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    @Override
    public List<String> getUser() {
        return convertData(getSomething("users"));
    }

    @Override
    public List<String> getLessons() {
        return convertData(getSomething("lessons"));
    }

    @Override
    public List<String> getDays() {
        return convertData(getSomething("Days"));
    }

    @Override
    public void setUser(int id) {
        User user = new User(id);

        myRef.child("users").child("id").setValue(user);
    }

    private String getSomething(String tag) {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                text = dataSnapshot.child(tag).getValue().toString();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "Failed to read value.", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(userListener);

        return text;
    }

    private List<String> convertData(String str) {

        String[] data = str.substring(1, str.length() - 1).split(",");
        List<String> list = Arrays.asList(data);
        return list;
    }
}

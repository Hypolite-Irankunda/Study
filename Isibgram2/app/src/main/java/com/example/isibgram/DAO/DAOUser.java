package com.example.isibgram.DAO;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.isibgram.Main.MainActivity;
import com.example.isibgram.Profile.ProfileActivity;
import com.example.isibgram.User.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOUser {

    private DatabaseReference databaseReference;
    FirebaseDatabase db;

    String  name;
    String profile_photo;
    String  email;
    Integer posts;
    String matriculeUser;
    User user;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public DAOUser(){

        db = FirebaseDatabase.getInstance("https://isibgram-1643184797189-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference("user");

    }

    public Task<Void> add(User user){

        //verrifier s'il existe !! email
        System.out.println("DAO lancé"+ db.toString());
        System.out.println("DAO databaseReference = "+ databaseReference.toString());





       return databaseReference.child(user.getMatricule()).setValue(user);
    }


}

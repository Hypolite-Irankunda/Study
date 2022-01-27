package com.example.isibgram.DAO;

import com.example.isibgram.Post.Post;
import com.example.isibgram.User.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPost {

    private DatabaseReference databaseReference;
    FirebaseDatabase db;


    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public DAOPost(){

        db = FirebaseDatabase.getInstance("https://isibgram-1643184797189-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference("post");

    }

    public Task<Void> add(Post post){

        //verrifier s'il existe !! email
        System.out.println("DAO lancé"+ db.toString());
        System.out.println("DAO databaseReference = "+ databaseReference.toString());





       return databaseReference.child(post.getTitle()).setValue(post);
    }


}

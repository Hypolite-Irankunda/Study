package com.example.isibgram.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.isibgram.DAO.DAOUser;
import com.example.isibgram.Main.MainActivity;
import com.example.isibgram.R;
import com.example.isibgram.User.User;
import com.example.isibgram.Utils.BottomNavigationViewHelper;
import com.example.isibgram.Utils.UniversalImageLoader;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private  static final int ACTIVITY_NUM = 3;
    private ImageView mProfilePhoto;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name;
    TextView email;
    TextView profilePostsTextView;
    Button logOutBtn;

    DAOUser daoUser;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");
        mProfilePhoto = findViewById(R.id.profile_photo);
        //mProfilePhoto.setImageResource(R.drawable.ic_android_background);

        ArrayList<String> profilePostsList= new ArrayList<String>();
        profilePostsList.add("foo");
        profilePostsList.add("bar");
        profilePostsList.add("Merci pour ...");

        setupProfilePostGrid(profilePostsList);

        setupBottomNavigationView();

        initImageLoader();
        setProfileImage();


        logOutBtn = findViewById(R.id.buttonlogout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        daoUser = new DAOUser();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){

            try {
                user = getUserData();
            }catch (NumberFormatException e){

                System.err.println(e);
            }


            profilePostsTextView = findViewById(R.id.textViewPosts);
            profilePostsTextView.setText(user.getPosts().toString());

            name = findViewById(R.id.profileName);
            email = findViewById(R.id.textViewEmail);
            name.setText(user.getName());
            email.setText(user.getEmail());

        }

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        //setupBottomNavigationView();
        // setupProfileParams("Hypolite.Irankunda", "345");
    }

    private User getUserData(){

        Intent intent = getIntent();


        String name = intent.getStringExtra("nameUser");
        String email = intent.getStringExtra("emailUser");
        String posts = intent.getStringExtra("postsUser");
        String matricule = intent.getStringExtra("matriculeUser");

        User user = new User(name,"",email, Integer.parseInt(posts), matricule);
        return user;
    }

    void signOut(){

        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {

                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

    private void setupProfilePostGrid(ArrayList<String> profilePostsTitles){

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                profilePostsTitles );

        listView.setAdapter(arrayAdapter);
    }



    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }


    private void setupProfileParams(String profileName, String Posts){

        TextView profileNameTextView = findViewById(R.id.profileName);
        profileNameTextView.setText(profileName);

        TextView profilePostsTextView = findViewById(R.id.textViewPosts);
        profilePostsTextView.setText(Posts);

    }

    private void initImageLoader(){

        UniversalImageLoader universalImageLoader = new UniversalImageLoader(ProfileActivity.this); // peut être ProfileActivity.this
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage : setting profile image. ");
        String imgURL = "encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSghevZZUNzIcKYtOos_0iJCvyfNB3BDoGhhg&usqp=CAU";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, "https://");
    }
}

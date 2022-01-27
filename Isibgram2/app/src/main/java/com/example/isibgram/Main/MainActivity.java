package com.example.isibgram.Main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isibgram.DAO.DAOPost;
import com.example.isibgram.DAO.DAOUser;
import com.example.isibgram.Post.Post;
import com.example.isibgram.Profile.ProfileActivity;
import com.example.isibgram.R;
import com.example.isibgram.User.User;
import com.example.isibgram.Utils.BottomNavigationViewHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

      private static final String TAG = "MainActivity";
//    private  static final int ACTIVITY_NUM = 0;
      GoogleSignInOptions gso;
      GoogleSignInClient gsc;
      ImageView googleBtn;
      DAOUser daoUser;
      User user ;
      DAOPost daoPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_sign_in);
        Log.d(TAG, "onCreate: starting..");

        daoUser = new DAOUser();
        user = new User();
        daoPost = new DAOPost();
//
//
//        setupBottomNavigationView();
//        setupViewPager();

        googleBtn = findViewById(R.id.google_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        googleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    void signIn(){

        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

//    private void setupViewPager(){
//
//        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new CameraFragment());
//        adapter.addFragment(new MainFragment());
//
//        ViewPager viewPager = findViewById(R.id.container);
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_isiblogo);
//
//    }
//
//    private void setupBottomNavigationView(){
//        Log.d(TAG, "setupBottomNavigationView");
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
//
//        BottomNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationView);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                if( true ) { // --> acct.getEmail().contains("@etu.he2b.be")
                    System.out.println("************ICI*********");
                    System.out.println(acct.getEmail().contains("@etu.he2b.be"));

                    TextView textViewError = findViewById(R.id.signInError);
                    textViewError.setText("");


                    initPost();

                    getUserByMatricule( daoUser.getDatabaseReference(),user,acct);
                    //navigateToSecondActivity();
                }else{

                    signOut();
                    TextView textViewError = findViewById(R.id.signInError);
                    textViewError.setText("Vous devez utiliser une adresse @etu.he2b.be");
                }

            }catch (ApiException e){
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }
    void signOut(){

        gsc.signOut();
    }

    private void initPost(){

        Post post1= new Post("post1","11603","Voici LA vie la belle vie qui nous attend"); //String title, String postMatricule, String textDescription
        Post post2= new Post("post2","hypolite","Voici LA vie la belle vie qui nous attend");
        Post post3= new Post("post3","11603","Voici LA vie la belle vie qui nous attend");
        Post post4= new Post("post4","11603","Voici LA vie la belle vie qui nous attend");
        Post post5= new Post("post5","hypolite","Voici LA vie la belle vie qui nous attend");

        daoPost.add(post1);
        daoPost.add(post2);
        daoPost.add(post3);
        daoPost.add(post4);
        daoPost.add(post5);
    }

    private void navigateToSecondActivity() {
        System.out.println("NavugateToSecondeACtivity");
        System.out.println("********************************");

        finish();
        Intent intent = new Intent(MainActivity.this, HomeActivity.class );
        startActivity(intent);
    }


    public void  getUserByMatricule( DatabaseReference databaseReference, User user, GoogleSignInAccount acct){


        user.setEmail(acct.getEmail());
        user.setName(acct.getDisplayName());
        user.setMatricule(acct.getEmail().replace("@etu.he2b.be","").replace("-","").replace("@outlook.com",""));
        user.setPosts(0);
        daoUser.add(user);

        String matricule = user.getMatricule();
        Query checkUser = databaseReference.orderByChild("matricule").equalTo(matricule);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child(matricule).child("name").getValue(String.class);
                String email = snapshot.child(matricule).child("email").getValue(String.class);
                Integer posts = snapshot.child(matricule).child("posts").getValue(Integer.class);
                String matriculeUser = snapshot.child(matricule).child("matricule").getValue(String.class);

                //finish();
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);

                intent.putExtra("nameUser", name);
                intent.putExtra("emailUser", email);
                intent.putExtra("postsUser", posts.toString());
                intent.putExtra("matriculeUser", matriculeUser);

                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        System.out.println(" Lecture dans la base de donnée --> name = "+name);
//        System.out.println(" Lecture dans la base de donnée --> email = "+email);
//        user = new User(name, "", email, posts,matricule);
        //String name, String profile_photo, String email, int posts, String matricule


    }
}
package com.example.isibgram.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.isibgram.Main.MainActivity;
import com.example.isibgram.Profile.ProfileActivity;
import com.example.isibgram.R;
import com.example.isibgram.Search.SearchActivity;
import com.example.isibgram.Share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationViewHelper {

    //todo

    private static  final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView){
        Log.d(TAG, "setupBottomNavGView : settiing");

    }

    public static void enableNavigation(final Context context, BottomNavigationView view){

        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_circle:
                        Intent intent3 = new Intent(context, ShareActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent4);
                        break;



                }
                return false;
            }
        });
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean sw = true;

    public void switchLogAndPass(View view){



        Log.i("Info", "Button Pressed");
        TextView loginView = findViewById(R.id.loginTextView);
        TextView passwordView = findViewById(R.id.passwordTextView);
        String intermediateString = "";

        intermediateString = loginView.getText().toString();
        loginView.setText(passwordView.getText().toString());
        passwordView.setText(intermediateString);

        Toast.makeText(MainActivity.this, "You switch "+intermediateString+" and "+passwordView.getText().toString(), Toast.LENGTH_LONG).show();

        ImageView logo = findViewById(R.id.isibLogo);

        if ( sw){
            logo.setImageResource(R.drawable.esilogo);
            sw = !sw;

        }else {
            logo.setImageResource(R.drawable.isiblogo);
            sw = !sw;
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
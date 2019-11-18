package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.Toast;

public class MotionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);


        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(MotionScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(MotionScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, MotionScreen.class);
    }

}

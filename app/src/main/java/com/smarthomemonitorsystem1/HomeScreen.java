package com.smarthomemonitorsystem1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button tempButton = findViewById(R.id.TemperatureButton);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent THScreenIntent = TemperatureScreen.makeIntent(HomeScreen.this);
                startActivity(THScreenIntent);
                finish();
            }
        });

        Button smokeButton = findViewById(R.id.SmokeButton);
        smokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SmokeScreenIntent = SmokeScreen.makeIntent(HomeScreen.this);
                startActivity(SmokeScreenIntent);
                finish();
            }
        });

        Button MotionButton = findViewById(R.id.MotionButton);
        MotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MotionScreenIntent = MotionScreen.makeIntent(HomeScreen.this);
                startActivity(MotionScreenIntent);
                finish();
            }
        });

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Already at the home screen",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(HomeScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });


    }


    public static Intent makeIntent(Context context){
        return new Intent(context, HomeScreen.class);
    }
}

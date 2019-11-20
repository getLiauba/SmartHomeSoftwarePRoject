package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {

    private String mLanguageCode = "fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button LanEng = findViewById(R.id.LanEng);
        LanEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button LanFrn = findViewById(R.id.LanFrn);
        LanFrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageHelper.setLocale(SettingsScreen.this, mLanguageCode);
                recreate();
            }
        });

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(SettingsScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Already at the settings screen",Toast.LENGTH_SHORT).show();
            }
        });

        Button LogoutButton = findViewById(R.id.Logout_Button);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogoutIntent = LogInScreen.makeIntent(SettingsScreen.this);
                startActivity(LogoutIntent);
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, SettingsScreen.class);
    }

}

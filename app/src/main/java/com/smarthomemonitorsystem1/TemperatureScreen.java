package com.smarthomemonitorsystem1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TemperatureScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Temperature Screen");

    }
}
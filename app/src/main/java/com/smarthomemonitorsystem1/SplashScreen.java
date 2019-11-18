package com.smarthomemonitorsystem1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstantState){
        setTheme(R.style.SpalshTheme);
        super.onCreate(savedInstantState);
        Intent intent = new Intent(this, LogInScreen.class);
        startActivity(intent);
        finish();
    }
}

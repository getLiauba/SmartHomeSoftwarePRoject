package com.smarthomemonitorsystem1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstantState){
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstantState);
        Intent intent = new Intent(this, LogInScreen.class);
        startActivity(intent);
        finish();
    }
}

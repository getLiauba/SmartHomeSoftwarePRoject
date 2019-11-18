package com.smarthomemonitorsystem1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Forgetpassword1 extends AppCompatActivity {

    private TextView plogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Password Reset");


        plogin = (TextView) findViewById(R.id.pLogin);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                openMainActivity();


            }


        });

    }

    public void openMainActivity(){

        Intent intent1 = new Intent(this, LogInScreen.class);
        startActivity(intent1);


    }

}

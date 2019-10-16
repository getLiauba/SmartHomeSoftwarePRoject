package com.smarthomemonitorsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView ptext;
    private Button loginb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptext = (TextView) findViewById(R.id.pButton);
        loginb = (Button) findViewById(R.id.loginb);


        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openhomescreen();


            }


        });


        ptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openforgotpassword1();


            }


        });

    }

      public void openforgotpassword1(){

        Intent intent1 = new Intent(this,Forgetpassword1.class);
        startActivity(intent1);


      }

    public void openhomescreen(){

        Intent intent2 = new Intent(this,home_screen.class);
        Intent intent3 = new Intent(this,Navbar.class);
        startActivity(intent2);
        startActivity(intent3);


    }



}

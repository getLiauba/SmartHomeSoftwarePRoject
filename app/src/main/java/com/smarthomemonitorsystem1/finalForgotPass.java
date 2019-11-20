package com.smarthomemonitorsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalForgotPass extends AppCompatActivity {

    private Button loginbut;
    private Button backbut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_forgot_pass);

        loginbut = (Button) findViewById(R.id.loginb2);
        backbut = (Button) findViewById(R.id.backb1);


        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openloginscreen();


            }


        });

        backbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openpasswordscreen();

            }

        });
    }


    public void openloginscreen(){

        Intent intent1 = new Intent(this,LogInScreen.class);
        startActivity(intent1);


    }

    public void openpasswordscreen(){

        Intent intent1 = new Intent(this,Forgetpassword1.class);
        startActivity(intent1);


    }

}

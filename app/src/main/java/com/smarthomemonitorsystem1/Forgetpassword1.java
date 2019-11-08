package com.smarthomemonitorsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smarthomemonitorsystem1.R;

public class Forgetpassword1 extends AppCompatActivity {

    private TextView plogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);


        plogin = (TextView) findViewById(R.id.pLogin);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                openMainActivity();


            }


        });

    }

    public void openMainActivity(){

        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);


    }

}

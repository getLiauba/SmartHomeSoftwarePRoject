package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePasswordScreen extends AppCompatActivity {

    private TextView plogin;
    private Button nextbut;
    EditText userEmail;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);



        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(ChangePasswordScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(ChangePasswordScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

        nextbut = (Button) findViewById(R.id.nxtbutton);
        userEmail = (EditText) findViewById(R.id.Email);
        nextbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openResetPass();
            }

        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, ChangePasswordScreen.class);
    }

    public void openMainActivity(){

        Intent intent1 = new Intent(this,LogInScreen.class);
        startActivity(intent1);


    }

    public void openResetPass(){

        auth.sendPasswordResetEmail(userEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("MapleLeaf", "Email sent.");



                        }

                    }
                });


        Intent FinalChangePasswordIntent = FinalChangePasswordScreen.makeIntent(ChangePasswordScreen.this);
        startActivity(FinalChangePasswordIntent);
        finish();
    }

}

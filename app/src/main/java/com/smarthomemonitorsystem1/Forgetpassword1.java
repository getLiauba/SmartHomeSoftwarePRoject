package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class Forgetpassword1 extends AppCompatActivity {

    private TextView plogin;
    private Button nextbut;
    EditText userEmail;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);
        plogin = (TextView) findViewById(R.id.pLogin);
        nextbut = (Button) findViewById(R.id.nxtbutton);
        userEmail = (EditText) findViewById(R.id.Email);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openMainActivity();
            }


        });
        nextbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openResetPass();
            }

        });
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
        Intent intent2 = new Intent(this,finalForgotPass.class);
        startActivity(intent2);
    }
}

package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddDeviceScreen extends AppCompatActivity {
    private TextView plogin;
    private Button nextbut;
    EditText userEmail;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    String memberID;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_screen);

        memberID = auth.getUid().toString();



        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(AddDeviceScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(AddDeviceScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

        nextbut = (Button) findViewById(R.id.nxtbutton);
        userEmail = (EditText) findViewById(R.id.Email);

        nextbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                myRef = FirebaseDatabase.getInstance().getReference("Member/"+ memberID + "/");

                myRef.child(""+ "Devices/" + "" + userEmail.getText().toString() + "/Device code/").setValue("new device");


             //   ).setValue(member);

            }

        });

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AddDeviceScreen.class);
    }


}

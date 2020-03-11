package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SmokeScreen extends AppCompatActivity {

    private static final String TAG = "SmokeScreen";
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private String userID;
    private ListView gas;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseGasReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke_screen);

        Intent intent = getIntent();
        String deviceName = intent.getStringExtra(HomeScreen.EXTRA_NAME);
        System.out.println("------------------- ----- The device name is :" + deviceName);


        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        userID = user.getUid();
        gas = findViewById(R.id.actual_gas);


        databaseGasReference = firebaseDatabase.getReference("Member/" + userID + "/Devices/" + deviceName + "/Gas_Readings");


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChange:signed_in" + user.getUid());
                    //  toastMessage("Getting information for: " + user.getEmail());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        databaseGasReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showDataGas(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(SmokeScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(SmokeScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

    }
        @Override
        public void onStart () {
            super.onStart();
            auth.addAuthStateListener(authStateListener);
        }

        @Override
        public void onStop () {
            super.onStop();
            if (authStateListener != null) {
                auth.removeAuthStateListener(authStateListener);
            }
        }

    private void showDataGas(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            toastMessage("Datasnapshot is getting new Smoke reading----------------------------------." + ds.getValue().toString());
            String gas_val = ds.getValue().toString();
            int gasvalue = Integer.parseInt(gas_val);

            if(gasvalue >= 600) {

                findViewById(R.id.x).setVisibility(View.VISIBLE);
                findViewById(R.id.checkmark).setVisibility(View.GONE);
            }
            else {
                findViewById(R.id.x).setVisibility(View.GONE);
                findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
            }

            Gas_UserInformation userInformationgas =  new Gas_UserInformation();
            userInformationgas.setGasInfo("" + gas_val);
            ArrayList<String> arraygas = new ArrayList<>();
            arraygas.add(userInformationgas.getGasInfo());
            ArrayAdapter adaptergas = new ArrayAdapter(SmokeScreen.this, R.layout.mytextview,arraygas);
            gas.setAdapter (adaptergas);
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, SmokeScreen.class);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}

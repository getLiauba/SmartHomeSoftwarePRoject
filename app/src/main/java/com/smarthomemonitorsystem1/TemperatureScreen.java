package com.smarthomemonitorsystem1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TemperatureScreen extends AppCompatActivity {
    private static final String TAG = "TemperatureScreen";
    private String userID;
    private ListView temp;
    private ListView hum;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseTempReference;
    private DatabaseReference databaseHumReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        temp = findViewById(R.id.actual_temp);
        hum = findViewById(R.id.actual_hum);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        userID = user.getUid();

        databaseTempReference = firebaseDatabase.getReference("Member/" + userID + "/Temperature_Readings");
        databaseHumReference = firebaseDatabase.getReference("Member/" + userID + "/Humidity_Readings");


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
        databaseTempReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showDataTemp(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseHumReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showDataHum(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = HomeScreen.makeIntent(TemperatureScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(TemperatureScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });
    }

    private void showDataHum(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            String hum_val = ds.getValue().toString();
            Temp_UserInformation userInformationHum = new Temp_UserInformation();
            userInformationHum.setHumidity("" + hum_val);
            ArrayList<String> arrayHum = new ArrayList<>();
            arrayHum.add(userInformationHum.getHumidity());
            ArrayAdapter adapterHum = new ArrayAdapter(TemperatureScreen.this,android.R.layout.simple_list_item_1,arrayHum);
            hum.setAdapter(adapterHum);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(authStateListener != null){
            auth.removeAuthStateListener(authStateListener);
        }
    }

    private void showDataTemp(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            toastMessage("Datasnapshot is getting children");
            String temp_val = ds.getValue().toString();

            Temp_UserInformation userInformationTemp =  new Temp_UserInformation();

            userInformationTemp.setTemperature("" + temp_val);

            ArrayList<String> arrayTemp = new ArrayList<>();

            arrayTemp.add(userInformationTemp.getTemperature());

            ArrayAdapter adapterTemp = new ArrayAdapter(TemperatureScreen.this, android.R.layout.simple_list_item_1,arrayTemp);

            temp.setAdapter(adapterTemp);


        }
    }

//    private void showData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            Temp_UserInformation userInformation =  new Temp_UserInformation();
//            userInformation.setTemperature(ds.child(userID).getValue(Temp_UserInformation.class).getTemperature());
//            userInformation.setHumidity(ds.child(userID).getValue(Temp_UserInformation.class).getHumidity());
//
//            Log.d(TAG, "showData: temperature: " + userInformation.getTemperature());
//            Log.d(TAG, "showData: humidity: " + userInformation.getHumidity());
//
//            ArrayList<String> arrayTemp = new ArrayList<>();
//            ArrayList<String> arrayHum = new ArrayList<>();
//
//            arrayTemp.add(userInformation.getTemperature());
//            arrayHum.add(userInformation.getHumidity());
//
//            ArrayAdapter adapterTemp = new ArrayAdapter(TemperatureScreen.this, android.R.layout.simple_list_item_1,arrayTemp);
//            ArrayAdapter adapterHum = new ArrayAdapter(TemperatureScreen.this, android.R.layout.simple_list_item_1,arrayHum);
//
//            temp.setAdapter(adapterTemp);
//            hum.setAdapter(adapterHum);
//        }
//    }

    public static Intent makeIntent(Context context){
        return new Intent(context, TemperatureScreen.class);
    }

    private void toastMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

}

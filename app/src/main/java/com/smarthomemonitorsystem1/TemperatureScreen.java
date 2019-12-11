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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TemperatureScreen extends AppCompatActivity {
    private static final String TAG = "TemperatureScreen";
    private String userID;
    private TextView temp;
    private TextView hum;
    private GraphView tempGraph;
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

        Intent intent = getIntent();
        String deviceName = intent.getStringExtra(HomeScreen.EXTRA_NAME);

        temp = findViewById(R.id.actual_gas);
        hum = findViewById(R.id.actual_hum);
        tempGraph = findViewById(R.id.graph);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        userID = user.getUid();

        databaseTempReference = firebaseDatabase.getReference("Member/" + userID + "/Devices/" + deviceName + "/Temperature_Readings");
        databaseHumReference = firebaseDatabase.getReference("Member/" + userID + "/Devices/" + deviceName + "/Humidity_Readings");

        System.out.println("-------------------------------" + deviceName +"--------------");


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
            //toastMessage("Datasnapshot is getting new Humidity reading.");
            String hum_val = ds.getValue().toString();
            Temp_UserInformation userInformationHum = new Temp_UserInformation();
            userInformationHum.setHumidity("" + hum_val);
            hum.setText(userInformationHum.getHumidity().toString());
            
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
        String temp_val ="";
        String temp_oldVal1 ="";
        String temp_oldVal2 ="";
        DataPoint[] dataPoints = new DataPoint[3];
        int i =0;
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            //toastMessage("Datasnapshot is getting new Temperature reading.");
            temp_val = ds.getValue().toString();
            //temp_oldVal1 = ds.getValue().toString();
            //temp_oldVal2 = ds.getValue().toString();
            Temp_UserInformation userInformationTemp =  new Temp_UserInformation();
            //Temp_UserInformation userInformationOldTemp1 = new Temp_UserInformation();
            //Temp_UserInformation userInformationOldTemp2 = new Temp_UserInformation();
            userInformationTemp.setTemperature("" + temp_val);
            userInformationTemp.setOldValue(""+ temp_oldVal1);
            userInformationTemp.setEvenOlder(""+ temp_oldVal2);
            temp.setText(userInformationTemp.getTemperature().toString());
            dataPoints[i++] = (new DataPoint((i+1)*10, Double.parseDouble(temp_val)));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        tempGraph.removeAllSeries();
        tempGraph.addSeries(series);

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, TemperatureScreen.class);
    }

   // private void toastMessage(String message){
        //Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    //}
}

package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeScreen extends AppCompatActivity {



    SharedPreferences LastSelect;
    SharedPreferences.Editor editor;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private Spinner spinner;
    private ArrayAdapter<List> adapter;
    private List<String> list;
    String deviceName;

    int LastClick;


    FirebaseAuth auth;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);

        spinner = (Spinner) findViewById(R.id.spinner);
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);



        LastSelect = getSharedPreferences("LastSetting",Context.MODE_PRIVATE);

        editor = LastSelect.edit();

        LastClick = LastSelect.getInt("LastClick",0);




         list = new ArrayList<>();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Member/" + auth.getUid() + "/Devices");

        System.out.println("The device name is --------------------" + auth.getUid());

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("-----------------------------------SnapShot loop -----------------------------------------------------");

                    //deviceName = postSnapshot.getValue().toString();
                    deviceName = postSnapshot.getKey().toString();
                    System.out.println("The device name is --------------------" + postSnapshot.getKey());
                    list.add(deviceName);
                }
                CreateSpinner(list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Button tempButton = findViewById(R.id.TemperatureButton);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent THScreenIntent = TemperatureScreen.makeIntent(HomeScreen.this);
                startActivity(THScreenIntent);
                finish();
            }
        });

        Button smokeButton = findViewById(R.id.SmokeButton);
        smokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SmokeScreenIntent = SmokeScreen.makeIntent(HomeScreen.this);
                startActivity(SmokeScreenIntent);
                finish();
            }
        });

        Button MotionButton = findViewById(R.id.MotionButton);
        MotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MotionScreenIntent = MotionScreen.makeIntent(HomeScreen.this);
                startActivity(MotionScreenIntent);
                finish();
            }
        });

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Already at the home screen",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(HomeScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("LastClick",position).commit();
                Toast toast = Toast.makeText(HomeScreen.this, list.get(position), Toast.LENGTH_LONG);
                toast.show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HomeScreen.class);
    }

    public void CreateSpinner (List list) {
        //adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter = new ArrayAdapter<List>(this,R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);
        spinner.setSelection(LastClick);
    }
}

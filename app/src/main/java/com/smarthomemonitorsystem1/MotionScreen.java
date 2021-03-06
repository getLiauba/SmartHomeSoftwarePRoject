package com.smarthomemonitorsystem1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.net.Uri;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MotionScreen extends AppCompatActivity {


    private DatabaseReference myRef;
    public static final String SHARED_PREFS = "sharedPRefs";
    public static final String SWITCH1 = "switch1";

    private Uri mImageUri;
    private String[] imageUrls = new String[] {
      "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "gs://smarthomemonitor-65364.appspot.com/Images/5A2fIApSyJSTtYCvM25aX46Ugy83/1.jpg"
    };


    ViewPager viewPager;

    String memberID;
    ImageAdapter adpater;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    int ison = 0;

    private Switch mswitch;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private boolean SWITCHONOFF;

    List<Uploads> myuploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        memberID = auth.getUid().toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_screen);


        //Hello world
        viewPager = findViewById(R.id.viewPager);
        mswitch = (Switch) findViewById(R.id.switch1);

        myuploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("-----------------------------------SnapShot loop -----------------------------------------------------");

                    Uploads upload = new Uploads();
                    upload.setmImageUrl(postSnapshot.getValue().toString());

                //    Uploads upload = postSnapshot.getValue(Uploads.class);

                    myuploads.add(upload);

                    adpater = new ImageAdapter(MotionScreen.this, myuploads);

                    viewPager.setAdapter(adpater);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MotionScreen.this, databaseError.getMessage(),Toast.LENGTH_SHORT);

            }
        });

        mswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                saveData();





                System.out.println("The TOGGLE BUTTON HAS BEEN CHANGED");
                myRef = FirebaseDatabase.getInstance().getReference("Member/"+ memberID + "/Devices/Lucas's Room/MotionDectection");


                if (mswitch.isChecked()) {


                    myRef.setValue(1);
                    System.out.println("You turned the switch on!");
                } else {

                    myRef.setValue(0);
                    System.out.println("You turned the switch OFF!");
                }


            }
        });



        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
                Intent HomeScreenIntent = HomeScreen.makeIntent(MotionScreen.this);
                startActivity(HomeScreenIntent);
                finish();

            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(MotionScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

        loadData();
        updateView();



    }   //End of On create


    public static Intent makeIntent(Context context){
        return new Intent(context, MotionScreen.class);
    }

    private String getFileExtention(Uri uri){

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH1,mswitch.isChecked());

        editor.apply();
    }

    public  void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SWITCHONOFF = sharedPreferences.getBoolean(SWITCH1,false);
    }

    public void updateView() {
        mswitch.setChecked(SWITCHONOFF);
    }

}


































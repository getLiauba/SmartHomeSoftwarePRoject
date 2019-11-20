package com.smarthomemonitorsystem1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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


    private Uri mImageUri;
    private String[] imageUrls = new String[] {
      "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "gs://smarthomemonitor-65364.appspot.com/Images/5A2fIApSyJSTtYCvM25aX46Ugy83/1.jpg"
    };


    ViewPager viewPager;

    ImageAdapter adpater;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    List<Uploads> myuploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewPager);


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

        FloatingActionButton HomeScreenButton = findViewById(R.id.HomeScreenButton);
        HomeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent HomeScreenIntent = HomeScreen.makeIntent(MotionScreen.this);
                startActivity(HomeScreenIntent);
                finish();
            }
        });

        FloatingActionButton SettingsScreenButton = findViewById(R.id.SettingsScreenButton);
        SettingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreenIntent = SettingsScreen.makeIntent(MotionScreen.this);
                startActivity(SettingsScreenIntent);
                finish();
            }
        });

    }   //End of On create


    public static Intent makeIntent(Context context){
        return new Intent(context, MotionScreen.class);
    }

    private String getFileExtention(Uri uri){

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

}

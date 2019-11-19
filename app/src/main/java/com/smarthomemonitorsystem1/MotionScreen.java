package com.smarthomemonitorsystem1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.net.Uri;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MotionScreen extends AppCompatActivity {


    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    List<Uploads> myuploads;

   // private List

    private String[] imageUrls = new String[] {
      "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "gs://smarthomemonitor-65364.appspot.com/Images/5A2fIApSyJSTtYCvM25aX46Ugy83/1.jpg"
    };


    ViewPager viewPager;
    ImageAdapter adpater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStorageRef = FirebaseStorage.getInstance().getReference("");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();


         viewPager = findViewById(R.id.viewPager);

        //ImageAdapter adpater = new ImageAdapter(this,"This is a string",myuploads);



        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    //Uploads upload = postSnapShot.getValue(Uploads.class);
                    //myuploads.add(upload);


                }

               // adpater = new ImageAdapter(MotionScreen.this,myuploads);
                //viewPager.setAdapter(adpater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MotionScreen.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

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

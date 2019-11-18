package com.smarthomemonitorsystem1;

import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImageAdapter extends PagerAdapter {


    private DatabaseReference database;
    private Context mContext;
    private int[] mImageIds = new int[] {R.drawable.background_splash,R.drawable.common_full_open_on_phone};

    ImageAdapter(Context context) {

        mContext = context;
        getDatabase();
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView,0);
        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }



    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance().getReference().child("Member").child("5A2fIApSyJSTtYCvM25aX46Ugy83");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String val = dataSnapshot.child("numPhoto").getValue().toString();
                System.out.println(" ---------------------------IS THIS RUNNING---------------------------------" + Integer.parseInt(val));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }








}

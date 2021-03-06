package com.smarthomemonitorsystem1;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import androidx.viewpager.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {

    private Context context1;

    private String[] imageUrls;

    private List<Uploads> mUploads;


    public ImageAdapter(Context context, List<Uploads> uploads) {
        context1 = context;
        mUploads =  uploads;
    }

    @Override
    public int getCount() {
        return mUploads.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        Uploads uploadCurrent = mUploads.get(position);
        ImageView imageview = new ImageView(context1);


        Picasso.get().load(uploadCurrent.getmImageUrl())
                .fit()
                .centerCrop().into(imageview);


       // Picasso.get()
       //         .load(mUploads.get(position).getmImageUrl()).fit().centerCrop().into(imageview);


      //  Picasso.get()
      //          .load(uploadCurrent.getmImageUrl())
      //          .fit()
      //          .centerCrop().into(imageview);


        container.addView(imageview,0);

        return imageview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ImageView) object);

    }
}

package com.smarthomemonitorsystem1;

import java.util.List;

public class Uploads {


    private String mName;
    private String mImageUrl;

    List<Uploads> myuploads;


    public Uploads() {

        //Empty constructor do not delete.
    }

    public Uploads(String uploads) {


        mImageUrl = uploads;

    }

//
//    public Uploads(List<Uploads> uploads) {
//
//
//        //mImageUrl = imageUrl;
//        //myuploads = uploads;
//
//    }

    public String getmImageUrl(){

        return  mImageUrl;
    }

    public void setmImageUrl(String imageUrl) {

        mImageUrl = imageUrl;
    }



}

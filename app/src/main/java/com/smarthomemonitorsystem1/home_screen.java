package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class home_screen extends Fragment {
    private Button temp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //temp=findViewById(R.id.temperature_button);
        //temp.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //openTemperature();
            //}
        //});
        return inflater.inflate(R.layout.home_screen, container, false);
    }

    private void openTemperature() {
        Intent tempScreen = new Intent();
        startActivity(tempScreen);
    }
}

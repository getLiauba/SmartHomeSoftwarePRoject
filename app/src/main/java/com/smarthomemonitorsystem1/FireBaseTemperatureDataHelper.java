package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseTemperatureDataHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String temperature;

    public interface DataStatus{
        void DataIsLoaded(String temperature, String key);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FireBaseTemperatureDataHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("userSignup/XTJUQZsTN4VsRVsKGnkkZMReqr13/-Lt_qAg3gZ7oLyjq8S5q/Temperature");
    }

    public void readTemperature(final DataStatus dataStatus ){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key = new String();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    key = keyNode.getKey();
                    Temperature temp = keyNode.getValue(Temperature.class);
                }
                dataStatus.DataIsLoaded(temperature, key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

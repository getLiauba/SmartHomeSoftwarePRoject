package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText repassword;
    private TextView ltext;
    private EditText email;
    private DatePicker picker;
    private Button register1;

    private FirebaseDatabase database;
    private int numPhotos = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    DataStructure mData;

    FirebaseAuth fAuth;

    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SettingsScreen User");

        findAllViewsfromLayout();

        getDatabase();


        member = new Member();

        fAuth = FirebaseAuth.getInstance();


        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisteration();
            }
        });


        ltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloginscreen();
            }
        });
    }


    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference().child("Member/");

        //String path = "userSignup/" + mAuth.getUid();  // Write to the user account.
        System.out.println("The UID is ------------------------" + mAuth.getUid());

    }

    private void findAllViewsfromLayout() {
        username =findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword= findViewById(R.id.repass);
        email = findViewById(R.id.email2);
        register1 = findViewById(R.id.createA);
        picker = findViewById(R.id.dob);
        ltext = findViewById(R.id.logintext);
    }

    private void startRegisteration() {
        // TODO: Create new users on Firebase.

        int month = picker.getMonth()+1;

        final String registerUsername = String.valueOf(username.getText());
        final String registerdob = String.valueOf(picker.getDayOfMonth()+"/"+month+"/"+picker.getYear());
        final String registerEmail = String.valueOf(email.getText());
        final String registerPassword1 = String.valueOf(password.getText());
        final String registerPassword2 = String.valueOf(repassword.getText());
        //------End of getting values

       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");

        if (registerEmail.length() == 0 || registerPassword1.length() == 0 || registerPassword2.length() == 0 || registerdob.length() == 0 || registerUsername.length() == 0 ){
            Toast.makeText(getApplicationContext(), "No box can be empty",
                    Toast.LENGTH_LONG).show();
            return; // do nothing if empty.
        }
        else if (!registerPassword1.equals(registerPassword2))
        {
            Toast.makeText(getApplicationContext(), "The password does't match, cannot continue",
                    Toast.LENGTH_LONG).show();
        }
        else if (registerPassword1.equals(registerPassword2)) {

            fAuth.createUserWithEmailAndPassword(registerEmail,registerPassword1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        createData(registerUsername, registerdob, registerEmail, registerPassword1, registerPassword2);
                        gotohomeScreen();
                    }
                }
            });

        }

    }


    private void  createData(String username, String password, String password2, String email, String dob){

        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference().child("Member/");

        //String path = "userSignup/" + mAuth.getUid();  // Write to the user account.
        System.out.println("The UID is ------------------------" + mAuth.getUid());



        member.setDob(dob);
        member.setEmail(email);
        member.setPassword(password);
        member.setRepassword(password2);
        member.setUsername(username);
        member.setNumPhoto(1);
       // myRef.push().setValue(member);
        if (mAuth.getUid().toString() != null){

            myRef.child("" + mAuth.getUid().toString()).setValue(member);
        }
    }

    private void writeData(DataStructure mData) {

        // Select one of the following methods to update the data.
        // 1. To set the value of data
        // myRef.setValue(mData);
        // 2. To create a new node on database.
        //  myRef.push().setValue(mData);
        // TODO: Write the data to the database.
        // 3. To create a new node on database and detect if the writing is successful.
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
                gotohomeScreen();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Writing failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void gotohomeScreen() {
        // TODO : Start the read option After login
        getDatabase();
        Intent intent20 = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent20);
        finish();
    }


    public void openloginscreen() {

        Intent intent50 = new Intent(this, LogInScreen.class);
        startActivity(intent50);
        finish();
    }

}

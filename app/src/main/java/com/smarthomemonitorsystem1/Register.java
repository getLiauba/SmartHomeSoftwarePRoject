package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username;
    private EditText password;
    private EditText repassword;
    private TextView ltext;
    private EditText email;
    private DatePicker picker;
    private Button register1;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Signup User");
        findAllViewsfromLayout();
        getDatabase();

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

        String path = "userSignup/" + mAuth.getUid();  // Write to the user account.
        myRef = database.getReference(path);

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

        String registerUsername = String.valueOf(username.getText());
        String registerdob = String.valueOf(picker.getDayOfMonth()+"/"+month+"/"+picker.getYear());
        String registerEmail = String.valueOf(email.getText());
        String registerPassword1 = String.valueOf(password.getText());
        String registerPassword2 = String.valueOf(repassword.getText());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
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

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(registerEmail, registerPassword1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MapleLeaf", "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Create new user worked",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MapleLeaf", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Create new user failed.",
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
        DataStructure mData = createData(registerUsername, registerPassword1, registerPassword2, registerEmail,registerdob);
        writeData(mData);

    }


    private DataStructure createData(String username, String password, String password2, String email, String dob){
        // TODO: Get the timestamp
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(username),
                String.valueOf(password),
                String.valueOf(password2),
                String.valueOf(email),
                String.valueOf(dob),
                timestamp);


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
                openNavbar();

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
        Intent intent20 = new Intent(getApplicationContext(), home_screen.class);
        startActivity(intent20);
        finish();
    }

    public void openNavbar() {

        Intent intent3 = new Intent(this, Navbar.class);
        startActivity(intent3);
        finish();
    }

    public void openloginscreen() {

        Intent intent50 = new Intent(this, MainActivity.class);
        startActivity(intent50);
        finish();
    }


}

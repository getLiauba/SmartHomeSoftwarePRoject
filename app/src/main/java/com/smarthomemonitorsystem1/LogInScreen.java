package com.smarthomemonitorsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInScreen extends AppCompatActivity {

    private int RC_SIGN_IN = 6;
    private  String TAG;
    private TextView ptext;
    private Button loginb;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private Button registerb;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("LogIn");
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        findAllViewsfromLayout();
        handleLogin();
    }

        private void handleLogin() {
            // Initialize Firebase Auth
            mAuth = FirebaseAuth.getInstance();
            loginb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
                }
            });

            registerb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openregister();
            }
            });

            ptext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openforgotpassword1();
                }
            });
        }

        private void findAllViewsfromLayout() {

            ptext = findViewById(R.id.pButton);
            loginb = findViewById(R.id.loginb);
            email = findViewById(R.id.email1);
            password = findViewById(R.id.password1);
            registerb = findViewById(R.id.registerb);
        }

        private void loginUser(String email, String password){
            // TODO: Login with Email and Password on Firebase.
            if (email.length()==0 || password.length()==0){
                Toast.makeText(getApplicationContext(), "Email and/or password cannot be empty",
                        Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MapleLeaf", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();



                                Intent mainScreenIntent = HomeScreen.makeIntent(LogInScreen.this);
                                startActivity(mainScreenIntent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("MapleLeaf", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

@Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
    }

    public void openforgotpassword1(){
        Intent PasswordResetIntent = new Intent(LogInScreen.this,Forgetpassword1.class);
        startActivity(PasswordResetIntent);
        finish();
    }

    public void openregister() {
        Intent AcountCreationIntent = new Intent(LogInScreen.this, Register.class);
        startActivity(AcountCreationIntent);
        finish();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, LogInScreen.class);
    }
}

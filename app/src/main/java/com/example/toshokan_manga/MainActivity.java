package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    private SignInButton signInButtonggle;
    private GoogleSignInAccount googleSignInAccount;
    private LoginButton facebooklogin;
    private CallbackManager callbackManager;
    String username;
    String email;
    String password;

    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;
    private final static String TAG = "FACELOG";

    public MainActivity() {
    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

      progressBar = findViewById(R.id.progrbar);
        signInButtonggle = findViewById(R.id.google_sign_in);
        findViewById(R.id.text_view_Skip).setOnClickListener(this);
        findViewById(R.id.bttn_IRegister).setOnClickListener(this);
        findViewById(R.id.bttn_ILogin).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        facebooklogin = findViewById(R.id.loginButtonFB);
       callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        facebooklogin.setPermissions(Arrays.asList("email"));


// Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.loginButtonFB);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
// ...
    }



    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }





//Check User

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

           updateUI();

        }
    }





    private void updateUI() {

        Toast.makeText(MainActivity.this,"You are logged in !",Toast.LENGTH_LONG).show();
        Intent HomeP = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(HomeP);
        finish();


    }

    //Buttons Click

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.bttn_IRegister:
            finish();
            startActivity(new Intent(this,RegisterActivity.class));
            break;
        case  R.id.bttn_ILogin:
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            break;
        case  R.id.text_view_Skip:
            finish();
            startActivity(new Intent(this,HomeActivity.class));
            break;
        case R.id.loginButtonFB:
            break;
            

    }
    }


}

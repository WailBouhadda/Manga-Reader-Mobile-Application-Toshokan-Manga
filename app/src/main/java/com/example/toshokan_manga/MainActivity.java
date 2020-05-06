package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    private SignInButton signInButtonggle;
    private GoogleSignInAccount googleSignInAccount;
    private LoginButton facebooklogin;
    private CallbackManager callbackManager;


    private FirebaseAuth mAuth;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.toshokan_manga",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

    }

    public void buttonclickFB(View v)
    {
progressBar.setVisibility(View.VISIBLE);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handelFacbookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Operation Canceled",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void handelFacbookToken(AccessToken accessToken)
    {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            FirebaseUser newuser = mAuth.getCurrentUser();
                            String username = newuser.getDisplayName();
                            String email = newuser.getEmail();

                            User user = new User
                                    (
                                    username,
                                    email
                            );
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, HomeActivity.class));

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode , resultCode, data);

    }

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
            handelFacbookToken(AccessToken.getCurrentAccessToken());
    }
    }
}

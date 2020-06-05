package com.example.toshokan_manga.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.toshokan_manga.HomeActivity;
import com.example.toshokan_manga.R;
import com.example.toshokan_manga.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private CallbackManager mCallbackManager;
    TextView usernametxt, emailtxt;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseUser user;
    private NavigationView navigationView;
    RelativeLayout loggedin;
    RelativeLayout notloggedin;
    Button logoutbttn;
    private Button facebookbttn;
    private final static String TAG = "FACELOG";
    String username;
    String email;
    ProgressBar progressBar;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        loggedin = view.findViewById(R.id.loggedin);
        notloggedin = view.findViewById(R.id.notloggedin);
        logoutbttn = view.findViewById(R.id.logoutbttn);
        usernametxt = (TextView) view.findViewById(R.id.usernametxt);
        emailtxt = (TextView) view.findViewById(R.id.emailtxt);
        progressBar = view.findViewById(R.id.progressBar);





        if (user !=null){
            loggedin.setVisibility(View.VISIBLE);
            loadUserInformation();
            logout();
        }else{
            notloggedin.setVisibility(View.VISIBLE);

        }




        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton =  view.findViewById(R.id.facebookbttn);
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

        return view;

    }
    private void addUser()
    {

        progressBar.setVisibility(View.VISIBLE);
        username = Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName();
        email = mAuth.getCurrentUser().getEmail();
        User user = new User(
                username,
                email
        );


        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressBar.setVisibility(View.GONE);


                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment =  getActivity().getSupportFragmentManager().findFragmentById(R.id.account);
        fragment.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }

                });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        // TODO: Use the ViewModel
    }


    private void updateUI() {
        startActivity(new Intent(getContext(), HomeActivity.class));
        getActivity().finish();
    }




    private void logout() {
        logoutbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                updateUI();

            }
        });

    }

    private void loadUserInformation() {

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                emailtxt.setText(email);
                usernametxt.setText(username);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}

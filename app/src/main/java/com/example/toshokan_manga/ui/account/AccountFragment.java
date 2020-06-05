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
import com.example.toshokan_manga.MainActivity;
import com.example.toshokan_manga.R;
import com.example.toshokan_manga.RegisterActivity;
import com.example.toshokan_manga.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {

    private Button login;
    private AccountViewModel mViewModel;
    TextView usernametxt, emailtxt;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseUser user;
    private NavigationView navigationView;
    RelativeLayout loggedin;
    RelativeLayout notloggedin;
    Button logoutbttn;
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
        login = view.findViewById(R.id.Login_bttn);








        if (user !=null){
            loggedin.setVisibility(View.VISIBLE);
            loadUserInformation();
            logout();
        }else{
            notloggedin.setVisibility(View.VISIBLE);
            login();

        }




        return view;

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

private void login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MainActivity.class));

                getActivity().finish();

            }
        });
}



}

package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshokan_manga.ui.account.AccountFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {

    TextView usernametxt, emailtxt;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseUser user;
    private NavigationView navigationView;
    RelativeLayout loggedin;
    RelativeLayout notloggedin;
    Button logoutbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        loggedin.findViewById(R.id.loggedin);
        notloggedin.findViewById(R.id.notloggedin);
        logoutbttn.findViewById(R.id.logoutbttn);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AccountFragment.newInstance())
                    .commitNow();


            if (user != null){
                loggedin.setVisibility(View.VISIBLE);
                loadUserInformation();
                logout();
            }else{
                notloggedin.setVisibility(View.VISIBLE);
            }


        }
    }

    private void logout() {
    }

    private void loadUserInformation() {

            usernametxt = (TextView) findViewById(R.id.usernametxt);
            emailtxt = (TextView) findViewById(R.id.emailtxt);
            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.child("username").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    emailtxt.setText(email);
                    usernametxt.setText(username);

                    Toast.makeText(Account.this, "Welcome", Toast.LENGTH_LONG).show();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

    }




}
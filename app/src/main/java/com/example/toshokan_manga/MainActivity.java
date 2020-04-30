package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text_view_Skip).setOnClickListener(this);
        findViewById(R.id.bttn_IRegister).setOnClickListener(this);
        findViewById(R.id.bttn_ILogin).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
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
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.bttn_IRegister:
            startActivity(new Intent(this,RegisterActivity.class));
            break;
        case  R.id.bttn_ILogin:
            startActivity(new Intent(this,LoginActivity.class));
            break;
        case  R.id.text_view_Skip:
            finish();
            startActivity(new Intent(this,HomeActivity.class));
            break;
    }
    }
}

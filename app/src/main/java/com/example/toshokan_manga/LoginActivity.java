package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextemail, editTextpassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.text_view_Skip).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editTextemail = (EditText) findViewById(R.id.text_email);
        editTextpassword = (EditText) findViewById(R.id.edit_text_password);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_sign_in).setOnClickListener(this);
        findViewById(R.id.button_back1).setOnClickListener(this);

    }
    private void loginUser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            editTextemail.setError("Email Invalide");
            editTextemail.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextemail.setError("Email Required");
            editTextemail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextpassword.setError("Password Required");
            editTextpassword.requestFocus();
            return;
        }

        if (password.length()<8) {
            editTextpassword.setError("Minimum is 8 characteres");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_back1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button_sign_in:
                loginUser();
                break;
            case  R.id.text_view_Skip:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }

    }


}

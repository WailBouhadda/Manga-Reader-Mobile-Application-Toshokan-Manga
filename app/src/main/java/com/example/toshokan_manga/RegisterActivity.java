package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextusername, editTextemail, editTextpassword, editTextconfirmepassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextusername = (EditText) findViewById(R.id.text_name);
        editTextemail = (EditText) findViewById(R.id.text_email);
        editTextpassword = (EditText) findViewById(R.id.edit_text_password);
        editTextconfirmepassword = (EditText) findViewById(R.id.edit_text_confirmepassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button_register).setOnClickListener(this);

    }
    private void registerUser() {

        String username = editTextusername.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String confirmepassword = editTextconfirmepassword.getText().toString().trim();

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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_register:
                registerUser();
                break;
            case  R.id.button_back2:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}

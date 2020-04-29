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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextusername, editTextemail, editTextpassword, editTextconfirmepassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editTextusername = (EditText) findViewById(R.id.text_name);
        editTextemail = (EditText) findViewById(R.id.text_email);
        editTextpassword = (EditText) findViewById(R.id.edit_text_password);
        editTextconfirmepassword = (EditText) findViewById(R.id.edit_text_confirmepassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.button_back2).setOnClickListener(this);


    }
    private void registerUser() {

        final String username = editTextusername.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();
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
        if(!password.equals(confirmepassword)){
            editTextconfirmepassword.setError("Password Doesn't Match");
            editTextconfirmepassword.requestFocus();
            return;
        }

        


        progressBar.setVisibility(View.VISIBLE);




        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {




                if (task.isSuccessful()){

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
                                Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    });

                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"Account Already Existed",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
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

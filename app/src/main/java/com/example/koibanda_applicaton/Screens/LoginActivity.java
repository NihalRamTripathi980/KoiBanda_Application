package com.example.koibanda_applicaton.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koibanda_applicaton.MainActivity;
import com.example.koibanda_applicaton.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmailAddress,loginPassword;
    Button loginbtn;
    TextView register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        loginEmailAddress=findViewById(R.id.editTextEmailAddress);
        loginPassword=findViewById(R.id.editTextPassword);
        loginbtn=findViewById(R.id.loginbutton);
        register=findViewById(R.id.textview_register);

        mAuth= FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              loginUser();

           }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void loginUser() {

        String email=loginEmailAddress.getText().toString();
        String password=loginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            loginEmailAddress.setError("Email cannot be Empty");
            loginEmailAddress.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        }
        else{
          mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                  if (task.isSuccessful()){
                      Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(LoginActivity.this,MainActivity.class));
                  }
                  else{
                      Toast.makeText(LoginActivity.this, "Login Error" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
        }
    }
}
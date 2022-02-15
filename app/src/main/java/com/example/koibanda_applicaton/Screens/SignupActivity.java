package com.example.koibanda_applicaton.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koibanda_applicaton.MainActivity;
import com.example.koibanda_applicaton.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    Button signupbtn;
    TextView login;
    RadioButton radioConsumer ,radioServiceProvider ;
    RadioGroup radioGroup;
    FirebaseAuth mAuth ;
    EditText signUpEmail ,signUpPassword, userName ,mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupbtn=findViewById(R.id.signupbutton);
        login=findViewById(R.id.textview_login);
        radioConsumer=findViewById(R.id.radioConsumer);
        radioServiceProvider=findViewById(R.id.radioServiceProvider);
        signUpEmail =findViewById(R.id.signUpEmailAddress);
        signUpPassword=findViewById(R.id.signUpPassword);
        userName=findViewById(R.id.name);
        mobileNumber=findViewById(R.id.mobileNumber);


        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        mAuth= FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(view -> {
            createUser();
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

                                         {
                                             @Override
                                             public void onCheckedChanged (RadioGroup group,int checkedId){
                                             if (radioConsumer.isChecked()) {
                                                 signupbtn.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         Intent intent = new Intent(getApplicationContext(), SignUpConsumer.class);
                                                         intent.putExtra("UserName",userName.getText().toString().trim());
                                                         intent.putExtra("MobileNumber",mobileNumber.getText().toString().trim());
                                                         intent.putExtra("email",signUpEmail.getText().toString().trim());
                                                         startActivity(intent);

                                                     }
                                                 });


                                             } else {
                                                 if (radioServiceProvider.isChecked()) {
                                                     signupbtn.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             Intent intent = new Intent(getApplicationContext(), SignUpServiceProvider.class);
                                                             intent.putExtra("UserName",userName.getText().toString().trim());
                                                             intent.putExtra("MobileNumber",mobileNumber.getText().toString().trim());
                                                             intent.putExtra("email",signUpEmail.getText().toString().trim());
                                                             startActivity(intent);

                                                         }
                                                     });
                                                 }
                                             }
                                         }
                                        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void createUser() {

        String email=signUpEmail.getText().toString();
        String password=signUpPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            signUpEmail.setError("Email cannot be Empty");
            signUpEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            signUpPassword.setError("Password cannot be empty");
            signUpPassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                      //  startActivity(new Intent(getApplicationContext(),SignUpServiceProvider.class));

                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Registration Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    reset();
                }
            });
        }
    }

    private void reset() {
        signUpEmail.setText("");
        signUpPassword.setText("");
        userName.setText("");
        mobileNumber.setText("");
        radioServiceProvider.setSelected(false);
        radioConsumer.setSelected(false);

    }
}
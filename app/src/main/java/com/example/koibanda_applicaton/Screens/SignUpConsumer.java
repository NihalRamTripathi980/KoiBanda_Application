package com.example.koibanda_applicaton.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koibanda_applicaton.MainActivity;
import com.example.koibanda_applicaton.R;

public class SignUpConsumer extends AppCompatActivity {

   EditText userName ,address, password ,confirmPassword;
   Button registerBtn;
   TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_consumer);

        userName =findViewById(R.id.consumerUserName);
        address =findViewById(R.id.address);
        password=findViewById(R.id.password);
        confirmPassword =findViewById(R.id.confirmPassword);
        registerBtn=findViewById(R.id.registerBtn);
        login=findViewById(R.id.Login);



        Spinner spin_category = findViewById(R.id.spinner_categoryC);
        Spinner spin_time = findViewById(R.id.spinner_timeC);

        String ConsumerCategory = spin_category.getSelectedItem().toString();
        String ComsumerTime = spin_time.getSelectedItem().toString();

        String consumerUserName = getIntent().getStringExtra("UserName");
        String consumerMobileNumber = getIntent().getStringExtra("MobileNumber");
        String cEmail =getIntent().getStringExtra("email");

        userName.setText(consumerUserName);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             // username=  userName.getText();
                //editText.getText().toString().trim().equalsIgnoreCase("")
              if (userName.getText().toString().trim().equalsIgnoreCase("")){
                  userName.setError("Enter User name");
              }
               else if (address.getText().toString().trim().equalsIgnoreCase("")){
                    address.setError("Please Enter Your Full Address ");
                }
                else if (password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Please Create Password ");
                }
                else if (confirmPassword.getText().toString().trim().equalsIgnoreCase("")){
                    confirmPassword.setError("Confirm Your Password");
                }
                else if (!(password.getText().toString().equals(confirmPassword.getText().toString()))){
                    confirmPassword.setError("Password is not Equal");
                }
           else {
                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(intent);
                 }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpConsumer.this,LoginActivity.class);
                startActivity(i);
            }
        });




        Spinner spinner = findViewById(R.id.spinner_categoryC);
        Spinner spinnerTime = findViewById(R.id.spinner_timeC);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(SignUpConsumer.this, "Select Category",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner for Time
        ArrayAdapter<CharSequence> adapterTime= ArrayAdapter.createFromResource(this,
                R.array.AvailableTime, android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterTime);
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(SignUpConsumer.this, "Select Available Time",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
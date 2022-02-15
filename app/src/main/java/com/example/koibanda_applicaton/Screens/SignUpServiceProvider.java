package com.example.koibanda_applicaton.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.example.koibanda_applicaton.SPDataHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpServiceProvider extends AppCompatActivity  {

    public static final String TAG = "TAG";
    EditText userName ,address, password ,confirmPassword,experience;
    Button registerBtn;
    TextView login;

    FirebaseFirestore firebaseFirestore ;
    FirebaseAuth mAuth;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_service_provider);

        firebaseFirestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        userName =findViewById(R.id.consumerUserName);
        address =findViewById(R.id.address);
        experience=findViewById(R.id.spExperience);
        password=findViewById(R.id.password);
        confirmPassword =findViewById(R.id.confirmPassword);
        registerBtn=findViewById(R.id.registerBtn);
        login=findViewById(R.id.Login);

        Spinner spin_category = findViewById(R.id.spinner_categoryC);
       Spinner spin_time = findViewById(R.id.spinner_timeC);
       Spinner gender = findViewById(R.id.spinner_genderSP);




        String spUserName = getIntent().getStringExtra("UserName");
        userName.setText(spUserName);
        String spMobileNumber = getIntent().getStringExtra("MobileNumber");
        String spEmail =getIntent().getStringExtra("email");

        String spAddress =address.getText().toString();
        String spExperience =experience.getText().toString();







        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpServiceProvider.this,LoginActivity.class);
                startActivity(i);
            }
        });




        Spinner spinner = findViewById(R.id.spinner_categoryC);
        Spinner spinnerTime = findViewById(R.id.spinner_timeC);
        Spinner spinnerGender =findViewById(R.id.spinner_genderSP);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                String spCategory = spin_category.getSelectedItem().toString();

                if (text.equals("Category")){
                    Toast.makeText(SignUpServiceProvider.this, "Select Category", Toast.LENGTH_SHORT).show();
                    spinner.getSelectedView().setBackgroundColor(Color.RED);
                }

                categoryMethod(spCategory);

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
                String spTime = spin_time.getSelectedItem().toString();

                timeMethod(spTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner for Gender

        ArrayAdapter<CharSequence> adapterGender= ArrayAdapter.createFromResource(this,
                R.array.Gender, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                String spGender = gender.getSelectedItem().toString();
               genderMethod(spGender);
                Toast.makeText(SignUpServiceProvider.this, "Select Gender" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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



//                    userID = mAuth.getCurrentUser().getUid();
//                    DocumentReference documentReference =firebaseFirestore.collection("ServiceProviderUsers").document(userID);
//                    Map<String , Object> user = new HashMap<>();
//                    user.put("spUserName",spUserName);
//                    user.put("spMobileNumber",spMobileNumber);
//                    user.put("spEmail",spEmail);
//                    user.put("spCategory",spCategory);
//                   user.put("spTime",spTime);
//                    user.put("spGender",spGender);
//                    user.put("spExperience",spExperience);
//                    user.put("spAddress",spAddress);

                  storeData(spUserName,spAddress,spExperience,"Developer","Morning","Male");

                    startActivity(intent);
                }
            }
        });


    }

    private void categoryMethod(String spCategory)  {

    }

    private void timeMethod( String spTime) {


    }

    private void genderMethod( String spGender) {

    }

    private void storeData(String spUserName,String spAddress,String spExperience,String spCategory,String spTime,String spGender) {


        SPDataHolder obj =new SPDataHolder(spUserName,spAddress,spExperience,spCategory,spTime,spGender);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node =db.getReference("ServiceProvider");

        node.child(spAddress).push().setValue(obj);
    }


}

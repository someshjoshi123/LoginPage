package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.nio.charset.StandardCharsets;

public class RegisterActivity extends AppCompatActivity {

    Button btn_reg, btn_cancel;
    TextView tv_gender;
    String gend_er = "";
    RadioGroup genderGrp;
    RadioButton g_male, g_female;
    EditText et_firstName, et_lastName, et_email, et_pwd;
    DBHelper myDb;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        genderGrp = findViewById(R.id.genderGrp);
        tv_gender = findViewById(R.id.tv_gender);
        g_male = findViewById(R.id.g_male);
        g_female = findViewById(R.id.g_female);
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);

        btn_reg = findViewById(R.id.btn_reg);

        myDb = new DBHelper(this);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validatefName() | !validatelName() | !validateEmail() | !validatePwd()){
                    return;
                }

                String firstname =  et_firstName.getText().toString();
                String lastname =  et_lastName.getText().toString();
                String gender = gend_er;
                String email =  et_email.getText().toString();
                String pwd =  et_pwd.getText().toString();

                if(firstname.equals("") || lastname.equals("") || email.equals("") || pwd.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean emailCheckResult =  myDb.checkemail(email);
                    if(emailCheckResult == false){
                        Boolean regResult = myDb.insertData(firstname,lastname,gender,email,pwd);
                        if(regResult == true){
                            Toast.makeText(RegisterActivity.this, "Registration done successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent( RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "User already exists.\n Please LOGIN!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        genderGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.g_male){
                    gend_er="Male";
                }
                else if(i==R.id.g_female){
                    gend_er="Female";
                }
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private Boolean validatefName(){
        String val =  et_firstName.getText().toString();
        String noWhiteSpace = "\\A\\w{2,20}\\z";

        if(val.isEmpty()){
            et_firstName.setError("First Name cannot be empty");
            return false;
        }

        else if(!val.matches(noWhiteSpace)){
            et_firstName.setError("White spaces are not allowed");
            return false;
        }

        else{
            et_firstName.setError(null);
            return true;
        }
    }

    private Boolean validatelName(){
        String val =  et_lastName.getText().toString();
        String noWhiteSpace = "\\A\\w{2,20}\\z";

        if(val.isEmpty()){
            et_lastName.setError("Last Name cannot be empty");
            return false;
        }

        else if(!val.matches(noWhiteSpace)){
            et_lastName.setError("White spaces are not allowed");
            return false;
        }

        else{
            et_lastName.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val =  et_email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            et_email.setError("Email cannot be empty");
            return false;
        }

        else if (!val.matches(emailPattern)) {
            et_email.setError("Invalid email address");
            return false;
        }

        else{
            et_email.setError(null);
            return true;
        }
    }

    private Boolean validatePwd(){
        String val =  et_pwd.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{8,}" +
                "$";

        if(val.isEmpty()){
            et_pwd.setError("Password cannot be empty");
            return false;
        }

        else if (!val.matches(passwordVal)) {
            et_pwd.setError("Invalid password");
            return false;
        }

        else{
            et_pwd.setError(null);
            return true;
        }
    }
}

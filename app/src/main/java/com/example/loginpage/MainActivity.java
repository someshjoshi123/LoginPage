package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_register, btn_login;
    EditText et_email, et_pwd;
    DBHelper myDb;
    SharedPreferences sp;

    private static final String KEY_EMAIL = "e_mail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        sp = getSharedPreferences("LoginData", MODE_PRIVATE);

        String email = sp.getString(KEY_EMAIL, null);
        if(email != null){
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }

        myDb = new DBHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String pwd = et_pwd.getText().toString();

                if(email.equals("") || pwd.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean loginResult = myDb.checkemailpwd(email,pwd);
                    if(loginResult == true){
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(KEY_EMAIL,et_email.getText().toString());
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
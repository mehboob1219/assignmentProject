package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    private Button loginButton,registerButton;
    private EditText emailField, passwordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginButton = (Button) findViewById(R.id.btn_login);
        registerButton = (Button) findViewById(R.id.btn_signup);


        emailField = (EditText) findViewById(R.id.login_email);
        passwordField = (EditText) findViewById(R.id.login_password);

        databaseHelper dbhelper = databaseHelper.getDB(this);
        sessions ses = new sessions(signup.this,sessions.USER_SESSION);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting the sharedPerferencecs Object


                String email = emailField.getText().toString();
                String pass = passwordField.getText().toString();
            if(dbhelper.UserDaoFun().userLogin(email, pass)){
                int uid  = dbhelper.UserDaoFun().getUserDataOnEmail(email).getId();
                ses.InitializeSession(email,uid);
                System.out.println("Signup Session Test: "+ ses.getUserIdRememberSession());
                Toast.makeText(signup.this, "SuccessFully logged In", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(signup.this, dashboard.class);

                intent.putExtra("user_id",Integer.toString(uid));
                startActivity(intent);
            }
            else
                Toast.makeText(signup.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}
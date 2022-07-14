package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    public int id;
    private Button loginButton,registerButton;
    private EditText name,email,password,repass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //getting the values from the XML file
        name = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.re_pass) ;

        registerButton = (Button) findViewById(R.id.register);
        loginButton = (Button) findViewById(R.id.backtologin);


        databaseHelper dbhelper = databaseHelper.getDB(this);
        sessions ses = new sessions(register.this,sessions.USER_SESSION);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmailId(email.getText().toString().trim())){
                String queryEmail=email.getText().toString();
                String queryName = name.getText().toString();
                String queryPass = password.getText().toString();
                String queryrePass= repass.getText().toString();
                if(! queryPass.equals(queryrePass)){
                    Toast.makeText(register.this, "Passwords not match", Toast.LENGTH_SHORT).show();
                }
                if (dbhelper.UserDaoFun().email_alredy(queryEmail)){
                    Toast.makeText(register.this, "Email Already Taken", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbhelper.UserDaoFun().addUser( new user(queryName,queryEmail,queryPass));


                    ArrayList<user>  allusers = (ArrayList<user>) dbhelper.UserDaoFun().getAllUsers();
                    for (int i=0;i<allusers.size();i++){
                        if(allusers.get(i).getEmail().equals(queryEmail)){
                            id=allusers.get(i).getId();
                        }
                    }
                    Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                    user usObj = new user(queryName,queryEmail,queryPass);
                    Intent intent = new Intent(register.this , dashboard.class);
                    ses.InitializeSession(queryEmail,id);
                    ses.saveLocationInSessions("");
                    intent.putExtra("user_id",Integer.toString(id));
                    startActivity(intent);
                }

                }
                else
                    Toast.makeText(register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this , signup.class));
            }
        });
    }
//    boolean isEmailValid(CharSequence email) {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
//                .matches();
//    }
private boolean isValidEmailId(String email){

    return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
}

}

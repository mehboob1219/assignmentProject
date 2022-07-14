package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.gotologin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignupActivity();
            }
        });


//    clickMeButton= findViewById(R.id.button2);
//    editText = findViewById(R.id.text1);
//        textView3 = findViewById(R.id.textView3);
//    clickMeButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//            String val = editText.getText().toString();
//            if (val.equals("")){
//                Toast.makeText(MainActivity.this, "You entered nothing", Toast.LENGTH_SHORT).show();
//            }
//            else
//                textView3.setText("You entered "+ val);
//                Toast.makeText(MainActivity.this, "You Entered " + val, Toast.LENGTH_SHORT).show();
//        }
//    });
//
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this , signup.class);
        startActivity(intent);
    }
}
package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class display extends AppCompatActivity {
    private Button backToDashboard;
    private TextView searchTextDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
        backToDashboard = (Button) findViewById(R.id.btn_back_dashboard);
        searchTextDisplay = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        String SearchLocation = intent.getStringExtra("search");

        searchTextDisplay.setText(SearchLocation);

        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(display.this , dashboard.class));
            }
        });



    }
}
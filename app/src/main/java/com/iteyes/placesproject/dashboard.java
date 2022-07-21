package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
public class dashboard extends AppCompatActivity {
    private TextView userEmail , userName,userEmailLow,userFullName,myLocation,dateView;
    private Button buttonLocation, btnweather;
    public BroadcastReceiver broadcastReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        broadcastReceiver = new InternetCheckService();
        checkInternet();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userName = (TextView) findViewById(R.id.userName);
        userEmailLow = (TextView) findViewById(R.id.userEmail2);
        userFullName = (TextView) findViewById(R.id.userFullName);
        buttonLocation = (Button) findViewById(R.id.btn_location);
        myLocation = (TextView) findViewById(R.id.myLocation);
        dateView = (TextView) findViewById(R.id.dateView);
        btnweather = (Button) findViewById(R.id.wether_check);
        sessions ses = new sessions(dashboard.this , sessions.USER_SESSION);
        System.out.println("Dashbaord Session Test: "+ ses.getUserIdRememberSession());
        String id =ses.getUserIdRememberSession();
        String location_date = "Updated On Date";
        try{
                location_date = ses.getUpdatedLocation();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("LOcation Date: "+ location_date);
//        System.out.println("PRINT ID : "+id);
        databaseHelper dbhelper = databaseHelper.getDB(this);
        int userId = Integer.parseInt(id);
        userEmail.setText(dbhelper.UserDaoFun().Userdata(userId).getEmail());
        userName.setText(dbhelper.UserDaoFun().Userdata(userId).getName());
        //get user location

//        System.out.println("PRINTING DASHBOARD ERRORR 1 : "+ dbhelper.locationDaoF().getUserLocation(userId).getUserLocation());
        if (dbhelper.locationDaoF().checkAlreadyPresentRecord(userId)){
        myLocation.setText(dbhelper.locationDaoF().getUserLocation(userId).getUserLocation());}

        if (location_date == null)
        dateView.setText("Updated On");
        else
            dateView.setText(location_date);


        //activity content Data

        userEmailLow.setText(dbhelper.UserDaoFun().Userdata(userId).getEmail());
        userFullName.setText(dbhelper.UserDaoFun().Userdata(userId).getName());
        System.out.println(dbhelper.UserDaoFun().Userdata(userId).getEmail());

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                Intent intentView =  new Intent(dashboard.this , PermissionsActivity.class);
                intentView.putExtra("user_id",id);
                ses.saveLocationInSessions(formatter.format(date));
                //intentView.putExtra("location_date",formatter.format(date));
                startActivity(intentView);
            }
        });

        btnweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this , wether.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);

    }
    private void checkInternet() {
        registerReceiver(broadcastReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}
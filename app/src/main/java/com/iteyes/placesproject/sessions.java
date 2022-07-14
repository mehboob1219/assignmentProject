package com.iteyes.placesproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class sessions {
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String USER_SESSION = "USERDETAILS" ;
    public static final String SESSION_EMAIL = "userEmail";
    public static final String SESSION_USERID = "userId";
    public static final String SESSION_LOCATIONUPDATEDON = "last updated location";
//    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//
//    SharedPreferences sharedpreferences;

//    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedpreferences.edit();
      sessions(Context _context , String sessionName){
        context = _context;
        sharedpreferences = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        //shared prference code ends
    }


    public void InitializeSession(String email , int user_id){

        editor.putString(SESSION_EMAIL,email);
        editor.putString(SESSION_USERID, Integer.toString(user_id));
        editor.commit();
    }

    public  void saveLocationInSessions(String loc){
          editor.putString(SESSION_LOCATIONUPDATEDON,loc);
          editor.commit();
    }

    public String getUserIdRememberSession(){
        String userId = sharedpreferences.getString(SESSION_USERID,null);
        return userId;
    }

    public String getUpdatedLocation(){
          return sharedpreferences.getString(SESSION_LOCATIONUPDATEDON,null);
    }
}

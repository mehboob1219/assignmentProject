package com.iteyes.placesproject;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
//
@Database(entities ={user.class , location.class} , exportSchema = false , version = 1)
public abstract class databaseHelper extends RoomDatabase {
    private static final String DB_NAME= "userdb";
    private static databaseHelper instance;


    public static synchronized databaseHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context , databaseHelper.class , DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
public abstract usersDao UserDaoFun();
public abstract locationDao locationDaoF();

}


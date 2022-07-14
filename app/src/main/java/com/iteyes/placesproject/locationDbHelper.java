//package com.iteyes.placesproject;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = location.class , exportSchema = false , version = 1)
//public abstract class locationDbHelper extends RoomDatabase {
//    private static final String DB_NAME= "userLocation";
//    private static databaseHelper instance;
//
//
//    public static synchronized databaseHelper getDB(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context , databaseHelper.class , DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
//        }
//        return instance;
//    }
//
//    public abstract locationDao locationFun();
//
//}
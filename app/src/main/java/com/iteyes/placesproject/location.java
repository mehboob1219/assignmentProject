package com.iteyes.placesproject;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "location")
public class location{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "userLocation")
    private String userLocation;


    public location( int userId, String userLocation) {
        this.userId = userId;
        this.userLocation = userLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
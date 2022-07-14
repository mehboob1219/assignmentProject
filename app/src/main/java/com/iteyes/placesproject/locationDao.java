package com.iteyes.placesproject;
import androidx.room.Insert;
import androidx.room.Index;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface locationDao {

    @Update
    void updateLocation(location loc);

    @Insert
    void addUserLocation(location loc);

    @Query("SELECT EXISTS (SELECT * FROM location WHERE userId=:userID)")
    boolean checkAlreadyPresentRecord(int userID);

    @Query("SELECT * FROM location WHERE userId=:userID")
    location getUserLocation(int userID);
}

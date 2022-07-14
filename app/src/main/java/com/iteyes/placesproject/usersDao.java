package com.iteyes.placesproject;


import androidx.room.Insert;
import androidx.room.Index;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface usersDao {

    @Query("SELECT * FROM user")
    List<user> getAllUsers();

    @Query("SELECT EXISTS (SELECT * FROM user WHERE email=:userEmail)")
    boolean email_alredy(String userEmail);


    @Query("SELECT EXISTS (SELECT * FROM user WHERE email=:userEmail AND password=:userPass)")
    boolean userLogin(String userEmail, String userPass);

    @Query("SELECT * FROM user WHERE id=:userId")
    user Userdata(int userId);

    @Query("SELECT * FROM user WHERE email=:userEmail")
    user getUserDataOnEmail(String userEmail);

    @Insert
    void addUser(user userobj);

    @Update
    void updateUser(user userobj);


}

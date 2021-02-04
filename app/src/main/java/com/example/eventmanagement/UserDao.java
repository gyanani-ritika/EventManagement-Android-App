package com.example.eventmanagement;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM `UserInfo` where email= :email and password= :password")
    UserEntity getUser(String email, String password);

    @Insert
    void insert(UserEntity user);
}

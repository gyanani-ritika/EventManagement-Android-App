package com.example.eventmanagement;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM `Organiser's Info`")
    List<EventEntity> getAll();

    @Query("SELECT * FROM `Organiser's Info` WHERE id = :id")
    EventEntity getDetail(int id);

    @Query("DELETE FROM `Organiser's Info` WHERE id = :id")
    int delete(int id);
//
//    @Query("Update `Organiser's Info` SET Name = :name, Date = :date, FromTime = :fromTime, ToTime = :toTime" +
//            ",Price = :price, Place = :place WHERE id = :id")
//    EventEntity update( int id, int name,int date,int fromTime , int toTime, int price, int place);

    @Update
    void updateEvent(EventEntity event);

    @Insert
    void insert(EventEntity event);

}

package com.example.eventmanagement;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Organiser's Info")
public class EventEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Name")
    private String eventName;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "FromTime")
    private String fromTime;

    @ColumnInfo(name = "ToTime")
    private String toTime;

    @ColumnInfo(name = "Place")
    private String place;

    @ColumnInfo(name = "Price")
    private String price;

    @ColumnInfo(name = "Description")
    private String desc;

    public EventEntity(String eventName, String date, String fromTime, String toTime,
                       String place, String price, String desc) {
        this.eventName = eventName;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.place = place;
        this.price = price;
        this.desc = desc;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getFromTime() { return fromTime; }

    public void setFromTime(String fromTime) { this.fromTime = fromTime; }

    public String getToTime() { return toTime; }

    public void setToTime(String toTime) { this.toTime = toTime; }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }
}

package com.navin.alarmproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_alarm")
public class Alarm {
    @Ignore
    public Alarm(int id, String title, String description, int hour, int minute) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hour = hour;
        this.minute = minute;
    }

    public Alarm( String title, String description, int hour, int minute) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hour = hour;
        this.minute = minute;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "hour")
    private int hour;
    @ColumnInfo(name = "minute")
    private int minute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}

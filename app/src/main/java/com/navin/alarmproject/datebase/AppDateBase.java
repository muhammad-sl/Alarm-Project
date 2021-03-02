package com.navin.alarmproject.datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.navin.alarmproject.models.Alarm;

@Database(entities = Alarm.class ,version = 1 , exportSchema = true)
public abstract  class AppDateBase extends RoomDatabase {

    public abstract IDAO idao();

    public static AppDateBase instance = null;

    public static AppDateBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,AppDateBase.class,"alarm.db").allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

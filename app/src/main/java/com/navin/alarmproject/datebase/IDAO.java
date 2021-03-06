package com.navin.alarmproject.datebase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.navin.alarmproject.models.Alarm;

import java.util.List;

@Dao
public interface IDAO {
    @Insert
    long insert(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("select * from tbl_alarm  order by id desc" )
    List<Alarm> getAlarmList();
}

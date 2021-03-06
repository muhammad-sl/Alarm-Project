package com.navin.alarmproject.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.navin.alarmproject.R;
import com.navin.alarmproject.datebase.AppDateBase;
import com.navin.alarmproject.models.Alarm;
import com.navin.alarmproject.models.Iselection;
import com.navin.alarmproject.services.AlarmService;

import java.util.Calendar;

public class AlarmDialog {
AlarmManager alarmManager;
    Activity activity;
    AppDateBase appDateBase;
    int mhour = 0 ;
    int mminute = 0 ;

    public AlarmDialog(Activity activity ) {
        appDateBase = AppDateBase.getInstance(activity);
        this.activity = activity;
        alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
    }

    public void showAddAlarm(Iselection iselection){

        AlertDialog alertDialog =new AlertDialog.Builder(activity).create();

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.add_alarm,null);
        alertDialog.setView(view);

        AppCompatEditText edt_title = view.findViewById(R.id.alarm_title);
        AppCompatEditText edt_description = view.findViewById(R.id.alarm_description);
        TimePicker timePicker = view.findViewById(R.id.time_picker);
        AppCompatButton btn_add = view.findViewById(R.id.btn_add_alarm);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                mhour = hourOfDay;
                mminute = minute;

            }
        });


        btn_add.setOnClickListener(v -> {
            String title = edt_title.getText().toString();
            String description =edt_description.getText().toString();

            Alarm alarm = new Alarm(title,description,mhour,mminute);
            long result = appDateBase.idao().insert(alarm);

            if(result > 0 ){
                alertDialog.dismiss();
                Toast.makeText(activity, "Alarm Added Succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, AlarmService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, (int) result,intent,0);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY,mhour);
                calendar.set(Calendar.MINUTE,mminute);

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                iselection.isLoading();
            }else{
                Toast.makeText(activity, "Error Creating DateBase", Toast.LENGTH_SHORT).show();
            }
        });


        alertDialog.show();
    }
}

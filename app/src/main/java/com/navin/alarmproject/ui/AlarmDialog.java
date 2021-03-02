package com.navin.alarmproject.ui;

import android.app.Activity;
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

public class AlarmDialog {

    Activity activity;
    AppDateBase appDateBase;
    int mhour = 0 ;
    int mminute = 0 ;

    public AlarmDialog(Activity activity , AppDateBase appDateBase) {
        this.appDateBase =appDateBase;
        this.activity = activity;
    }

    public void showAddAlarm(){

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
            String tilte = edt_title.getText().toString();
            String description =edt_description.getText().toString();
            Alarm alarm = new Alarm(tilte,description,mhour,mminute);

            long result = appDateBase.idao().insert(alarm);

            if(result > 0 ){
                // watched until min 30
            }else{
                Toast.makeText(activity, "Error Creating DateBase", Toast.LENGTH_SHORT).show();
            }
        });


        alertDialog.show();
    }
}

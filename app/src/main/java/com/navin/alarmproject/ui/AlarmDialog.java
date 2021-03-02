package com.navin.alarmproject.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.navin.alarmproject.R;
import com.navin.alarmproject.datebase.AppDateBase;
import com.navin.alarmproject.models.Alarm;

public class AlarmDialog {

    Activity activity;
    AppDateBase appDateBase;

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


        btn_add.setOnClickListener(v -> {
            String tilte = edt_title.getText().toString();
            String description =edt_description.getText().toString();
            Alarm alarm = new Alarm(tilte,description,12,12); //until min 25 watched

            appDateBase.idao().insert(alarm);
        });


        alertDialog.show();
    }
}

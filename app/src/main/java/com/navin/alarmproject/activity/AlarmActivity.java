package com.navin.alarmproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.navin.alarmproject.R;
import com.navin.alarmproject.adapter.AlarmAdapter;
import com.navin.alarmproject.databinding.ActivityAlarmBinding;
import com.navin.alarmproject.datebase.AppDateBase;
import com.navin.alarmproject.models.Iselection;
import com.navin.alarmproject.ui.AlarmDialog;

public class AlarmActivity extends AppCompatActivity {
ActivityAlarmBinding  binding;
AlarmDialog alarmDialog;
AppDateBase appDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmDialog = new AlarmDialog(this);
        appDateBase = AppDateBase.getInstance(this);
        binding = ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingButton.setOnClickListener(v -> {

            alarmDialog.showAddAlarm(new Iselection() {
                @Override
                public void isLoading() {
                    loading();
                }
            });

        });

    }

    @Override
    protected void onResume() {
            super.onResume();
        loading();
    }

    private void loading() {
        binding.recyclerAlarms.setAdapter(new AlarmAdapter(appDateBase.idao().getAlarmList(),getApplicationContext()));
        binding.recyclerAlarms.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
    }
}
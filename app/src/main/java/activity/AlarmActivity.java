package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.navin.alarmproject.R;
import com.navin.alarmproject.databinding.ActivityAlarmBinding;
import com.navin.alarmproject.datebase.AppDateBase;
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

            alarmDialog.showAddAlarm();

        });
    }
}
package com.navin.alarmproject.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.navin.alarmproject.R;
import com.navin.alarmproject.datebase.AppDateBase;
import com.navin.alarmproject.models.Alarm;
import com.navin.alarmproject.services.AlarmService;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmVH> {
AlarmManager alarmManager;
    List<Alarm> alarmList;
    Context context;
    AppDateBase appDateBase;

    public AlarmAdapter(List<Alarm> alarmList, Context context) {
        this.alarmList = alarmList;
        this.context = context;
        appDateBase = AppDateBase.getInstance(context);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @NonNull
    @Override
    public AlarmVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_row,null);
        return new AlarmVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmVH holder, int position) {

        Alarm alarm = alarmList.get(position);

        holder.title.setText(alarm.getTitle());
        holder.description.setText(alarm.getDescription());
        holder.hour.setText(alarm.getHour()+"");
        holder.minute.setText(alarm.getMinute()+"");

        holder.delete_alarm.setOnClickListener(v -> {
            appDateBase.idao().delete(alarm);
            // this will refresh our list and remove alarm
            alarmList.remove(position);
            notifyItemChanged(position);
            notifyItemRangeChanged(position,alarmList.size());

            Intent intent = new Intent(context, AlarmService.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(),intent,0);
            alarmManager.cancel(pendingIntent);

        });

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public class AlarmVH extends RecyclerView.ViewHolder{
    AppCompatTextView title,description;
        AppCompatTextView hour,minute;
        AppCompatImageView delete_alarm;


        public AlarmVH(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.txt_hour);
            minute = itemView.findViewById(R.id.txt_minute);
            title = itemView.findViewById(R.id.txt_title);
            description = itemView.findViewById(R.id.txt_description);
            delete_alarm = itemView.findViewById(R.id.delete_alarm);
        }
    }
}

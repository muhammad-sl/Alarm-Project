package com.navin.alarmproject.services;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.navin.alarmproject.BuildConfig;
import com.navin.alarmproject.models.Application;
import com.navin.alarmproject.webService.ImessageListener;
import com.navin.alarmproject.webService.WebServiceCaller;

public class UpdateService extends Service {
WebServiceCaller webServiceCaller;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Update Service", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

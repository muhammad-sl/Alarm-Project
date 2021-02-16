package com.navin.alarmproject.webService;

import com.navin.alarmproject.models.Application;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("getCurrentVersion.php")
    Call<Application>  getModel();

    @GET("getCurrentVersion.php")
    Call<ResponseBody> getCurrentVersion();
}

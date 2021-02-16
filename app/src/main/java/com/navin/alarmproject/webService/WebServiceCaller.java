package com.navin.alarmproject.webService;

import com.navin.alarmproject.models.Application;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceCaller  {

    ApiService apiService;

    public WebServiceCaller(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);

    }


    public void getVersion(ImessageListener listener){

        apiService.getModel().enqueue(new Callback<Application>() {
            @Override
            public void onResponse(Call<Application> call, Response<Application> response) {
                listener.OnSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Application> call, Throwable t) {
            listener.OnFailure(t.getMessage().toString()+"");
            }
        });

    }

    public void getCurrentVersion(ImessageListener listener){

        apiService.getCurrentVersion().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response != null){
                    try {
                        listener.OnSuccess(response.body().string());
                    } catch (IOException e) {
                        listener.OnFailure("");
                        e.printStackTrace();
                    }catch (Exception e){
                        listener.OnFailure("");
                    }
                }else {
                    listener.OnFailure("");
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.OnFailure(t.getMessage().toString()+"");
            }
        });


    }
}

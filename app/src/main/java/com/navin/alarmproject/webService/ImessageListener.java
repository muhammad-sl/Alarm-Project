package com.navin.alarmproject.webService;

public interface ImessageListener<T> {

    public void OnSuccess(T responseMessage);
    public void OnFailure(T errorResponseMessage);
}

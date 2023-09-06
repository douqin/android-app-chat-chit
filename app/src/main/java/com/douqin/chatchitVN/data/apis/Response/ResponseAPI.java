package com.douqin.chatchitVN.data.apis.Response;

public class ResponseAPI<T> {
    public boolean success;
    public String message;
    public T data;

    ResponseAPI(
            boolean success,
            String message, T data
    ) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

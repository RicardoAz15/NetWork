package com.example.retrofit_montepio;

import com.example.retrofit_montepio.ResponsePack.ResponseContent;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;


import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public final class MyService {
    public static final String API_URL =
            "http://";

    public interface Request {

        @POST("192.168.100.49:1001")
        Call<ResponseContent> getContent(
                @HeaderMap Map<String,String> headers,
                @Body JsonObject objectToSend
                );
    }
}



package com.example.unnatiandroidpractical.NetworkUtility;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static WebServices apiService;
    private static String BaseUrl = "http://esptiles.imperoserver.in/api/API/Product/";

    private static Retrofit retrofit = null;

    public static WebServices getApiService() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        if (apiService == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(WebServices.class);
        }
        return apiService;
    }
}

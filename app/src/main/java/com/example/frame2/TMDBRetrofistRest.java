package com.example.frame2;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBRetrofistRest {

    private static final String base_Url = "https://api.themoviedb.org/3/";

    private static OkHttpClient myokHttpClient =new OkHttpClient()
            .newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .build();

    private static Retrofit myretrofit = new Retrofit.Builder()
            .baseUrl(base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(myokHttpClient)
            .build();


    static MooveiServich myMooveiServich = myretrofit.create(MooveiServich.class);






}

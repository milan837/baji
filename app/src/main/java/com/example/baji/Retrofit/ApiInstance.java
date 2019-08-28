package com.example.baji.Retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {
    private static final String BASE_URL="";

    public ApiCalls getInstance(){
        Gson gson=new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        ApiCalls retrofitInstance=new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(ApiCalls.class);
        return retrofitInstance;
    }
}

//logger class for ok http to debugge request and response
class LoggingInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d("OkHttp: ", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d("OkHttp: ", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}

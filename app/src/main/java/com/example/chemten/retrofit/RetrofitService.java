package com.example.chemten.retrofit;

import com.example.chemten.helper.Const;
import com.example.chemten.helper.RegisterResponse;
import com.example.chemten.helper.TokenResponse;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndPoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    public RetrofitService(String token){
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")){
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build();
                return chain.proceed(request);
            });
        }else{
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }
        api = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(ApiEndPoints.class);
    }
    public static RetrofitService getInstance(String token){
        if(service == null){
            service = new RetrofitService(token);
        }else if(!token.equals("")){
            service = new RetrofitService(token);
        }

        return service;
    }
    public Call<TokenResponse> login(String email, String password){
        return api.login(email, password);
    }
    public Call<RegisterResponse> register(String nama, String email, String password){
        return api.register(nama, email, password);
    }
    public Call<JsonObject> logout(){return api.logout();}
}

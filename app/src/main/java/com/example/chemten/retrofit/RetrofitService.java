package com.example.chemten.retrofit;

import com.example.chemten.helper.Const;

import com.example.chemten.model.DataUser;
import com.example.chemten.model.ExerciseScores;
import com.example.chemten.model.Exercises;
import com.example.chemten.model.Lessons;
import com.example.chemten.model.Questions;
import com.example.chemten.model.RegisterResponse;
import com.example.chemten.model.TokenResponse;
import com.example.chemten.model.Users;
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
    public Call<JsonObject> logout() {
        return api.logout();
    }
    public Call<RegisterResponse> register(String nama, String email, String password) {
        return api.register(nama, email, password);
    }
    public Call<Lessons> getLesson() {
            return api.getLesson();
        }
    public Call<Lessons> getLessonDetail(int code) {
            return api.getLessonDetail(code);
    }
    public Call<Exercises> getExerciseDetail(int code) { return api.getExerciseDetail(code);}
    public Call<Users> getUser(){
        return api.getUser();
    }
    public Call<Users> getUserDetails(int code){return api.getUserDetail(code);}
    public Call<Users.Leaderboard> updateLeaderboard(int code){return api.updateLeaderboard(code);}
    public Call<DataUser> getDataUser(String email){return api.getDataUser(email);}
    public Call<ExerciseScores> getExerciseScores(int code){return api.getExerciseScore(code);}
    public Call<ExerciseScores.Exercisescore> createExerciseScore(ExerciseScores.Exercisescore exercisescore){return api.createExerciseScore(exercisescore);}
    public Call<ExerciseScores.Exercisescore> updateExerciseScore(int code, ExerciseScores.Exercisescore exercisescore){return api.updateExerciseScore(code, exercisescore);}
}

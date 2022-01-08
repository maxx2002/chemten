package com.example.chemten.retrofit;

import com.example.chemten.model.DataUser;
import com.example.chemten.model.Exercises;
import com.example.chemten.model.Lessons;
import com.example.chemten.model.RegisterResponse;
import com.example.chemten.model.TokenResponse;
import com.example.chemten.model.Users;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("logout")
    Call<JsonObject> logout();

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @GET("lesson")
    Call<Lessons> getLesson();

    @GET("lesson/{lesson}")
    Call<Lessons> getLessonDetail(@Path("lesson") int code);

    @GET("exercise/{exercise}")
    Call<Exercises> getExerciseDetail(@Path("exercise") int code);

    @GET("leaderboard")
    Call<Users> getUser();

    @GET("leaderboard/{leaderboard}")
    Call<Users> getUserDetail(@Path("leaderboard") int code);

    @GET("users/{email}")
    Call<DataUser> getDataUser(@Path("email") String email);
}

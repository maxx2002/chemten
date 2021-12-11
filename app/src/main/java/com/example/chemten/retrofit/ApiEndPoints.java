package com.example.chemten.retrofit;

import com.example.chemten.model.Lesson;
import com.example.chemten.model.RegisterResponse;
import com.example.chemten.model.TokenResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @GET("lessons")
    Call<Lesson> getLesson();

    @GET("lessons/{lesson}")
    Call<Lesson> getLessonDetail(@Path("lesson") String code);

    @POST("lessons")
    Call<Lesson.Lessons> createLesson(@Body Lesson.Lessons lesson);

    @PUT("lessons/{lesson}")
    Call<Lesson.Lessons> editLesson(@Path("lesson") String code, @Body Lesson.Lessons lesson);

    @DELETE("courses/{course}")
    Call<Lesson> deleteLesson(@Path("course") String code);

    @POST("logout")
    Call<JsonObject> logout();

}

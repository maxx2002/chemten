package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.retrofit.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.chemten.model.Lessons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonRepository {
    private static LessonRepository lessonRepository;
    private RetrofitService apiService;
    private static final String TAG = "LessonRepository";

    private LessonRepository(String token) {
        Log.d(TAG, "token: "+ token);
        apiService = RetrofitService.getInstance(token);
    }

    public static LessonRepository getInstance(String token) {
        if (lessonRepository == null) {
            lessonRepository = new LessonRepository(token);
        }
        return lessonRepository;
    }

    public synchronized void resetInstance(){
        if (lessonRepository != null){
            lessonRepository = null;
        }else {
            lessonRepository = null;
        }
    }

    public MutableLiveData<Lessons> getLesson() {
        final MutableLiveData<Lessons> listLesson = new MutableLiveData<>();

        apiService.getLesson().enqueue(new Callback<Lessons>() {
            @Override
            public void onResponse(Call<Lessons> call, Response<Lessons> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body().getLesson().size());
                        listLesson.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Lessons> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLesson;
    }

    public MutableLiveData<Lessons> getLessonDetail(int code) {
        final MutableLiveData<Lessons> listLessonDetail = new MutableLiveData<>();

        apiService.getLessonDetail(code).enqueue(new Callback<Lessons>() {
            @Override
            public void onResponse(Call<Lessons> call, Response<Lessons> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLessonDetail.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Lessons> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLessonDetail;
    }
}


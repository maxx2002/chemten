package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Lessons;
import com.example.chemten.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static HomeRepository homeRepository;
    private RetrofitService apiService;
    private static final String TAG = "HomeRepository";

    private HomeRepository(String token) {
        Log.d(TAG, "token: "+ token);
        apiService = RetrofitService.getInstance(token);
    }

    public static HomeRepository getInstance(String token) {
        if (homeRepository == null) {
            homeRepository = new HomeRepository(token);
        }
        return homeRepository;
    }

    public synchronized void resetInstance(){
        if (homeRepository != null){
            homeRepository = null;
        }else {
            homeRepository = null;
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
                        Log.d(TAG, "onResponse" + response.body());
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

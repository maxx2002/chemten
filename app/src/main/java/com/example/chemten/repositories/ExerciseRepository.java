package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Exercises;
import com.example.chemten.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseRepository {
    private static ExerciseRepository exerciseRepository;
    private RetrofitService apiService;
    private final static String TAG = "ExerciseRepository";

    private ExerciseRepository(String token){
        Log.d(TAG, "token: "+token);
        apiService = RetrofitService.getInstance(token);
    }
    public static ExerciseRepository getInstance(String token){
        if(exerciseRepository == null){
            exerciseRepository = new ExerciseRepository(token);
        }
        return exerciseRepository;
    }

    public synchronized void resetInstance(){
        if(exerciseRepository != null){
            exerciseRepository = null;
        }else{
            exerciseRepository = null;
        }
    }

    public MutableLiveData<Exercises> getExerciseDetail(int code){
        final MutableLiveData<Exercises> exerciseDetailList = new MutableLiveData<>();

        apiService.getExerciseDetail(code).enqueue(new Callback<Exercises>() {
            @Override
            public void onResponse(Call<Exercises> call, Response<Exercises> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: " + response.body());
                        exerciseDetailList.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Exercises> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return exerciseDetailList;
    }
}

package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.ExerciseScores;
import com.example.chemten.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseScoreRepository {

    private static ExerciseScoreRepository exerciseScoreRepository;
    private RetrofitService apiService;
    private static final String TAG = "ExerciseScoreRepository";

    private ExerciseScoreRepository(String token) {
        Log.d(TAG, "token: "+ token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ExerciseScoreRepository getInstance(String token) {
        if (exerciseScoreRepository == null) {
            exerciseScoreRepository = new ExerciseScoreRepository(token);
        }
        return exerciseScoreRepository;
    }

    public synchronized void resetInstance(){
        if (exerciseScoreRepository != null){
            exerciseScoreRepository = null;
        }else {
            exerciseScoreRepository = null;
        }
    }

    public MutableLiveData<ExerciseScores> getExerciseScore(int code) {
        final MutableLiveData<ExerciseScores> listExerciseScore = new MutableLiveData<>();

        apiService.getExerciseScores(code).enqueue(new Callback<ExerciseScores>() {
            @Override
            public void onResponse(Call<ExerciseScores> call, Response<ExerciseScores> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listExerciseScore.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExerciseScores> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listExerciseScore;
    }
    public MutableLiveData<ExerciseScores.Exercisescore> updateExerciseScore(int code, ExerciseScores.Exercisescore exercisescore){
        final MutableLiveData<ExerciseScores.Exercisescore> listUpdateExerciseScore = new MutableLiveData<>();
        apiService.updateExerciseScore(code, exercisescore).enqueue(new Callback<ExerciseScores.Exercisescore>() {
            @Override
            public void onResponse(Call<ExerciseScores.Exercisescore> call, Response<ExerciseScores.Exercisescore> response) {
                Log.d(TAG, "onResponse: " + response.body());
                listUpdateExerciseScore.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ExerciseScores.Exercisescore> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUpdateExerciseScore;
    }

    public MutableLiveData<ExerciseScores.Exercisescore> createExerciseScore(ExerciseScores.Exercisescore exercisescore){
        final MutableLiveData<ExerciseScores.Exercisescore> listCreateExerciseScore = new MutableLiveData<>();

        apiService.createExerciseScore(exercisescore).enqueue(new Callback<ExerciseScores.Exercisescore>() {
            @Override
            public void onResponse(Call<ExerciseScores.Exercisescore> call, Response<ExerciseScores.Exercisescore> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse" + response.body());
                        listCreateExerciseScore.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExerciseScores.Exercisescore> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
            }
        });
        return listCreateExerciseScore;
    }
}

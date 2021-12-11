package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.retrofit.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.chemten.model.Lesson;
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

    public MutableLiveData<Lesson> getLesson() {
        final MutableLiveData<Lesson> listLesson = new MutableLiveData<>();

        apiService.getLesson().enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLesson.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLesson;
    }

    public MutableLiveData<Lesson> getLessonDetail(String code) {
        final MutableLiveData<Lesson> listLessonDetail = new MutableLiveData<>();

        apiService.getLessonDetail(code).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listLessonDetail.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listLessonDetail;
    }

    public MutableLiveData<Lesson.Lessons> createLesson(Lesson.Lessons lessons) {
        final MutableLiveData<Lesson.Lessons> listAddLesson = new MutableLiveData<>();

        apiService.createLesson(lessons).enqueue(new Callback<Lesson.Lessons>() {
            @Override
            public void onResponse(Call<Lesson.Lessons> call, Response<Lesson.Lessons> response) {
                Log.d(TAG, "onResponse: "+response.body());
                listAddLesson.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Lesson.Lessons> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listAddLesson;
    }

    public MutableLiveData<Lesson.Lessons> editLesson(String code, Lesson.Lessons lessons) {
        final MutableLiveData<Lesson.Lessons> listEditLesson = new MutableLiveData<>();
        apiService.editLesson(code, lessons).enqueue(new Callback<Lesson.Lessons>() {
            @Override
            public void onResponse(Call<Lesson.Lessons> call, Response<Lesson.Lessons> response) {
                Log.d(TAG, "onResponse: "+response.body());
                listEditLesson.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Lesson.Lessons> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listEditLesson;
    }

    public MutableLiveData<String> deleteLesson(String code) {
        MutableLiveData<String> message = new MutableLiveData<>();

        apiService.deleteLesson(code).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "result: "+msg);
                            message.postValue(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return message;
    }
}


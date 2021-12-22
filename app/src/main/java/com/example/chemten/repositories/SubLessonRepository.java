package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.SubLessons;
import com.example.chemten.retrofit.RetrofitService;

public class SubLessonRepository {
    private static SubLessonRepository subLessonRepository;
    private RetrofitService apiService;
    private static final String TAG = "SubLessonRepository";

    private SubLessonRepository(String token){
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static SubLessonRepository getInstance(String token){
        if(subLessonRepository == null){
            subLessonRepository = new SubLessonRepository(token);
        }

        return subLessonRepository;
    }

    public synchronized void resetInstance(){
        if(subLessonRepository != null){
            subLessonRepository = null;
        }else{
            subLessonRepository = null;
        }
    }

//    public MutableLiveData<SubLessons> getSublesson(){
//        final MutableLiveData<SubLessons> listSublesson = new MutableLiveData<>();
//
//        return null;
//    }
}

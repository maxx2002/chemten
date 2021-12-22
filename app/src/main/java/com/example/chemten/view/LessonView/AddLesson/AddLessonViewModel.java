package com.example.chemten.view.LessonView.AddLesson;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.repositories.LessonRepository;

import com.example.chemten.model.Lessons;

public class AddLessonViewModel extends AndroidViewModel {
    private LessonRepository lessonRepository;
    private static final String TAG = "AddLessonViewModel";

    public AddLessonViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "token: "+token);
        lessonRepository = LessonRepository.getInstance(token);
    }
}


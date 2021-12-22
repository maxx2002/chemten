package com.example.chemten.view.LessonView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.repositories.LessonRepository;

import com.example.chemten.model.Lessons;

public class LessonViewModel extends AndroidViewModel {

    private LessonRepository lessonRepository;
    private static final String TAG = "LessonViewModel";

    public LessonViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: "+token);
        lessonRepository = LessonRepository.getInstance(token);
    }

    //== Begin of view model to get all lesson
    private MutableLiveData<Lessons> resultLesson = new MutableLiveData<>();
    public void getLesson(){
        resultLesson = lessonRepository.getLesson();
    }
    public LiveData<Lessons> getResultLesson() {
        return resultLesson;
    }

    //== Begin of view model to get detail lesson
    private MutableLiveData<Lessons> resultLessonDetail = new MutableLiveData<>();
    public void getLessonDetail(int code) {
        resultLessonDetail = lessonRepository.getLessonDetail(code);
    }
    public LiveData<Lessons> getResultLessonDetail()
    {
        return resultLessonDetail;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        lessonRepository.resetInstance();
    }
}



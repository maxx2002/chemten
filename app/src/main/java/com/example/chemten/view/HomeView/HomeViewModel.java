package com.example.chemten.view.HomeView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Lessons;
import com.example.chemten.repositories.HomeRepository;

public class HomeViewModel extends AndroidViewModel {
    private HomeRepository homeRepository;
    private static final String TAG = "HomeViewModel";

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: "+token);
        homeRepository = HomeRepository.getInstance(token);
    }

    //== Begin of view model to get all lesson
    private MutableLiveData<Lessons> resultLesson = new MutableLiveData<>();
    public void getLesson(){
        resultLesson = homeRepository.getLesson();
    }
    public LiveData<Lessons> getResultLesson() {
        return resultLesson;
    }

    //== Begin of view model to get detail lesson
    private MutableLiveData<Lessons> resultLessonDetail = new MutableLiveData<>();
    public void getLessonDetail(int code) {
        resultLessonDetail = homeRepository.getLessonDetail(code);
    }
    public LiveData<Lessons> getResultLessonDetail()
    {
        return resultLessonDetail;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        homeRepository.resetInstance();
    }
}

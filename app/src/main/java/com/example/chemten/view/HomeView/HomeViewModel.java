package com.example.chemten.view.HomeView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.DataUser;
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

    private MutableLiveData<DataUser> resultDataUser = new MutableLiveData<>();
    public void getDataUser(String email){
        resultDataUser = homeRepository.getUserData(email);
    }
    public LiveData<DataUser> getResultDataUser(){
        return resultDataUser;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        homeRepository.resetInstance();
    }
}

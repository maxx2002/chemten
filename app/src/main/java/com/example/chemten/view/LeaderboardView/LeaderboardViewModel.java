package com.example.chemten.view.LeaderboardView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Users;
import com.example.chemten.repositories.HomeRepository;
import com.example.chemten.repositories.UserRepository;

public class LeaderboardViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private static final String TAG = "LeaderboardViewModel";

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "init: "+token);
        userRepository = UserRepository.getInstance(token);
    }

    //==Begin of viewmodel get user by id
    private MutableLiveData<Users> resultGetUser_id = new MutableLiveData<>();
    public void getUser() {
        resultGetUser_id = userRepository.getUser();
    }
    public LiveData<Users> GetResultGetUser_id(){
        return resultGetUser_id;
    }
    //==End of viewmodel get user by id

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        userRepository.resetInstance();
    }
}


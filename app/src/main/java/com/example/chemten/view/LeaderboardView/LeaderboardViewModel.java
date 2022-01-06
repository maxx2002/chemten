package com.example.chemten.view.LeaderboardView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Users;
import com.example.chemten.repositories.UserRepository;

public class LeaderboardViewModel extends AndroidViewModel {

    private UserRepository repository;

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance();
    }

    //==Begin of viewmodel get user by id
    private MutableLiveData<Users> resultGetUser_id = new MutableLiveData<>();
    public void getUser_id() {
        resultGetUser_id = repository.getUser();
    }
    public LiveData<Users> GetResultGetUser_id(){
        return resultGetUser_id;
    }
    //==End of viewmodel get user by id
}


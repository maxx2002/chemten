package com.example.chemten.view.RegisterView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.RegisterResponse;
import com.example.chemten.repositories.AuthRepository;

public class RegisterViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private static final String TAG="RegisterViewModel";

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }
    public MutableLiveData<RegisterResponse> register(String nama, String email, String password){
        return authRepository.register(nama, email, password);
    }
}
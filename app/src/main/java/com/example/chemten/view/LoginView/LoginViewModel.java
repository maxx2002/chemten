package com.example.chemten.view.LoginView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.helper.TokenResponse;
import com.example.chemten.repositories.AuthRepository;

public class LoginViewModel extends AndroidViewModel {

    private AuthRepository authRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public MutableLiveData<TokenResponse> login(String email, String password){
        return authRepository.login(email, password);
    }

}

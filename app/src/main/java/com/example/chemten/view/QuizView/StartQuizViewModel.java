package com.example.chemten.view.QuizView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.chemten.repositories.AuthRepository;

public class StartQuizViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private static final String TAG="StartQuizViewModel";

    public StartQuizViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }
}

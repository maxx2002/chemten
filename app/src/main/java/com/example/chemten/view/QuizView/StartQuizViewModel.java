package com.example.chemten.view.QuizView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Exercises;
import com.example.chemten.repositories.AuthRepository;
import com.example.chemten.repositories.ExerciseRepository;

public class StartQuizViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;
    private static final String TAG="StartQuizViewModel";

    public StartQuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: " + token);
        exerciseRepository = ExerciseRepository.getInstance(token);
    }

    public MutableLiveData<Exercises> resultExerciseDetail = new MutableLiveData<>();
    public void getExerciseDetail(int code){resultExerciseDetail = exerciseRepository.getExerciseDetail(code);}
    public LiveData<Exercises> getResultExerciseDetail(){return resultExerciseDetail;}
}

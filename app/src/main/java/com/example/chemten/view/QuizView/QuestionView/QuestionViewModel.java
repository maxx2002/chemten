package com.example.chemten.view.QuizView.QuestionView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.ExerciseScores;
import com.example.chemten.model.Exercises;
import com.example.chemten.model.Users;
import com.example.chemten.repositories.ExerciseRepository;
import com.example.chemten.repositories.ExerciseScoreRepository;

public class QuestionViewModel extends AndroidViewModel {
    private ExerciseScoreRepository exerciseScoreRepository;
    private static final String TAG = "QuestionViewModel";

    public QuestionViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Token: " + token);
        exerciseScoreRepository = ExerciseScoreRepository.getInstance(token);
    }
    private MutableLiveData<ExerciseScores> resultGetExerciseScore = new MutableLiveData<>();
    public void getExerciseScore(int code) {
        resultGetExerciseScore = exerciseScoreRepository.getExerciseScore(code);
    }
    public LiveData<ExerciseScores> GetResultGetExerciseScore(){
        return resultGetExerciseScore;
    }

    public MutableLiveData<ExerciseScores.Exercisescore> createExerciseScore(ExerciseScores.Exercisescore exercisescore) {
        return exerciseScoreRepository.createExerciseScore(exercisescore);
    }
    public MutableLiveData<ExerciseScores.Exercisescore> updateExerciseScore(int code, ExerciseScores.Exercisescore exercisescore) {
        return exerciseScoreRepository.updateExerciseScore(code, exercisescore);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        exerciseScoreRepository.resetInstance();
    }
}

package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class ExerciseScores implements Parcelable {

    private List<Exercisescore> exercisescore;

    protected ExerciseScores(Parcel in) {
    }

    public static final Creator<ExerciseScores> CREATOR = new Creator<ExerciseScores>() {
        @Override
        public ExerciseScores createFromParcel(Parcel in) {
            return new ExerciseScores(in);
        }

        @Override
        public ExerciseScores[] newArray(int size) {
            return new ExerciseScores[size];
        }
    };

    public static ExerciseScores objectFromData(String str) {

        return new Gson().fromJson(str, ExerciseScores.class);
    }

    public List<Exercisescore> getExercisescore() {
        return exercisescore;
    }

    public void setExercisescore(List<Exercisescore> exercisescore) {
        this.exercisescore = exercisescore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Exercisescore {
        private int id;
        private int user_id;
        private int exercise_id;
        private int score;

        public Exercisescore(int user_id, int exercise_id, int score){
            this.user_id = user_id;
            this.exercise_id = exercise_id;
            this.score = score;
        }
        public static Exercisescore objectFromData(String str) {

            return new Gson().fromJson(str, Exercisescore.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getExercise_id() {
            return exercise_id;
        }

        public void setExercise_id(int exercise_id) {
            this.exercise_id = exercise_id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}

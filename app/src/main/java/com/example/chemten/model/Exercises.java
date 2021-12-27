package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Exercises implements Parcelable {

    private List<Exercise> exercise;
    private List<Question> question;

    protected Exercises(Parcel in) {
    }

    public static final Creator<Exercises> CREATOR = new Creator<Exercises>() {
        @Override
        public Exercises createFromParcel(Parcel in) {
            return new Exercises(in);
        }

        @Override
        public Exercises[] newArray(int size) {
            return new Exercises[size];
        }
    };

    public static Exercises objectFromData(String str) {

        return new Gson().fromJson(str, Exercises.class);
    }

    public List<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(List<Exercise> exercise) {
        this.exercise = exercise;
    }

    public List<Question> getQuestion(){return question;}

    public void setQuestion(List<Question> question){this.question = question;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Exercise {
        private String id;
        private String exercise_topic;
        private String exercise_level;
        private String exercise_image;
        private String exercise_description;

        public static Exercise objectFromData(String str) {

            return new Gson().fromJson(str, Exercise.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExercise_topic() {
            return exercise_topic;
        }

        public void setExercise_topic(String exercise_topic) {
            this.exercise_topic = exercise_topic;
        }

        public String getExercise_level() {
            return exercise_level;
        }

        public void setExercise_level(String exercise_level) {
            this.exercise_level = exercise_level;
        }

        public String getExercise_image() {
            return exercise_image;
        }

        public void setExercise_image(String exercise_image) {
            this.exercise_image = exercise_image;
        }

        public String getExercise_description() {
            return exercise_description;
        }

        public void setExercise_description(String exercise_description) {
            this.exercise_description = exercise_description;
        }
    }
    public static class Question {
        private int id;
        private int exercise_id;
        private String question_description;
        private String qchoice1;
        private String qchoice2;
        private String qchoice3;
        private String qchoice4;
        private String correctanswer;

        public static Questions.Question objectFromData(String str) {

            return new Gson().fromJson(str, Questions.Question.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExercise_id() {
            return exercise_id;
        }

        public void setExercise_id(int exercise_id) {
            this.exercise_id = exercise_id;
        }

        public String getQuestion_description() {
            return question_description;
        }

        public void setQuestion_description(String question_description) {
            this.question_description = question_description;
        }

        public String getQchoice1() {
            return qchoice1;
        }

        public void setQchoice1(String qchoice1) {
            this.qchoice1 = qchoice1;
        }

        public String getQchoice2() {
            return qchoice2;
        }

        public void setQchoice2(String qchoice2) {
            this.qchoice2 = qchoice2;
        }

        public String getQchoice3() {
            return qchoice3;
        }

        public void setQchoice3(String qchoice3) {
            this.qchoice3 = qchoice3;
        }

        public String getQchoice4() {
            return qchoice4;
        }

        public void setQchoice4(String qchoice4) {
            this.qchoice4 = qchoice4;
        }

        public String getCorrectanswer() {
            return correctanswer;
        }

        public void setCorrectanswer(String correctanswer) {
            this.correctanswer = correctanswer;
        }
    }
}

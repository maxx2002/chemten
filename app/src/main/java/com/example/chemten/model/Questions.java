package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Questions implements Parcelable {

    private List<Question> question;

    protected Questions(Parcel in) {
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public static Questions objectFromData(String str) {

        return new Gson().fromJson(str, Questions.class);
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
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

        public static Question objectFromData(String str) {

            return new Gson().fromJson(str, Question.class);
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

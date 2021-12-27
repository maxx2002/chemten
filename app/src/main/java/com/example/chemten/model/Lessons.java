package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Lessons implements Parcelable {

    private List<Lesson> lesson;
    private List<Sublesson> sublesson;

    protected Lessons(Parcel in) {
    }

    public static final Creator<Lessons> CREATOR = new Creator<Lessons>() {
        @Override
        public Lessons createFromParcel(Parcel in) {
            return new Lessons(in);
        }

        @Override
        public Lessons[] newArray(int size) {
            return new Lessons[size];
        }
    };

    public static Lessons objectFromData(String str) {

        return new Gson().fromJson(str, Lessons.class);
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }

    public List<Sublesson> getSublesson() {
        return sublesson;
    }

    public void setSublesson(List<Sublesson> sublesson) {
        this.sublesson = sublesson;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Lesson {
        private int id;
        private String lesson_topic;
        private String lesson_level;
        private String lesson_description;

        public static Lesson objectFromData(String str) {

            return new Gson().fromJson(str, Lesson.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLesson_topic() {
            return lesson_topic;
        }

        public void setLesson_topic(String lesson_topic) {
            this.lesson_topic = lesson_topic;
        }

        public String getLesson_level() {
            return lesson_level;
        }

        public void setLesson_level(String lesson_level) {
            this.lesson_level = lesson_level;
        }

        public String getLesson_description() {
            return lesson_description;
        }

        public void setLesson_description(String lesson_description) {
            this.lesson_description = lesson_description;
        }
    }
    public static class Sublesson {
        private int id;
        private int lesson_id;
        private String sublesson_topic;
        private String sublesson_image;
        private String sublesson_description;

        public static Lessons.Sublesson objectFromData(String str) {

            return new Gson().fromJson(str, Lessons.Sublesson.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getSublesson_topic() {
            return sublesson_topic;
        }

        public void setSublesson_topic(String sublesson_topic) {
            this.sublesson_topic = sublesson_topic;
        }

        public String getSublesson_image() {
            return sublesson_image;
        }

        public void setSublesson_image(String sublesson_image) {
            this.sublesson_image = sublesson_image;
        }

        public String getSublesson_description() {
            return sublesson_description;
        }

        public void setSublesson_description(String sublesson_description) {
            this.sublesson_description = sublesson_description;
        }
    }
}


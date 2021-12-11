package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Lesson implements Parcelable {
    private List<Lessons> lessons;
    private String message;

    protected Lesson(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    public static Lesson objectFromData(String str) {

        return new Gson().fromJson(str, Lesson.class);
    }

    public List<Lessons> getLesson() {
        return lessons;
    }

    public void setLesson(List<Lessons> lessons) {
        this.lessons = lessons;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Lessons {
        private String lesson_id;
        private String lesson_topic;
        private String lesson_level;
        private String lesson_image;
        private String lesson_description;

        public Lessons(String lesson_id, String lesson_topic, String lesson_level, String lesson_image, String lesson_description) {
            this.lesson_id = lesson_id;
            this.lesson_topic = lesson_topic;
            this.lesson_level = lesson_level;
            this.lesson_image = lesson_image;
            this.lesson_description = lesson_description;
        }

        public static Lessons objectFromData(String str) {

            return new Gson().fromJson(str, Lessons.class);
        }

        public String getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(String lesson_id) {
            this.lesson_id = lesson_id;
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

        public String getLesson_image() {
            return lesson_image;
        }
        public void setLesson_image(String lesson_image) {
            this.lesson_image = lesson_image;
        }

        public String getLesson_description() {
            return lesson_description;
        }

        public void setLesson_description(String lesson_description) {
            this.lesson_description = lesson_description;
        }
    }
}


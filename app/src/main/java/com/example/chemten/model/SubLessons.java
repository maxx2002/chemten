package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class SubLessons implements Parcelable {

    private List<Sublesson> sublesson;

    protected SubLessons(Parcel in) {
    }

    public static final Creator<SubLessons> CREATOR = new Creator<SubLessons>() {
        @Override
        public SubLessons createFromParcel(Parcel in) {
            return new SubLessons(in);
        }

        @Override
        public SubLessons[] newArray(int size) {
            return new SubLessons[size];
        }
    };

    public static SubLessons objectFromData(String str) {

        return new Gson().fromJson(str, SubLessons.class);
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

    public static class Sublesson {
        private int id;
        private int lesson_id;
        private String sublesson_topic;
        private String sublesson_image;
        private String sublesson_description;

        public static Sublesson objectFromData(String str) {

            return new Gson().fromJson(str, Sublesson.class);
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

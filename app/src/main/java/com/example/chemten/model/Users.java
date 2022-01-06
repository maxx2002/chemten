package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Users implements Parcelable {

    private List<Leaderboard> leaderboard;

    protected Users(Parcel in) {
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public static Users objectFromData(String str) {

        return new Gson().fromJson(str, Users.class);
    }

    public List<Leaderboard> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Leaderboard {
        private int id;
        private int user_id;
        private String rank_score;

        public static Leaderboard objectFromData(String str) {

            return new Gson().fromJson(str, Leaderboard.class);
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

        public String getRank_score() {
            return rank_score;
        }

        public void setRank_score(String rank_score) {
            this.rank_score = rank_score;
        }
    }
}


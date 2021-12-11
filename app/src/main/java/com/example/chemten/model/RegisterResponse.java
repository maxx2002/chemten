package com.example.chemten.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class RegisterResponse implements Parcelable{
    private String name;
    private String email;
    private String password;

    protected RegisterResponse(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Parcelable.Creator<RegisterResponse> CREATOR = new Parcelable.Creator<RegisterResponse>() {
        @Override
        public RegisterResponse createFromParcel(Parcel in) {
            return new RegisterResponse(in);
        }

        @Override
        public RegisterResponse[] newArray(int size) {
            return new RegisterResponse[size];
        }
    };

    public static RegisterResponse objectFromData(String str) {

        return new Gson().fromJson(str, RegisterResponse.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
    }
}

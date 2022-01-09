package com.example.chemten.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chemten.model.Users;
import com.example.chemten.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository userRepository;
    private RetrofitService apiService;
    private static final String TAG = "UserRepository";

    private UserRepository(String token) {
        Log.d(TAG, "token: "+ token);
        apiService = RetrofitService.getInstance(token);
    }

    public static UserRepository getInstance(String token) {
        if (userRepository == null) {
            userRepository = new UserRepository(token);
        }
        return userRepository;
    }

    public synchronized void resetInstance(){
        if (userRepository != null){
            userRepository = null;
        }else {
            userRepository = null;
        }
    }

    public MutableLiveData<Users> getUser() {
        final MutableLiveData<Users> listUser = new MutableLiveData<>();

        apiService.getUser().enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listUser.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUser;
    }

    public MutableLiveData<Users> getUserDetail(int code) {
        final MutableLiveData<Users> listUserDetail = new MutableLiveData<>();

        apiService.getUserDetails(code).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listUserDetail.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUserDetail;
    }
    public MutableLiveData<Users.Leaderboard> updateLeaderboard(int code){
        final MutableLiveData<Users.Leaderboard> listUpdateLeaderboard = new MutableLiveData<>();
        apiService.updateLeaderboard(code).enqueue(new Callback<Users.Leaderboard>() {
            @Override
            public void onResponse(Call<Users.Leaderboard> call, Response<Users.Leaderboard> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse" + response.body());
                        listUpdateLeaderboard.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Users.Leaderboard> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUpdateLeaderboard;
    }
}



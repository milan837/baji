package com.example.baji.Retrofit;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.example.baji.OnBoardingActivity.SaveInfoFragment.Model.SaveUserInfoPojo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCalls {

    @POST("user/register")
    Call<UserRegisterPojo> registerUser(@Body JsonObject jsonObject);

    @POST("user/saveInfo")
    Call<SaveUserInfoPojo> saveUserInfo(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);
}

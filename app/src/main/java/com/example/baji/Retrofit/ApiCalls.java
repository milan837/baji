package com.example.baji.Retrofit;

import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.ActiveBajiListResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.BajiOnboardResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBajiResponsePojo;
import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.ProfileOnboardingBajiListResponsePojo;
import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.ProfileOpenBajiListResponsePojo;
import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.example.baji.OnBoardingActivity.SaveInfoFragment.Model.SaveUserInfoPojo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCalls {

    @POST("user/register")
    Call<UserRegisterPojo> registerUser(@Body JsonObject jsonObject);

    @POST("user/saveInfo")
    Call<SaveUserInfoPojo> saveUserInfo(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);

    //-------------------------------------- matches api -------------------------------------------
    @POST("openBaji/list")
    Call<OpenBajiResponsePojo> getOpenBajiListResponse(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);

    @POST("onboard/baji/list")
    Call<BajiOnboardResponsePojo> getOnboardBajiListResponse(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);

    @GET("game/match/list")
    Call<GameWithMatchListResponsePojo> getGameAndMatchesList();

    @POST("active/onboardbaji/list")
    Call<ActiveBajiListResponsePojo> getAllActiveBajiList(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);

    @POST("openBaji/list/user")
    Call<ProfileOpenBajiListResponsePojo> getProfileOpenBajiList(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);

    @POST("onboard/baji/list/user")
    Call<ProfileOnboardingBajiListResponsePojo> getProfileOnboardBajiList(@Header ("Authorization") String authKey,@Body JsonObject jsonObject);
}

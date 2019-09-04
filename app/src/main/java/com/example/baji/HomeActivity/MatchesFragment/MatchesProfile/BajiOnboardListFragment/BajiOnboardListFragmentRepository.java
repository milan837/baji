package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.BajiOnboardResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BajiOnboardListFragmentRepository {
    Context context;
    BajiOnboardListFragmentContract.Presenter presenter;

    public BajiOnboardListFragmentRepository(Context context, BajiOnboardListFragmentContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(JsonObject jsonObject){
        Call<BajiOnboardResponsePojo> apiInterface= ApiInstance.getInstance().getOnboardBajiListResponse(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<BajiOnboardResponsePojo>() {
            @Override
            public void onResponse(Call<BajiOnboardResponsePojo> call, Response<BajiOnboardResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+"onboard baji response error code");
                }
            }

            @Override
            public void onFailure(Call<BajiOnboardResponsePojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" onboard baji response api faield");
            }
        });
    }
}

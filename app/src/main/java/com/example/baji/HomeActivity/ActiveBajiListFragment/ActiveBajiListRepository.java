package com.example.baji.HomeActivity.ActiveBajiListFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.ActiveBajiListResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveBajiListRepository {
    Context context;
    ActiveBajiListContract.Presenter presenter;

    public ActiveBajiListRepository(Context context, ActiveBajiListContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ActiveBajiListResponsePojo> apiInterface= ApiInstance.getInstance().getAllActiveBajiList(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<ActiveBajiListResponsePojo>() {
            @Override
            public void onResponse(Call<ActiveBajiListResponsePojo> call, Response<ActiveBajiListResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+" active error body");
                }
            }

            @Override
            public void onFailure(Call<ActiveBajiListResponsePojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" active  api failure");
            }
        });
    }
}

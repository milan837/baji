package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayMentMethodBottomRepository {
    Context context;
    PayMentMethodBottomContract.Presenter presenter;

    public PayMentMethodBottomRepository(Context context, PayMentMethodBottomContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitPaytmCheckSumApi(JsonObject jsonObject){
        Call<PaytmChecksumResponsePojo> apiInterface= ApiInstance.getPhpApiInstance().getPaytmCheckSumApi(
                "mIWrXK50956351186023",
                String.valueOf(jsonObject.get("customerId")),
                String.valueOf(jsonObject.get("orderId")),
                "Retail",
                "WAP",
                String.valueOf(jsonObject.get("amount")),
                "WEBSTAGING",
                "https://securegw-stage.paytm.in/theia/processTransaction"
                );

        apiInterface.enqueue(new Callback<PaytmChecksumResponsePojo>() {
            @Override
            public void onResponse(Call<PaytmChecksumResponsePojo> call, Response<PaytmChecksumResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+" error code paytm checksum");
                }
            }

            @Override
            public void onFailure(Call<PaytmChecksumResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" error code paytm checksum");
            }
        });

    }
}

package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.AcceptBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.android.gms.common.api.Api;
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

    public void hitPaytmCheckSumApi(String amount,String orderId,String customerId){

        Call<PaytmChecksumResponsePojo> apiInterface= ApiInstance.getPhpApiInstance().getPaytmCheckSumApi(
                Constant.PAYTM_MERCHENT_ID,
                customerId,
                orderId ,
                Constant.PAYTM_INDUSTRY_ID,
                Constant.PAYTM_CHANNEL_ID,
                amount,
                Constant.PAYTM_WEBSITE,
                Constant.PAYTM_CALLBACK_URL
                );

        Log.i("milan_log",amount+" => "+orderId+" => "+customerId);

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

    //api for create new baji
    public void hitCreateNewBajiApi(JsonObject jsonObject){
        Call<CreateNewBajiResponsePojo> apiINterface=ApiInstance.getInstance().getCreateNewBajiResponse(Constant.AUTH_KEY,jsonObject);
        apiINterface.enqueue(new Callback<CreateNewBajiResponsePojo>() {
            @Override
            public void onResponse(Call<CreateNewBajiResponsePojo> call, Response<CreateNewBajiResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromCreateBajiApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.code()+" error code create new baji");
                }
            }

            @Override
            public void onFailure(Call<CreateNewBajiResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" failure create new baji");
            }
        });
    }

    //api for accept baji api
    public void hitAcceptBajiApi(JsonObject jsonObject){
        Call<AcceptBajiResponsePojo> apiInterface= ApiInstance.getInstance().getAcceptBajiResponse(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<AcceptBajiResponsePojo>() {
            @Override
            public void onResponse(Call<AcceptBajiResponsePojo> call, Response<AcceptBajiResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromAcceptBajiApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.code()+" error code Accept Baji");
                }
            }

            @Override
            public void onFailure(Call<AcceptBajiResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+"  failure code Accept Baji");
            }
        });
    }
}

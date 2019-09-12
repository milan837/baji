package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.AcceptBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.R;
import com.example.baji.Utils.Constant;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.easypay.actions.CustomProgressDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodBottomFragment extends BottomSheetDialogFragment implements PayMentMethodBottomContract.View {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    PayMentMethodBottomPresenter presenter;
    String orderId="12335451",customerId="cust123";

    String amount,openBajiId,matchId,type,teamId,userId="132";

    ProgressDialog progressDialog;

    public static PaymentMethodBottomFragment getInstance(){
        return new PaymentMethodBottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_payment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
       // userId=sharedPreferences.getString("userId",null);

        amount=getArguments().getString("amount");
        type=getArguments().getString("type");
        matchId=getArguments().getString("matchId");

        Toast.makeText(getActivity(),amount,Toast.LENGTH_LONG).show();
        //for acception open baji
        if(type.equals("accept")){
            openBajiId=getArguments().getString("openBajiId");
        }

        //for creation new baji
        if(type.equals("create")){
            teamId=getArguments().getString("teamId");
        }

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        presenter=new PayMentMethodBottomPresenter(getActivity(),this);
        initViews();
    }

    private void initViews(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPaytmChecksumApi(amount,orderId,customerId);
            }
        });

    }

    private void paytmGateWayIntegration(String checkSum){
        PaytmPGService Service = PaytmPGService.getStagingService();
        HashMap<String, String> paramMap = new HashMap<String,String>();

        paramMap.put( "MID" , Constant.PAYTM_MERCHENT_ID);
        paramMap.put( "CUST_ID" , customerId);
        paramMap.put( "ORDER_ID" , orderId);
        paramMap.put( "INDUSTRY_TYPE_ID" , Constant.PAYTM_INDUSTRY_ID);
        paramMap.put( "CHANNEL_ID" , Constant.PAYTM_CHANNEL_ID);
        paramMap.put( "TXN_AMOUNT" , amount);
        paramMap.put( "WEBSITE" , Constant.PAYTM_WEBSITE);
        paramMap.put( "CALLBACK_URL", Constant.PAYTM_CALLBACK_URL);

        paramMap.put( "CHECKSUMHASH" , checkSum);

        PaytmOrder Order = new PaytmOrder(paramMap);

        Service.initialize(Order, null);

        Service.startPaymentTransaction(getActivity(), true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {

                Toast.makeText(getActivity(),"ui error",Toast.LENGTH_LONG).show();
            }
            public void onTransactionResponse(Bundle inResponse) {

                Log.i("milan_log_payment",inResponse.toString());

                // making call to api after payment is completed sucessfull
                if(type.equals("accept")){
                    callAcceptBajiApi();
                }else if(type.equals("create")){
                    callCreateNewBajiApi();
                }

            }
            public void networkNotAvailable() {

                Toast.makeText(getActivity(),"network not available",Toast.LENGTH_LONG).show();
            }
            public void clientAuthenticationFailed(String inErrorMessage) {

                Toast.makeText(getActivity(),"Authentication Faield",Toast.LENGTH_LONG).show();
            }
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

                Toast.makeText(getActivity(),"error loading",Toast.LENGTH_LONG).show();
            }
            public void onBackPressedCancelTransaction() {

                Toast.makeText(getActivity(),"back press",Toast.LENGTH_LONG).show();
            }
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                Toast.makeText(getActivity(),"cancle",Toast.LENGTH_LONG).show();
            }
        });

    }


    private void callPaytmChecksumApi(String amount,String orderId,String customerId){
        progressDialog.show();
        presenter.sendDataToApi(amount,orderId,customerId);
    }

    private void callCreateNewBajiApi(){
        progressDialog.show();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("matchesId",matchId);
        jsonObject.addProperty("teamId",teamId);
        jsonObject.addProperty("amount",amount);
        presenter.sendDataToCreateBajiApi(jsonObject);
    }

    private void callAcceptBajiApi(){
        progressDialog.show();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("openBajiId",openBajiId);
        jsonObject.addProperty("matchesId",matchId);
        jsonObject.addProperty("userId",userId);
        presenter.sendDataToAcceptBajiApi(jsonObject);
    }

    @Override
    public void displayResponse(PaytmChecksumResponsePojo paytmChecksumResponsePojo) {
        progressDialog.hide();
        paytmGateWayIntegration(paytmChecksumResponsePojo.getCHECKSUMHASH().trim());
    }

    @Override
    public void displayResponseFromAcceptBajiApi(AcceptBajiResponsePojo acceptBajiResponsePojo) {
        progressDialog.hide();
        progressDialog.dismiss();
        Toast.makeText(getActivity(),acceptBajiResponsePojo.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayResponseFromCreateBajiApi(CreateNewBajiResponsePojo createNewBajiResponsePojo) {
        progressDialog.hide();
        progressDialog.dismiss();
        Toast.makeText(getActivity(),createNewBajiResponsePojo.getMessage(),Toast.LENGTH_LONG).show();
    }

}

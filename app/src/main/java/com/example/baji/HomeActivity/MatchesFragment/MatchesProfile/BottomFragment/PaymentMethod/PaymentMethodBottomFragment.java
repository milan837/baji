package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodBottomFragment extends BottomSheetDialogFragment implements PayMentMethodBottomContract.View {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    PayMentMethodBottomPresenter presenter;
    String orderId="ORDER1",customerId="cust123";

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
                callPaytmChecksumApi("100",orderId,customerId);
            }
        });

    }

    private void paytmGateWayIntegration(String checkSum,String orderId){
        PaytmPGService Service = PaytmPGService.getStagingService();
        HashMap<String, String> paramMap = new HashMap<String,String>();
        paramMap.put( "MID" , "mIWrXK50956351186023");

        // Key in your staging and production MID available in your dashboard
        paramMap.put( "ORDER_ID" , orderId);
        paramMap.put( "CUST_ID" , "cust123");
        paramMap.put( "MOBILE_NO" , "8050078113");
        paramMap.put( "EMAIL" , "milanshrestha837@gmail.com");
        paramMap.put( "CHANNEL_ID" , "WAP");
        paramMap.put( "TXN_AMOUNT" , "100.12");
        paramMap.put( "WEBSITE" , "WEBSTAGING");

        // This is the staging value. Production value is available in your dashboard
        paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");

        // This is the staging value. Production value is available in your dashboard
        paramMap.put( "CALLBACK_URL", "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp");
        paramMap.put( "CHECKSUMHASH" , checkSum);
        PaytmOrder Order = new PaytmOrder(paramMap);

        Service.initialize(Order, null);
        Service.startPaymentTransaction(getActivity(), true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {

                Toast.makeText(getActivity(),"ui error",Toast.LENGTH_LONG).show();
            }
            public void onTransactionResponse(Bundle inResponse) {
                Toast.makeText(getActivity(),"response",Toast.LENGTH_LONG).show();
            }
            public void networkNotAvailable() {

                Toast.makeText(getActivity(),"network not available",Toast.LENGTH_LONG).show();
            }
            public void clientAuthenticationFailed(String inErrorMessage) {

                Toast.makeText(getActivity(),"clien auth faield",Toast.LENGTH_LONG).show();
            }
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

                Toast.makeText(getActivity(),"error loafing",Toast.LENGTH_LONG).show();
            }
            public void onBackPressedCancelTransaction() {

                Toast.makeText(getActivity(),"back prese",Toast.LENGTH_LONG).show();
            }
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

                Toast.makeText(getActivity(),"cancle",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void verfiyChecksum(String checksum){

    }

    private void callPaytmChecksumApi(String amount,String orderId,String customerId){

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("amount",amount);
        jsonObject.addProperty("orderId",orderId);
        jsonObject.addProperty("customerId",customerId);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponse(PaytmChecksumResponsePojo paytmChecksumResponsePojo) {
        Toast.makeText(getActivity(),paytmChecksumResponsePojo.getCHECKSUMHASH(),Toast.LENGTH_LONG).show();
        paytmGateWayIntegration(paytmChecksumResponsePojo.getCHECKSUMHASH(),orderId);
    }
}

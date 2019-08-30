package com.example.baji.OnBoardingActivity.OtpFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;

import com.chaos.view.PinView;
import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.HomeActivity;
import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.example.baji.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpFragment extends BaseFragment implements OptFragmentContract.View {

    @BindView(R.id.next_button)
    CardView nextButton;

    @BindView(R.id.back_button)
    RelativeLayout backButton;

    @BindView(R.id.otp_pin_view)
    PinView pinView;

    String verificationId="",phoneNumber="8050078113";
    FirebaseAuth auth;

    OtpFragmentPresenter presenter;

    View fview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        verificationId=getArguments().getString("verificationId");
//        phoneNumber=getArguments().getString("phoneNumber");
        auth=FirebaseAuth.getInstance();
        return inflater.inflate(R.layout.fragment_onbording_opt_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        this.presenter=new OtpFragmentPresenter(getActivity(),this);
        initViews(view);
        this.fview=view;
        Log.i("milan_",verificationId+"=>"+phoneNumber);
    }

    private void initViews(View view){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToApi();
//                String getOtp=pinView.getText().toString().trim();
//                if(getOtp.isEmpty()){
//                    showLongToast(getActivity(),"please enter the opt");
//                }else{
//                    showLongToast(getActivity(),getOtp);
//                    firebaseOtpVerify(getOtp,v);
//                }
            }
        });

    }

    private void firebaseOtpVerify(String otp,View view){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,otp);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showShortToast(getActivity(),"verified sucessfully");
                            sendDataToApi();
                        }else{
                            showShortToast(getActivity(),"Otp Wrong");
                        }
                    }
                });
    }

    private void resendOtp(){

    }

    private void sendDataToApi(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("phoneNumber",phoneNumber);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(UserRegisterPojo userRegisterPojo) {
        Log.i("milan_response", new Gson().toJson(userRegisterPojo));
        if(userRegisterPojo.getUser().getActive() == 1){
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("phoneNumber",userRegisterPojo.getUser().getPhoneNumber());
            editor.putString("authKey",userRegisterPojo.getUser().getAuthKey());
            editor.putString("userId", String.valueOf(userRegisterPojo.getUser().getId()));
            editor.putString("imageUrl",userRegisterPojo.getUser().getImageUrl());
            editor.putString("username",userRegisterPojo.getUser().getUsername());
            editor.apply();
            editor.commit();

            Intent intent=new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);

        }else if (userRegisterPojo.getUser().getActive() == 0){
            Bundle bundle=new Bundle();
            bundle.putString("phoneNumber",phoneNumber);
            Navigation.findNavController(fview).navigate(R.id.otpFragment_to_saveInfoFragment,bundle);
            showShortToast(getActivity(),"Verified Sucessfully");
        }
    }
}

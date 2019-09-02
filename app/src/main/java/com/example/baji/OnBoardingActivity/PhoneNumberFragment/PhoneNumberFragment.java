package com.example.baji.OnBoardingActivity.PhoneNumberFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.HomeActivity;
import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.example.baji.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneNumberFragment extends BaseFragment implements PhoneNumberFragmentContract.View {

    @BindView(R.id.next_button)
    CardView nextButton;

    @BindView(R.id.phone_number)
    EditText phoneNumber;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    FirebaseAuth auth;
    PhoneNumberFragmentPresenter presenter;
    View fview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding_phone_number_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fview=view;
        FirebaseApp.initializeApp(getActivity());
        ButterKnife.bind(this,view);

        auth=FirebaseAuth.getInstance();
        presenter=new PhoneNumberFragmentPresenter(getActivity(),this);


        initViews(view);
    }

    private void initViews(View view){

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPhoneNumber=phoneNumber.getText().toString().trim();
                if(getPhoneNumber.isEmpty()){
                    showLongToast(getActivity(),"Please enter your phone number");
                }else if(getPhoneNumber.length() != 10){
                    showLongToast(getActivity(),"Please enter 10 digit phone number");
                }else{
                    showProgress();
                    firebaseAuthProcess(getPhoneNumber,v);
                }
            }
        });

    }

    private void firebaseAuthProcess(String phoneNumber,View view){

        //handelling callbacks
        mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.i("milan_to",phoneAuthCredential.getSmsCode()+"=>code");
                if(phoneAuthCredential.getSmsCode() == null){
                    auth.signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    dismissProgress();
                                    if(task.isSuccessful()){
                                        showShortToast(getActivity(),"Login sucessfully");
                                        sendDataToApi(phoneNumber);
                                    }else{
                                        showShortToast(getActivity(),"Otp Wrong");
                                    }
                                }
                            });
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                dismissProgress();
                showLongToast(getActivity(),"Verification Faield");
                Log.i("milan_log_v_failed",e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                dismissProgress();
                showLongToast(getActivity(),"Otp Send ");
                Log.i("milan_log_v_failed",s);

                Bundle bundle=new Bundle();
                bundle.putString("verificationId",s);
                bundle.putString("phoneNumber",phoneNumber);

                Navigation.findNavController(fview).navigate(R.id.phoneNumberFragment_to_otpFragment,bundle);
            }
        };

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                getActivity(),        // Activity (for callback binding)
                mCallBack
        );

    }

    private void sendDataToApi(String phoneNumber){
        showProgress();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("phoneNumber",phoneNumber);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(UserRegisterPojo userRegisterPojo) {
        dismissProgress();
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
            bundle.putString("userId", String.valueOf(userRegisterPojo.getUser().getId()));
            bundle.putString("authKey", userRegisterPojo.getUser().getAuthKey());
            Navigation.findNavController(fview).navigate(R.id.phoneNumberFragment_to_saveInfoFragment,bundle);
            showShortToast(getActivity(),"Verified Sucessfully");
        }
    }
}

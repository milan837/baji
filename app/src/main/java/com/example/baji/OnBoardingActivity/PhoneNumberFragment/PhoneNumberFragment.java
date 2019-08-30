package com.example.baji.OnBoardingActivity.PhoneNumberFragment;

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
import com.example.baji.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneNumberFragment extends BaseFragment {

    @BindView(R.id.next_button)
    CardView nextButton;

    @BindView(R.id.phone_number)
    EditText phoneNumber;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding_phone_number_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(getActivity());
        ButterKnife.bind(this,view);
        initViews(view);
    }

    private void initViews(View view){

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.phoneNumberFragment_to_otpFragment);
                String getPhoneNumber=phoneNumber.getText().toString().trim();
                if(getPhoneNumber.isEmpty()){
                    showLongToast(getActivity(),"Please enter your phone number");
                }else if(getPhoneNumber.length() != 10){
                    showLongToast(getActivity(),"Please enter 10 digit phone number");
                }else{
                    Navigation.findNavController(v).navigate(R.id.phoneNumberFragment_to_otpFragment);
                    //firebaseAuthProcess(getPhoneNumber,v);
                }
            }
        });

    }

    private void firebaseAuthProcess(String phoneNumber,View view){

        //handelling callbacks
        mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                showLongToast(getActivity(),"Processing");
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                showLongToast(getActivity(),"Verification Faield");
                Log.i("milan_log_v_failed",e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                showLongToast(getActivity(),"Otp Send ");
                Log.i("milan_log_v_failed",s);

                Bundle bundle=new Bundle();
                bundle.putString("verificationId",s);
                bundle.putString("phoneNumber",phoneNumber);

                Navigation.findNavController(view).navigate(R.id.phoneNumberFragment_to_otpFragment,bundle);
            }
        };

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                getActivity(),        // Activity (for callback binding)
                mCallBack
        );

    }

}

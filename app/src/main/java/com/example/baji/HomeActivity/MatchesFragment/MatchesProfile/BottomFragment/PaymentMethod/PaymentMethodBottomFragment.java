package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PaymentMethodBottomFragment extends BottomSheetDialogFragment {

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
    }
}

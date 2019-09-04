package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.AcceptBaji;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.PaymentMethodBottomFragment;
import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceptBajiBottomFragment extends BottomSheetDialogFragment {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    public static AcceptBajiBottomFragment getInstance(){
        return new AcceptBajiBottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_accept_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentMethodBottomFragment paymentMethodBottomFragment=PaymentMethodBottomFragment.getInstance();
                paymentMethodBottomFragment.show(getChildFragmentManager(),"paymentBottomFragment");
            }
        });
    }
}

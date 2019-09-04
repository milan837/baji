package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.CreateNewBaij;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.PaymentMethodBottomFragment;
import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNewBajiBottomFragment extends BottomSheetDialogFragment {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    @BindView(R.id.amount_spinner)
    Spinner spinner;

    String[] amount={"10","20","30","40","50","60","70","80","90","100"};
    ArrayAdapter<String> amountSpinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_create_new_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
        amountSpinnerAdapter=new ArrayAdapter<>(getActivity(),R.layout.adapter_spinner,amount);
        spinner.setAdapter(amountSpinnerAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentMethodBottomFragment paymentMethodBottomFragment=PaymentMethodBottomFragment.getInstance();
                paymentMethodBottomFragment.show(getChildFragmentManager(),"paymentBottomFragment");
            }
        });
    }

    public static CreateNewBajiBottomFragment getInstance(){
        return new CreateNewBajiBottomFragment();
    }


}
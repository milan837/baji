package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.OnboardBaji;
import com.example.baji.R;
import com.example.baji.Utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BajiOnboardRecyclerViewAdapter extends RecyclerView.Adapter<BajiOnboardRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<OnboardBaji> onboardBajiList;

    public BajiOnboardRecyclerViewAdapter(Context context,List<OnboardBaji> onboardBajiList) {
        this.context = context;
        this.onboardBajiList=onboardBajiList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapater_baji_onborading_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OnboardBaji baji=onboardBajiList.get(position);
        Glide.with(context).load(baji.getTeamOne().getTeamImageUrl()).into(holder.teamOneImage);
        Glide.with(context).load(baji.getTeamOne().getUserImageUrl()).into(holder.teamOneUserImage);
        holder.teamOneUsername.setText(baji.getTeamOne().getUsername());

        Glide.with(context).load(baji.getTeamTwo().getTeamImageUrl()).into(holder.teamTwoImage);
        Glide.with(context).load(baji.getTeamTwo().getUserImageUrl()).into(holder.teamTwoUserImage);
        holder.teamTwoUsername.setText(baji.getTeamTwo().getUsername());

        holder.amount.setText("₹ "+String.valueOf(baji.getAmount()));
        holder.bajiTime.setText(Utils.getDateFromTimeStamp(baji.getTimeStamp()));

    }

    @Override
    public int getItemCount() {
        return onboardBajiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.team_one_image)
        ImageView teamOneImage;

        @BindView(R.id.team_two_image)
        ImageView teamTwoImage;

        @BindView(R.id.team_one_user_image)
        ImageView teamOneUserImage;

        @BindView(R.id.team_two_user_image)
        ImageView teamTwoUserImage;

        @BindView(R.id.team_one_username)
        TextView teamOneUsername;

        @BindView(R.id.team_two_username)
        TextView teamTwoUsername;

        @BindView(R.id.matches_date)
        TextView bajiTime;

        @BindView(R.id.baji_amount)
        TextView amount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

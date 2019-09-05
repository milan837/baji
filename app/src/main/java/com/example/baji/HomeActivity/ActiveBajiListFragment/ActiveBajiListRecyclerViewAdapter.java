package com.example.baji.HomeActivity.ActiveBajiListFragment;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.OnboardBaji;
import com.example.baji.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveBajiListRecyclerViewAdapter extends RecyclerView.Adapter<ActiveBajiListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<OnboardBaji> list;

    public ActiveBajiListRecyclerViewAdapter(Context context, List<OnboardBaji> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_all_baji_onboard_list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OnboardBaji baji=list.get(position);

        holder.amount.setText(String.valueOf(baji.getAmount()));
        holder.gameTitle.setText(baji.getGameTitle());
        holder.matchesDate.setText(baji.getTimeStamp());

        holder.teamOneName.setText(baji.getTeamOne().getTeamName());
        holder.teamOneUsername.setText(baji.getTeamOne().getUsername());
        Glide.with(context).load(baji.getTeamOne().getTeamImageUrl()).into(holder.teamOneImage);
        Glide.with(context).load(baji.getTeamOne().getUserImageUrl()).into(holder.teamOneUserImage);

        holder.teamTwoName.setText(baji.getTeamTwo().getTeamName());
        holder.teamTwoUsername.setText(baji.getTeamTwo().getUsername());
        Glide.with(context).load(baji.getTeamTwo().getTeamImageUrl()).into(holder.teamTwoImage);
        Glide.with(context).load(baji.getTeamTwo().getUserImageUrl()).into(holder.teamTwoUserImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.team_one_name)
        TextView teamOneName;

        @BindView(R.id.team_one_image)
        ImageView teamOneImage;

        @BindView(R.id.team_one_username)
        TextView teamOneUsername;

        @BindView(R.id.team_one_user_image)
        ImageView teamOneUserImage;

        @BindView(R.id.team_two_name)
        TextView teamTwoName;

        @BindView(R.id.team_two_image)
        ImageView teamTwoImage;

        @BindView(R.id.team_two_username)
        TextView teamTwoUsername;

        @BindView(R.id.team_two_user_image)
        ImageView teamTwoUserImage;

        @BindView(R.id.matches_time)
        TextView matchesTime;

        @BindView(R.id.matches_date)
        TextView matchesDate;

        @BindView(R.id.baji_amount)
        TextView amount;

        @BindView(R.id.game_title)
        TextView gameTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}

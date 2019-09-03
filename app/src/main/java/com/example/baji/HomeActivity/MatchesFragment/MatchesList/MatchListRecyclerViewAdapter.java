package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Matches;
import com.example.baji.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListRecyclerViewAdapter extends RecyclerView.Adapter<MatchListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Matches> list;

    public MatchListRecyclerViewAdapter(Context context, List<Matches> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_match_list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teamOneName.setText(list.get(position).getTeamOne());
        holder.teamTwoName.setText(list.get(position).getTeamTwo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_one_name)
        TextView teamOneName;

        @BindView(R.id.team_two_name)
        TextView teamTwoName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

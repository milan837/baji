package com.example.baji.OnBoardingActivity.SaveInfoFragment;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baji.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AwaterListRecyclerViewAdapter extends RecyclerView.Adapter<AwaterListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<String> imageList;
    int selectedPosition=-1;

    public AwaterListRecyclerViewAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_awater_image_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(imageList.get(position)).into(holder.awaterImageView);

        if (selectedPosition == -1) {
            holder.frameLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background_no_frame));
        } else {
            if (selectedPosition == position) {
                holder.frameLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background_white_frame));
            } else {
                holder.frameLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background_no_frame));
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.frameLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background_white_frame));
                if (selectedPosition != position) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = position;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_awater_imageview)
        ImageView awaterImageView;

        @BindView(R.id.awater_frame)
        RelativeLayout frameLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    public String getSelectedAwater(){
        if(selectedPosition != -1){
            return imageList.get(selectedPosition);
        }
        return null;
    }
}

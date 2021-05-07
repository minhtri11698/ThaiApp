package com.trizz.thaiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trizz.thaiapp.R;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    private ArrayList<Integer> imgList;

    public HomeRecyclerAdapter(ArrayList<Integer> imgList) {
        this.imgList = imgList;
    }
    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View topicImgView = inflater.inflate(R.layout.item_main_topic_layout, parent, false);
        return new HomeRecyclerViewHolder(topicImgView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        holder.drawable = imgList.get(position);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }
}

class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {
    public int drawable;
    public ImageView topicImgView;

    public HomeRecyclerViewHolder(@NonNull View view) {
        super(view);
        topicImgView = view.findViewById(R.id.item_image_view);
        topicImgView.setImageResource(drawable);
    }
}

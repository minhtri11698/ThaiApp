package com.trizz.thaiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trizz.thaiapp.Model.TopicModel;
import com.trizz.thaiapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerViewHolder> {

    private final ArrayList<TopicModel> topicList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(TopicModel item);
    }

    public HomeRecyclerAdapter(ArrayList<TopicModel> topicArrayList, OnItemClickListener listener) {
        this.topicList = topicArrayList;
        this.listener = listener;
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
        holder.bind(listener, topicList.get(position));
        holder.topicImg.setImageResource(topicList.get(position).getTopicImage());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView topicImg;

        public HomeRecyclerViewHolder(@NonNull View view) {
            super(view);
            topicImg = view.findViewById(R.id.item_image_view);
        }

        public void bind(final OnItemClickListener listener, TopicModel item) {
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}

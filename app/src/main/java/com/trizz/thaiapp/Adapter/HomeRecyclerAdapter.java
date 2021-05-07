package com.trizz.thaiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trizz.thaiapp.Model.TopicModel;
import com.trizz.thaiapp.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    private final ArrayList<TopicModel> topicList;
    private View.OnClickListener itemListener;

    public HomeRecyclerAdapter(ArrayList<TopicModel> topicArrayList) {
        this.topicList = topicArrayList;
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View topicImgView = inflater.inflate(R.layout.item_main_topic_layout, parent, false);
        HomeRecyclerViewHolder vh = new HomeRecyclerViewHolder(topicImgView);
        vh.itemView.setOnClickListener(itemListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        holder.topicImg.setImageResource(topicList.get(position).getTopicImage());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}

class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView topicImg;

    public HomeRecyclerViewHolder(@NonNull View view) {
        super(view);
        topicImg = view.findViewById(R.id.item_image_view);
    }
}

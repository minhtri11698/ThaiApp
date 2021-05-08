package com.trizz.thaiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trizz.thaiapp.Model.WordModel;
import com.trizz.thaiapp.R;

import java.util.ArrayList;

public class ListWordAdapter extends RecyclerView.Adapter<ListWordAdapter.ListWordViewHolder> {

    private final ArrayList<WordModel> wordModels;
    private final OnWordClickListener listener;

    public interface OnWordClickListener {
        void onItemClick(WordModel item);
    }

    public ListWordAdapter(ArrayList<WordModel> item, OnWordClickListener listener) {
        this.wordModels = item;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View wordView = inflater.inflate(R.layout.item_word_layout, parent, false);
        return new ListWordViewHolder(wordView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListWordViewHolder holder, int position) {
        holder.onBind(wordModels.get(position), listener);
        holder.wordText.setText(wordModels.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return wordModels.size();
    }

    class ListWordViewHolder extends RecyclerView.ViewHolder {
        public TextView wordText;

        public ListWordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.word_tv);
        }

        public void onBind(WordModel item, final OnWordClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}

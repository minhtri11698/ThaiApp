package com.trizz.thaiapp.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trizz.thaiapp.Adapter.ListWordAdapter;
import com.trizz.thaiapp.Dialog.WordDialog;
import com.trizz.thaiapp.Model.TopicModel;
import com.trizz.thaiapp.Model.WordModel;
import com.trizz.thaiapp.R;

public class TopicFragment extends Fragment {

    private final TopicModel item;

    public TopicFragment(TopicModel item) {
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView wordRecycler = requireActivity().findViewById(R.id.list_word_recycler);
        ListWordAdapter adapter = new ListWordAdapter(item.getWordArrayList(), item -> showWordDialog(item));
        wordRecycler.setAdapter(adapter);
        wordRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public void showWordDialog(WordModel item) {
        // Create an instance of the dialog fragment and show it
        WordDialog dialog = new WordDialog(requireActivity(), item);
        dialog.show();
    }

    public static TopicFragment newInstance(TopicModel topicModel) {
        TopicFragment fragment = new TopicFragment(topicModel);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}

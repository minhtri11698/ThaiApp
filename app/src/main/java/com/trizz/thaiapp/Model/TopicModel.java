package com.trizz.thaiapp.Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class TopicModel {
    private Drawable topicImage;
    private ArrayList<WordModel> wordArrayList;

    public TopicModel(Drawable topicImage, ArrayList<WordModel> wordArrayList) {
        this.topicImage = topicImage;
        this.wordArrayList = wordArrayList;
    }

    public Drawable getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(Drawable topicImage) {
        this.topicImage = topicImage;
    }

    public ArrayList<WordModel> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<WordModel> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }
}

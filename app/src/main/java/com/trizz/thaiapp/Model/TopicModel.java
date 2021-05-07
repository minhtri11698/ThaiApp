package com.trizz.thaiapp.Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class TopicModel {
    private Integer topicImage;
    private String topicName;

    public TopicModel(Integer topicImage, String topicName, ArrayList<WordModel> wordArrayList) {
        this.topicImage = topicImage;
        this.topicName = topicName;
        this.wordArrayList = wordArrayList;
    }

    private ArrayList<WordModel> wordArrayList;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(Integer topicImage) {
        this.topicImage = topicImage;
    }

    public ArrayList<WordModel> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<WordModel> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }
}

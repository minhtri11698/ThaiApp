package com.trizz.thaiapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TopicModel implements Parcelable {
    private Integer topicImage;
    private String topicName;
    private ArrayList<WordModel> wordArrayList;

    public TopicModel(Integer topicImage, String topicName, ArrayList<WordModel> wordArrayList) {
        this.topicImage = topicImage;
        this.topicName = topicName;
        this.wordArrayList = wordArrayList;
    }

    protected TopicModel(Parcel in) {
        if (in.readByte() == 0) {
            topicImage = null;
        } else {
            topicImage = in.readInt();
        }
        topicName = in.readString();
    }

    public static final Creator<TopicModel> CREATOR = new Creator<TopicModel>() {
        @Override
        public TopicModel createFromParcel(Parcel in) {
            return new TopicModel(in);
        }

        @Override
        public TopicModel[] newArray(int size) {
            return new TopicModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(topicImage);
        dest.writeString(topicName);
        dest.writeTypedList(wordArrayList);
    }
}

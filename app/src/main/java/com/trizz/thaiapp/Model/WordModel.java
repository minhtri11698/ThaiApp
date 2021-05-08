package com.trizz.thaiapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.poi.ss.usermodel.Picture;

public class WordModel implements Parcelable {
    private String word;
    private String meaning;
    private Picture wordDraw;

    protected WordModel(Parcel in) {
        word = in.readString();
        meaning = in.readString();
    }

    public static final Creator<WordModel> CREATOR = new Creator<WordModel>() {
        @Override
        public WordModel createFromParcel(Parcel in) {
            return new WordModel(in);
        }

        @Override
        public WordModel[] newArray(int size) {
            return new WordModel[size];
        }
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Picture getWordDraw() {
        return wordDraw;
    }

    public void setWordDraw(Picture wordDraw) {
        this.wordDraw = wordDraw;
    }

    public WordModel(String word, String meaning, Picture wordDraw) {
        this.word = word;
        this.meaning = meaning;
        this.wordDraw = wordDraw;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(meaning);
        dest.writeValue(wordDraw);
    }
}

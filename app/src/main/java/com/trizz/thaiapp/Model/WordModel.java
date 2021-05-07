package com.trizz.thaiapp.Model;

import org.apache.poi.ss.usermodel.Picture;

public class WordModel {
    private String word = null;
    private String meaning = null;
    private Picture wordDraw = null;

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
}

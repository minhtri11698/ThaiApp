package com.trizz.thaiapp.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.trizz.thaiapp.Model.WordModel;
import com.trizz.thaiapp.R;

import java.util.Locale;

public class WordDialog extends Dialog implements
        android.view.View.OnClickListener, TextToSpeech.OnInitListener {

    TextToSpeech textToSpeech;

    private WordModel wordModel;
    private Activity activity;
    private ImageView wordImage, close;
    private TextView word, meaning;

    public WordDialog(Activity activity, WordModel model) {
        super(activity);
        this.activity = activity;
        this.wordModel = model;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_word_layout);
        wordImage = findViewById(R.id.word_image);
        close = findViewById(R.id.close_word);
        word = findViewById(R.id.word);
        meaning = findViewById(R.id.meaning);
        wordImage.setOnClickListener(this);
        close.setOnClickListener(this);
        word.setText(wordModel.getWord());
        meaning.setText(wordModel.getMeaning());
        byte[] data = wordModel.getWordDraw().getPictureData().getData();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        wordImage.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.word_image:
                Log.d("Click", "word image clicked");
                speak();
                break;
            case R.id.close_word:
                dismiss();
                break;
            default:
                break;
        }
    }

    private void speak() {
        if(textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }else{
            textToSpeech = new TextToSpeech(activity, this);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int res = textToSpeech.setLanguage(Locale.forLanguageTag("th"));
            //tts.getAvailableLanguages().;
            textToSpeech.setSpeechRate(1f);

            if (res >= TextToSpeech.LANG_AVAILABLE) {
                String toSpeak = wordModel.getWord();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}

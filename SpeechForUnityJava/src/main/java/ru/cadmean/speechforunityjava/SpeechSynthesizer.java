package ru.cadmean.speechforunityjava;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

public class SpeechSynthesizer {

    private boolean isReady;

    private String currentLanguage = "en-US";
    private float currentRate = 1f;

    private TextToSpeech tts;

    public SpeechSynthesizer(Context context, SpeechSynthesizerDelegate delegate) {
        tts = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.SUCCESS) {
                Log.d("Speech", "Failed to initialize text to speech: " + status);
                return;
            }

            tts.setLanguage(Locale.ENGLISH);
            tts.setSpeechRate(currentRate);

            tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    delegate.onSpeakingStarted();
                }

                @Override
                public void onDone(String utteranceId) {
                    delegate.onSpeakingFinished();
                }

                @Override
                public void onError(String utteranceId) {
                    delegate.onSpeakingCancelled();
                }
            });

            isReady = true;
        });
    }

    public boolean isReady() {
        return isReady;
    }

    public void speakText(String text, String lang, float rate) {
        if (!currentLanguage.equals(lang)) {
            currentLanguage = lang;
            tts.setLanguage(new Locale(lang));
        }
        if (currentRate != rate) {
            currentRate = rate;
            tts.setSpeechRate(rate);
        }
        Log.d("Speech", "Speking: " + text);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public boolean isSpeaking() {
        return tts.isSpeaking();
    }

    public void stopSpeaking() {
        Log.d("Speech", "Stopping");
        tts.stop();
    }
}

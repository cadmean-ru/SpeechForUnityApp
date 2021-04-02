package ru.cadmean.speechforunityjava;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

public class UnityUtteranceProgressListener extends UtteranceProgressListener {

    private SpeechSynthesizerDelegate delegate;

    public UnityUtteranceProgressListener(SpeechSynthesizerDelegate delegate) {
        this.delegate = delegate;
    }

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
}

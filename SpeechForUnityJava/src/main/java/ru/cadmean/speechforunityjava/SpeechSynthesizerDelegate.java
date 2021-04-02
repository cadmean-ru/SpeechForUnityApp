package ru.cadmean.speechforunityjava;

public interface SpeechSynthesizerDelegate {
    void onSpeakingStarted();
    void onSpeakingFinished();
    void onSpeakingCancelled();
}

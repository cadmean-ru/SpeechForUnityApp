package ru.cadmean.speechforunityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.cadmean.speechforunityjava.SpeechSynthesizer;
import ru.cadmean.speechforunityjava.SpeechSynthesizerDelegate;

public class MainActivity extends AppCompatActivity {

    private EditText editSpeechText;
    private Button startButton;
    private Button stopButton;

    private SpeechSynthesizer speechSynthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechSynthesizer = new SpeechSynthesizer(getApplicationContext(), new SpeechSynthesizerDelegate() {
            @Override
            public void onSpeakingStarted() {
                Log.d("Bruh", "speaking started");
            }

            @Override
            public void onSpeakingFinished() {
                Log.d("Bruh", "speaking finished");
            }

            @Override
            public void onSpeakingCancelled() {
                Log.d("Bruh", "speaking cancelled");
            }
        });

        editSpeechText = findViewById(R.id.editSpeechText);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(v -> {
            if (!speechSynthesizer.isReady()) {
                Log.d("Bruh", "Not ready");
                return;
            }

            speechSynthesizer.speakText(editSpeechText.getText().toString(), "ru-RU", 1f);
        });

        stopButton.setOnClickListener(v -> {
            speechSynthesizer.stopSpeaking();
        });
    }
}
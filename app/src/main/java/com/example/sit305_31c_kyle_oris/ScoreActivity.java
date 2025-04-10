package com.example.sit305_31c_kyle_oris;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        TextView congratulationsText = findViewById(R.id.congratulationsText);
        TextView scoreText = findViewById(R.id.scoreText);
        Button takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        Button finishButton = findViewById(R.id.finishButton);

        String userName = getIntent().getStringExtra("userName");

        if (userName != null) {
            congratulationsText.setText(getString(R.string.congratulations_message, userName));
        } else {
            congratulationsText.setText(R.string.congratulations_message_generic);
        }

        scoreText.setText(getString(R.string.score_message, score, totalQuestions));

        takeNewQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, QuestionActivity.class);
            startActivity(intent);
        });

        finishButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}

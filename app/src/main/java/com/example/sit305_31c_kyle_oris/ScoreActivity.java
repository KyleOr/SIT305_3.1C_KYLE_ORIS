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

        // Assume a score calculation (for now, using 4/5 as a static value)
        int totalQuestions = 5;
        int score = 4;  // You would replace this with actual score logic

        // Local variables for views
        TextView congratulationsText = findViewById(R.id.congratulationsText);
        TextView scoreText = findViewById(R.id.scoreText);
        Button takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        Button finishButton = findViewById(R.id.finishButton);

        // Get the username from the intent
        String userName = getIntent().getStringExtra("userName");

        // Set the congratulations message dynamically using resources
        if (userName != null) {
            congratulationsText.setText(getString(R.string.congratulations_message, userName));
        } else {
            congratulationsText.setText(R.string.congratulations_message_generic);
        }

        // Set the score text using resources
        scoreText.setText(getString(R.string.score_message, score, totalQuestions));

        // Button actions
        takeNewQuizButton.setOnClickListener(v -> {
            // Logic to start a new quiz
            Intent intent = new Intent(ScoreActivity.this, QuestionActivity.class);
            startActivity(intent);
        });

        finishButton.setOnClickListener(v -> {
            // Logic to finish the quiz (close the app or go back)
            finish(); // Finishes the activity and goes back to the previous one
        });
    }
}

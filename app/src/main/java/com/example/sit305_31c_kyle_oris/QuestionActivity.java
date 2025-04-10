package com.example.sit305_31c_kyle_oris;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QuestionActivity extends AppCompatActivity {

    private int currentQuestion = 0;
    private RadioGroup radioGroup;
    private Button submitButton;
    private boolean answerSubmitted = false;
    private TextView quizTracker; // 👈 New field for quiz tracker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submitButton);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        quizTracker = findViewById(R.id.quizTracker); // 👈 Initialize quiz tracker

        String userName = getIntent().getStringExtra("userName");
        welcomeMessage.setText(getString(R.string.welcome_message, TextUtils.isEmpty(userName) ? "User" : userName));

        loadQuestion(questionText, radioGroup, progressBar, quizTracker); // 👈 Pass tracker

        submitButton.setOnClickListener(v -> {
            if (!answerSubmitted) {
                int selectedOptionId = radioGroup.getCheckedRadioButtonId();
                if (selectedOptionId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedOptionId);
                    int selectedAnswerIndex = radioGroup.indexOfChild(selectedRadioButton);

                    if (selectedAnswerIndex == getCorrectAnswerIndex(currentQuestion)) {
                        changeRowColor(selectedRadioButton, true);
                    } else {
                        changeRowColor(selectedRadioButton, false);
                    }

                    submitButton.setText(R.string.next_button);
                    answerSubmitted = true;
                }
            } else {
                currentQuestion++;
                if (currentQuestion < getTotalQuestions()) {
                    loadQuestion(questionText, radioGroup, progressBar, quizTracker); // 👈 Update tracker
                    submitButton.setText(R.string.submit_button);
                    answerSubmitted = false;
                } else {
                    showScore();
                }
            }
        });
    }

    private void loadQuestion(TextView questionText, RadioGroup radioGroup, ProgressBar progressBar, TextView quizTracker) {
        final String[] questions = getResources().getStringArray(R.array.questions);
        final String[][] options = {
                getResources().getStringArray(R.array.options_1),
                getResources().getStringArray(R.array.options_2)
        };

        if (currentQuestion < getTotalQuestions()) {
            questionText.setText(questions[currentQuestion]);

            radioGroup.clearCheck();
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setText(options[currentQuestion][i]);
                radioButton.setTextColor(ContextCompat.getColor(this, android.R.color.black));
                radioButton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            }

            int progress = (int) ((float) (currentQuestion + 1) / getTotalQuestions() * 100);
            progressBar.setProgress(progress);

            // 👇 Update quiz tracker text properly here
            quizTracker.setText(getString(R.string.quiz_tracker, currentQuestion + 1, getTotalQuestions()));
        }
    }

    private int getTotalQuestions() {
        return getResources().getStringArray(R.array.questions).length;
    }

    private int getCorrectAnswerIndex(int questionIndex) {
        return new int[]{1, 2}[questionIndex]; // Adjust this based on your actual answers
    }

    private void showScore() {
        Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
        startActivity(intent);
    }

    private void changeRowColor(RadioButton selectedRadioButton, boolean isCorrect) {
        int color = isCorrect ? android.R.color.holo_green_dark : android.R.color.holo_red_dark;

        selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, color));
        selectedRadioButton.setTextColor(ContextCompat.getColor(this, android.R.color.white));

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton != selectedRadioButton) {
                radioButton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                radioButton.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            }
        }
    }
}

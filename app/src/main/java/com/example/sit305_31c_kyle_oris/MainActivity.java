package com.example.sit305_31c_kyle_oris;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Local variable for userName
        EditText userName = findViewById(R.id.userName);

        // Local variable for startButton
        Button startButton = findViewById(R.id.startButton);

        // Initially disable the start button
        startButton.setEnabled(false);

        // Add a TextWatcher to listen for text changes in the EditText
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Enable the button only if there is text in the EditText
                startButton.setEnabled(charSequence.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Using lambda expression for the OnClickListener
        startButton.setOnClickListener(v -> {
            // Save the name entered
            String name = userName.getText().toString();

            // Start the next activity if the name is not empty
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("userName", name);  // Pass name to the next activity
                startActivity(intent);
            }
        });
    }
}

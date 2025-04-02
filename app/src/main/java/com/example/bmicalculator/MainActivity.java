package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputWeight;
    private EditText inputHeight;
    private TextView bmiValueText;
    private TextView healthStatusText;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processBMI();
            }
        });
    }

    private void initializeViews() {
        inputWeight = findViewById(R.id.weightInput);
        inputHeight = findViewById(R.id.heightInput);
        btnCalculate = findViewById(R.id.calculateButton);
        bmiValueText = findViewById(R.id.resultText);
        healthStatusText = findViewById(R.id.statusText);
    }

    private void processBMI() {
        String weightStr = inputWeight.getText().toString();
        String heightStr = inputHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            bmiValueText.setText("Proszę uzupełnić dane");
            healthStatusText.setText("");
            return;
        }

        double weightKg = Double.parseDouble(weightStr);
        double heightCm = Double.parseDouble(heightStr);
        double heightMeters = heightCm / 100.0;

        double bmi = weightKg / (heightMeters * heightMeters);
        bmiValueText.setText(String.format("BMI: %.1f", bmi));

        String category;
        if (bmi < 18.5) {
            category = "Niedowaga";
        } else if (bmi < 25.0) {
            category = "Optimum";
        } else if (bmi < 30.0) {
            category = "Nadwaga";
        } else {
            category = "Otyłość";
        }

        healthStatusText.setText("Stan zdrowia: " + category);
    }
}

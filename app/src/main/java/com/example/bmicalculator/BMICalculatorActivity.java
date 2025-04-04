package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    EditText weightInput, heightInput;
    Button calculateBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        resultText = findViewById(R.id.resultText);

        calculateBtn.setOnClickListener(view -> calculateBMI());
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightInput.getText().toString());
            double height = Double.parseDouble(heightInput.getText().toString()) / 100.0;

            double bmi = weight / (height * height);
            String result;

            if (bmi < 18.5) result = "Niedowaga";
            else if (bmi < 25) result = "Optimum";
            else if (bmi < 30) result = "Nadwaga";
            else result = "Otyłość";

            resultText.setText(String.format("BMI: %.2f\nStatus: %s", bmi, result));

        } catch (Exception e) {
            resultText.setText("Błąd: nieprawidłowe dane.");
        }
    }
}

package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    EditText weightInput, heightInput;
    Button calculateBtn;
    TextView resultText;
    Button showRecommendationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        resultText = findViewById(R.id.resultText);
        showRecommendationsButton = findViewById(R.id.showRecommendationsButton);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        showRecommendationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMICalculatorActivity.this, RecommendationActivity.class);
                startActivity(intent);
            }
        });

        // Początkowo ukryj przycisk z zaleceniami
        showRecommendationsButton.setVisibility(View.GONE);
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
            showRecommendationsButton.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Toast.makeText(this, "Wprowadź prawidłowe dane", Toast.LENGTH_SHORT).show();
            showRecommendationsButton.setVisibility(View.GONE);
        }
    }
}

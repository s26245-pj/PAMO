package com.example.bmicalculator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmicalculator.RecipeActivity;

/**
 * Activity that calculates the daily calorie needs using the Mifflin-St Jeor formula.
 */
public class BMICalculatorActivity extends AppCompatActivity {

    EditText weightInput, heightInput;
    Button calculateBtn;
    TextView resultText;
    Button showRecommendationsButton;

    String[] genderOptions = {"Male", "Female"};
    String[] activityLevels = {
            "Sedentary (little or no exercise)",        // 1.2
            "Lightly active (1–3 days/week)",          // 1.375
            "Moderately active (3–5 days/week)",       // 1.55
            "Very active (6–7 days/week)",             // 1.725
            "Super active (twice/day workouts)"        // 1.9
    };
    double[] activityMultipliers = {1.2, 1.375, 1.55, 1.725, 1.9};

    Spinner genderSpinner, activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        resultText = findViewById(R.id.resultText);
        showRecommendationsButton = findViewById(R.id.showRecommendationsButton);
        genderSpinner = findViewById(R.id.genderSpinner);
        activitySpinner = findViewById(R.id.activitySpinner);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderOptions);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> activityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, activityLevels);
        activitySpinner.setAdapter(activityAdapter);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        showRecommendationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMICalculatorActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });

        showRecommendationsButton.setVisibility(View.GONE);
    }

    @SuppressLint("DefaultLocale")
    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightInput.getText().toString());
            double height = Double.parseDouble(heightInput.getText().toString()) / 100.0;

            double bmi = weight / (height * height);
            String result;

            if (bmi < 18.5) result = "Underweight";
            else if (bmi < 25) result = "Normal weight";
            else if (bmi < 30) result = "Overweight";
            else result = "Obesity";

            resultText.setText(String.format("BMI: %.2f\nStatus: %s", bmi, result));
            showRecommendationsButton.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show();
            showRecommendationsButton.setVisibility(View.GONE);
        }
    }
}

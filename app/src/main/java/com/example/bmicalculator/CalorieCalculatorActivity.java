package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class CalorieCalculatorActivity extends AppCompatActivity {

    EditText weightInput, heightInput, ageInput;
    Spinner genderSpinner, activitySpinner;
    Button calculateButton;
    TextView calorieResult;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        ageInput = findViewById(R.id.ageInput);
        genderSpinner = findViewById(R.id.genderSpinner);
        activitySpinner = findViewById(R.id.activitySpinner);
        calculateButton = findViewById(R.id.calculateButton);
        calorieResult = findViewById(R.id.calorieResult);
        showRecommendationsButton = findViewById(R.id.showRecommendationsButton);


        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderOptions);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> activityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, activityLevels);
        activitySpinner.setAdapter(activityAdapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCalories();
            }
        });
        showRecommendationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalorieCalculatorActivity.this, RecipeRecommendationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void calculateCalories() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();
        String ageStr = ageInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            int age = Integer.parseInt(ageStr);
            String gender = genderSpinner.getSelectedItem().toString();
            int activityIndex = activitySpinner.getSelectedItemPosition();

            if (weight <= 0 || height <= 0 || age <= 0) {
                Toast.makeText(this, "Values must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double bmr;
            if (gender.equals("Male")) {
                bmr = 10 * weight + 6.25 * height - 5 * age + 5;
            } else {
                bmr = 10 * weight + 6.25 * height - 5 * age - 161;
            }

            double calories = bmr * activityMultipliers[activityIndex];

            calorieResult.setText("Daily Calorie Needs: " + String.format("%.0f", calories) + " kcal");
            showRecommendationsButton.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
        }
    }

}
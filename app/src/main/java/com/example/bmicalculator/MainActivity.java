package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

/**
 * BMI Calculator App
 *
 * Author: Michał Jastrzemski
 *
 * Description:
 * This is a simple Android application that calculates the Body Mass Index (BMI)
 * based on the user's weight (in kilograms) and height (in centimeters).
 * The app then provides a health status classification such as Underweight,
 * Optimal, Overweight, or Obesity.
 *
 * Features:
 * - User inputs weight and height.
 * - Calculates BMI using standard formula.
 * - Displays BMI value with two decimal precision.
 * - Shows health status based on BMI.
 * - Navigation to additional features like Calorie Calculator and BMI Chart.
 *
 * How to run this app:
 * 1. Open the project in Android Studio.
 * 2. Ensure Gradle sync is complete (File > Sync Project with Gradle Files).
 * 3. Connect an Android device or start an emulator.
 * 4. Run the app via Android Studio Run button.
 *
 * Usage:
 * On launch, the user can enter their weight and height, then tap "Calculate BMI".
 * The app will display the calculated BMI and corresponding health status.
 */

public class MainActivity extends AppCompatActivity {

    EditText weightInput, heightInput;
    Button calculateButton;
    TextView bmiResult, bmiStatus;
    Button openCalorieCalculatorButton;
    Button openBmiChartButton;

    /**
     * Initializes the activity and sets up UI element bindings and button listener.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateButton = findViewById(R.id.calculateButton);
        bmiResult = findViewById(R.id.bmiResult);
        bmiStatus = findViewById(R.id.bmiStatus);
        openCalorieCalculatorButton = findViewById(R.id.openCalorieCalculatorButton);
        openBmiChartButton = findViewById(R.id.bmiChartButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
        openCalorieCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalorieCalculatorActivity.class);
                startActivity(intent);
            }
        });
        openBmiChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BmiChartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Enter your weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float heightCm = Float.parseFloat(heightStr);
            float heightM = heightCm / 100;

            if (weight <= 0 || heightCm <= 0) {
                Toast.makeText(this, "Weight and height must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (heightM * heightM);
            bmiResult.setText("Your BMI: " + String.format("%.2f", bmi));

            String status = calculateBmiStatus(weight, heightCm);

            bmiStatus.setText("Status: " + status);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
        }
    }

    public static String calculateBmiStatus(float weight, float heightCm) {
        if (weight <= 0 || heightCm <= 0) {
            return "Error";
        }

        float heightM = heightCm / 100;
        float bmi = weight / (heightM * heightM);

        if (bmi < 18.5f) {
            return "Underweight";
        } else if (bmi < 25f) {
            return "Optimal";
        } else if (bmi < 30f) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
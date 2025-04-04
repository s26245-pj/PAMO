package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalorieCalculatorActivity extends AppCompatActivity {

    EditText ageInput, weightInput, heightInput;
    RadioGroup genderGroup;
    Spinner activityLevel;
    Button calculateCaloriesBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.genderGroup);
        activityLevel = findViewById(R.id.activityLevel);
        calculateCaloriesBtn = findViewById(R.id.calculateCaloriesBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevel.setAdapter(adapter);

        calculateCaloriesBtn.setOnClickListener(v -> calculateCalories());
    }

    private void calculateCalories() {
        try {
            int age = Integer.parseInt(ageInput.getText().toString());
            double weight = Double.parseDouble(weightInput.getText().toString());
            double height = Double.parseDouble(heightInput.getText().toString());

            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            boolean isMale = selectedGenderId == R.id.radioMale;

            double bmr;
            if (isMale) {
                bmr = 66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age);
            } else {
                bmr = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
            }

            double multiplier = getActivityMultiplier(activityLevel.getSelectedItemPosition());
            double dailyCalories = bmr * multiplier;

            resultText.setText(String.format("Zapotrzebowanie kaloryczne: %.0f kcal", dailyCalories));
        } catch (Exception e) {
            resultText.setText("Proszę uzupełnić wszystkie pola poprawnie.");
        }
    }

    private double getActivityMultiplier(int index) {
        switch (index) {
            case 1: return 1.2;
            case 2: return 1.375;
            case 3: return 1.55;
            case 4: return 1.725;
            case 5: return 1.9;
            default: return 1.0;
        }
    }
}

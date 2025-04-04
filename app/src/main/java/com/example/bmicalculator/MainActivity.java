package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnBMI, btnCalories, btnRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBMI = findViewById(R.id.btnBMI);
        btnCalories = findViewById(R.id.btnCalories);
        btnRecipes = findViewById(R.id.btnRecipes);

        btnBMI.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, BMICalculatorActivity.class))
        );

        btnCalories.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, CalorieCalculatorActivity.class))
        );

        btnRecipes.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RecipeActivity.class))
        );
    }
}

package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * BMI Calculator
 *
 * Aplikacja mobilna na system Android służąca do obliczania wskaźnika BMI (Body Mass Index) na podstawie wprowadzonych danych: wagi (w kg) oraz wzrostu (w cm).
 *
 * Funkcjonalności:
 * - Wprowadzenie wagi i wzrostu
 * - Obliczenie wartości BMI
 * - Wyświetlenie statusu zdrowotnego: Niedowaga, Optimum, Nadwaga, Otyłość
 * - Obsługa błędów w przypadku braku danych
 *
 * Autor:
 * Michał Jastrzemski, numer indeksu: s26245, grupa: 82
 *
 * Informacje techniczne:
 * - Język: Java
 * - Środowisko: Android Studio
 * - Minimum SDK: API 25
 * - Układ: LinearLayout (XML)
 * - Projekt wykorzystuje AppCompatActivity
 *
 * Źródło inspiracji:
 * Projekt bazuje na przykładzie aplikacji Tipper dostarczonym w Android Studio.
 *
 * Uruchamianie aplikacji:
 * - Sklonuj repozytorium lub pobierz folder bmi-calculator
 * - Otwórz projekt w Android Studio
 * - Uruchom aplikację na emulatorze lub fizycznym urządzeniu z Androidem
 */
public class MainActivity extends AppCompatActivity {

    Button btnBMI, btnCalories, btnRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBMI = findViewById(R.id.btnBMI);
        btnCalories = findViewById(R.id.btnCalories);
        btnRecipes = findViewById(R.id.btnRecipes);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMICalculator();
            }
        });

        btnCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalorieCalculator();
            }
        });

        btnRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeActivity();
            }
        });
    }

    private void openBMICalculator() {
        Intent intent = new Intent(MainActivity.this, BMICalculatorActivity.class);
        startActivity(intent);
    }

    private void openCalorieCalculator() {
        Intent intent = new Intent(MainActivity.this, BMICalculatorActivity.class);
        startActivity(intent);
    }

    private void openRecipeActivity() {
        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
        startActivity(intent);
    }
}

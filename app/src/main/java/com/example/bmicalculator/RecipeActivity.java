package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmicalculator.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * RecipeActivity
 *
 * Aktywność wyświetlająca propozycje przepisów w zależności od typu diety.
 *
 * Funkcjonalności:
 * - Wyświetlanie listy rekomendowanych przepisów
 * - Informacja o kaloryczności i typie diety
 */
public class RecipeActivity extends AppCompatActivity {

    TextView recipeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeText = findViewById(R.id.recipeText);

        List<Recipe> recipes = getRecommendedRecipes();
        StringBuilder builder = new StringBuilder();

        for (Recipe r : recipes) {
            builder.append("🍽 ").append(r.title).append("\n")
                    .append("Kalorie: ").append(r.calories).append(" kcal\n")
                    .append("Dieta: ").append(r.dietType).append("\n\n")
                    .append(r.description).append("\n\n---\n\n");
        }

        recipeText.setText(builder.toString());
    }

    private List<Recipe> getRecommendedRecipes() {
        List<Recipe> list = new ArrayList<>();

        list.add(new Recipe(
                "Wysokobiałkowy omlet",
                "3 jajka, szpinak, pomidory, ser feta. Smażyć razem na oliwie z oliwek. Świetny na budowę mięśni.",
                450,
                "Wysokobiałkowa"));

        list.add(new Recipe(
                "Wegańska owsianka",
                "Płatki owsiane z mlekiem migdałowym, nasionami chia, bananem i masłem orzechowym. Szybka i bogata w błonnik.",
                400,
                "Wegańska"));

        return list;
    }
}

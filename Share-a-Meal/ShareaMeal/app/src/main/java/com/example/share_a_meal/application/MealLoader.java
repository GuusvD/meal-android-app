package com.example.share_a_meal.application;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.share_a_meal.domain.Meal;

import java.util.ArrayList;

public class MealLoader extends AsyncTaskLoader<ArrayList<Meal>> {

    public MealLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public ArrayList<Meal> loadInBackground() {
        return new NetworkMeal().getMealInfo();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
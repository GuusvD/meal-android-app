package com.example.share_a_meal.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.share_a_meal.R;
import com.example.share_a_meal.application.MealLoader;
import com.example.share_a_meal.domain.Meal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Meal>> {

    private static final String LOG_TAG1 = MainActivity.class.getName();
    private static final String LOG_TAG2 = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private MealAdapter mMealAdapter;
    private ArrayList<Meal> mMealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        if (LoaderManager.getInstance(this).getLoader(0) == null) {
            LoaderManager.getInstance(this).restartLoader(0, null, this);
        }

        Log.d(LOG_TAG1, "Successfully completed onCreate(Bundle savedInstanceState) in MainActivity");
    }

    @NonNull
    @Override
    public Loader<ArrayList<Meal>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MealLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Meal>> loader, ArrayList<Meal> data) {
        mMealList = data;
        mMealAdapter = new MealAdapter(this, data);
        mRecyclerView.setAdapter(mMealAdapter);

        Toast.makeText(this, "Loaded " + data.size() + " items from the API", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Meal>> loader) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MainActivity.class.getSimpleName(), mMealList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMealList = savedInstanceState.getParcelableArrayList(MainActivity.class.getSimpleName());

        mMealAdapter = new MealAdapter(this, mMealList);
        mRecyclerView.setAdapter(mMealAdapter);

        Toast.makeText(this, "Loaded " + mMealList.size() + " items from the savedInstanceState", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG2, "Successfully completed onRestoreInstanceState(@NonNull Bundle savedInstanceState) in MainActivity");
    }
}
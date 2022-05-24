package com.example.share_a_meal.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.share_a_meal.R;
import com.example.share_a_meal.domain.Meal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private static final String LOG_TAG = MealAdapter.class.getSimpleName();
    private ArrayList<Meal> mMealList;
    private Context mContext;

    public MealAdapter(Context context, ArrayList<Meal> mealList) {
        this.mMealList = mealList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_meal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
        Meal currentMeal = mMealList.get(position);
        holder.bindTo(currentMeal);
    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mMealImage;
        private TextView mMealName;
        private TextView mMealDate;
        private TextView mMealCity;
        private TextView mMealPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mMealImage = itemView.findViewById(R.id.mealImage);
            mMealName = itemView.findViewById(R.id.mealName);
            mMealDate = itemView.findViewById(R.id.mealDate);
            mMealCity = itemView.findViewById(R.id.mealCity);
            mMealPrice = itemView.findViewById(R.id.mealPrice);

            itemView.setOnClickListener(this);
        }

        public void bindTo(Meal currentMeal) {
            Glide.with(mContext).load(currentMeal.getImageUrl()).into(mMealImage);
            mMealName.setText(currentMeal.getName());
            mMealDate.setText(dateToString(currentMeal.getDate()));
            mMealCity.setText(currentMeal.getCook().getCity());
            mMealPrice.setText("€ " + currentMeal.getPrice());
        }

        public String dateToString(Date d) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String string = null;

            try {
                string = simpleDateFormat.format(d);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return string;
        }

        @Override
        public void onClick(View view) {
            Meal currentMeal = mMealList.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);

            detailIntent.putExtra("mealImageUrl", currentMeal.getImageUrl());
            detailIntent.putExtra("mealName", currentMeal.getName());
            detailIntent.putExtra("mealPrice", "€ " + currentMeal.getPrice());
            detailIntent.putExtra("mealDescription", currentMeal.getDescription());
            detailIntent.putExtra("mealDate", dateToString(currentMeal.getDate()));
            detailIntent.putExtra("mealAllergenList", currentMeal.getAllergenList());
            detailIntent.putExtra("mealIsToTakeHome", currentMeal.getIsToTakeHome());
            detailIntent.putExtra("mealIsActive", currentMeal.getIsActive());
            detailIntent.putExtra("mealMaxAmountParticipants", currentMeal.getMaxAmountParticipants());
            detailIntent.putExtra("mealIsVega", currentMeal.getIsVega());
            detailIntent.putExtra("mealIsVegan", currentMeal.getIsVegan());
            detailIntent.putExtra("mealCook", currentMeal.getCook());

            mContext.startActivity(detailIntent);

            Log.d(LOG_TAG, "Successfully completed onClick(View view) in MealAdapter");
        }
    }
}
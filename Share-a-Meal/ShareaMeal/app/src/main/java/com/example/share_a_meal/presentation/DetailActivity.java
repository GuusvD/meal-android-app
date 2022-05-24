package com.example.share_a_meal.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.share_a_meal.R;
import com.example.share_a_meal.domain.User;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mealImage = findViewById(R.id.mealImageDetail);
        TextView mealName = findViewById(R.id.mealNameDetail);
        TextView mealPrice = findViewById(R.id.mealPriceDetail);
        TextView mealDescription = findViewById(R.id.mealDescriptionDetail);
        TextView mealDate = findViewById(R.id.mealDateDetail);
        TextView mealAllergen = findViewById(R.id.mealAllergenDetail);
        TextView mealMaxAmountParticipants = findViewById(R.id.mealMaxAmountParticipantsDetail);
        TextView userName = findViewById(R.id.userNameDetail);
        TextView cityName = findViewById(R.id.cityNameDetail);
        TextView streetName = findViewById(R.id.streetNameDetail);
        TextView emailAddress = findViewById(R.id.emailAddressDetail);
        TextView phoneNumber = findViewById(R.id.phoneNumberDetail);
        TextView roles = findViewById(R.id.rolesDetail);
        ImageView isToTakeHomeImage = findViewById(R.id.mealIsToTakeHomeImageDetail);
        ImageView isActiveImage = findViewById(R.id.mealIsActiveImageDetail);
        ImageView isVeganImage = findViewById(R.id.isVeganImageDetail);
        ImageView isVegaImage = findViewById(R.id.isVegaImageDetail);

        Glide.with(this).load(getIntent().getStringExtra("mealImageUrl")).into(mealImage);
        mealName.setText(getIntent().getStringExtra("mealName"));
        mealPrice.setText(getIntent().getStringExtra("mealPrice"));
        mealDescription.setText(getIntent().getStringExtra("mealDescription"));
        mealDate.setText("Geserveerd op " + getIntent().getStringExtra("mealDate"));

        ArrayList<String> allergenList = getIntent().getStringArrayListExtra("mealAllergenList");
        if (allergenList.isEmpty()) {
            mealAllergen.setText("Allergenen info: leeg");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Allergenen info: ");

            if (allergenList.size() == 1) {
                stringBuilder.append(allergenList.get(0));
            } else {
                for (int i = 0; i < allergenList.size(); i++) {
                    if (i == allergenList.size() - 1) {
                        stringBuilder.append(allergenList.get(i));
                    } else {
                        stringBuilder.append(allergenList.get(i) + ", ");
                    }
                }
            }

            mealAllergen.setText(stringBuilder.toString());
        }

        mealMaxAmountParticipants.setText("Max aantal deelnemers: " + getIntent().getStringExtra("mealMaxAmountParticipants"));

        User cook = (User) getIntent().getSerializableExtra("mealCook");

        userName.setText(cook.getFirstName() + " " + cook.getLastName());
        cityName.setText(cook.getCity());
        streetName.setText(cook.getStreet());
        emailAddress.setText(cook.getEmailAddress());
        phoneNumber.setText(cook.getPhoneNumber());

        String[] rolesArray = cook.getRoles();
        if (rolesArray.length == 0) {
            roles.setText("Rollen: leeg");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Rollen: ");

            if (rolesArray.length == 1) {
                stringBuilder.append(rolesArray[0]);
            } else {
                for (int i = 0; i < rolesArray.length; i++) {
                    if (i == rolesArray.length - 1) {
                        stringBuilder.append(rolesArray[i]);
                    } else {
                        stringBuilder.append(rolesArray[i] + ", ");
                    }
                }
            }

            roles.setText(stringBuilder.toString());
        }

        if (Boolean.parseBoolean(getIntent().getStringExtra("mealIsToTakeHome"))) {
            isToTakeHomeImage.setImageResource(R.drawable.ic_check);
        } else {
            isToTakeHomeImage.setImageResource(R.drawable.ic_cross);
        }

        if (Boolean.parseBoolean(getIntent().getStringExtra("mealIsActive"))) {
            isActiveImage.setImageResource(R.drawable.ic_check);
        } else {
            isActiveImage.setImageResource(R.drawable.ic_cross);
        }

        if (Boolean.parseBoolean(getIntent().getStringExtra("mealIsVega"))) {
            isVegaImage.setImageResource(R.drawable.ic_check);
        } else {
            isVegaImage.setImageResource(R.drawable.ic_cross);
        }

        if (Boolean.parseBoolean(getIntent().getStringExtra("mealIsVegan"))) {
            isVeganImage.setImageResource(R.drawable.ic_check);
        } else {
            isVeganImage.setImageResource(R.drawable.ic_cross);
        }

        Log.d(LOG_TAG, "Successfully completed onCreate(Bundle savedInstanceState) in DetailActivity");
    }
}
package com.example.share_a_meal.application;

import android.util.Log;

import com.example.share_a_meal.domain.Meal;
import com.example.share_a_meal.domain.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NetworkMeal {

    private static final String LOG_TAG_1 = NetworkMeal.class.getName();
    private static final String LOG_TAG_2 = NetworkMeal.class.getSimpleName();
    private static final String MEAL_BASE_URL = "https://shareameal-api.herokuapp.com/api/meal";

    public ArrayList<Meal> getMealInfo() {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String mealJSONString = null;
        ArrayList<Meal> mealList = new ArrayList<>();

        try {
            URL requestURL = new URL(MEAL_BASE_URL);

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            if (stringBuilder.length() == 0) {
                return null;
            }

            mealJSONString = stringBuilder.toString();
            mealList = this.jsonParseResponse(mealJSONString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG_1, "Successfully completed getMealInfo() in NetworkMeal");
        return mealList;
    }

    public ArrayList<Meal> jsonParseResponse(String s) {
        ArrayList<Meal> mealList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject mealJson = result.getJSONObject(i);

                String name = mealJson.getString("name");
                String description = mealJson.getString("description");
                String imageUrl = mealJson.getString("imageUrl");
                String dateTime = mealJson.getString("dateTime");
                String isVega = mealJson.getString("isVega");
                String isVegan = mealJson.getString("isVegan");
                String isToTakeHome = mealJson.getString("isToTakeHome");
                String isActive = mealJson.getString("isActive");
                String maxAmountParticipants = mealJson.getString("maxAmountOfParticipants");
                String price = mealJson.getString("price");

                JSONArray allergenArrayJson = mealJson.getJSONArray("allergenes");

                ArrayList<String> allergenList = new ArrayList<>();
                for (int j = 0; j < allergenArrayJson.length(); j++) {
                    allergenList.add(allergenArrayJson.getString(j));
                }

                JSONObject cookJson = mealJson.getJSONObject("cook");

                String firstName = cookJson.getString("firstName");
                String lastName = cookJson.getString("lastName");
                String street = cookJson.getString("street");
                String city = cookJson.getString("city");
                String emailAddress = cookJson.getString("emailAdress");
                String phoneNumber = cookJson.getString("phoneNumber");
                JSONArray rolesJson = cookJson.getJSONArray("roles");
                String[] roles = new String[rolesJson.length()];
                for (int x = 0; x < rolesJson.length(); x++) {
                    roles[x] = rolesJson.get(x).toString();
                }

                User cook = new User(firstName, lastName, street, city, emailAddress, phoneNumber, roles);

                mealList.add(new Meal(name, description, imageUrl, this.stringToDate(dateTime), allergenList, isVega, isVegan, isToTakeHome, isActive, maxAmountParticipants, price, cook));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG_2, "Successfully completed jsonParseResponse(String s) in NetworkMeal");
        return mealList;
    }

    public Date stringToDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
package com.example.share_a_meal.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Meal implements Parcelable {

    private final String name;
    private final String description;
    private final String imageUrl;
    private Date date;
    private final ArrayList<String> allergenList;
    private final String isVega;
    private final String isVegan;
    private final String isToTakeHome;
    private final String isActive;
    private final String maxAmountParticipants;
    private final String price;
    private User cook;

    public Meal(String name, String description, String imageUrl, Date date, ArrayList<String> allergenList, String isVega, String isVegan, String isToTakeHome, String isActive, String maxAmountParticipants, String price, User cook) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.allergenList = allergenList;
        this.isVega = isVega;
        this.isVegan = isVegan;
        this.isToTakeHome = isToTakeHome;
        this.isActive = isActive;
        this.maxAmountParticipants = maxAmountParticipants;
        this.price = price;
        this.cook = cook;
    }

    protected Meal(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        allergenList = in.createStringArrayList();
        isVega = in.readString();
        isVegan = in.readString();
        isToTakeHome = in.readString();
        isActive = in.readString();
        maxAmountParticipants = in.readString();
        price = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<String> getAllergenList() {
        return allergenList;
    }

    public String getIsVega() {
        return isVega;
    }

    public String getIsVegan() {
        return isVegan;
    }

    public String getIsToTakeHome() {
        return isToTakeHome;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getMaxAmountParticipants() {
        return maxAmountParticipants;
    }

    public String getPrice() {
        return price;
    }

    public User getCook() {
        return cook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeStringList(allergenList);
        parcel.writeString(isVega);
        parcel.writeString(isVegan);
        parcel.writeString(isToTakeHome);
        parcel.writeString(isActive);
        parcel.writeString(maxAmountParticipants);
        parcel.writeString(price);
    }
}
package com.example.myelmdemo;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by wjn on 2017/2/13.
 */

public class NoodleEntity implements Serializable{
    private Drawable imageRes;
    private String title;
    private String monthSale;
    private String goodEvaluate;
    private String address;
    private String price;
    private String activity;

    public Drawable getImageRes() {
        return imageRes;
    }

    public void setImageRes(Drawable imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMonthSale() {
        return monthSale;
    }

    public void setMonthSale(String monthSale) {
        this.monthSale = monthSale;
    }

    public String getGoodEvaluate() {
        return goodEvaluate;
    }

    public void setGoodEvaluate(String goodEvaluate) {
        this.goodEvaluate = goodEvaluate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

package com.android.productdetails.model;

import java.io.Serializable;

public class CompareProduct implements Serializable {
    String ssku;
    String region;
    String product_url;
    String compare_price;
    String rating;


    public String getSsku() {
        return ssku;
    }

    public void setSsku(String ssku) {
        this.ssku = ssku;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

    public String getCompare_price() {
        return compare_price;
    }

    public void setCompare_price(String compare_price) {
        this.compare_price = compare_price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
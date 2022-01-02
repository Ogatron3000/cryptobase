package com.example.cryptobase;

import java.math.BigInteger;

/**
 * Model class representing a coin entity.
 */
public class Coin {
    private int id;
    private String name;
    private String abbreviation;
    private float price;
    private long marketCap;

    public Coin(int id, String name, String abbreviation, float price, long marketCap) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.price = price;
        this.marketCap = marketCap;
    }

    public Coin(String name, String abbreviation, float price, long marketCap) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.price = price;
        this.marketCap = marketCap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }
}

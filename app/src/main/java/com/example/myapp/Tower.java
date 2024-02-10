package com.example.myapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Tower implements Parcelable {
    private int towerHealth;
    private int towerCost;
    private double range;
    private int damage;
    private String name;
    private int index = 0;

    public Tower() {
        this.name = "Basic Tower";
        this.towerHealth = 30;
        this.range = 400;
        this.damage = 10;
        this.towerCost = 50;
    }

    public Tower(String name) {
        if (name.equals("Basic Tower")) {
            this.name = name;
            this.towerHealth = 30;
            this.range = 300;
            this.damage = 10;
            this.towerCost = 50;
        } else if (name.equals("Intermediate Tower")) {
            this.name = name;
            this.towerHealth = 60;
            this.range = 300;
            this.damage = 20;
            this.towerCost = 100;
        } else {
            this.name = name;
            this.towerHealth = 90;
            this.range = 600;
            this.damage = 20;
            this.towerCost = 150;
        }
    }


    protected Tower(Parcel in) {
        towerHealth = in.readInt();
        towerCost = in.readInt();
        range = in.readDouble();
        damage = in.readInt();
        name = in.readString();
    }

    public static final Creator<Tower> CREATOR = new Creator<Tower>() {
        @Override
        public Tower createFromParcel(Parcel in) {
            return new Tower(in);
        }

        @Override
        public Tower[] newArray(int size) {
            return new Tower[size];
        }
    };

    public int getTowerHealth() {
        return this.towerHealth;
    }

    public void setTowerHealth(int health) {
        this.towerHealth = health;
    }

    public int getTowerCost() {
        return towerCost;
    }

    public void setTowerCost(int cost) {
        this.towerCost = cost;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRange() {
        return this.range;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setIndex(int num) { index = num; }

    public int getIndex() { return this.index; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(towerHealth);
        parcel.writeInt(towerCost);
        parcel.writeDouble(range);
        parcel.writeInt(damage);
        parcel.writeString(name);
    }
}
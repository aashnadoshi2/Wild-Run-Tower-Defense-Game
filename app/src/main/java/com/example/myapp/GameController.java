package com.example.myapp;

/**
 * This class handles arithmetic operations including
 * changing money, multiplier, health based on game difficulty
 */
public class GameController {
    private DataHolder h;
    private int initialCost;
    public GameController(DataHolder h) {
        this.h = h.getInstance();
        this.initialCost = h.getTowerCost();
    }

    public void calculateMultiplier() {
        if (h.getDifficulty().equals("Easy")) {
            h.setMultiplier(1);
        } else if (h.getDifficulty().equals("Medium")) {
            h.setMultiplier(2);
        } else {
            h.setMultiplier(3);
        }
    }
    public void calculateStartMoney() {
        h.setMoney(1000 / h.getMultiplier());
    }

    public void calculateMonumentHealth() {
        h.setMonumentHealth(100 / h.getMultiplier());
    }

    public void calculateTowerHealth() {
        h.setTowerHealth(30 / h.getMultiplier());
    }

    public void calculateTowerHealth(String name) {
        if (name.equals("Basic Tower")) {
            h.setTowerHealth(30 / h.getMultiplier());
        } else if (name.equals("Intermediate Tower")) {
            h.setTowerHealth(60 / h.getMultiplier());
        } else {
            h.setTowerHealth(90 / h.getMultiplier());
        }
    }

    public void calculateTowerCost() {
        h.setTowerCost(50 * h.getMultiplier());
    }

    public void calculateTowerCost(String name) {
        if (name.equals("Basic Tower")) {
            h.setTowerCost(50 * h.getMultiplier());
        } else if (name.equals("Intermediate Tower")) {
            h.setTowerCost(100 * h.getMultiplier());
        } else {
            h.setTowerCost(150 * h.getMultiplier());
        }
    }

    //buy Tower
    public void buyTower() {
        if (h.getMoney() >= h.getTowerCost()) {
            DataHolder data = DataHolder.getInstance();
            data.spentMoney(h.getTowerCost());
            h.setMoney(h.getMoney() - h.getTowerCost());
        }
    }

    public void buyUpgrade() {
        if (h.getMoney() >= h.getUpgradeCost()) {
            DataHolder data = DataHolder.getInstance();
            data.spentMoney(h.getUpgradeCost());
            h.setMoney(h.getMoney() - h.getUpgradeCost());
        }
    }
}
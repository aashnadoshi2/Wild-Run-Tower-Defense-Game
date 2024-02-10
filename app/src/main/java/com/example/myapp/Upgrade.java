package com.example.myapp;

public class Upgrade {
    private int upgradeCost;

    public Upgrade() {
       this.upgradeCost = 50;
    }

    public Upgrade(String towerName) {
        if (towerName.equals("Basic Tower")) {
            this.upgradeCost = 50;
        } else if (towerName.equals("Intermediate Tower")) {
            this.upgradeCost = 100;
        } else {
           this.upgradeCost = 150;
        }
    }

    public static void upgradeTower(Tower tower) {
        if (tower.getName().equals("Basic Tower")) {
            tower.setRange(tower.getRange() * 1.2);
            tower.setDamage((int) (tower.getDamage() * 1.2));
        } else if (tower.getName().equals("Intermediate Tower")) {
            tower.setRange(tower.getRange() * 1.4);
            tower.setDamage((int) (tower.getDamage() * 1.4));
        } else {
            tower.setRange(tower.getRange() * 1.5);
            tower.setDamage((int) (tower.getDamage() * 1.5));
        }
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }
}

package com.example.myapp;

public class Enemy {
    protected String type;
    protected int health;
    protected int damage;

    protected int number;
    protected boolean active = false;

    public Enemy(String type) {
        if (type == null) {
            this.type = "Enemy1";
            health = 20;
            damage = 20;
        } else if (type.equals("Enemy3")) {
            this.type = type;
            health = 50;
            damage = 50;
        } else if (type.equals("Enemy2")) {
            this.type = type;
            health = 30;
            damage = 30;
        } else {
            this.type = "Enemy1";
            health = 20;
            damage = 20;
        }
    }

    public Enemy() {
        this("Enemy1");
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(Tower t) {
        DataHolder data = DataHolder.getInstance();
        if (health - t.getDamage() <= 0) {
            data.dealDamage(health);
            health = 0;
        } else {
            data.dealDamage(t.getDamage());
            health -= t.getDamage();
        }
    }

    public boolean compareTo(Object other) {
        if (!(other instanceof Enemy)) {
            return false;
        }
        Enemy otherenemy = (Enemy) other;
        if (!otherenemy.getType().equals(type)) {
            return false;
        } else if (otherenemy.getDamage() != damage) {
            return false;
        } else if (otherenemy.getHealth() != health) {
            return false;
        }
        return true;
    }
}
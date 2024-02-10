package com.example.myapp;


/**
 * This class holds getters and setters for global variables (money, difficulty, tower health)
 */
public class DataHolder extends GameScreen {
    private static DataHolder instance;

    private DataHolder() {
        this.difficulty = "Easy";
        this.money = 1000;
        this.multiplier = 1;
        this.monumentHealth = 100;
        this.tower = new Tower();
        this.enemiesDefeated = 0;
        this.moneySpent = 0;
        this.damageDealt = 0;
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }

    private String difficulty;
    private int money;
    private int multiplier;
    private int monumentHealth;
    private Tower tower;
    private int enemiesDefeated;
    private int moneySpent;
    private int damageDealt;
    private Upgrade upgrade;

    //    private ArrayList<LinearLayout> pathTiles = new ArrayList<>();
    //    private ArrayList<LinearLayout> emptyTiles = new ArrayList<>();
    //    private ArrayList<LinearLayout> towerList = new ArrayList<>();
    //    private ArrayList<Integer> towerType = new ArrayList<>();


    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMonumentHealth() {
        return this.monumentHealth;
    }

    public void setMonumentHealth(int monumentHealth) {
        this.monumentHealth = monumentHealth;
    }

    public void decreaseMonumentHealth(int num) {
        int newHealth = this.monumentHealth - num;
        if (newHealth < 0) {
            monumentHealth = 0;
        } else {
            monumentHealth = newHealth;
        }
    }

    public void attackMonument(Enemy e) {
        decreaseMonumentHealth(e.getDamage());
    }



    public int getTowerHealth() {
        return this.tower.getTowerHealth();
    }

    public void setTowerHealth(int health) {
        this.tower.setTowerHealth(health);
    }

    public int getTowerCost() {
        return this.tower.getTowerCost();
    }

    public void setTowerCost(int cost) {
        this.tower.setTowerCost(cost);
    }

    public String getTowerName() {
        return this.tower.getName();
    }

    public void setTowerName(String name) {
        this.tower.setName(name);
    }

    public Tower getTower() {
        return this.tower;
    }

    public void setTower(Tower t) {
        tower = t;
    }

    public static void clearInstance() {
        instance = null;
    }

    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void killEnemy() { enemiesDefeated++; }

    public void dealDamage(int damage) { damageDealt += damage; }

    public void spentMoney(int money) { moneySpent += money; }

    public int getUpgradeCost() { return this.upgrade.getUpgradeCost(); }

    public void setUpgrade(Upgrade upgrade) { this.upgrade = upgrade; }
}
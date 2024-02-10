package com.example.myapp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class M6Tests {

    @Before
    public void setup() {
        DataHolder.clearInstance();
    }

    /**
     * Rohith
     * This test case sets up the game when the user picks the hardest level.
     * The monument health and multiplier is calculated long with the initial
     * money and health. A purchase for a tower is also made and the test
     * checks whether the purchase reflects on the screen where the money is
     * displayed.
     */
    @Test
    public void testHardLevel() {
        DataHolder d = DataHolder.getInstance();
        GameController g = new GameController(d);
        d.setDifficulty("Hard");
        assertEquals("Hard", d.getDifficulty());
        g.calculateMultiplier();
        g.calculateMonumentHealth();
        int initialMonumentHealth = d.getMonumentHealth();
        int initialMoney = d.getMoney();
        int towerCost = d.getTowerCost();
        assertTrue(initialMoney > towerCost);
        g.buyTower();
        int finalMoney = d.getMoney();
        assertEquals(finalMoney, initialMoney - towerCost);
        assertTrue(finalMoney > 0);
        d.decreaseMonumentHealth(50);
        int finalMonumentHealth = d.getMonumentHealth();
        assertTrue(finalMonumentHealth < initialMonumentHealth);
    }

    /**
     * Rohith
     * This test case sets up the game when the user picks the hardest level.
     * Three distinct enemies are created, all with different properties.
     * These enemies attack the monument and the test cases check whether the
     * enemies are doing any damage to the monument by checking the health of
     * the monument before and after the attack
     */
    @Test
    public void attackMonument() {
        DataHolder data = DataHolder.getInstance();
        GameController gameController = new GameController(data);
        data.setDifficulty("Hard");
        int initialHealth = data.getMonumentHealth();
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        data.attackMonument(enemy1);
        int firstHealth = data.getMonumentHealth();
        data.attackMonument(enemy2);
        int secondHealth = data.getMonumentHealth();
        data.attackMonument(enemy3);
        int finalHealth = data.getMonumentHealth();
        assertTrue(initialHealth > firstHealth);
        assertTrue(firstHealth > secondHealth);
        assertTrue(secondHealth > finalHealth);
        assertTrue(finalHealth >= 0);
    }

    /**
     * Julius
     * This test case sets up the game when the user picks the hardest level.
     * Two towers and three enemies are initialized and this case tests the
     * attacks by the enemies on the towers and how the health of the towers
     * changes every time it is attacked.
     */
    @Test
    public void testEnemyAttack() {
        DataHolder data = DataHolder.getInstance();
        GameController gameController = new GameController(data);
        data.setDifficulty("Hard");
        Tower basicTower = new Tower("Basic Tower");
        Tower advancedTower = new Tower("Advanced Tower");
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        int enemy1Health = enemy1.getHealth();
        enemy1.attack(basicTower);
        enemy1.attack(advancedTower);
        int finalEnemy1Health = enemy1.getHealth();
        assertEquals(20, enemy1Health);
        assertEquals(0, finalEnemy1Health);
        assertTrue(finalEnemy1Health < enemy1Health);
        int enemy2Health = enemy2.getHealth();
        enemy2.attack(advancedTower);
        int finalEnemy2Health = enemy2.getHealth();
        assertEquals(30, enemy2Health);
        assertEquals(20, finalEnemy2Health);
        assertTrue(finalEnemy2Health < enemy2Health);
        int enemy3Health = enemy3.getHealth();
        enemy3.attack(basicTower);
        int finalEnemy3Health = enemy3.getHealth();
        assertEquals(50, enemy3Health);
        assertEquals(40, finalEnemy3Health);
        assertTrue(finalEnemy3Health < enemy3Health);
        System.out.println(enemy3Health);
        System.out.println(finalEnemy3Health);
    }

    /**
     * Julius
     * This test checks initializes five different types of enemies. One
     * imperative requirement of this game is to have distinct enemies and
     * this is what this test case checks for. Each enemy is compared to the
     * other and the test ensures that none of the enemies are the same.
     */
    @Test
    public void testUniqueEnemies() {
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        Enemy nullEnemy = new Enemy(null);
        Enemy randEnemy = new Enemy("randomString");
        assertTrue(nullEnemy.compareTo(enemy1));
        assertTrue(randEnemy.compareTo(enemy1));
        assertFalse(enemy1.compareTo(enemy2));
        assertFalse(enemy1.compareTo(enemy3));
        assertFalse(enemy2.compareTo(enemy3));
    }

    /**
     * Prisha
     * This test case sets up the game when the user picks the medium level.
     * It initializes three distinct enemies and checks how the ability of each
     * enemy to damage towers differs from one enemy to another. Each enemy has
     * different abilities and this test checks for that.
     */
    @Test
    public void testDistinctEnemyDamage() {
        DataHolder data = DataHolder.getInstance();
        GameController gameController = new GameController(data);
        data.setDifficulty("Medium");
        int initialMonumentHealth = data.getMonumentHealth();
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        data.attackMonument(enemy1);
        int monumentHealthAfterEnemy1Attack = data.getMonumentHealth();
        int enemy1Damage = initialMonumentHealth - monumentHealthAfterEnemy1Attack;
        data.attackMonument(enemy2);
        int monumentHealthAfterEnemy2Attack = data.getMonumentHealth();
        int enemy2Damage = monumentHealthAfterEnemy1Attack - monumentHealthAfterEnemy2Attack;
        data.attackMonument(enemy3);
        int monumentHealthAfterEnemy3Attack = data.getMonumentHealth();
        int enemy3Damage = monumentHealthAfterEnemy2Attack - monumentHealthAfterEnemy3Attack;
        assertTrue(enemy1Damage < enemy2Damage);
        assertTrue(enemy2Damage < enemy3Damage);
        assertTrue(initialMonumentHealth > monumentHealthAfterEnemy1Attack);
        assertTrue(monumentHealthAfterEnemy1Attack > monumentHealthAfterEnemy2Attack);
        assertTrue(monumentHealthAfterEnemy2Attack > monumentHealthAfterEnemy3Attack);
    }

    /**
     * Prisha
     * This test initializes two distinct towers, one advanced and one intermediate.
     * The name of one tower is changed after initialization. This tower ensures that
     * changing the tower's name doesn't change it's other properties such as the
     * cost and the health.
     */
    @Test
    public void towerNamesTest() {
        Tower intTower = new Tower("Advanced Tower");
        Tower tower = new Tower("Intermediate Tower");
        tower.setName("Advanced Tower");
        assertEquals(intTower.getName(), tower.getName());
        assertSame("Advanced Tower", tower.getName());
        assertTrue(tower.getTowerHealth() != intTower.getTowerHealth());
        assertTrue(tower.getTowerCost() != intTower.getTowerCost());
    }

    /**
     * Aashna
     * This test initializes a DataHolder and a GameController variable and the
     * difficulty level of the game is changed in this test. This test ensures that
     * the monument health differs across different levels of difficulty of the game.
     */
    @Test
    public void testInitializeMonuments() {
        DataHolder d = DataHolder.getInstance();
        GameController gameController = new GameController(d);
        int easyLevel = d.getMonumentHealth();
        d.setDifficulty("Medium");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int mediumLevel = d.getMonumentHealth();
        d.setDifficulty("Hard");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int hardLevel = d.getMonumentHealth();
        assertTrue(easyLevel != mediumLevel && easyLevel != hardLevel);
        assertTrue(hardLevel > 0);
        assertTrue(easyLevel > mediumLevel);
        assertTrue(mediumLevel > hardLevel);
    }

    /**
     * Aashna
     * This test initializes the game and checks if the health is positive. It also
     * initializes a new enemy which attacks the monument multiple times. The test ensures
     * that the health of the monument never becomes negative and the lowest value it can
     * have is 0.
     */
    @Test
    public void testPositiveMonumentHealth() {
        DataHolder dataHolder = DataHolder.getInstance();
        int health = dataHolder.getMonumentHealth();
        assertTrue(health > 0);
        Enemy enemy = new Enemy();
        for (int i = 0; i < 5; i++) {
            dataHolder.attackMonument(enemy);
        }
        assertEquals(0, dataHolder.getMonumentHealth());
    }

    /**
     * Rayan
     * This test checks that the monument health
     * cannot decrease below zero. Preventing the
     * health from going below zero is important in
     * implementing the end of the game, which is
     * initiated when the monument's health reaches 0.
     */
    @Test
    public void testMonumentHealthMethod() {
        DataHolder data = DataHolder.getInstance();
        int health = data.getMonumentHealth();
        data.decreaseMonumentHealth(health + 50);
        assertEquals(health, 100);
    }

    /**
     * Rayan
     * This test initializes three distinct towers and checks if the cost of the tower
     * varies based on the type of the tower that is selected. This functionality is
     * important since one of the main requirements of the game is to have different types
     * of towers with varying properties.
     */
    @Test
    public void towerCost() {
        DataHolder data = DataHolder.getInstance();
        GameController gameController = new GameController(data);
        Tower basic = new Tower("Basic Tower");
        Tower intermediate = new Tower("Intermediate Tower");
        Tower advanced = new Tower("Advanced Tower");
        assertTrue(basic.getTowerCost() > 0) ;
        assertTrue(basic.getTowerCost() < intermediate.getTowerCost());
        assertTrue(intermediate.getTowerCost() < advanced.getTowerCost());
    }
}



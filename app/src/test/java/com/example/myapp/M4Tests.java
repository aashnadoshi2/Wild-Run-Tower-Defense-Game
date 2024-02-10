package com.example.myapp;

import static org.junit.Assert.*;
// import android.test.ActivityInstrumentationTestCase2;

//import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class M4Tests {

    @Before
    public void setup() {
        DataHolder.clearInstance();
    }

    /**
     * Julius
     * This tests the monument the monument health
     * decrease mechanics that serves behind the functionality
     * for enemies to attack the tower. The health should be able to
     * decrease regardless of initialization amount and the health
     * should not be able to decrease below zero.
     */
    @Test
    public void testMonumentHealth() {
        DataHolder d = DataHolder.getInstance();
        GameController g = new GameController(d);
        d.setDifficulty("Hard");
        g.calculateMultiplier();
        g.calculateMonumentHealth();
        int hardHealth = d.getMonumentHealth();
        d.setDifficulty("Medium");
        g.calculateMultiplier();
        g.calculateMonumentHealth();
        int mediumHealth = d.getMonumentHealth();
        d.setDifficulty("Easy");
        g.calculateMultiplier();
        g.calculateMonumentHealth();
        int easyHealth = d.getMonumentHealth();
        d.decreaseMonumentHealth(50);
        int decreaseOne = d.getMonumentHealth();
        d.decreaseMonumentHealth(100);
        int decreaseTwo = d.getMonumentHealth();
        assertTrue(hardHealth < mediumHealth && mediumHealth < easyHealth);
        assertTrue(hardHealth > 0);
        assertTrue(easyHealth > decreaseOne && decreaseOne > decreaseTwo);
        assertTrue(decreaseTwo >= 0);
    }

    /**
     * Julius
     * This tests the various enemy initializations that
     * are used to spawn new enemies in the game. Regardless
     * of input, only three types of enemies should be able
     * to be instantiated.
     */
    @Test
    public void testEnemyInitializations() {
        String nullString = null;
        String enemy1 = "enemy1";
        String enemy2 = "Enemy2";
        String enemy3 = "Enemy3";
        String randomString = "absjuf";
        Enemy nullEnemy = new Enemy(nullString);
        Enemy enemyOne = new Enemy(enemy1);
        Enemy enemyTwo = new Enemy(enemy2);
        Enemy enemyThree = new Enemy(enemy3);
        Enemy randEnemy = new Enemy(randomString);
        assertTrue(nullEnemy.compareTo(enemyOne));
        assertTrue(randEnemy.compareTo(enemyOne));
        assertFalse(enemyOne.compareTo(enemyTwo));
        assertFalse(enemyOne.compareTo(enemyTwo));
        assertFalse(enemyTwo.compareTo(enemyThree));
    }
    
        /**
     * Prisha
     * Tests that the game is able to create three
     * different types of enemies, each with different
     * types, health, and damage done to the monument.
     *
     * Three different types of enemies is essential
     * to creating rounds that can vary in difficulty, not
     * only be amount or frequency of enemies spawned
     * but also by enemy type.
     */
    @Test
    public void threeEnemyTypes() {
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");

        assertFalse(enemy1.compareTo(enemy2));
        assertFalse(enemy1.compareTo(enemy3));
        assertFalse(enemy2.compareTo(enemy3));
    }

    /**
     * Prisha
     * Tests the functionality of the enemy
     * attacking the monument and decreasing its health.
     *
     * The enemies should be able to attack the
     * monument once they reach the end of the path,
     * decreasing the monument's health by an
     * amount variant on the type of attacking enemy.
     */
    @Test
    public void enemyAttacksMonument() {
        DataHolder data = DataHolder.getInstance();
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
    
    /** Rayan
 * This test checks the mechanics of the
 * monument health initialization and decrease.
 * Having a functioning health decrease mechanic
 * is the backbone of the attackMonument feature,
 * which decreases the monument's health once an
 * enemy makes it through the path without being
 * killed by a tower.
 */
@Test
public void testCheckHealth() {
    DataHolder data = DataHolder.getInstance();
    int health1 = data.getMonumentHealth();
    data.decreaseMonumentHealth(20);
    int health2 = data.getMonumentHealth();
    data.decreaseMonumentHealth(30);
    int health3 = data.getMonumentHealth();
    assertTrue(health1 > health2);
    assertTrue(health2 > health3);
    assertTrue(health3 >= 0);
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
public void testNegativeMonumentHealth() {
    DataHolder data = DataHolder.getInstance();

    int health = data.getMonumentHealth();
    data.decreaseMonumentHealth(health + 50);
    assertEquals(health, 0);
}

    /**
     * Aashna
     * Tests the functionality of the attackMonument
     * feature, which allows enemies to do damage to the
     * monument once they reach the end of the path. When
     * the end of the path is reached, health should be
     * decreased by an amount that depends on the
     * enemy's "damage" field and the health should not
     * decrease below 0.
     */
    @Test
    public void testAttackMonument() {
        DataHolder d = DataHolder.getInstance();
        Enemy enemy = new Enemy("Enemy1");
        int health0 = d.getMonumentHealth();
        d.attackMonument(enemy);
        int health1 = d.getMonumentHealth();
        d.attackMonument(enemy);
        int health2 = d.getMonumentHealth();
        assertTrue(health0 > health1);
        assertTrue(health1 > health2);
        assertTrue(health2 >= 0);
    }

    /** Aashna
     * Tests the monument's health functionality
     * across each of the different difficulties.
     * Having the monument health vary depending
     * on the game's difficulty is important to
     * making the "hard" difficulty harder (by
     * having the least amount of health) and
     * vice versa for easier for "easy".
     */
    @Test
    public void testMonumentInitialization() {
        DataHolder d = DataHolder.getInstance();
        GameController gameController = new GameController(d);
        int easy = d.getMonumentHealth();

        d.setDifficulty("Medium");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int medium = d.getMonumentHealth();

        d.setDifficulty("Hard");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int hard = d.getMonumentHealth();

        assertTrue(hard > 0);
        assertTrue(easy > medium);
        assertTrue(medium > hard);
    }

        /**
        * Rohith
        * Ensures that the monument health can never be
        * negative, even if the amount of damage to be done
        * to the monument is greater than the current monument
        * health or if the current health is zero. Preventing
        * the health from reaching zero both helps with implementing
        * the end-of-game scenario and makes sense in the context
        * of the overall game.
        */
        @Test
        public void nonNegativeMonumentHealth() {
        DataHolder dataHolder = DataHolder.getInstance();
        int health = dataHolder.getMonumentHealth();
        assertTrue(health > 0);

        Enemy enemy = new Enemy();
        for (int i = 0; i < 10; i++) {
            dataHolder.attackMonument(enemy);
        }

        assertTrue(dataHolder.getMonumentHealth() == 0);
        }

        /**
        * Rohith
        * Tests that the three different types of enemies
        * each deal varying amounts of damage to the monument
        * once the end of the path is reached. Damage is currently
        * the only relevant stat (aside from type) that applies to this
        * module, but future additions will call for testing of the other
        * enemy statistics across types.
        */
        @Test
        public void enemyStatsAcrossTypes() {
        DataHolder dataHolder = DataHolder.getInstance();
        Enemy e1 = new Enemy("Enemy1");
        Enemy e2 = new Enemy("Enemy2");
        Enemy e3 = new Enemy("Enemy3");

        assertTrue(e1.getDamage() < e2.getDamage());
        assertTrue(e2.getDamage() < e3.getDamage());
        assertTrue(e1.getDamage() > 0);
        }


}



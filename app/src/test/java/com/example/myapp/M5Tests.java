package com.example.myapp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class M5Tests {

    @Before
    public void setup() {
        DataHolder.clearInstance();
    }


    /**
     * Aashna
     * Tests that each tower has a positive damage value and a positive
     * attacking range. This is an imperative attribute for each tower since
     * it uses these properties to attack the enemy.
     */
    @Test
    public void testTowerDamageAndRange() {
        Tower basic = new Tower("Basic Tower");
        Tower intermediate = new Tower("Intermediate Tower");
        Tower advanced = new Tower("Advanced Tower");

        assertTrue(basic.getDamage() > 0) ;
        assertTrue(basic.getRange() > 0) ;
        assertTrue(intermediate.getDamage() > 0) ;
        assertTrue(intermediate.getRange() > 0) ;
        assertTrue(advanced.getDamage() > 0) ;
        assertTrue(advanced.getRange() > 0) ;
    }

    /**
     * Aashna
     * This test initializes three enemies and a tower. It tests if the
     * health of the enemy decreases when the tower attacks the enemy.
     * This property is important to help determine the game state and for the
     * player to make progress in the game.
     */
    @Test
    public void testEnemyHealthOnAttack() {
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        Tower basic = new Tower("Basic Tower");

        int initialEnemy1Health = enemy1.getHealth();
        int initialEnemy2Health = enemy1.getHealth();
        int initialEnemy3Health = enemy1.getHealth();

        enemy1.attack(basic);
        enemy2.attack(basic);
        enemy3.attack(basic);

        int finalEnemy1Health = enemy1.getHealth();
        int finalEnemy2Health = enemy1.getHealth();
        int finalEnemy3Health = enemy1.getHealth();

        assertNotEquals(initialEnemy1Health, finalEnemy1Health);
        assertNotEquals(initialEnemy2Health, finalEnemy2Health);
        assertNotEquals(initialEnemy3Health, finalEnemy3Health);
    }


    /**
     * Julius
     * This tests the various enemy initializations that
     * are used to spawn new enemies in the game. Regardless
     * of input, only three types of enemies should be able
     * to be instantiated.
     */
    @Test
    public void testDistinctEnemies() {
        Enemy nullEnemy = new Enemy(null);
        Enemy enemy1 = new Enemy("Enemy1");
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        Enemy randEnemy = new Enemy("randomString");

        assertTrue(nullEnemy.compareTo(enemy1));
        assertTrue(randEnemy.compareTo(enemy1));
        assertFalse(enemy1.compareTo(enemy2));
        assertFalse(enemy2.compareTo(enemy3));
        assertFalse(enemy1.compareTo(enemy3));
    }

    /**
     * Julius
     * Tests that the three different types of towers have different (and non-zero)
     * in-game costs. Having different costs is an important factor that
     * differentiates different types of towers, a key functionality of our
     * tower defense game.
     */
    @Test
    public void testTowerCostsForDifferentTypes() {
        Tower basic = new Tower("Basic Tower");
        Tower intermediate = new Tower("Intermediate Tower");
        Tower advanced = new Tower("Advanced Tower");

        assertTrue(basic.getTowerCost() > 0) ;
        assertTrue(basic.getTowerCost() < intermediate.getTowerCost());
        assertTrue(intermediate.getTowerCost() < advanced.getTowerCost());
    }

    /**
     * Rohith
     * Tests for if the towers are correctly initialized when no parameters
     * are passed and tests if the enemies are correctly initialized when the
     * enemy type is null.
     */
    @Test
    public void testForNullTowersAndEnemies() {
        Tower tower = new Tower();
        Enemy enemy = new Enemy(null);
        Tower tower1 = new Tower("Basic Tower");
        Enemy enemy1 = new Enemy("Enemy1");

        assertEquals(tower.getName(), tower1.getName());
        assertEquals(tower.getDamage(), tower1.getDamage());
        assertEquals(tower.getTowerCost(), tower1.getTowerCost());
        assertEquals(tower.getTowerHealth(), tower1.getTowerHealth());
        assertEquals(enemy.getType(), enemy1.getType());
        assertEquals(enemy.getHealth(), enemy1.getHealth());
        assertEquals(enemy.getActive(), enemy1.getActive());
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
    public void testEnemyDamageAcrossDifferentEnemies() {
        Enemy e1 = new Enemy("Enemy1");
        Enemy e2 = new Enemy("Enemy2");
        Enemy e3 = new Enemy("Enemy3");

        assertTrue(e1.getDamage() < e2.getDamage());
        assertTrue(e2.getDamage() < e3.getDamage());
        assertTrue(e1.getDamage() > 0);
    }

    /**
     * Rayan
     * This test checks the functionality of the enemy's getter and setter methods
     * and verifies the changes that are being made to the properties of the
     * enemies.
     */
    @Test
    public void testEnemyGettersAndSetters() {
        Enemy enemy = new Enemy("Enemy1");
        enemy.setHealth(100);
        enemy.setActive(true);
        enemy.setNumber(2);
        assertEquals(100, enemy.getHealth());
        assertTrue(enemy.getActive());
        assertEquals(2, enemy.getNumber());
    }

    /**
     * Rayan
     * This test ensures that even if you change the tower name after
     * initializing it to another name, the other properties of the tower
     * such as the cost and the health do not change.
     */
    @Test
    public void testSetTowerName() {
        Tower intTower = new Tower("Intermediate Tower");
        Tower tower = new Tower("Basic Tower");
        tower.setName("Intermediate Tower");
        System.out.println(intTower.getName());
        System.out.println(tower.getName());
        assertEquals(intTower.getName(), tower.getName());
        assertTrue(tower.getTowerHealth() != intTower.getTowerHealth());
        assertTrue(tower.getTowerCost() != intTower.getTowerCost());
    }

    /**
     * Prisha
     * This test checks the functionality of the tower's getter and setter methods
     * and verifies the changes that are being made to the properties of the
     * towers.
     */
    @Test
    public void testTowerGettersAndSetters() {
        Tower tower = new Tower("Advanced Tower");
        tower.setTowerHealth(100);
        tower.setTowerCost(40);
        tower.setName("Basic Tower");

        assertEquals(40, tower.getTowerCost());
        assertEquals("Basic Tower", tower.getName());
        assertEquals(100, tower.getTowerHealth());
    }

    /**
     * Prisha
     * This test checks for the enemy health before and after the tower and
     * ensures that the enemy health is decrementing every time the tower attacks
     * the enemy. The attacked is performed a couple of times.
     */
    @Test
    public void attackEnemy() {
        Enemy enemy = new Enemy("Enemy2");
        Tower tower = new Tower("Intermediate Tower");
        int enemyHealthBeforeAttack = enemy.getHealth();
        enemy.attack(tower);
        int enemyHealthAfterFirstAttack = enemy.getHealth();
        enemy.attack(tower);
        int enemyHealthAfterSecondAttack = enemy.getHealth();
        assertTrue(enemyHealthBeforeAttack > enemyHealthAfterFirstAttack);
        assertTrue(enemyHealthAfterFirstAttack > enemyHealthAfterSecondAttack);
    }
}



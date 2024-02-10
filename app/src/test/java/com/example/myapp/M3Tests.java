package com.example.myapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testng.annotations.AfterMethod;

public class M3Tests {

    @Before
    public void setup() {
        DataHolder.clearInstance();
    }

    /**
     * 1) Aashna
     * Tests that the cost for a tower is different (and non-zero)
     * for each difficulty, with the price of a tower increasing as
     * the difficulty level is increased. Tower price is an essential part
     * of adjusting the game's difficulty, as more expensive towers mean the player
     * can afford less of them, making the game harder to win.
     */
    @Test
    public void testTowerCostsForDifferentTypes() {
        //*all cases on easy difficulty
        //instantiate three types of towers
        Tower basic = new Tower("Basic Tower");
        Tower intermediate = new Tower("Intermediate Tower");
        Tower advanced = new Tower("Advanced Tower");

        //check that costs differ for different tower types
        assertTrue(basic.getTowerCost() > 0) ;
        assertTrue(basic.getTowerCost() < intermediate.getTowerCost());
        assertTrue(intermediate.getTowerCost() < advanced.getTowerCost());
    }

    /**
     * 2) Aashna
     * Tests that the three different types of towers have different (and non-zero)
     * in-game costs. Having different costs is an important factor that
     * differentiates different types of towers, a key functionality of our
     * tower defense game.
     */
    @Test
    public void testTowerCostsForDifferentDifficulties() {
        int easyCost;
        int medCost;
        int hardCost;
        //calulate different tower costs for each difficulty
        DataHolder data = DataHolder.getInstance();
        GameController game = new GameController(data);
        game.calculateTowerCost();
        easyCost = data.getTowerCost();
        data.setDifficulty("Medium");
        game.calculateMultiplier();
        game.calculateTowerCost();
        medCost = data.getTowerCost();
        data.setDifficulty("Hard");
        game.calculateMultiplier();
        game.calculateTowerCost();
        hardCost = data.getTowerCost();

        //test each cost
        assertTrue(easyCost > 0);
        assertTrue(easyCost < medCost);
        assertTrue(medCost < hardCost);
    }

    /**
     * 3) Prisha
     * This test checks the buy tower feature to ensure that
     * the player's money decreases with tower purchases, and never
     * reaches an amount below zero. The ability to buy towers in exchange
     * for money is a core feature of the shop's functionality.
     */
    @Test
    public void buyTower() {
        DataHolder dataHolder = DataHolder.getInstance();
        GameController gameController = new GameController(dataHolder);
        int startingMoney = dataHolder.getMoney();
        assertTrue(startingMoney >= 0);
        gameController.buyTower();
        int moneyAfterBuying = dataHolder.getMoney();
        assertTrue(startingMoney > moneyAfterBuying);
        assertTrue(moneyAfterBuying >= 0);
    }

    /**
     * 4) Prisha
     * This test compares the monument's health for each difficulty,
     * ensuring that the health never starts at or below 0 and the health
     * decreases as difficulty increases. Decreasing the health of the monument
     * lowers the amount of enemies that can reach the end of the path
     * before losing the game, thus making the game harder dependent on the
     * game's difficulty.
     */
    @Test
    public void monumentInitialization() {
        DataHolder dataHolder = DataHolder.getInstance();
        GameController gameController = new GameController(dataHolder);
        int easyMonumentHealth = dataHolder.getMonumentHealth();
        dataHolder.setDifficulty("Medium");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int mediumMonumentHealth = dataHolder.getMonumentHealth();
        dataHolder.setDifficulty("Hard");
        gameController.calculateMultiplier();
        gameController.calculateMonumentHealth();
        int hardMonumentHealth = dataHolder.getMonumentHealth();
        assertTrue(hardMonumentHealth > 0);
        assertTrue(easyMonumentHealth > mediumMonumentHealth);
        assertTrue(mediumMonumentHealth > hardMonumentHealth);
    }

    /**
     * 5) Julius
     * Checks the initial money for each difficulty.
     * Harder difficulties start the game with less money,
     * but always a non-negative amount.
     * Starting the game with less money limits
     * the player's ability to purchase towers, thus making the
     * game more difficult for harder difficulties.
     */
    @Test
    public void moneyInitialization() {
        int eMoney;
        int mMoney;
        int hMoney;

        DataHolder d = DataHolder.getInstance();
        GameController g = new GameController(d);

        eMoney = d.getMoney();

        d.setDifficulty("Medium");
        g.calculateMultiplier();
        g.calculateStartMoney();
        mMoney = d.getMoney();

        d.setDifficulty("Hard");
        g.calculateMultiplier();
        g.calculateStartMoney();
        hMoney = d.getMoney();

        assertTrue(hMoney >= 0);
        assertTrue(eMoney > mMoney);
        assertTrue(mMoney > hMoney);
    }

    /**
     * 6) Julius
     * Checks to ensure the player's money can
     * never be negative as the result of a purchase.
     * If a player's balance is insufficient, the
     * attempt to purchase the tower will be denied.
     * Preventing players from having a negative balance
     * ensures the game is played properly and the shop's
     * functionality is not abused.
     */
    @Test
    public void insufficientBalance() {
        DataHolder d = DataHolder.getInstance();
        d.setDifficulty("Hard");
        GameController g = new GameController(d);
        g.calculateMultiplier();
        g.calculateStartMoney();
        g.calculateTowerCost();

        assertTrue(d.getMoney() >= 0);

        for (int i = 0; i < 100; i++) {
            g.buyTower();
        }

        assertTrue(d.getMoney() >= 0);
    }

    /**
     * 7) Rayan
     * Tests that the selected tower retains its attributes
     * when selected. Also tests the cost of each tower for the
     * different types. In order to have three distinct towers, each
     * tower must be able to match its attributes displayed in the shop
     * after being selected and places on the map. Additionally, the different
     * towers should differentiate based on cost to add variety to the player's
     * tower selection.
     */
    @Test
    public void testTowerSelect() {
        DataHolder data = DataHolder.getInstance();
        assertTrue(data.getTowerName().equals("Basic Tower"));
        int basicCost = data.getTowerCost();
        Tower intermediate = new Tower("Intermediate Tower");
        data.setTower(intermediate);
        assertTrue(data.getTowerName().equals("Intermediate Tower"));
        int intermediateCost = data.getTowerCost();
        Tower advanced = new Tower("Advanced Tower");
        data.setTower(advanced);
        assertTrue(data.getTowerName().equals("Advanced Tower"));
        int advancedCost = data.getTowerCost();
        assertTrue(basicCost > 0 && intermediateCost > 0 && advancedCost > 0);
        assertTrue(basicCost != intermediateCost);
        assertTrue(intermediateCost != advancedCost);
        assertTrue(basicCost != advancedCost);
    }

    /**
     * 8) Rayan
     * Tests the player's ability to buy multiple
     * towers of different types in a sequence. The game both
     * needs to be able to produce multiple types of towers from
     * the shop and properly track the player's money across the
     * different types of tower purchases.
     */
    @Test
    public void buyMultipleTowers() {
        DataHolder globals = DataHolder.getInstance();
        GameController gc = new GameController(globals);
        int start = globals.getMoney();
        gc.buyTower();
        int firstBuy = globals.getMoney();
        assertTrue(start > firstBuy);
        int diff1 = start - firstBuy;
        Tower intm = new Tower("Intermediate Tower");
        globals.setTower(intm);
        gc.buyTower();
        int secondBuy = globals.getMoney();
        int diff2 = firstBuy - secondBuy;
        Tower adv = new Tower("Advanced Tower");
        globals.setTower(adv);
        gc.buyTower();
        int finalBuy = globals.getMoney();
        int diff3 = secondBuy - finalBuy;
        assertTrue(start > firstBuy);
        assertTrue(firstBuy > secondBuy);
        assertTrue(secondBuy > finalBuy);
        assertTrue(diff1 != diff2);
        assertTrue(diff1 != diff3);
        assertTrue(diff2 != diff3);
    }

    /**
     * 9) Rohith
     * Tests the full initialization of the game
     * for each difficulty, including starting money,
     * monument health, and multiplier. Testing the full
     * initialization including variables unseen by the player
     * such as multiplier ensures that the game starts properly
     * and is ready for new features that build off the ones
     * currently implemented.
     */
    @Test
    public void testInitialization() {
        int emoney, mmoney, hmoney;
        int emonument, mmonument, hmonument;
        int emult, mmult, hmult;
        DataHolder d = DataHolder.getInstance();
        GameController g = new GameController(d);
        emoney = d.getMoney();
        emonument = d.getMonumentHealth();
        emult = d.getMultiplier();
        d.setDifficulty("Medium");
        g.calculateMultiplier();
        g.calculateStartMoney();
        g.calculateMonumentHealth();
        mmoney = d.getMoney();
        mmonument = d.getMonumentHealth();
        mmult = d.getMultiplier();
        d.setDifficulty("Hard");
        g.calculateMultiplier();
        g.calculateStartMoney();
        g.calculateMonumentHealth();
        hmoney = d.getMoney();
        hmonument = d.getMonumentHealth();
        hmult = d.getMultiplier();

        assertTrue(0 <= hmoney && 0 <= hmonument);
        assertTrue(emoney > mmoney && emonument > mmonument);
        assertTrue(mmoney > hmoney && mmonument > hmonument);
        assertTrue(emult < mmult && mmult < hmult);
    }

    /**
     * 10) Rohith
     * Tests the towers' costs across both difficulty and
     * type. It is essential for the game's functionality that
     * the towers have different prices for each type AND the
     * tower's price increases with difficulty level for all
     * scenarios of tower type and difficulty. This ensures that no
     * matter the player's choice(s), the tower pricing will
     * function correctly.
     */
    @Test
    public void checkTowerCosts() {
        int ebasic, mbasic, hbasic;
        int eint, mint, hint;
        int eadv, madv, hadv;
        DataHolder d = DataHolder.getInstance();
        GameController g = new GameController(d);
        g.calculateTowerCost("Basic Tower");
        ebasic = d.getTowerCost();
        g.calculateTowerCost("Intermediate Tower");
        eint = d.getTowerCost();
        g.calculateTowerCost("Advanced Tower");
        eadv = d.getTowerCost();
        d.setDifficulty("Medium");
        g.calculateMultiplier();
        g.calculateTowerCost("Basic Tower");
        mbasic = d.getTowerCost();
        g.calculateTowerCost("Intermediate Tower");
        mint = d.getTowerCost();
        g.calculateTowerCost("Advanced Tower");
        madv = d.getTowerCost();
        d.setDifficulty("Hard");
        g.calculateMultiplier();
        g.calculateTowerCost("Basic Tower");
        hbasic = d.getTowerCost();
        g.calculateTowerCost("Intermediate Tower");
        hint = d.getTowerCost();
        g.calculateTowerCost("Advanced Tower");
        hadv = d.getTowerCost();

        assertTrue(ebasic > 0 && ebasic < mbasic && mbasic < hbasic);
        assertTrue(eint > 0 && eint < mint && mint < hint);
        assertTrue(eadv > 0 && eadv < madv && madv < hadv);
    }
}

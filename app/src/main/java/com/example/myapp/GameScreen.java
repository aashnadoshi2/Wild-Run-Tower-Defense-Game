package com.example.myapp;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity implements View.OnTouchListener,
        View.OnDragListener {

    private boolean combatStarted = false;
    private static ArrayList<LinearLayout> pathTiles = new ArrayList<>();
    private static ArrayList<LinearLayout> emptyTiles = new ArrayList<>();
    private static ArrayList<Integer> towerType = new ArrayList<>();
    //stores where towers are places to save state of the game
    private static ArrayList<Integer> idList = new ArrayList<>();
    private static int i = 1;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private Handler handler;
    private static ArrayList<Tower> towerList = new ArrayList<>();
    //holds the views for each enemy in the order that they are instantiated
    private ArrayList<LinearLayout> eViews = new ArrayList<>();

    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        TextView startMoney = findViewById(R.id.startMoney);
        TextView monHealth = findViewById(R.id.monumentHealth);
        TextView difficulty = findViewById(R.id.showdifficulty);
        Toolbar myToolbar = findViewById(R.id.gametoolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        DataHolder data = DataHolder.getInstance();
        startMoney.setText(String.format("Money: %d", data.getMoney()));
        monHealth.setText(String.format("Health: %d", data.getMonumentHealth()));
        difficulty.setText(String.format("Difficulty: %s", data.getDifficulty()));
        Button storeButton = findViewById(R.id.button_store);
        if (storeButton != null) {
            storeButton.setOnClickListener(it -> {
                // displaying a toast message
                if (!combatStarted) {
                    Intent myIntent = new Intent(
                            GameScreen.this, BuyScreen.class);
                    startActivity(myIntent);
                    i = 1;
                }
            });
        }
        //handler  = new Handler();
        combat(data);
        doStuff();
    }

    public void doStuff() {
        Log.d("Call doStuff()", "Reached");
        String purchasedTower = getIntent().getStringExtra("Tower purchased");
        //Tower towerBought = getIntent().getParcelableExtra("Tower object");
        LinearLayout startLayout = findViewById(R.id.startLayout);
        LinearLayout map0105 = findViewById(R.id.map01_05);
        LinearLayout map0205 = findViewById(R.id.map02_05);
        LinearLayout map0207 = findViewById(R.id.map02_07);
        LinearLayout map0208 = findViewById(R.id.map02_08);
        LinearLayout map0209 = findViewById(R.id.map02_09);
        LinearLayout map0210 = findViewById(R.id.map02_10);
        LinearLayout map0211 = findViewById(R.id.map02_11);
        LinearLayout map0212 = findViewById(R.id.map02_12);
        LinearLayout map0213 = findViewById(R.id.map02_13);
        LinearLayout map0313 = findViewById(R.id.map03_13);
        LinearLayout map0404 = findViewById(R.id.map04_04);
        LinearLayout map0405 = findViewById(R.id.map04_05);
        LinearLayout map0406 = findViewById(R.id.map04_06);
        LinearLayout map0407 = findViewById(R.id.map04_07);
        LinearLayout map0408 = findViewById(R.id.map04_08);
        LinearLayout map0409 = findViewById(R.id.map04_09);
        LinearLayout map0410 = findViewById(R.id.map04_10);
        LinearLayout map0411 = findViewById(R.id.map04_11);
        LinearLayout map0412 = findViewById(R.id.map04_12);
        LinearLayout map0413 = findViewById(R.id.map04_13);
        LinearLayout map0503 = findViewById(R.id.map05_03);
        LinearLayout map0511 = findViewById(R.id.map05_11);
        LinearLayout map0513 = findViewById(R.id.map05_13);
        LinearLayout map0601 = findViewById(R.id.map06_01);
        LinearLayout map0602 = findViewById(R.id.map06_02);
        LinearLayout map0603 = findViewById(R.id.map06_03);
        LinearLayout map0605 = findViewById(R.id.map06_05);
        LinearLayout map0606 = findViewById(R.id.map06_06);
        LinearLayout map0607 = findViewById(R.id.map06_07);
        LinearLayout map0608 = findViewById(R.id.map06_08);
        LinearLayout map0609 = findViewById(R.id.map06_09);
        LinearLayout map0611 = findViewById(R.id.map06_11);
        LinearLayout map0613 = findViewById(R.id.map06_13);
        LinearLayout map0707 = findViewById(R.id.map07_07);
        LinearLayout map0709 = findViewById(R.id.map07_09);
        LinearLayout map0711 = findViewById(R.id.map07_11);
        LinearLayout map0713 = findViewById(R.id.map07_13);
        LinearLayout map0801 = findViewById(R.id.map08_01);
        LinearLayout map0802 = findViewById(R.id.map08_02);
        LinearLayout map0803 = findViewById(R.id.map08_03);
        LinearLayout map0807 = findViewById(R.id.map08_07);
        LinearLayout map0808 = findViewById(R.id.map08_08);
        LinearLayout map0809 = findViewById(R.id.map08_09);
        LinearLayout map0811 = findViewById(R.id.map08_11);
        LinearLayout map0813 = findViewById(R.id.map08_13);
        LinearLayout map0903 = findViewById(R.id.map09_03);
        LinearLayout map0905 = findViewById(R.id.map09_05);
        LinearLayout map0911 = findViewById(R.id.map09_11);
        LinearLayout map0913 = findViewById(R.id.map09_13);
        LinearLayout map1003 = findViewById(R.id.map10_03);
        LinearLayout map1005 = findViewById(R.id.map10_05);
        LinearLayout map1006 = findViewById(R.id.map10_06);
        LinearLayout map1007 = findViewById(R.id.map10_07);
        LinearLayout map1008 = findViewById(R.id.map10_08);
        LinearLayout map1009 = findViewById(R.id.map10_09);
        LinearLayout map1010 = findViewById(R.id.map10_10);
        LinearLayout map1011 = findViewById(R.id.map10_11);
        LinearLayout map1013 = findViewById(R.id.map10_13);
        LinearLayout map1103 = findViewById(R.id.map11_03);
        LinearLayout map1105 = findViewById(R.id.map11_05);
        LinearLayout map1113 = findViewById(R.id.map11_13);
        LinearLayout map1203 = findViewById(R.id.map12_03);
        LinearLayout map1213 = findViewById(R.id.map12_13);

        emptyTiles = new ArrayList<>(Arrays.asList(startLayout, map0105, map0205, map0207,
                map0208, map0209, map0210, map0211, map0212, map0213, map0313, map0404, map0405,
                map0406, map0407, map0408, map0409, map0410, map0411, map0413, map0503, map0511,
                map0513, map0601, map0602, map0603, map0605, map0606, map0607, map0608, map0609,
                map0611, map0613, map0707, map0709, map0711, map0713, map0801, map0802, map0803,
                map0807, map0808, map0809, map0811, map0813, map0903, map0905, map0911, map0913,
                map1003, map1005, map1006, map1007, map1008, map1009, map1010, map1011, map1013,
                map1103, map1105, map1113, map1203, map1213));

        LinearLayout map0701 = findViewById(R.id.map07_01);
        LinearLayout map0702 = findViewById(R.id.map07_02);
        LinearLayout map0703 = findViewById(R.id.map07_03);
        LinearLayout map0704 = findViewById(R.id.map07_04);
        LinearLayout map0604 = findViewById(R.id.map06_04);
        LinearLayout map0504 = findViewById(R.id.map05_04);
        LinearLayout map0505 = findViewById(R.id.map05_05);
        LinearLayout map0506 = findViewById(R.id.map05_06);
        LinearLayout map0507 = findViewById(R.id.map05_07);
        LinearLayout map0508 = findViewById(R.id.map05_08);
        LinearLayout map0509 = findViewById(R.id.map05_09);
        LinearLayout map0510 = findViewById(R.id.map05_10);
        LinearLayout map0610 = findViewById(R.id.map06_10);
        LinearLayout map0710 = findViewById(R.id.map07_10);
        LinearLayout map0810 = findViewById(R.id.map08_10);
        LinearLayout map0910 = findViewById(R.id.map09_10);
        LinearLayout map0909 = findViewById(R.id.map09_09);
        LinearLayout map0908 = findViewById(R.id.map09_08);
        LinearLayout map0907 = findViewById(R.id.map09_07);
        LinearLayout map0906 = findViewById(R.id.map09_06);
        LinearLayout map0806 = findViewById(R.id.map08_06);
        LinearLayout map0706 = findViewById(R.id.map07_06);
        LinearLayout map0705 = findViewById(R.id.map07_05);
        LinearLayout map0804 = findViewById(R.id.map08_04);
        LinearLayout map0904 = findViewById(R.id.map09_04);
        LinearLayout map1004 = findViewById(R.id.map10_04);
        LinearLayout map1104 = findViewById(R.id.map11_04);
        LinearLayout map1204 = findViewById(R.id.map12_04);
        LinearLayout map1205 = findViewById(R.id.map12_05);
        LinearLayout map1106 = findViewById(R.id.map11_06);
        LinearLayout map1107 = findViewById(R.id.map11_07);
        LinearLayout map1108 = findViewById(R.id.map11_08);
        LinearLayout map1109 = findViewById(R.id.map11_09);
        LinearLayout map1110 = findViewById(R.id.map11_10);
        LinearLayout map1211 = findViewById(R.id.map12_11);
        LinearLayout map1212 = findViewById(R.id.map12_12);
        LinearLayout map1112 = findViewById(R.id.map11_12);
        LinearLayout map1012 = findViewById(R.id.map10_12);
        LinearLayout map0912 = findViewById(R.id.map09_12);
        LinearLayout map0812 = findViewById(R.id.map08_12);
        LinearLayout map0712 = findViewById(R.id.map07_12);
        LinearLayout map0612 = findViewById(R.id.map06_12);
        LinearLayout map0512 = findViewById(R.id.map05_12);
        LinearLayout map0312 = findViewById(R.id.map03_12);
        LinearLayout map0311 = findViewById(R.id.map03_11);
        LinearLayout map0310 = findViewById(R.id.map03_10);
        LinearLayout map0309 = findViewById(R.id.map03_09);
        LinearLayout map0308 = findViewById(R.id.map03_08);
        LinearLayout map0307 = findViewById(R.id.map03_07);
        LinearLayout map0306 = findViewById(R.id.map03_06);
        LinearLayout map0305 = findViewById(R.id.map03_05);
        LinearLayout map0304 = findViewById(R.id.map03_04);
        LinearLayout map0204 = findViewById(R.id.map02_04);
        LinearLayout map0104 = findViewById(R.id.map01_04);
        pathTiles = new ArrayList<>(Arrays.asList(map0701, map0702, map0703, map0704, map0604,
                map0504, map0505, map0506, map0507, map0508, map0509, map0510, map0610, map0710,
                map0810, map0910, map0909, map0908, map0907, map0906, map0806, map0706, map0705,
                map0704, map0804, map0904, map1004, map1104, map1204, map1205, map1106, map1107,
                map1108, map1109, map1110, map1211, map1212, map1112, map1012, map0912, map0812,
                map0712, map0612, map0512, map0412, map0312, map0311, map0310, map0309, map0308,
                map0307, map0306, map0305, map0304, map0204, map0104));
        ImageView towerView = new ImageView(this);
        RelativeLayout.LayoutParams paramsImage = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsImage.addRule(RelativeLayout.CENTER_IN_PARENT);
        towerView.setLayoutParams(paramsImage);
        startLayout.addView(towerView);
        purchaseTowerSwitch(purchasedTower, towerView);
        towerView.setOnTouchListener(this);
        startLayout.setOnDragListener(this);
        for (LinearLayout layout: emptyTiles) {
            layout.setOnDragListener(this);
        }
        towerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String towerType = purchasedTower;
                Intent myIntent = new Intent(
                        GameScreen.this, UpgradeScreen.class);
                myIntent.putExtra("Tower type", purchasedTower);
                startActivity(myIntent);

                Log.d("Purchased tower", purchasedTower);

                boolean purchased = getIntent().getBooleanExtra("Purchase made", true);
                if (purchased) {
                    Log.d("Purchase Check", "True");
                    DataHolder data = DataHolder.getInstance();
                    purchaseUpgradeSwitch(purchasedTower, towerView);
                    Upgrade.upgradeTower(towerList.get(data.getTower().getIndex()));
                }
            }
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && i == 1) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            i++;
            return true;
        } else {
            return false;
        }
    }

    public boolean onDrag(View layoutView, DragEvent dragevent) {
        int action = dragevent.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d("Drag event", "Drag event started");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                layoutView.invalidate();
                Log.d("Drag event", "Drag event entered into " + layoutView.toString());
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                layoutView.invalidate();
                Log.d("Drag event", "Drag event exited from " + layoutView.toString());
                break;
            case DragEvent.ACTION_DROP:
                Log.d("Drag event", "Dropped at " + layoutView.toString());

                int finalId = layoutView.getId();
                LinearLayout finalVal = layoutView.findViewById(finalId);
                int finalID = layoutView.getId();
                idList.add(finalID);
                //if can drop, then execute code below, else return false
                emptyTiles.remove(finalVal);

                layoutView.invalidate();
                View view = (View) dragevent.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) layoutView;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                layoutView.invalidate();
                Log.d("Drag event", "Drag ended");

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void startCombat(LinearLayout enemyView, int startDelay, Enemy enemy) {
        enemy.setActive(true);

        float currentX = enemyView.getX();
        float currentY = enemyView.getY();

        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 485f);
        animatorY1.setDuration(2000);

        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(enemyView, "x",
                currentX - 220f);
        animatorX1.setDuration(2000);

        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 1270f);
        animatorY2.setDuration(2000);

        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(enemyView, "x",
                enemyView.getX() + 170f);
        animatorX2.setDuration(2000);

        ObjectAnimator animatorY3 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 750f);
        animatorY3.setDuration(2000);

        ObjectAnimator animatorX3 = ObjectAnimator.ofFloat(enemyView, "x",
                currentX - 25f);
        animatorX3.setDuration(1500);

        ObjectAnimator animatorY4 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 485f);
        animatorY4.setDuration(1500);

        ObjectAnimator animatorX4 = ObjectAnimator.ofFloat(enemyView, "x",
                currentX + 355f);
        animatorX4.setDuration(2000);

        ObjectAnimator animatorY5 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 1540f);
        animatorY5.setDuration(2000);

        ObjectAnimator animatorX5 = ObjectAnimator.ofFloat(enemyView, "x",
                currentX - 400f);
        animatorX5.setDuration(2000);

        ObjectAnimator animatorY6 = ObjectAnimator.ofFloat(enemyView, "y",
                currentY + 500f);
        animatorY6.setDuration(2000);

        ObjectAnimator animatorX6 = ObjectAnimator.ofFloat(enemyView, "x",
                currentX - 570);
        animatorX6.setDuration(1500);
        // 22,500 seconds through the path
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setStartDelay(startDelay);
        animationSet.playSequentially(animatorY1, animatorX1, animatorY2, animatorX2, animatorY3,
                animatorX3, animatorY4, animatorX4, animatorY5, animatorX5, animatorY6, animatorX6);
        animationSet.start();

        AnimatorSet eAnimationSet = new AnimatorSet();
        AnimatorSet healthAnimationSet = new AnimatorSet();

    }

    public void restoreGameState() {
        for (int k = 0; k < towerType.size(); k++) {
            LinearLayout linearLayout = findViewById(idList.get(k));
            linearLayout.setBackgroundResource(towerType.get(k));
        }
    }

    public boolean checkHealth(int health) {
        return health == 0;
    }

    public void purchaseTowerSwitch(String purchasedTower, ImageView towerView) {
        if (purchasedTower != null) {
            restoreGameState();
            switch (purchasedTower) {
                case "Basic Tower":
                    towerView.setImageResource(R.drawable.basic_tower);
                    towerView.setVisibility(View.VISIBLE);
                    towerType.add(R.drawable.basic_tower);
                    System.out.println("Basic Tower Purchased");
                    break;
                case "Intermediate Tower":
                    towerView.setImageResource(R.drawable.intermediate_tower);
                    towerView.setVisibility(View.VISIBLE);
                    towerType.add(R.drawable.intermediate_tower);
                    System.out.println("Intermediate Tower Purchased");
                    break;
                case "Advanced Tower":
                    towerView.setImageResource(R.drawable.advanced_tower);
                    towerView.setVisibility(View.VISIBLE);
                    towerType.add(R.drawable.advanced_tower);
                    System.out.println("Advanced Tower Purchased");
                    break;
                default:
                    break;
            }
        }
    }

    public void purchaseUpgradeSwitch(String purchasedTower, ImageView towerView) {
        if (purchasedTower != null) {
            Log.d("Purchase upgrade switch", "Reached");
            restoreGameState();
            DataHolder data = DataHolder.getInstance();
            switch (purchasedTower) {
                case "Basic Tower":
                    towerView.setImageResource(R.drawable.upgrade_basic);
                    int idx = data.getTower().getIndex();
                    if (idx != -1) {
                        Log.d("Check index", "" + idx);
                        towerType.set(idx, R.drawable.upgrade_basic);
                    }
                    break;
                case "Intermediate Tower":
                    towerView.setImageResource(R.drawable.upgrade_intermediate);
                    idx = data.getTower().getIndex();
                    if (idx != -1) {
                        towerType.set(idx, R.drawable.upgrade_intermediate);
                    }
                    break;
                case "Advanced Tower":
                    towerView.setImageResource(R.drawable.upgrade_advanced);
                    idx = data.getTower().getIndex();
                    if (idx != -1) {
                        towerType.set(idx, R.drawable.upgrade_advanced);
                    }
                    break;
                default:
                    break;
            }
            Log.d("Reached default", "");
            towerView.setVisibility(View.INVISIBLE);
            towerView.setVisibility(View.VISIBLE);
            restoreGameState();
        }
    }

    /*
     * READ THIS
     * The current method for implementing combat is to have a
     * repeating loop that keeps track of repetitions and repeats every 250ms.
     * These loops would run independently for each enemy.
     * By counting the number of loops (counts) passed, timing can be
     * coordinated for when the enemies start travel, reach the end of the path,
     * and for when the towers attack the monuments.
     * While iterating through each enemy, each tower is then iterated over every 250ms,
     * checking if the enemy is in range. If in range, then the enemy is attacked.
     * Ideally, we need to get the mechanics for attacking down before we add animations.
     * When an enemy is destroyed (its health equals zero), rather than attempting to
     * completely erase the enemy, the goal is to set its View to INVISIBLE and
     * write methods such that the enemy cannot attack the monument or be attacked
     * when its health is 0.
     *
     * May face an issue where count is not independent for each enemy
     */
    public void combat(DataHolder data) {
        Button startCombatButton = findViewById(R.id.button_startCombat);
        System.out.println("Monument health" + data.getMonumentHealth());
        startCombatButton.setOnClickListener(it -> {
            combatStarted = true;
            enemies.add(new Enemy("Enemy1"));
            //enemies.add(new Enemy("Enemy1"));
            enemies.add(new Enemy("Enemy2"));
            //enemies.add(new Enemy("Enemy2"));
            enemies.add(new Enemy("Enemy3"));
            enemies.add(new FinalBoss());

            TextView monHealthUpdate = findViewById((R.id.monumentHealth));
            int delay = 500;
            //iterates through each enemy
            for (int i = 0; i < enemies.size(); i++) {
                //sets current enemy
                Enemy enemy = enemies.get(i);
                enemy.setNumber(i);
                //sets the correct layout for each type of enemy
                LinearLayout enemyLinearLayout;
                if (enemy.getType().equals("FinalBoss")) {
                    enemyLinearLayout = (LinearLayout) findViewById(R.id.finalBoss);
                }
                else if (enemy.getType().equals("Enemy1")) {
                    enemyLinearLayout = (LinearLayout) findViewById(R.id.enemyView);
                } else if (enemy.getType().equals("Enemy2")) {
                    enemyLinearLayout = (LinearLayout) findViewById(R.id.enemyView2);
                } else {
                    enemyLinearLayout = (LinearLayout) findViewById(R.id.enemyView3);
                }

                TextView enemyHealth = new TextView(this);
                enemyHealth.setText("" + enemy.getHealth());
                enemyHealth.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                enemyLinearLayout.addView(enemyHealth);
                enemyLinearLayout.setVisibility(View.VISIBLE);
                eViews.add(enemyLinearLayout);

                //The first enemy should start its combat animation at 500ms,
                //with 2.5 seconds between each enemy after that

                startCombat(enemyLinearLayout, 500 + i * 2500, enemy);
                //The code within run() should repeat every 250ms
                monHealthUpdate.postDelayed(new Runnable() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void run() {
                        // count is the number of 250ms intervals since combat started
                        // it takes 22500/250 = 90 counts to reach the end of the path
                        //int count = 0;
                        //checks if enemy has been spawned yet
                        //enemy.number tracks the order for enemies to spawn
                        //unspawned enemies should not be able to be attacked
                        //checks if enemy has reached the end of the path
                        //-2 for the 500ms delay for the first enemy to start
                        //then 90 counts to reach the end of the path plus
                        //enemy.number*10 to add time before enemy starts to travel
                        //eg. 92 counts until enemy 0 reaches end of path, 102 for enemy 1...

                        if (enemy.getHealth() != 0 && enemy.getActive()) {
                            data.attackMonument(enemy);
                        }
                        if (data.getMonumentHealth() == 0 && combatStarted) {
                            endGame(data);
                        }
                        monHealthUpdate.setText(String.format("Health %d",
                                data.getMonumentHealth()));
                        enemyLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }, 22500 + delay);
                delay += 2500;
            }
            //                //debugging purposes can be useful for M5
            //                float startingPointX = enemyLinearLayout.getX();
            //                float startingPointY = enemyLinearLayout.getY();
            //                Log.d("Starting Point", "x: " + startingPointX + "Y: " +
            //                startingPointY);
            //                Log.d("Starting Tile", "Path Tile: " + pathTiles.get(3));
        });
    }

    // code below uses a timerTask as referenced by https://examples.javacodegeeks.com/android/core/activity/android-timertask-example/
    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 0, 1000);
    }

    private int ct = 0;

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (combatStarted) {
                            for (int k = 0; k < towerList.size(); k++) {
                                LinearLayout tow = findViewById(idList.get(k));
                                checkEnemies(towerList.get(k), tow);
                            }
                            checkIfRoundEnded();
                            if (!combatStarted) {
                                winGame();
                            }
                            ct++;
                        }
                    }
                });
            }
        };

    }

    public void winGame() {
        Intent myIntent = new Intent(this, WinningScreen.class);
        startActivity(myIntent);
    }


    public void endGame(DataHolder data) {
        System.out.println("Health " + data.getMonumentHealth());
        combatStarted = false;
        Log.d("Game Over", "game over screen");
        Intent myIntent = new Intent(
                this, GameOverScreen.class);
        startActivity(myIntent);
    }

    public static void addToTowerList(Tower t) {
        towerList.add(t);
    }

    public static ArrayList<Tower> getTowerList() { return towerList; }

    public int checkTowerList(Tower tower) {
        for (int i = 0; i < towerList.size(); i++) {
            if (tower == towerList.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public void checkTowers(Enemy enemy, ArrayList<LinearLayout> eViews) {
        for (int k = 0; k < towerList.size(); k++) {
            Tower curr = towerList.get(k);
            LinearLayout tow = findViewById(idList.get(k));
            boolean inRange = curr.getRange() >= getDistance(eViews.get(enemy.getNumber()), tow);
            if (inRange && enemy.getHealth() > 0 && enemy.getActive()) {
                enemy.attack(curr);
                if (enemy.getHealth() <= 0) {
                    eViews.get(enemy.getNumber()).setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void checkEnemies(Tower tower, LinearLayout tow) {
        Enemy closest = null;
        double leastDistance = 10000000.0;
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            LinearLayout eV = eViews.get(i);
            double distance = getDistance(tow, eV);
            if (enemy.getHealth() > 0 && enemy.getActive() && distance < leastDistance) {
                closest = enemy;
                leastDistance = distance;
            }
        }
        if (closest != null && leastDistance <= tower.getRange()) {
            closest.attack(tower);
            TextView enemyHealth = (TextView) eViews.get(closest.getNumber()).getChildAt(0);
            enemyHealth.setText("" + closest.getHealth());
            projectile(eViews.get(closest.getNumber()), tow, tower);
            if (closest.getHealth() <= 0) {
                DataHolder data = DataHolder.getInstance();
                data.killEnemy();
                addMoney(20);
                eViews.get(closest.getNumber()).setVisibility(View.INVISIBLE);
            }
        }
    }

    public double getDistance(LinearLayout towerView, LinearLayout enemyView) {
        double enemyX = (double) enemyView.getX();
        double enemyY = (double) enemyView.getY();
        double towerX = (double) towerView.getX();
        double towerY = (double) towerView.getY();

        return Math.sqrt(Math.pow((enemyX - towerX), 2) + Math.pow((enemyY - towerY), 2));
    }

    // updates combatStarted after combat has started to tell when round ends
    public void checkIfRoundEnded() {
        combatStarted = false;
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                combatStarted = true;
            }
        }
    }

    public void addMoney(int amount) {
        DataHolder  data = DataHolder.getInstance();
        data.setMoney(data.getMoney() + amount);
        TextView startMoney = findViewById(R.id.startMoney);
        startMoney.setText(String.format("Money: %d", data.getMoney()));
    }

    public void projectile(LinearLayout eV, LinearLayout tV, Tower tower) {
        LinearLayout attackEnemyView = findViewById(R.id.attackEnemyView);
        float enemyX = (float) eV.getX();
        float enemyY = (float) eV.getY();
        float towerX = (float) tV.getX();
        float towerY = (float) tV.getY();

        attackEnemyView.setX((float) towerX);
        attackEnemyView.setY((float) towerY);

        if (tower.getName().equals("Basic Tower")) {
            attackEnemyView.setBackgroundResource(R.drawable.basic_tower_attack);
            attackEnemyView.setVisibility(View.VISIBLE);
        } else if (tower.getName().equals("Intermediate Tower")) {
            attackEnemyView.setBackgroundResource(R.drawable.intermediate_tower_attack);
            attackEnemyView.setVisibility(View.VISIBLE);
        } else {
            attackEnemyView.setBackgroundResource(R.drawable.advanced_tower_attack);
            attackEnemyView.setVisibility(View.VISIBLE);
        }

        TranslateAnimation anim = new TranslateAnimation(0, enemyX - towerX, 0, enemyY - towerY);
        anim.setDuration(100);
        attackEnemyView.startAnimation(anim);
        if (anim.hasEnded()) {
            attackEnemyView.clearAnimation();
        }

    }
}
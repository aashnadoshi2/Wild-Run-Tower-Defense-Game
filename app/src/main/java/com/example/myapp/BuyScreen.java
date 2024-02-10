package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_screen);

        Spinner dropdownBuy = findViewById(R.id.dropdownBuy);
        String[] items = new String[]{"Basic Tower", "Intermediate Tower", "Advanced Tower"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdownBuy.setAdapter(adapter);

        TextView towerHealth = (TextView) findViewById(R.id.towerHealth);
        TextView towerCost = (TextView) findViewById(R.id.towerCost);

        Button submitButton = (Button) findViewById(R.id.button_submit);

        //set values of TextView according to tower selected on BuyScreen
        if (dropdownBuy != null) {
            dropdownBuy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String tower = dropdownBuy.getSelectedItem().toString();
                    DataHolder data = DataHolder.getInstance();
                    GameController gameController = new GameController(data);
                    gameController.calculateTowerCost(tower);
                    gameController.calculateTowerHealth(tower);
                    towerHealth.setText("Tower Health: " + data.getTowerHealth());
                    towerCost.setText("Tower Cost: " + data.getTowerCost());
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        // operations to be performed
        // when user tap on the button
        if (submitButton != null) {
            submitButton.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {
                    boolean purchaseMade = false;
                    String tower = dropdownBuy.getSelectedItem().toString();
                    DataHolder data = DataHolder.getInstance();
                    data.setTowerName(tower);

                    GameController gameController = new GameController(data);
                    gameController.calculateTowerCost(tower);
                    gameController.calculateTowerHealth(tower);
                    if (data.getMoney() < data.getTowerCost()) {
                        Toast.makeText((Context) BuyScreen.this,
                                R.string.buyMessage, Toast.LENGTH_LONG).show();
                    } else {
                        gameController.buyTower();
                        purchaseMade = true;
                    }


                    // displaying a toast message
                    Intent myIntent = new Intent(
                            BuyScreen.this, GameScreen.class);
                    if (purchaseMade) {
                        Tower towerBought = new Tower(data.getTowerName());
                        towerBought.setIndex(GameScreen.getTowerList().size());
                        GameScreen.addToTowerList(towerBought);
                        data.setTower(towerBought);
                        myIntent.putExtra("Tower purchased", data.getTowerName());
                        //myIntent.putExtra("Tower object", towerBought);
                    } else {
                        myIntent.putExtra("Tower purchased", "None");
                    }
                    startActivity(myIntent);

                    //finish();

                }
            }));
        }
    }
}
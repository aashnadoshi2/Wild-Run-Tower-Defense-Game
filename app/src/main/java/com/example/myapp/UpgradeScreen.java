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

public class UpgradeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_screen);

        DataHolder data = DataHolder.getInstance();
        String towerType = getIntent().getStringExtra("Tower type");

        Upgrade upgrade = new Upgrade(towerType);
        TextView upgradeCost = (TextView) findViewById(R.id.upgradeCost);

        data.setUpgrade(upgrade);
        upgradeCost.setText("Upgrade Cost: " + data.getUpgradeCost());

        Button buyButton = (Button) findViewById(R.id.upgradeButton);

        // operations to be performed
        // when user tap on the button
        if (buyButton != null) {
            buyButton.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {
                    boolean purchaseMade = false;

                    GameController gameController = new GameController(data);

                    if (data.getMoney() < data.getUpgradeCost()) {
                        Toast.makeText((Context) UpgradeScreen.this,
                                R.string.buyMessage, Toast.LENGTH_LONG).show();
                    } else {
                        gameController.buyUpgrade();
                        purchaseMade = true;
                    }


                    // displaying a toast message
                    Intent myIntent = new Intent(
                            UpgradeScreen.this, GameScreen.class);
                    myIntent.putExtra("Purchase made", purchaseMade);
                    startActivity(myIntent);
                }
            }));
        }
    }
}
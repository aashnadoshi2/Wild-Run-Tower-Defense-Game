package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        TextView statView1 = findViewById(R.id.statView1);
        TextView statView2 = findViewById(R.id.statView2);
        TextView statView3 = findViewById(R.id.statView3);
        DataHolder data = DataHolder.getInstance();
        statView1.setText(String.format("Enemies Defeated: %d", data.getEnemiesDefeated()));
        statView2.setText(String.format("Money Spent: %d", data.getMoneySpent()));
        statView3.setText(String.format("Damage Dealt: %d", data.getDamageDealt()));

        Button restartButton = findViewById(R.id.restartButton);
        if (restartButton != null) {
            restartButton.setOnClickListener(it -> {
                Intent myIntent = new Intent(
                        GameOverScreen.this, ConfigurationScreen.class);
                startActivity(myIntent);
            });
        }

    }
}
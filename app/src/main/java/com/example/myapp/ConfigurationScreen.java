package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfigurationScreen extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_screen);

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Easy", "Medium", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button submitButton = (Button) findViewById(R.id.submit);

        // operations to be performed
        // when user tap on the button
        if (submitButton != null) {
            submitButton.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {
                    EditText nameEntry = (EditText) findViewById(R.id.editText1);
                    name = nameEntry.getText().toString(); //make global variable
                    if (name == null || name.trim().isEmpty()) {
                        Toast.makeText((Context) ConfigurationScreen.this,
                                R.string.message, Toast.LENGTH_LONG).show();
                        name = null;
                    }
                }
            }));
        }

        Button nextButton = (Button) findViewById(R.id.button_next);

        // operations to be performed
        // when user tap on the button
        if (nextButton != null) {
            nextButton.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {

                    // displaying a toast message
                    if (name != null) {
                        String difficulty = dropdown.getSelectedItem().toString();
                        DataHolder data = DataHolder.getInstance();
                        data.setDifficulty(difficulty);
                        GameController gameController = new GameController(data);
                        gameController.calculateMultiplier();
                        gameController.calculateStartMoney();
                        gameController.calculateMonumentHealth();
                        Intent myintent = new Intent(
                                ConfigurationScreen.this, GameScreen.class);
                        startActivity(myintent);
                    }
                }
            }));
        }
    }
}
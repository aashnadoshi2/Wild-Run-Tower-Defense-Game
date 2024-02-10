package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.simpleButton1);
        Button button2 = (Button) findViewById(R.id.simpleButton2);

        // operations to be performed
        // when user tap on the button
        if (button != null) {
            button.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {

                    // displaying a toast message
                    Intent myintent = new Intent(
                            MainActivity.this, ConfigurationScreen.class);
                    startActivity(myintent);
                }
            }));
        }

        if (button2 != null) {
            button2.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                public final void onClick(View it) {
                    finish();
                }
            }));
        }
    }
}
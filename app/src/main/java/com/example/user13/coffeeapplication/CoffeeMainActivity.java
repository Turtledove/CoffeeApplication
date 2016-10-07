package com.example.user13.coffeeapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoffeeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_main);
        setTitle("Coffee Orders");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CoffeeMainActivity.this, SecondCoffeeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4*1000);
    }
}

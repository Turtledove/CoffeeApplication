package com.example.user13.coffeeapplication;


import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondCoffeeActivity extends AppCompatActivity {

    private int quantity = 2;
    private double price = 5.0;
    private double totalPrice = 0.0;
    private Boolean hasWhippedCream = false;
    private Boolean hasChocolate = false;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_coffee);
    }

    public void onClickMinus(View view) {

        if(quantity == 1)
        {
            quantity= quantity;
            Toast.makeText(getBaseContext(), "Coffee cannot exceed be less than zero cups", Toast.LENGTH_LONG).show();
        }
        else
        {
            quantity -= 1;
        }
            displayQuantity(String.valueOf(quantity));
    }

    public void onClickPlus(View view) {

        if(quantity ==100)
        {
            quantity = quantity;
            Toast.makeText(getBaseContext(), "Coffee cannot exceed 100 cups", Toast.LENGTH_LONG).show();
        }
        else
        {
            quantity += 1;
        }
        displayQuantity(String.valueOf(quantity));
    }

    public  void isChocolateChecked(View view)
    {
        CheckBox chk = (CheckBox)view; // my checkbox code
        if(chk.isChecked())
        {
            hasChocolate = true;
            price+=2;
        }
        else
        {
            hasChocolate = false;
            price-=2;
        }
    }

    public  void isChecked(View view)
    {
        CheckBox chk = (CheckBox)view; // my 2nd checkbox
        if(chk.isChecked())
        {
            hasWhippedCream = true;
            price+=1;
        }
        else
        {
            hasWhippedCream = false;
            price-=1;
        }
    }

    public double calculatePrice(int qty, double price) {
        double totPrice = qty * price;
        return totPrice;
    }

    public String createorderSummary(double price) { // display message
        String summary =    "Name : " + name + "\n" +
                            "Add whipped cream?  " + hasWhippedCream + "\n" +
                            "Add Chocolate?  " + hasChocolate + "\n" +
                            "Quantity " + quantity + "\n" +
                            "Total $ " + price + "\n" +
                            "Thank you";
        return summary;
    }

    public void onClickOrder(View view) {
        EditText edName = (EditText)findViewById(R.id.editTextName);
        if(edName.getText().toString().matches(""))
        {
            Toast.makeText(getBaseContext(), "Enter your name", Toast.LENGTH_LONG).show();
        }
        else
        {
            name = edName.getText().toString();

            totalPrice = calculatePrice(quantity, price);
            String summary = createorderSummary(totalPrice);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // sending email

            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for : " + name);
            intent.putExtra(Intent.EXTRA_TEXT, summary);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

            displayTotalPrice(summary);
        }
    }

    public void displayQuantity(String value) {
        TextView tv = (TextView) findViewById(R.id.quantity);
        tv.setText(value);
    }

    public void displayTotalPrice(String value) {
        TextView tv = (TextView) findViewById(R.id.total_price);
        tv.setText(value);
    }
}

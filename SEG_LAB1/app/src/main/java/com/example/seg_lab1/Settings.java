package com.example.seg_lab1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Settings extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMonthlyPaymentResult,mTotalPaymentsResult;
    String currentcurrency, payment_frequency;
    public static final String[] currency = {"Select your currency", "$", "€","£"};
    public static final String[] payment_mode = {"Select the payment mode", "bi-weekly", "weekly", "monthly"};
    Spinner spinner , secondSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        spinner = findViewById(R.id.currencies);
        secondSpinner = findViewById(R.id.paymentfrequency);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,payment_mode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(secondAdapter);
    }


    public void OnWelcomePage(View view) {

        currentcurrency  = spinner.getSelectedItem().toString();
        payment_frequency = secondSpinner.getSelectedItem().toString();

        if(spinner.getSelectedItem().toString().equals(currency[0])){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid currency selected");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (spinner.getSelectedItem().toString().equals(payment_mode[0])){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid payment mode selected");
            alert.setMessage("Please chose a valid payment mode");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else  {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("CURRENT_CURRENCY", currentcurrency);
            intent.putExtra("PAYMENT_FREQUENCY", payment_frequency);
            startActivity(intent);
        }
    }
}

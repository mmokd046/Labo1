package com.example.seg_lab1;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SummaryScreen extends AppCompatActivity {
    private TextView mMonthlyPaymentResult,mTotalPaymentsResult;
    private String  periodic_payment,total_payment, mortgage_amount, interest_rate, amortisation_period, payment_frequency,currentCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_screen);

        mMonthlyPaymentResult = findViewById(R.id.monthly_payment_result);
        mTotalPaymentsResult = findViewById(R.id.total_payment_result);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            periodic_payment = bd.get("PAYMENT").toString();
            total_payment = bd.get("TOTAL_AMOUNT").toString();
            mortgage_amount = bd.get("MORTGAGE_AMOUNT").toString();
            interest_rate = bd.get("INTEREST_RATE").toString();
            amortisation_period = bd.get("AMORTISATION_PERIOD").toString();
            payment_frequency = bd.get("PAYMENT_FREQUENCY").toString();
            currentCurrency = bd.get("CURRENCY").toString();

            mMonthlyPaymentResult.setText( periodic_payment +" "+ currentCurrency+ " / " + payment_frequency);
            mTotalPaymentsResult.setText(total_payment + " "+ currentCurrency);

        }

    }


    public void OnNext(View view) {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("MORTGAGE_AMOUNT",  mortgage_amount);
        intent.putExtra("INTEREST_RATE",  interest_rate);
        intent.putExtra("AMORTISATION_PERIOD",amortisation_period);
        intent.putExtra("CURRENCY", currentCurrency);
        intent.putExtra("PAYMENT_FREQUENCY", payment_frequency);
        intent.putExtra("PAYMENT", periodic_payment);
        intent.putExtra("TOTAL_AMOUNT", total_payment);
        startActivity(intent);
    }
}


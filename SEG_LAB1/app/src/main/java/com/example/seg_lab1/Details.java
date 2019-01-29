package com.example.seg_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Details extends AppCompatActivity {
    private TextView mortgage_amount,interest_rate, amortisation_period, payment_frequency, interest_paid, payment_amount, principal_ammount, final_amount;
    private String  periodic_payment,total_payment, mortGageAmountValue, interestRateValue, amortisationPeriodValue, paymentFrequencyValue,currentCurrency;


    private void DisplayPaymentInfo () {

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            periodic_payment = bd.get("PAYMENT").toString();
            total_payment = bd.get("TOTAL_AMOUNT").toString();
            mortGageAmountValue = bd.get("MORTGAGE_AMOUNT").toString();
            interestRateValue = bd.get("INTEREST_RATE").toString();
            amortisationPeriodValue = bd.get("AMORTISATION_PERIOD").toString();
            paymentFrequencyValue = bd.get("PAYMENT_FREQUENCY").toString();
            currentCurrency = bd.get("CURRENCY").toString();

            double  interestPaid = Double.parseDouble(total_payment) - Double.parseDouble(mortGageAmountValue);

            payment_amount.setText(periodic_payment+" "+currentCurrency+ "/"+paymentFrequencyValue);
            mortgage_amount.setText(mortGageAmountValue+" "+currentCurrency);
            interest_rate.setText(interestRateValue + " %");
            amortisation_period.setText(amortisationPeriodValue);
            //currency.setText(currentCurrency);
            payment_frequency.setText(paymentFrequencyValue);
            principal_ammount.setText(mortGageAmountValue);
            final_amount.setText(total_payment);
            interest_paid.setText(new DecimalFormat("##.##").format(interestPaid));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        mortgage_amount = findViewById(R.id.mortgageAmountEntered);
        interest_rate = findViewById(R.id.interestRateEntered);
        amortisation_period = findViewById(R.id.amortizationPeriodEntered);
        //currency = findViewById(R.id.);
        payment_frequency = findViewById(R.id.paymentFrequencyCalc);
        interest_paid = findViewById(R.id.interestPayedCalc);
        payment_amount = findViewById(R.id.paymentAmountCalc);
        final_amount=findViewById(R.id.totalPaymentOverTermCalc);
        principal_ammount = findViewById(R.id.principalPayedCalc);
        DisplayPaymentInfo();
    }


    public void OnNext(View view) {
            Intent intent = new Intent(this, SummaryScreen.class);
            intent.putExtra("MORTGAGE_AMOUNT",  mortGageAmountValue);
            intent.putExtra("INTEREST_RATE",  interestRateValue);
            intent.putExtra("AMORTISATION_PERIOD",amortisationPeriodValue);
            intent.putExtra("CURRENCY", currentCurrency);
            intent.putExtra("PAYMENT_FREQUENCY", paymentFrequencyValue);
            intent.putExtra("PAYMENT", periodic_payment);
            intent.putExtra("TOTAL_AMOUNT", total_payment);
            startActivity(intent);
    }
}
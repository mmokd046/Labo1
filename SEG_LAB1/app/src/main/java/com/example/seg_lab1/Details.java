package com.example.seg_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mortgage_amount,interest_rate, amortisation_period, currency, payment_frequency, interest_paid, principal_payment, balance_end_of_term;
//    public static final String[] currency = {"Select your currency...","$", "€","£"};
//    public static final String[] payment_mode = {"Select your payment mode...","bi-weekly", "weekly", "monthly"};
//    private Spinner spinner, secondSpinner;
//    private String [] answers = new String [5];

    private void DisplayPaymentInfo () {

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            mortgage_amount.setText(bd.get("MORTGAGE_AMOUNT").toString());
            interest_rate.setText(bd.get("INTEREST_RATE").toString());
            amortisation_period.setText(bd.get("AMORTISATION_PERIOD").toString());
            currency.setText(bd.get("CURRENCY").toString());
            payment_frequency.setText(bd.get("PAYMENT_FREQUENCY").toString());
            interest_paid.setText(bd.get("MORTGAGE_AMOUNT").toString());
            principal_payment.setText(bd.get("MORTGAGE_AMOUNT").toString());
            balance_end_of_term.setText(bd.get("MORTGAGE_AMOUNT").toString());

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        mortgage_amount = findViewById(R.id.payment_amount);
        interest_rate = findViewById(R.id.mortgage_amount);
        amortisation_period = findViewById(R.id.interest_rate);
        currency = findViewById(R.id.amortization_period);
        payment_frequency = findViewById(R.id.payment_frequency);
        interest_paid = findViewById(R.id.interest_paid);
        principal_payment = findViewById(R.id.principal_paid);
        balance_end_of_term = findViewById(R.id.balance_at_end_of_term);

    }

//        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
//        mInterestRate = (EditText)findViewById(R.id.interest_rate);
//        mLoanPeriod= (EditText)findViewById(R.id.loan_period);
//
//        spinner = (Spinner)findViewById(R.id.currency);
//        secondSpinner = (Spinner)findViewById(R.id.payment_frequency);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item,currency);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item,payment_mode);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        secondSpinner.setAdapter(secondAdapter);
//    }

//    public void OnFinish(View view) {
//
//        answers[0] =  mLoanAmount.getText().toString();
//        answers[1] = mInterestRate.getText().toString();
//        answers[2] = mLoanPeriod.getText().toString();
//        answers[3] = spinner.getSelectedItem().toString();
//        answers[4] = secondSpinner.getSelectedItem().toString();
//
//
//        boolean invalid = false;
//        int i =0;
//        while(!invalid && i<answers.length ) {
//            if (answers[i].isEmpty()) {
//                invalid = true;
//            }
//            i++;
//        }
//        if(invalid){
//            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
//            alert.setTitle("Empty field alert");
//            alert.setMessage("You need to fill up all the field");
//            alert.setPositiveButton("OK",null);
//            alert.show();
//        } else if (spinner.getSelectedItem().toString().equals(currency[0])){
//            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
//            alert.setTitle("Currency not selected");
//            alert.setMessage("Please choose a currency");
//            alert.setPositiveButton("OK",null);
//            alert.show();
//        } else if (secondSpinner.getSelectedItem().toString().equals(payment_mode[0])) {
//            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
//            alert.setTitle("Payment frequency not selected");
//            alert.setMessage("Please select a payment frequency");
//            alert.setPositiveButton("OK",null);
//            alert.show();
//        }  else{
//            Intent intent = new Intent(this, SummaryScreen.class);
//            intent.putExtra("MORTGAGE_AMOUNT",  answers[0]);
//            intent.putExtra("INTEREST_RATE",  answers[1]);
//            intent.putExtra("AMORTISATION_PERIOD", answers[2]);
//            intent.putExtra("CURRENCY", answers[3]);
//            intent.putExtra("PAYMENT_FREQUENCY", answers[4]);
//            startActivity(intent);
//            finish();
//        }
//    }
}

package com.example.seg_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    public String currentCurrency;
    public String currentPayment_mode;
    private String [] answers = new String [3];
    public static final String[] amortisation_period_unit = {"Select amortisation period unit","year", "month"};
    public Spinner timeSpinner;
    public static final String[] time = {"Select Time", "month", "year",};

    public Double [] showLoanPayments(){
        verifyAmountEntered(mLoanAmount.getText().toString());
        verifyAmountEntered(mInterestRate.getText().toString());
        verifyAmountEntered(mLoanPeriod.getText().toString());
        double loanAmount = Double.parseDouble(mLoanAmount.getText().toString());
        double interestRate = Double.parseDouble(mInterestRate.getText().toString());
        double loanPeriod = 0;


        if(timeSpinner.getSelectedItem().toString().equals(time[1])){
            loanPeriod=Double.parseDouble(mLoanPeriod.getText().toString());
        } else if(timeSpinner.getSelectedItem().toString().equals(time[2])){
            loanPeriod=Double.parseDouble(mLoanPeriod.getText().toString())*12;

        }
        double r=0;
        double r1=0;
        r = interestRate/1200;
        r1=Math.pow(r+1,loanPeriod);

        Double [] payments = new Double[2];
        if (currentPayment_mode.equals("monthly")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount);
                payments [1] = payments [0]*loanPeriod;
        } else if (currentPayment_mode.equals("bi-weekly")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) / 2;
                payments [1] = payments [0]*loanPeriod*2;

        } else {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) / 4;
                payments [1] = payments [0]*loanPeriod*4;
        }
        return payments;
    }

    private boolean verifyAmountEntered(String amount) {
        return !Character.isLetter(amount.charAt(0)) && !(Double.parseDouble(amount) < 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            currentCurrency = bd.get("CURRENT_CURRENCY").toString();
            currentPayment_mode = bd.get("PAYMENT_FREQUENCY").toString();
        } else {
            currentCurrency = "$";
            currentPayment_mode = "monthly";
        }

        mLoanAmount = (EditText)findViewById(R.id.mortgageAmountEntered);
        mInterestRate = (EditText)findViewById(R.id.interestRateEntered);
        mLoanPeriod= (EditText)findViewById(R.id.amortizationPeriodEntered);

        mLoanAmount.setHint("ex : 10,000 "+ currentCurrency );

        timeSpinner=findViewById(R.id.timeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,time);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

    }

    public void Onsettings (View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void OnFinish(View view) ={

        answers[0] =  mLoanAmount.getText().toString();
        answers[1] = mInterestRate.getText().toString();
        answers[2] = mLoanPeriod.getText().toString();



        boolean invalid = false;
        int i =0;
        while(!invalid && i<answers.length ) {
            if (answers[i].isEmpty()) {
                invalid = true;
            }
            i++;
        }
        if(invalid){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (!verifyAmountEntered(mLoanAmount.getText().toString())){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid loan amount entered");
            alert.setMessage("Please enter a valid loan amount using this format ...");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (! verifyAmountEntered(mInterestRate.getText().toString())) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Payment frequency not selected");
            alert.setMessage("Please select a payment frequency");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (! verifyAmountEntered(mLoanPeriod.getText().toString()) ) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid Loan amount entered");
            alert.setMessage("Please enter a valid loan amount ");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else{
            Intent intent = new Intent(this, SummaryScreen.class);
            intent.putExtra("MORTGAGE_AMOUNT",  answers[0]);
            intent.putExtra("INTEREST_RATE",  answers[1]);
            intent.putExtra("AMORTISATION_PERIOD", answers[2]);
            intent.putExtra("CURRENCY", currentCurrency);
            intent.putExtra("PAYMENT_FREQUENCY", currentPayment_mode);
            intent.putExtra("PAYMENT", new DecimalFormat("##.##").format(showLoanPayments()[0]));
            intent.putExtra("TOTAL_AMOUNT", new DecimalFormat("##.##").format(showLoanPayments()[1]));
            startActivity(intent);
        }
    }
}
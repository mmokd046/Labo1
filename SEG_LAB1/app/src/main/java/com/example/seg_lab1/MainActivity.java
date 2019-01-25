package com.example.seg_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMonthlyPaymentResult,mTotalPaymentsResult;
    public String currentCurrency;
    public String currentPayment_mode;
    private String [] answers = new String [3];


    public Double [] showLoanPayments(){
        verifyAmountEntered(mLoanAmount.getText().toString());
        verifyAmountEntered(mInterestRate.getText().toString());
        verifyAmountEntered(mLoanPeriod.getText().toString());
        double loanAmount = Double.parseDouble(mLoanAmount.getText().toString());
        double interestRate = Double.parseDouble(mInterestRate.getText().toString());
        double loanPeriod = Double.parseDouble(mLoanPeriod.getText().toString());
        double r = interestRate/1200;
        double r1=Math.pow(r+1,loanPeriod);
        Double [] payments = new Double[2];
        if (currentPayment_mode.equals("monthly")) {
            if (currentCurrency.equals("$")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount);
                payments [1] = payments [0]*loanPeriod;
            } else if (currentCurrency.equals("£")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) * 0.57;
                payments [1] = payments [0]*loanPeriod;
            } else {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) * 0.66;
                payments [1] = payments [0]*loanPeriod;
            }
        } else if (currentPayment_mode.equals("bi-weekly")) {
            if (currentCurrency.equals("$")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) / 2;
                payments [1] = payments [0]*loanPeriod;
            } else if (currentCurrency.equals("£")) {
                payments [0] = (((r+(r/(r1-1)))*loanAmount) * 0.57)/2;
                payments [1] = payments [0]*loanPeriod;
            } else {
                payments [0] = (((r+(r/(r1-1)))*loanAmount) * 0.66) / 2;
                payments [1] = payments [0]*loanPeriod;
            }
        } else {
            if (currentCurrency.equals("$")) {
                payments [0] = ((r+(r/(r1-1)))*loanAmount) / 4;
                payments [1] = payments [0]*loanPeriod;
            } else if (currentCurrency.equals("£")) {
                payments [0] = (((r+(r/(r1-1)))*loanAmount) * 0.57)/4;
                payments [1] = payments [0]*loanPeriod;
            } else {
                payments [0] = (((r+(r/(r1-1)))*loanAmount) * 0.66) / 4;
                payments [1] = payments [0]*loanPeriod;
            }
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
            currentPayment_mode = bd.get("PAYMENT_MODE").toString();
        } else {
            currentCurrency = "$";
            currentPayment_mode = "monthly";
        }

        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
        mInterestRate = (EditText)findViewById(R.id.interest_rate);
        mLoanPeriod= (EditText)findViewById(R.id.loan_period);
    }

    public void Onsettings (View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void OnFinish(View view) {

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

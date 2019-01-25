package com.example.seg_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SummaryScreen extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMonthlyPaymentResult,mTotalPaymentsResult;
    private String  periodic_payment,total_payment, mortgage_amount, interest_rate, amortisation_period, payment_frequency ;
//    public static final String[] currency = {"Select your currency...","$", "€","£"};
//    public static final String[] payment_mode = {"Select your payment mode...","bi-weekly", "weekly", "monthly"};
//    private Spinner spinner, secondSpinner;
//    private String [] answers = new String [5];
//
//                intent.putExtra("MORTGAGE_AMOUNT",  answers[0]);
//            intent.putExtra("INTEREST_RATE",  answers[1]);
//            intent.putExtra("AMORTISATION_PERIOD", answers[2]);
//            intent.putExtra("CURRENCY", currentCurrency);
//            intent.putExtra("PAYMENT_FREQUENCY", currentPayment_mode);
//            intent.putExtra("PAYMENT", new DecimalFormat("##.##").format(showLoanPayments()[0]));
//            intent.putExtra("TOTAL_AMOUNT", new DecimalFormat("##.##").format(showLoanPayments()[1]));




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
            mMonthlyPaymentResult.setText(bd.get("PAYMENT").toString());
            mTotalPaymentsResult.setText(bd.get("TOTAL_AMOUNT").toString());
        }

    }


//    public void OnMoreDetails(View view) {
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

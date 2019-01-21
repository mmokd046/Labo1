package com.example.seg_lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMonthlyPaymentResult,mTotalPaymentsResult;

    public void showLoanPayments(View clickedButton){
        double loanAmount = Integer.parseInt(mLoanAmount.getText().toString());
        double interestRate = Integer.parseInt(mInterestRate.getText().toString());
        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());
        double r = interestRate/1200;
        double r1=Math.pow(r+1,loanPeriod);

        double monthlyPayment = (double) ((r+(r/(r1-1)))*loanAmount);
        double totalPayment = monthlyPayment*loanPeriod;
        mMonthlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
        mInterestRate = (EditText)findViewById(R.id.interest_rate);
        mLoanPeriod= (EditText)findViewById(R.id.loan_period);

        mMonthlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result);
        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payment_result);

    }
}

package gurukulsolutions.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gurukulsolutions.com.User.EmiCalculatorActivity;
import gurukulsolutions.com.User.HMLoanApplicationActivity;
import gurukulsolutions.com.User.LoanApplicationActivity;
import gurukulsolutions.com.User.UserDashboardActivity;

public class QuicklyLoanActivity extends AppCompatActivity {


    private ImageView pLoan,bLoan,hLoan,mLoan,yourDashboard,emiCalculator;
    private TextView pLoanTxt,bLoanTxt,hLoanTxt,mLoanTxt,yourDashboardTxt,emiCalculatorTxt;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickly_loan);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat( "MMddyyyy" );
        saveCurrentDate = currentDate.format( calendar.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HHmmss" );
        saveCurrentTime = currentTime.format( calendar.getTime() );

        productRandomKey=saveCurrentDate + saveCurrentTime;

        pLoan=(ImageView) findViewById( R.id. ql_pl);
        bLoan=(ImageView) findViewById( R.id.ql_bl ) ;
        hLoan=(ImageView) findViewById( R.id. ql_hl);
        mLoan=(ImageView) findViewById( R.id. ql_ml);
        yourDashboard=(ImageView) findViewById( R.id.ql_your_dashboard) ;
        emiCalculator=(ImageView)findViewById(R.id.ql_emicalculator);


        pLoanTxt=(TextView) findViewById( R.id. ql_pl_txt);
        bLoanTxt=(TextView) findViewById( R.id.ql_bl_txt ) ;
        hLoanTxt=(TextView) findViewById( R.id. ql_hl_txt);
        mLoanTxt=(TextView) findViewById( R.id. ql_ml_txt);
        yourDashboardTxt=(TextView) findViewById( R.id.ql_your_dashboard_txt) ;
        emiCalculatorTxt=(TextView) findViewById( R.id.ql_emicalculator_txt) ;






        pLoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent ploan=new Intent( QuicklyLoanActivity.this,LoanApplicationActivity.class );
                ploan.putExtra( "Loan Type","Personal Loan" );
                ploan.putExtra( "pid", productRandomKey);
                startActivity( ploan );


            }
        } );
        bLoan.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent bloan=new Intent( QuicklyLoanActivity.this,LoanApplicationActivity.class );
                bloan.putExtra( "Loan Type","Business Loan" );
                bloan.putExtra( "pid", productRandomKey);
                startActivity( bloan );

            }
        } );
        hLoan.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent hloan=new Intent( QuicklyLoanActivity.this,HMLoanApplicationActivity.class );
                hloan.putExtra( "Loan Type","Home Loan" );
                hloan.putExtra( "pid", productRandomKey);
                startActivity( hloan );

            }
        } );
        mLoan.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mloan=new Intent( QuicklyLoanActivity.this,HMLoanApplicationActivity.class );
                mloan.putExtra(  "Loan Type","Mortgage Loan" );
                mloan.putExtra( "pid", productRandomKey);
                startActivity( mloan );

            }
        } );

        yourDashboard.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent yourdashboard=new Intent( QuicklyLoanActivity.this,UserDashboardActivity.class );
                startActivity( yourdashboard );

            }
        } );

        emiCalculator.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent emi=new Intent( QuicklyLoanActivity.this,EmiCalculatorActivity.class );
                startActivity( emi );

            }
        } );






        pLoanTxt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent ploan=new Intent( QuicklyLoanActivity.this,LoanApplicationActivity.class );
                ploan.putExtra( "Loan Type","Personal Loan" );
                ploan.putExtra( "pid", productRandomKey);
                startActivity( ploan );


            }
        } );
        bLoanTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent bloan=new Intent( QuicklyLoanActivity.this,LoanApplicationActivity.class );
                bloan.putExtra( "Loan Type","Business Loan" );
                bloan.putExtra( "pid", productRandomKey);
                startActivity( bloan );

            }
        } );
        hLoanTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent hloan=new Intent( QuicklyLoanActivity.this,HMLoanApplicationActivity.class );
                hloan.putExtra( "Loan Type","Home Loan" );
                hloan.putExtra( "pid", productRandomKey);
                startActivity( hloan );

            }
        } );
        mLoanTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mloan=new Intent( QuicklyLoanActivity.this,HMLoanApplicationActivity.class );
                mloan.putExtra(  "Loan Type","Mortgage Loan" );
                mloan.putExtra( "pid", productRandomKey);
                startActivity( mloan );

            }
        } );

        yourDashboardTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent yourdashboard=new Intent( QuicklyLoanActivity.this,UserDashboardActivity.class );
                startActivity( yourdashboard );

            }
        } );

        emiCalculatorTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent emi=new Intent( QuicklyLoanActivity.this,EmiCalculatorActivity.class );
                startActivity( emi );

            }
        } );





    }
}

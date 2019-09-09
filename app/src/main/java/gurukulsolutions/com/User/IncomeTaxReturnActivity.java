package gurukulsolutions.com.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;




public class IncomeTaxReturnActivity extends AppCompatActivity {
    private EditText ETTaxPayerName,ETTaxPayerContactNo,ETTaxPayerEmail,ETTaxPayerAadhaar,ETTaxPayerPan;
    private Button BTNIncomeTaxReturn;
    private String productRandomKey;
    private ProgressDialog loadingBar;
    private String getLoanType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_income_tax_return );
        loadingBar = new ProgressDialog(this);
        getLoanType=getIntent().getStringExtra( "Application Type" );
        productRandomKey=getIntent().getStringExtra( "pid" );

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        ETTaxPayerName=(EditText)findViewById( R.id.etTaxPayerName );
        ETTaxPayerContactNo=(EditText)findViewById( R.id.etTaxPayerContactNo );
        ETTaxPayerEmail=(EditText)findViewById( R.id.etTaxPayerEmail );
        ETTaxPayerAadhaar=(EditText)findViewById( R.id.etTaxPayerAadhaarno);
        ETTaxPayerPan=(EditText)findViewById( R.id.etTaxPayerPanNo );
        BTNIncomeTaxReturn=(Button)findViewById( R.id.IncomeTaxReturnApplicationSubmit_btn );



        BTNIncomeTaxReturn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SubmitIncomeTaxApplication();

            }
        } );
        ETTaxPayerContactNo.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
                String Length=arg0.toString();
                if(Length.length() < 10)
                {

                    ETTaxPayerContactNo.setTextColor(Color.RED);

                }else
                    {
                        ETTaxPayerContactNo.setTextColor(Color.GREEN);
                    }
            }
        });

    }


    private void SubmitIncomeTaxApplication()
    {



        final String taxPayerName = ETTaxPayerName.getText().toString();
        String taxPayerContactNo = ETTaxPayerContactNo.getText().toString();
        final String taxPayerEmail = ETTaxPayerEmail.getText().toString();
        String taxPayerAadhaar = ETTaxPayerAadhaar.getText().toString();
        String taxPayerPan = ETTaxPayerPan.getText().toString();
        String GetApplicationType=getLoanType.toString();
        String pid=productRandomKey.toString();


        if (TextUtils.isEmpty(taxPayerName))
        {
            Toast.makeText(this, "Please write Tax Payer name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(taxPayerContactNo))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(taxPayerAadhaar))
        {
            Toast.makeText(this, "Please write Tax Payer AADHAAR Card Number....", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(taxPayerPan))
        {
            Toast.makeText(this, "Please write Tax Payer PAN Card Number...", Toast.LENGTH_SHORT).show();
        }


        else
        {
            loadingBar.setTitle("Income Tax Return File..");
            loadingBar.setMessage("Please wait, while we are Uploading Your Application...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            final DatabaseReference incomeRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
            final HashMap<String,Object> IncomeMap=new HashMap <>(  );
            IncomeMap.put( "Tax_Payer_Name",taxPayerName );
            IncomeMap.put( "PhoneNumber",taxPayerContactNo );
            IncomeMap.put( "Email",taxPayerEmail);
            IncomeMap.put( "AADHAAR_number",taxPayerAadhaar );
            IncomeMap.put( "PAN_Card",taxPayerPan );
            IncomeMap.put( "Loan_Type",GetApplicationType );
            IncomeMap.put( "pid",pid );


            incomeRef.child("User View").child( Prevalent.currentOnlineUser.getPhone() )
                    .child( productRandomKey )
                    .updateChildren( IncomeMap )
                    .addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                incomeRef.child( "Admin View" ).child( "ITR" )
                                        .child( productRandomKey )
                                        .updateChildren( IncomeMap )
                                        .addOnCompleteListener( new OnCompleteListener <Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task <Void> task)
                                            {
                                                if(task.isSuccessful())
                                                {
                                                    loadingBar.dismiss();
                                                    Toast.makeText( IncomeTaxReturnActivity.this,
                                                            "Thank You, Dear  " + taxPayerName + "  Your Income Tax Return Application has been saved successfully..   " , Toast.LENGTH_LONG ).show();
                                                    Toast.makeText( IncomeTaxReturnActivity.this,
                                                            "Thank You, Dear  " + taxPayerName + "  Your Income Tax Return Application has been saved successfully..   " , Toast.LENGTH_LONG ).show();
                                                    Intent intent=new Intent( IncomeTaxReturnActivity.this,DashboardActivity.class );
                                                    startActivity( intent );


                                                }

                                            }
                                        } );

                            }

                        }
                    } );
        }
    }

}

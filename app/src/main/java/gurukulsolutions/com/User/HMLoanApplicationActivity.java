package gurukulsolutions.com.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;

public class HMLoanApplicationActivity extends AppCompatActivity
    {
        private static EditText ETHMBorrowerName,ETHMContactNo;
        private static EditText ETHMNewLoanAmoount,ETHMAddress,ETHMMeetingTime;
        private Button BTNHMloanApplicationSubmit_btn;
        private ProgressDialog loadingBar;
        public static String occupatation,oldloanexistsornot;
        private String productRandomKey;

        private String getHMLoanType;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hmloan_application );

        getHMLoanType=getIntent().getStringExtra( "Loan Type" );
        productRandomKey=getIntent().getStringExtra( "pid" );
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ETHMBorrowerName=(EditText)findViewById( R.id.etHMBorrowerName );
        ETHMContactNo=(EditText)findViewById( R.id.etHMContactNo );
        ETHMNewLoanAmoount=(EditText)findViewById( R.id.etHMNewLoan );
        ETHMAddress=(EditText)findViewById( R.id.etHMAddress);
        ETHMMeetingTime=(EditText)findViewById( R.id.etHMMeetingTime );
        BTNHMloanApplicationSubmit_btn=(Button)findViewById( R.id.HMloanApplicationSubmit_btn );

        loadingBar = new ProgressDialog(this);

        final Spinner spinnerOccupation=findViewById( R.id.HMspinnerOccupation );
        final Spinner spinnerOldLoan=findViewById( R.id.HMspinnerLoan );

        ArrayAdapter<CharSequence> adapterOccupation=ArrayAdapter.createFromResource( HMLoanApplicationActivity.this,R.array.occupation,android.R.layout.simple_spinner_item );

        ArrayAdapter <CharSequence> adapterOldLoan=ArrayAdapter.createFromResource( HMLoanApplicationActivity.this,R.array.yesNo,android.R.layout.simple_spinner_item );
        adapterOccupation.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        adapterOldLoan.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinnerOccupation.setAdapter( adapterOccupation );
        spinnerOldLoan.setAdapter( adapterOldLoan );


        spinnerOccupation.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id)
            {
                if(position==1 ||position==2||position==3 ||position==4 ||position==0)
                {
                    occupatation=String.valueOf( spinnerOccupation.getSelectedItem() );

                }
                else
                {
                    Toast.makeText( HMLoanApplicationActivity.this, "Please select occupation..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( HMLoanApplicationActivity.this, "Please select  occupation", Toast.LENGTH_SHORT ).show();

            }
        } );


        //spinner old loan
        spinnerOldLoan.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id)
            {

                if(position==0||position==1 || position==2)
                {
                    oldloanexistsornot=String.valueOf( spinnerOldLoan.getSelectedItem() );
                }
                else
                {
                    Toast.makeText( HMLoanApplicationActivity.this, "Please select Existing loan status..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( HMLoanApplicationActivity.this, "Please select old loan yes or no..", Toast.LENGTH_SHORT ).show();

            }
        } );

        if(getHMLoanType.equals("Home Loan") || getHMLoanType.equals( "Mortgage Loan" ))
        {

            BTNHMloanApplicationSubmit_btn.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    SubmitHMLoanApplication();
                }

            } );


        }
    }

        private void SubmitHMLoanApplication()
        {


            final String borrowerName = ETHMBorrowerName.getText().toString();
            String contactNo = ETHMContactNo.getText().toString();
            final String newLoanAmount = ETHMNewLoanAmoount.getText().toString();
            String address = ETHMAddress.getText().toString();
            String meetingTime = ETHMMeetingTime.getText().toString();
            String Occupatation=occupatation.toString();
            String Oldloanexistsornot=oldloanexistsornot.toString();
            final String GetLoanType=getHMLoanType.toString();
            String pid=productRandomKey.toString();


            if (TextUtils.isEmpty(borrowerName))
            {
                Toast.makeText(this, "Please write Borrower name...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(contactNo))
            {
                Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
            }

            else  if (TextUtils.isEmpty(newLoanAmount))
            {
                Toast.makeText(this, "Please write new Loan Amount...", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(address))
            {
                Toast.makeText(this, "Please write your full address..", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(meetingTime))
            {
                Toast.makeText(this, "Please write appropriate meeting time...", Toast.LENGTH_SHORT).show();
            }


            else
            {
                loadingBar.setTitle("Submit Loan Application");
                loadingBar.setMessage("Please wait, while we are Uploading Loan Application.");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                final DatabaseReference loanRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                final HashMap<String,Object> loanMap=new HashMap <>(  );
                loanMap.put( "Borrower_Name",borrowerName );
                loanMap.put( "PhoneNo",contactNo );
                loanMap.put( "New_Loan_Amount",newLoanAmount);
                loanMap.put( "Address",address );
                loanMap.put( "Meeting_Time",meetingTime );
                loanMap.put("Occupation",Occupatation);
                loanMap.put( "Loan_Type",GetLoanType );
                loanMap.put("Old_Loan_Exists_or_not",Oldloanexistsornot);
                loanMap.put( "pid",pid );

                loanRef.child("User View").child( Prevalent.currentOnlineUser.getPhone() )
                        .child( productRandomKey )
                        .updateChildren( loanMap )
                        .addOnCompleteListener( new OnCompleteListener <Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    loanRef.child( "Admin View" ).child( "HM Loan" )
                                            .child( productRandomKey )
                                            .updateChildren( loanMap )
                                            .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task <Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText( HMLoanApplicationActivity.this,
                                                                "Thank You, Dear  " + borrowerName + "  For Apply loan of   " + newLoanAmount, Toast.LENGTH_LONG ).show();
                                                        Toast.makeText( HMLoanApplicationActivity.this,
                                                                "Thank You, Dear  " + borrowerName + "  For Apply loan of   " +newLoanAmount, Toast.LENGTH_LONG ).show();
                                                        Toast.makeText( HMLoanApplicationActivity.this,
                                                                "Thank You, Dear  " + borrowerName + "  For Apply loan of   " +newLoanAmount, Toast.LENGTH_LONG ).show();
                                                        Intent intent=new Intent( HMLoanApplicationActivity.this,UploadDocumentForLoanActivity.class );
                                                        intent.putExtra( "pid",productRandomKey );
                                                        intent.putExtra( "Loan Type",GetLoanType );
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

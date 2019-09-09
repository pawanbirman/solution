package gurukulsolutions.com.User;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;


public class LoanApplicationActivity extends AppCompatActivity  implements View.OnClickListener
{
    private final String TAG = "Loan_Application";
    private static EditText mEditTextDateOfJoining, mEditTextDateOfBirth;
    private static EditText ETBorrowerName,ETContactNo,ETDateOfBirth,ETCompanyName,ETDateOfJoining,ETNetSalary,ETOldLoanAmount;
    private static EditText ETNewLoanAmoount,ETAddress,ETMeetingTime;
    private Button BTNloanApplicationSubmit_btn;
    private ProgressDialog loadingBar;
    private RadioButton RBmale,RBfemale;
    private TextView txtOldLoanAmount,txtOccupationInformation,txtDateOfJoining,txtNetSalary;
    String gender;
   public static String occupatation,oldloanexistsornot,ITRStatus;
    private String productRandomKey;

    private String getLoanType;


    private int mDay, mMonth, mYear;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loan_application );

        getLoanType=getIntent().getStringExtra( "Loan Type" );
        productRandomKey=getIntent().getStringExtra( "pid" );


        txtOldLoanAmount=(TextView)findViewById(R.id.textLoanAmount);
        txtOccupationInformation=(TextView)findViewById(R.id.textOccupationInfo);
        txtDateOfJoining=(TextView)findViewById(R.id.textDateOfJoining);
        txtNetSalary=(TextView)findViewById(R.id.textNetSalary);

        ETBorrowerName=(EditText)findViewById( R.id.etBorrowerName );
        ETContactNo=(EditText)findViewById( R.id.etContactNo );
        ETDateOfBirth=(EditText)findViewById( R.id.etDateOfBirth );
        ETCompanyName=(EditText)findViewById( R.id.etCompanyName );
        ETDateOfJoining=(EditText)findViewById( R.id.etDateOfJoining );
        ETNetSalary=(EditText)findViewById( R.id.etNetSalary);
        ETOldLoanAmount=(EditText)findViewById( R.id.etOldLoanAmount );
        ETNewLoanAmoount=(EditText)findViewById( R.id.etNewLoan );
        ETAddress=(EditText)findViewById( R.id.etAddress);
        ETMeetingTime=(EditText)findViewById( R.id.etMeetingTime );
        BTNloanApplicationSubmit_btn=(Button)findViewById( R.id.loanApplicationSubmit_btn );
        RBmale=(RadioButton)findViewById( R.id.male );
        RBfemale=(RadioButton)findViewById( R.id.female );
        loadingBar = new ProgressDialog(this);


        // Get date of Birth and Joining
        mEditTextDateOfJoining = (EditText) findViewById( R.id.etDateOfJoining);
        mEditTextDateOfBirth = (EditText) findViewById( R.id.etDateOfBirth);
        mEditTextDateOfJoining.setOnClickListener(this);
        mEditTextDateOfBirth.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if(getLoanType.equals("Business Loan"))
        {
            txtOccupationInformation.setText("Business Name");
            ETCompanyName.setHint("Business Name");

            txtDateOfJoining.setVisibility(View.GONE);
            ETDateOfJoining.setVisibility(View.GONE);
            txtNetSalary.setVisibility(View.GONE);
            ETNetSalary.setVisibility(View.GONE);


        }



        final Spinner spinnerOccupation=findViewById( R.id.spinnerOccupation );
        final Spinner spinnerOldLoan=findViewById( R.id.spinnerLoan );
        final Spinner spinnerITR=findViewById( R.id.spinnerItr );

        ArrayAdapter<CharSequence> adapterOccupation=ArrayAdapter.createFromResource( LoanApplicationActivity.this,R.array.occupation,android.R.layout.simple_spinner_item );
        ArrayAdapter<CharSequence> adapterITR=ArrayAdapter.createFromResource( LoanApplicationActivity.this,R.array.itryesno,android.R.layout.simple_spinner_item );

        ArrayAdapter <CharSequence> adapterOldLoan=ArrayAdapter.createFromResource( LoanApplicationActivity.this,R.array.yesNo,android.R.layout.simple_spinner_item );
        adapterOccupation.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        adapterOldLoan.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        adapterITR.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinnerOccupation.setAdapter( adapterOccupation );
        spinnerOldLoan.setAdapter( adapterOldLoan );
        spinnerITR.setAdapter( adapterITR );


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
                    Toast.makeText( LoanApplicationActivity.this, "Please select occupation..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( LoanApplicationActivity.this, "Please select  occupation", Toast.LENGTH_SHORT ).show();

            }
        } );

        //spinnerOldLoan.setSelection(0);
        spinnerOldLoan.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id)
            {

                if(position==0||position==1 || position==2)
                {
                    oldloanexistsornot=String.valueOf( spinnerOldLoan.getSelectedItem() );
                    if(oldloanexistsornot.equals("No"))
                    {
                        ETOldLoanAmount.setVisibility(View.GONE);
                        txtOldLoanAmount.setVisibility(View.GONE);
                    }
                    if(oldloanexistsornot.equals("Yes"))
                    {
                        ETOldLoanAmount.setVisibility(View.VISIBLE);
                        txtOldLoanAmount.setVisibility(View.VISIBLE);
                    }

                }
                else
                {
                    Toast.makeText( LoanApplicationActivity.this, "Please select Existing loan status..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( LoanApplicationActivity.this, "Please select old loan yes or no..", Toast.LENGTH_SHORT ).show();

            }
        } );
        spinnerITR.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id)
            {

                if(position==0||position==1 || position==2)
                {
                    ITRStatus=String.valueOf( spinnerITR.getSelectedItem() );
                    if(ITRStatus.equals("No"))
                    {
                        Intent intent=new Intent( LoanApplicationActivity.this,IncomeTaxReturnActivity.class );
                        intent.putExtra(  "Application Type","Income Tax" );
                        intent.putExtra( "pid", productRandomKey);
                        startActivity( intent );
                    }

                }
                else
                {
                    Toast.makeText( LoanApplicationActivity.this, "Please select ITR ..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( LoanApplicationActivity.this, "Please select old loan yes or no..", Toast.LENGTH_SHORT ).show();

            }
        } );


        if(getLoanType.equals( "Personal Loan" ) || getLoanType.equals( "Business Loan" ))
        {

            BTNloanApplicationSubmit_btn.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    //Radio Button implementation
                    if (RBmale.isChecked())

                    {
                        gender = "Male";

                    } else if (RBfemale.isChecked()) {
                        gender = "Female";
                    }

                    SubmitPBLoanApplication();
                }

            } );
        }

        ETMeetingTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(LoanApplicationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ETMeetingTime.setText( "" + selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
    private void SubmitPBLoanApplication()
    {


        final String borrowerName = ETBorrowerName.getText().toString();
        String contactNo = ETContactNo.getText().toString();
        String dateOfBirth = ETDateOfBirth.getText().toString();
        String companyName = ETCompanyName.getText().toString();
        String dateOfJoining = ETDateOfJoining.getText().toString();
        String netSalary = ETNetSalary.getText().toString();
        String oldLoanAmount = ETOldLoanAmount.getText().toString();
        final String newLoanAmount = ETNewLoanAmoount.getText().toString();
        String address = ETAddress.getText().toString();
        Date date;
        String meetingTime = ETMeetingTime.getText().toString();
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        try {
            date = formatter.parse( meetingTime );
            Log.d(TAG, "meeting time and date " + date);
        } catch (Exception e) {
            Log.e(TAG, "Invalid meeting time " + e);
        } finally {

        }

        String Gender=gender.toString();
        String Occupatation=occupatation.toString();
        String Oldloanexistsornot=oldloanexistsornot.toString();
        final String GetLoanType=getLoanType.toString();
        String pid=productRandomKey.toString();


        if (TextUtils.isEmpty(borrowerName))
        {
            Toast.makeText(this, "Please write Borrower name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(contactNo))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dateOfBirth))
        {
            Toast.makeText(this, "Please select Date of Birth...", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(companyName))
        {
            Toast.makeText(this, "Please write company  name...", Toast.LENGTH_SHORT).show();
        }
        else if (getLoanType.equals("Personal Loan") && TextUtils.isEmpty(dateOfJoining))
        {
            Toast.makeText(this, "Please select date of joining...", Toast.LENGTH_SHORT).show();
        }
        else if (getLoanType.equals("Personal Loan") && TextUtils.isEmpty(netSalary))
        {
            Toast.makeText(this, "Please write net salary...", Toast.LENGTH_SHORT).show();
        }
        else if (oldloanexistsornot.equals("Yes")&& TextUtils.isEmpty(oldLoanAmount))
        {
            Toast.makeText(this, "Please write old loan Amount...", Toast.LENGTH_SHORT).show();
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
            loanMap.put( "DOB",dateOfBirth );
            loanMap.put( "Company_Name",companyName );

            if(getLoanType.equals("Personal Loan"))
            {
                loanMap.put( "DOJ",dateOfJoining );
                loanMap.put( "Net_Salary",netSalary );
            }


            if(oldloanexistsornot.equals("Yes"))
            {
                loanMap.put( "Old_Loan_Amount",oldLoanAmount );
            }

            loanMap.put( "New_Loan_Amount",newLoanAmount);
            loanMap.put( "Address",address );
            loanMap.put( "Meeting_Time",meetingTime );
            loanMap.put("Gender",Gender);
            loanMap.put("Occupation",Occupatation);
            loanMap.put("Old_Loan_Exists_or_not",Oldloanexistsornot);
            loanMap.put( "Loan_Type",GetLoanType );
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
                               loanRef.child( "Admin View" ).child( "PB Loan" )
                                       .child(productRandomKey)
                                       .updateChildren( loanMap )
                                       .addOnCompleteListener( new OnCompleteListener <Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task <Void> task)
                                           {
                                               if(task.isSuccessful())
                                               {
                                                   Toast.makeText( LoanApplicationActivity.this,
                                                           "Thank You, Dear  " + borrowerName + "  For Apply loan of   " + newLoanAmount, Toast.LENGTH_LONG ).show();
                                                   Toast.makeText( LoanApplicationActivity.this,
                                                           "Thank You, Dear  " + borrowerName + "  For Apply loan of   " +newLoanAmount, Toast.LENGTH_LONG ).show();
                                                   Toast.makeText( LoanApplicationActivity.this,
                                                           "Thank You, Dear  " + borrowerName + "  For Apply loan of   " +newLoanAmount, Toast.LENGTH_LONG ).show();
                                                   Intent intent=new Intent( LoanApplicationActivity.this,UploadDocumentForLoanActivity.class );
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

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mDay = c.get( Calendar.DAY_OF_MONTH );
        mMonth = c.get( Calendar.MONTH );
        mYear = c.get( Calendar.YEAR );
        if (v == mEditTextDateOfJoining) {
            DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mEditTextDateOfJoining.setText( dayOfMonth + "/" + (month + 1) + "/" + year, TextView.BufferType.EDITABLE );

                }
            }, mYear, mMonth, mDay );
            datePickerDialog.show();
        } else if( v == mEditTextDateOfBirth ) {
            DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mEditTextDateOfBirth.setText( dayOfMonth + "/" + (month + 1)  + "/" + year, TextView.BufferType.EDITABLE );

                }
            }, mYear, mMonth, mDay );
            datePickerDialog.show();
        }

    }


}

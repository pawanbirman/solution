package gurukulsolutions.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.User.DashboardActivity;
import gurukulsolutions.com.User.LoanApplicationActivity;
import gurukulsolutions.com.User.UploadDocumentForLoanActivity;

public class QsPartnerActivity extends AppCompatActivity {
    private EditText mNameEditText, mPhoneNumEditText, mEmailEditText, mBdayEditText;
    private RadioGroup mGenderRadio;
    private EditText mAadharEditText, mPanEditText, mACNumEditText, mIFSCEditText, mBankName;
    private boolean isDelete = false;
    Button submitButton;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qs_partner);

        mNameEditText = (EditText) findViewById(R.id.editTextName);
        mPhoneNumEditText = (EditText) findViewById(R.id.editTextContactNo);
        mEmailEditText = (EditText) findViewById(R.id.editTextEmail);
        mBdayEditText = (EditText) findViewById(R.id.editTextDOB);
        mGenderRadio = (RadioGroup) findViewById(R.id.radioSex);
        final String gender = ((RadioButton) findViewById(mGenderRadio.getCheckedRadioButtonId()))
                .getText().toString();
        mAadharEditText = (EditText) findViewById(R.id.editTextAadharNum);
        mPanEditText = (EditText) findViewById(R.id.editTextAadharNum);
        mBankName = (EditText) findViewById(R.id.autoCompleteBank);
        mACNumEditText = (EditText) findViewById(R.id.editTextACNum);
        mIFSCEditText = (EditText) findViewById(R.id.editTextIFSC);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loadingBar = new ProgressDialog(this);

        submitButton=(Button)findViewById(R.id.QSPartnerSubmit);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String name = mNameEditText.getText().toString();
                String contactNo = mPhoneNumEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String dob = mBdayEditText.getText().toString();
                String Gender = gender.toString();
                String aadhaar = mAadharEditText.getText().toString();
                String pancard = mPanEditText.getText().toString();
                String bankname = mBankName.getText().toString();
                String accountno = mACNumEditText.getText().toString();
                String ifsc = mIFSCEditText.getText().toString();


                if (TextUtils.isEmpty(name))
                {
                    mNameEditText.setError("Name is mandatory..");
                    mNameEditText.requestFocus();
                }
                else if (TextUtils.isEmpty(contactNo))
                {
                    mPhoneNumEditText.setError("Phone number is mandatory..");
                    mPhoneNumEditText.requestFocus();
                }
                else if (TextUtils.isEmpty(email))
                {
                    mEmailEditText.setError("EMAIL is mandatory..");
                    mEmailEditText.requestFocus();
                }
                else  if (TextUtils.isEmpty(dob))
                {
                    mBdayEditText.setError("Date of birth is mandatory..");
                    mBdayEditText.requestFocus();
                }

                else
                {
                    loadingBar.setTitle("Submit Loan Application");
                    loadingBar.setMessage("Please wait, while we are Uploading Loan Application.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    final DatabaseReference loanRef=FirebaseDatabase.getInstance().getReference();
                    final HashMap<String,Object> loanMap=new HashMap <>(  );
                    loanMap.put( "Agent_Name",name );
                    loanMap.put( "PhoneNo",contactNo );
                    loanMap.put( "EMAIL",email );
                    loanMap.put( "DOB",dob );
                    loanMap.put( "Gender",Gender);
                    loanMap.put( "Aadhaar",aadhaar );
                    loanMap.put( "Pancard",pancard );
                    loanMap.put("Bank_Name",bankname);
                    loanMap.put("Account_Number",accountno);
                    loanMap.put( "IFSC",ifsc );


                    loanRef.child("Agents")
                            .child( Prevalent.currentOnlineUser.getPhone() )
                            .updateChildren( loanMap )
                            .addOnCompleteListener( new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {

                                        Toast.makeText(QsPartnerActivity.this, "Your Application has been successfully submitted....Quick Solution team will contact you soon..", Toast.LENGTH_LONG).show();

                                        Toast.makeText(QsPartnerActivity.this, "Your Application has been successfully submitted....Quick Solution team will contact you soon..", Toast.LENGTH_LONG).show();


                                        Intent intent=new Intent( QsPartnerActivity.this,DashboardActivity.class );
                                        startActivity( intent );


                                    }

                                }
                            } );



                }
            }
        });



    }

    private void submitPartnerInformation()
    {



    }
}


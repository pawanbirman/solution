package gurukulsolutions.com.User;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.HashMap;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;

public class InsuranceActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView InputDocumentRCFront,InputDocumentRCBack;
    private static final int GalleryPick = 1;
    private Uri ImageUri_RC_Front,ImageUri_RC_Back;
    private StorageReference InsuranceDocumentImageRef;
    private final int RC_FRONT_DOC =1;
    private final int RC_BACK_DOC =2;
    private String RCFrontUrl,RCBackUrl= "";
    private static int getDocType=0;


    private static EditText mEditTextInsuranceExpiry;
    private static EditText ETInsuranceOwnerName,ETInsuranceContactNo;
    private Button BTNInsuranceApplicationSubmit_btn;
    private ProgressDialog loadingBar;
    private RadioButton RBTwo,RBFour,RBFirstParty,RBThirdParty;
    String vehicleType,InsuranceType;
    private String productRandomKey;
    private String getLoanType;
    private String OwnerName,contactNo,dateOfExpiry,VehicleType,insuranceType,GetType,pid;
    private int mDay, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insurance );
        loadingBar = new ProgressDialog(this);
        InsuranceDocumentImageRef=FirebaseStorage.getInstance().getReference().child( "Insurance Document Images" );
        InputDocumentRCFront = (ImageView) findViewById( R.id.select_RCFront );
        InputDocumentRCBack = (ImageView) findViewById( R.id.select_RCBack );
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        getLoanType=getIntent().getStringExtra( "Application Type" );
        productRandomKey=getIntent().getStringExtra( "pid" );

        ETInsuranceOwnerName=(EditText)findViewById( R.id.etInsuranceOwnerName );
        ETInsuranceContactNo=(EditText)findViewById( R.id.etInsuranceContactNo );
        mEditTextInsuranceExpiry=(EditText)findViewById( R.id.etInsuranceExpiryDate);

        RBTwo=(RadioButton)findViewById( R.id.Two );
        RBFour=(RadioButton)findViewById( R.id.Four);
        RBFirstParty=(RadioButton)findViewById( R.id.FirstParty );
        RBThirdParty=(RadioButton)findViewById( R.id.ThirdParty );
        loadingBar = new ProgressDialog(this);
        BTNInsuranceApplicationSubmit_btn=(Button)findViewById( R.id.InsuranceApplicationSubmit_btn);

        mEditTextInsuranceExpiry.setOnClickListener(this);

        InputDocumentRCFront.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDocType= RC_FRONT_DOC;

                OpenGallery();

            }
        } );

        InputDocumentRCBack.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDocType=RC_BACK_DOC;

                OpenGallery();

            }
        } );



        BTNInsuranceApplicationSubmit_btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Radio Button implementation
                if (RBTwo.isChecked())

                {
                    vehicleType = "Two";

                } else if (RBFour.isChecked()) {
                    vehicleType = "Four";
                }

                if (RBFirstParty.isChecked())

                {
                    InsuranceType = "First Party";

                } else if (RBThirdParty.isChecked()) {
                    InsuranceType = "Third Party";
                }

                SubmitInsuranceApplication();
            }

        } );

    }


    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction( Intent.ACTION_GET_CONTENT );
        galleryIntent.setType( "image/*" );
        startActivityForResult( galleryIntent, GalleryPick );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {

            if(getDocType == RC_FRONT_DOC)
            {
                ImageUri_RC_Front = data.getData();
                InputDocumentRCFront.setImageURI( ImageUri_RC_Front );
            } else if(getDocType == RC_BACK_DOC)
            {
                ImageUri_RC_Back=data.getData();
                InputDocumentRCBack.setImageURI( ImageUri_RC_Back );
            }else
            {
                Toast.makeText(this, "Error, Try Again.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(InsuranceActivity.this, InsuranceActivity.class));
                finish();
            }

        }
    }

    private void SubmitInsuranceApplication()
    {

        OwnerName = ETInsuranceOwnerName.getText().toString();
        contactNo = ETInsuranceContactNo.getText().toString();
        dateOfExpiry = mEditTextInsuranceExpiry.getText().toString();
        VehicleType = vehicleType.toString();
        insuranceType= InsuranceType.toString();
        GetType=getLoanType.toString();
        pid=productRandomKey.toString();

        if (TextUtils.isEmpty(OwnerName))
        {
            Toast.makeText(this, "Please write Vehicle Owner name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(contactNo))
        {
            Toast.makeText(this, "Please write Vehicle Owner phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dateOfExpiry))
        {
            Toast.makeText(this, "Please select Expiry Date of Current Insurance Policy...", Toast.LENGTH_SHORT).show();
        }else if(ImageUri_RC_Front == null)
        {
            Toast.makeText( this, "RC Front Image is mandatory.. ", Toast.LENGTH_SHORT ).show();

        }else if(ImageUri_RC_Back==null)
        {
            Toast.makeText( this, "RC Back Image is mandatory.. ", Toast.LENGTH_SHORT ).show();

        }
        else
        {
            StoreInsuranceInformation();
        }
    }

    private void StoreInsuranceInformation()
    {
        loadingBar.setTitle("Upload Insurance Application");
        loadingBar.setMessage("Please wait, while we are Submitting your Application.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        final StorageReference filePath=InsuranceDocumentImageRef.child(ImageUri_RC_Front.getLastPathSegment() + (productRandomKey +1) + ".jpg");
        final UploadTask uploadTask=filePath.putFile(ImageUri_RC_Front  );

        uploadTask.continueWithTask(new Continuation() {
            @Override
            public Object then(@NonNull Task task) throws Exception
            {
                if (!task.isSuccessful())
                {
                    throw task.getException();
                }

                return filePath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task)
            {
                if (task.isSuccessful())
                {
                    Uri downloadUrl = task.getResult();
                    RCFrontUrl = downloadUrl.toString();

                    final StorageReference filePath=InsuranceDocumentImageRef.child(ImageUri_RC_Back.getLastPathSegment() + (productRandomKey +2) + ".jpg");
                    final UploadTask uploadTask=filePath.putFile(ImageUri_RC_Back  );

                    uploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception
                        {
                            if (!task.isSuccessful())
                            {
                                throw task.getException();
                            }

                            return filePath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task)
                        {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                RCBackUrl = downloadUrl.toString();


                                final DatabaseReference InsuranceRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                final HashMap<String,Object> InsuranceMap=new HashMap <>(  );
                                InsuranceMap.put( "Owner_Name",OwnerName );
                                InsuranceMap.put( "Contact_No",contactNo );
                                InsuranceMap.put( "DOExpiry_of_current_policy",dateOfExpiry );
                                InsuranceMap.put("Vehicle_Type",VehicleType);
                                InsuranceMap.put("Insurance_Type",insuranceType);
                                InsuranceMap.put( "Loan_Type",GetType );
                                InsuranceMap.put("RC_Front", RCFrontUrl);
                                InsuranceMap. put("RC_Back", RCBackUrl);
                                InsuranceMap. put("pid", pid);


                                InsuranceRef.child("User View").child( Prevalent.currentOnlineUser.getPhone() )
                                        .child( productRandomKey )
                                        .updateChildren( InsuranceMap )
                                        .addOnCompleteListener( new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if(task.isSuccessful())
                                                {
                                                    InsuranceRef.child( "Admin View" ).child("Insurance")
                                                            .child( productRandomKey )
                                                            .updateChildren( InsuranceMap )
                                                            .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task <Void> task)
                                                                {
                                                                    if(task.isSuccessful())
                                                                    {
                                                                        Toast.makeText( InsuranceActivity.this,
                                                                                "Thank You, Dear  " + OwnerName + "  For Apply Insurance of   " + VehicleType+ "Wheeler..", Toast.LENGTH_LONG ).show();

                                                                        Intent intent=new Intent( InsuranceActivity.this,DashboardActivity.class );
                                                                        startActivity( intent );

                                                                    }

                                                                }
                                                            } );

                                                }

                                            }
                                        } );




                            }
                            else
                            {
                                Toast.makeText(InsuranceActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(InsuranceActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void saveProduuctInfoToDatabase()
    {


    }

    @Override
    public void onClick(View v)
    {

        final Calendar c = Calendar.getInstance();
        mDay = c.get( Calendar.DAY_OF_MONTH );
        mMonth = c.get( Calendar.MONTH );
        mYear = c.get( Calendar.YEAR );
        if (v == mEditTextInsuranceExpiry) {
            DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mEditTextInsuranceExpiry.setText( dayOfMonth + "/" + (month + 1) + "/" + year, TextView.BufferType.EDITABLE );

                }
            }, mYear, mMonth, mDay );
            datePickerDialog.show();
        }

    }
}

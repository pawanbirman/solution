package gurukulsolutions.com.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;

public class UploadDocumentForLoanActivity extends AppCompatActivity {
    private ImageView InputDocumentAadharFront,InputDocumentAadharBack,InputDocumentYourPhoto,InputDocumentPan;
    private static final int GalleryPick = 1;
    private Uri ImageUri_Aadhar_Front,ImageUri_Aadhar_Back,ImageUri_Photo,ImageUri_Pan;
    private StorageReference LoanDocumentImageRef;
    private Button LoanDocumentUpload_btn;
    private ProgressDialog loadingBar;
    private final int AADHAR_FRONT_DOC =1;
    private final int AADHAR_BACK_DOC =2;
    private final int YOUR_PASSPORT_SIZE_PHOTO_DOC=3;
    private final int PAN_CARD_DOC=4;
    private String getpid,getLoanType;
    private static int getDocType=0;
    private String aadharFrontUrl,aadharBackUrl,yourPhotoUrl,panUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_upload_document_for_loan );

        loadingBar = new ProgressDialog(this);

        getpid=getIntent().getStringExtra( "pid" );
        getLoanType=getIntent().getStringExtra( "Loan Type" );

        LoanDocumentImageRef=FirebaseStorage.getInstance().getReference().child( "Loan Document Images" );
        InputDocumentAadharFront = (ImageView) findViewById( R.id.select_aadharcardFront );
        InputDocumentAadharBack = (ImageView) findViewById( R.id.select_aadharcardBack );
        InputDocumentYourPhoto = (ImageView) findViewById( R.id.select_yourphoto );
        InputDocumentPan = (ImageView) findViewById( R.id.select_pancard );
        LoanDocumentUpload_btn=(Button)findViewById( R.id.loanDocumentUpload_btn );




        InputDocumentAadharFront.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDocType= AADHAR_FRONT_DOC;

                OpenGallery();

            }
        } );

        InputDocumentAadharBack.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDocType=AADHAR_BACK_DOC;

                OpenGallery();

            }
        } );


        InputDocumentYourPhoto.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                getDocType=YOUR_PASSPORT_SIZE_PHOTO_DOC;

                OpenGallery();

            }
        } );

        InputDocumentPan.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDocType=PAN_CARD_DOC;

                OpenGallery();

            }
        } );

        LoanDocumentUpload_btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ValidateDocument();

            }
        } );
    }


    private void ValidateDocument() {

        if (ImageUri_Aadhar_Front == null) {
            Toast.makeText( this, "AADHAR Card Front Image is mandatory.. ", Toast.LENGTH_SHORT ).show();
        } else if (ImageUri_Aadhar_Back == null) {
            Toast.makeText( this, "AADHAR Card Back Image is mandatory.. ", Toast.LENGTH_SHORT ).show();
        } else if (ImageUri_Photo == null) {
            Toast.makeText( this, "Your passport Size Image is mandatory.. ", Toast.LENGTH_SHORT ).show();
        } else if (ImageUri_Pan == null) {
            Toast.makeText( this, "PAN Card Image is mandatory.. ", Toast.LENGTH_SHORT ).show();
        } else {
            StoreDocInformation();

        }

    }
    private void StoreDocInformation()
    {
        loadingBar.setTitle("Document Upload..");
        loadingBar.setMessage("Please wait, while we are uploading your Documents....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        final StorageReference filePath=LoanDocumentImageRef.child(ImageUri_Aadhar_Front.getLastPathSegment() + (getpid +1) + ".jpg");
        final UploadTask uploadTask=filePath.putFile(ImageUri_Aadhar_Front  );

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
                    aadharFrontUrl = downloadUrl.toString();

                    final StorageReference filePath=LoanDocumentImageRef.child(ImageUri_Aadhar_Back.getLastPathSegment() + (getpid +2) + ".jpg");
                    final UploadTask uploadTask=filePath.putFile(ImageUri_Aadhar_Back  );

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
                                aadharBackUrl = downloadUrl.toString();


                                final StorageReference filePath=LoanDocumentImageRef.child(ImageUri_Photo.getLastPathSegment() + (getpid +3) + ".jpg");
                                final UploadTask uploadTask=filePath.putFile(ImageUri_Photo  );

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
                                            yourPhotoUrl = downloadUrl.toString();


                                            final StorageReference filePath=LoanDocumentImageRef.child(ImageUri_Pan.getLastPathSegment() + (getpid +4)+ ".jpg");
                                            final UploadTask uploadTask=filePath.putFile(ImageUri_Pan  );

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
                                                        panUrl = downloadUrl.toString();

                                                        final DatabaseReference LoanDocumentRef = FirebaseDatabase.getInstance().getReference().child("Applications");

                                                        final HashMap<String, Object> userMap = new HashMap<>();
                                                        userMap. put("Aadhar_Front", aadharFrontUrl);
                                                        userMap. put("Aadhar_Back", aadharBackUrl);
                                                        userMap. put("Photo", yourPhotoUrl);
                                                        userMap. put("Pan_Card", panUrl);


                                                        LoanDocumentRef.child("User View").child( Prevalent.currentOnlineUser.getPhone() )
                                                                .child( getpid )
                                                                .updateChildren( userMap )
                                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task)
                                                                    {
                                                                        if(task.isSuccessful())
                                                                        {
                                                                            if(getLoanType.equals( "Personal Loan" ) ||getLoanType.equals( "Business Loan" ))
                                                                            {
                                                                                LoanDocumentRef.child( "Admin View" ).child( "PB Loan" )
                                                                                        .child(getpid)
                                                                                        .updateChildren( userMap )
                                                                                        .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task <Void> task)
                                                                                            {
                                                                                                if(task.isSuccessful())
                                                                                                {
                                                                                                    loadingBar.dismiss();
                                                                                                    Toast.makeText( UploadDocumentForLoanActivity.this,
                                                                                                            "Thank You Dear, your Documents uploaded successfully... ", Toast.LENGTH_LONG ).show();

                                                                                                    Intent intent=new Intent( UploadDocumentForLoanActivity.this,DashboardActivity.class );
                                                                                                    startActivity( intent );

                                                                                                }

                                                                                            }
                                                                                        } );


                                                                            }else if(getLoanType.equals( "Home Loan" ) ||getLoanType.equals( "Mortgage Loan" ))
                                                                            {
                                                                                LoanDocumentRef.child( "Admin View" ).child( "HM Loan" )
                                                                                        .child(getpid)
                                                                                        .updateChildren( userMap )
                                                                                        .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task <Void> task)
                                                                                            {
                                                                                                if(task.isSuccessful())
                                                                                                {
                                                                                                    loadingBar.dismiss();
                                                                                                    Toast.makeText( UploadDocumentForLoanActivity.this,
                                                                                                            "Thank You Dear, your Documents uploaded successfully... ", Toast.LENGTH_LONG ).show();

                                                                                                    Intent intent=new Intent( UploadDocumentForLoanActivity.this,DashboardActivity.class );
                                                                                                    startActivity( intent );

                                                                                                }

                                                                                            }
                                                                                        } );

                                                                            }

                                                                        }

                                                                    }
                                                                } );




                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(UploadDocumentForLoanActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                        }
                                        else
                                        {
                                            Toast.makeText(UploadDocumentForLoanActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(UploadDocumentForLoanActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(UploadDocumentForLoanActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

            if(getDocType == AADHAR_FRONT_DOC)
            {
                ImageUri_Aadhar_Front = data.getData();
                InputDocumentAadharFront.setImageURI( ImageUri_Aadhar_Front );
            } else if(getDocType == AADHAR_BACK_DOC)
            {
                ImageUri_Aadhar_Back=data.getData();
                InputDocumentAadharBack.setImageURI( ImageUri_Aadhar_Back );
            }else if(getDocType == YOUR_PASSPORT_SIZE_PHOTO_DOC)
            {
                ImageUri_Photo=data.getData();
                InputDocumentYourPhoto.setImageURI( ImageUri_Photo );
            }else if(getDocType == PAN_CARD_DOC)
            {
                ImageUri_Pan=data.getData();
                InputDocumentPan.setImageURI( ImageUri_Pan );
            } else
            {
                Toast.makeText(this, "Error, Try Again.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(UploadDocumentForLoanActivity.this, UploadDocumentForLoanActivity.class));
                finish();
            }

        }
    }

}

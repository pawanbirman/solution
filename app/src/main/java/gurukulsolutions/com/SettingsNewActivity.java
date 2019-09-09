package gurukulsolutions.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import gurukulsolutions.com.Admin.AdminDashboardActivity;
import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.User.DashboardActivity;

public class SettingsNewActivity extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText fullNameEditText, passwordEditText, addressEditText,securityEditText;
    private TextView profileChangeTextBtn,  closeTextBtn, saveTextButton;

    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePrictureRef;
    private String checker = "";
    private String getType="";
    private DatabaseReference UsersRef,ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings_new );
        getType=getIntent().getStringExtra( "Type" );


        storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        profileImageView = (CircleImageView) findViewById(R.id.settings_profile_image);
        fullNameEditText = (EditText) findViewById(R.id.settings_full_name);
        passwordEditText = (EditText) findViewById(R.id.settings_password);
        securityEditText = (EditText) findViewById(R.id.settings_security);
        addressEditText = (EditText) findViewById(R.id.settings_address);
        profileChangeTextBtn = (TextView) findViewById(R.id.profile_image_change_btn);
        closeTextBtn = (TextView) findViewById(R.id.close_settings_btn);
        saveTextButton = (TextView) findViewById(R.id.update_account_settings_btn);

        userInfoDisplay(profileImageView, fullNameEditText, passwordEditText,securityEditText, addressEditText);
        closeKeyboard();

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();

            }
        });
        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }



            }
        });
        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checker="clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1, 1)
                        .start(SettingsNewActivity.this);
            }
        });



    }

    private void closeKeyboard()
    {
        View view=this.getCurrentFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    private void updateOnlyUserInfo()
    {

        if(getType.equals( "user" ))
        {
            ref = FirebaseDatabase.getInstance().getReference().child("Users");
        }else  if(getType.equals( "admin" ))
        {
            ref = FirebaseDatabase.getInstance().getReference().child("Admins");
        }

        HashMap<String, Object> userMap = new HashMap<>();
        userMap. put("name", fullNameEditText.getText().toString());
        userMap. put("address", addressEditText.getText().toString());
        userMap. put("password", passwordEditText.getText().toString());
        userMap. put("Security_Question", securityEditText.getText().toString().toLowerCase());
        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);

        startActivity(new Intent(SettingsNewActivity.this, DashboardActivity.class));
        Toast.makeText(SettingsNewActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImageView.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(this, "Error, Try Again.", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SettingsNewActivity.this, SettingsNewActivity.class));
            finish();
        }
    }

    private void userInfoSaved()
    {
        if (TextUtils.isEmpty(fullNameEditText.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "address is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(passwordEditText.getText().toString()))
        {
            Toast.makeText(this, "phone no  is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked"))
        {
            uploadImage();
        }

    }

    private void uploadImage()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Update Profile");
        progressDialog.setMessage("Please wait, while we are updating your account information");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (imageUri != null)
        {
            final StorageReference fileRef = storageProfilePrictureRef
                    .child(Prevalent.currentOnlineUser.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        Uri downloadUrl = task.getResult();
                        myUrl = downloadUrl.toString();
                        if(getType.equals( "user" ))

                        {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                            HashMap<String, Object> userMap = new HashMap<>();
                            userMap. put("name", fullNameEditText.getText().toString());
                            userMap. put("address", addressEditText.getText().toString());
                            userMap. put("password", passwordEditText.getText().toString());
                            userMap. put("Security_Question", securityEditText.getText().toString().toLowerCase());
                            userMap. put("image", myUrl);
                            ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);
                            progressDialog.dismiss();
                            startActivity(new Intent(SettingsNewActivity.this, DashboardActivity.class));
                            Toast.makeText(SettingsNewActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else if(getType.equals( "admin" )) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child( "Admins" );

                            HashMap <String, Object> userMap = new HashMap <>();
                            userMap.put( "name", fullNameEditText.getText().toString() );
                            userMap.put( "address", addressEditText.getText().toString() );
                            userMap.put( "password", passwordEditText.getText().toString() );
                            userMap. put("Security_Question", securityEditText.getText().toString().toLowerCase());
                            userMap.put( "image", myUrl );
                            ref.child( Prevalent.currentOnlineUser.getPhone() ).updateChildren( userMap );
                            progressDialog.dismiss();
                            startActivity( new Intent( SettingsNewActivity.this, AdminDashboardActivity.class ) );
                            Toast.makeText( SettingsNewActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT ).show();
                            finish();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SettingsNewActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText passwordEditText,final EditText securityEditText, final EditText addressEditText)
    {
        if(getType.equals( "user" ))
        {
            UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());

        }else  if(getType.equals( "admin" ))
        {
            UsersRef = FirebaseDatabase.getInstance().getReference().child("Admins").child(Prevalent.currentOnlineUser.getPhone());
        }

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    if (dataSnapshot.child("image").exists())
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String password = dataSnapshot.child("password").getValue().toString();
                        String security = dataSnapshot.child("Security_Question").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);
                        fullNameEditText.setText(name);
                        passwordEditText.setText(password);
                        securityEditText.setText(security);
                        addressEditText.setText(address);

                    }
                    else
                        {

                            String name = dataSnapshot.child("name").getValue().toString();
                            String password = dataSnapshot.child("password").getValue().toString();
                            String security = dataSnapshot.child("Security_Question").getValue().toString();
                            fullNameEditText.setText(name);
                            passwordEditText.setText(password);
                            securityEditText.setText(security);

                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}

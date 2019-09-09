package gurukulsolutions.com;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shuhart.stepview.StepView;

import java.util.concurrent.TimeUnit;


import java.util.HashMap;

import gurukulsolutions.com.User.DashboardActivity;

public class RegisterWithOtpActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword, confirmPassword,securityQuestion;
    private ProgressDialog loadingBar;

    private int currentStep = 0;
    LinearLayout layout1,layout2;
    StepView stepView;
    AlertDialog dialog_verifying,profile_dialog;

    private static String uniqueIdentifier = null;
    private static final String UNIQUE_ID = "UNIQUE_ID";
    private static final long ONE_HOUR_MILLI = 60*60*1000;

    private static final String TAG = "FirebasePhoneNumAuth";

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;

    private String phoneNumber;
    private Button verifyCodeButton;

    private EditText phoneNum;
    private PinView verifyCodeET;
    private TextView phonenumberText;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_with_otp );
        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_input);
        securityQuestion=(EditText)findViewById(R.id.register_security_que_input);
        loadingBar = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        verifyCodeButton = (Button) findViewById(R.id.submit2);
        firebaseAuth = FirebaseAuth.getInstance();
        verifyCodeET = (PinView) findViewById(R.id.pinView);
        phonenumberText = (TextView) findViewById(R.id.phonenumberText);


        stepView = findViewById(R.id.step_view);
        stepView.setStepsNumber(3);
        stepView.go(0, true);
        layout1.setVisibility(View.VISIBLE);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name = InputName.getText().toString();
                final String phone = InputPhoneNumber.getText().toString();
                String password = InputPassword.getText().toString();
                String confirm_password = confirmPassword.getText().toString();
                String security_Question = securityQuestion.getText().toString().toLowerCase();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterWithOtpActivity.this, "Please write your name...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phone)) {

                    Toast.makeText(RegisterWithOtpActivity.this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
                }else if(phone.length() < 10)
                {
                    InputPhoneNumber.setError("Please enter a valid phone");
                    InputPhoneNumber.requestFocus();

                }
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterWithOtpActivity.this, "Please write your password...", Toast.LENGTH_SHORT).show();
                } else if(!confirm_password.equals(password)) {
                    InputPassword.setError("Password and confirm password should be same..");
                    InputPassword.requestFocus();
                    confirmPassword.setError("Password and confirm password should be same..");
                    confirmPassword.requestFocus();
                    InputPassword.setText("");
                    confirmPassword.setText("");
                    Toast.makeText(RegisterWithOtpActivity.this, "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(security_Question)) {
                    securityQuestion.setError("Security question is required..");
                    securityQuestion.requestFocus();
                    Toast.makeText(RegisterWithOtpActivity.this, "Please write your mother's name...", Toast.LENGTH_SHORT).show();
                    return;

                } else {

                    loadingBar.setTitle("Validating Phone number..");
                    loadingBar.setMessage("Please wait, while we are checking the credentials.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    final DatabaseReference RootRef;
                    RootRef=FirebaseDatabase.getInstance().getReference();
                    RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(!(dataSnapshot.child("Users").child(phone).exists()))
                            {
                                String phoneNumber ="+91"+InputPhoneNumber.getText().toString();
                                phonenumberText.setText(phoneNumber);
                                 if (currentStep < stepView.getStepCount() - 1)
                                    {
                                        currentStep++;
                                        stepView.go(currentStep, true);
                                    } else {
                                        stepView.done(true);
                                    }
                                    layout1.setVisibility(View.GONE);
                                    layout2.setVisibility(View.VISIBLE);
                                     loadingBar.dismiss();
                                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                            phoneNumber,        // Phone number to verify
                                            60,                 // Timeout duration
                                            TimeUnit.SECONDS,   // Unit of timeout
                                            RegisterWithOtpActivity.this,               // Activity (for callback binding)
                                            mCallbacks);        // OnVerificationStateChangedCallbacks

                            }
                            else{
                                Toast.makeText(RegisterWithOtpActivity.this, "This " + phone+ " already exists", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Toast.makeText(RegisterWithOtpActivity.this, "Please try again using another phone number..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterWithOtpActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });


        mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout= inflater.inflate(R.layout.processing_dialog,null);
                AlertDialog.Builder show = new AlertDialog.Builder(RegisterWithOtpActivity.this);

                show.setView(alertLayout);
                show.setCancelable(false);
                dialog_verifying = show.create();
                dialog_verifying.show();

                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(RegisterWithOtpActivity.this,"Please verify your Account with Valid Phone Number..",Toast.LENGTH_SHORT).show();


                startActivity(new Intent(RegisterWithOtpActivity.this,RegisterWithOtpActivity.class));

            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };

        verifyCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String verificationCode = verifyCodeET.getText().toString();
                if(verificationCode.isEmpty()){
                    Toast.makeText(RegisterWithOtpActivity.this,"Enter verification code",Toast.LENGTH_SHORT).show();
                }else {

                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout= inflater.inflate(R.layout.processing_dialog,null);
                    AlertDialog.Builder show = new AlertDialog.Builder(RegisterWithOtpActivity.this);

                    show.setView(alertLayout);
                    show.setCancelable(false);
                    dialog_verifying = show.create();
                    dialog_verifying.show();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            dialog_verifying.dismiss();

                            if (currentStep < stepView.getStepCount() - 1) {
                                currentStep++;
                                stepView.go(currentStep, true);
                            } else {
                                stepView.done(true);
                            }
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.GONE);

                            loadingBar.setTitle("Completing your profile..");
                            loadingBar.setMessage("Please wait, while we are checking the credentials.");
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.show();

                            String name = InputName.getText().toString();
                            final String phone = InputPhoneNumber.getText().toString();
                            String password = InputPassword.getText().toString();
                            String confirm_password = confirmPassword.getText().toString();
                            String security_Question = securityQuestion.getText().toString().toLowerCase();

                            HashMap<String, Object> userdataMap = new HashMap<>();
                            userdataMap.put("phone", phone);
                            userdataMap.put("password", password);
                            userdataMap.put("name", name);
                            userdataMap.put("Security_Question",security_Question );
                            final DatabaseReference RootRef;
                            RootRef=FirebaseDatabase.getInstance().getReference();


                            RootRef.child("Users").child(phone).updateChildren(userdataMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterWithOtpActivity.this, "Congratulations your account has been created...", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();

                                                Intent intent = new Intent(RegisterWithOtpActivity.this, LoginActivity.class);
                                                startActivity(intent);

                                            }
                                            else{
                                                loadingBar.dismiss();
                                                Toast.makeText(RegisterWithOtpActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                            // ...
                        } else {

                            loadingBar.dismiss();
                            dialog_verifying.dismiss();
                            Toast.makeText(RegisterWithOtpActivity.this,"" +
                                    "Please Enter correct OTP.. ",Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {


                            }
                        }
                    }
                });
    }


    @Override
    public void onBackPressed()
    {


                    Intent intent=new Intent(RegisterWithOtpActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);


    }



}

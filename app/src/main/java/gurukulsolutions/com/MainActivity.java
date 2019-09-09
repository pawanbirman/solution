package gurukulsolutions.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gurukulsolutions.com.Model.Users;
import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.User.DashboardActivity;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog loadingBar;
    public String SkipPhoneNumber;
    public String SkipPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        SkipPhoneNumber="7993989697";
        SkipPassword="123";

        loadingBar = new ProgressDialog( this );
        Paper.init( this );

        String UserPhoneKey = Paper.book().read( Prevalent.UserPhoneKey );
        String UserPasswordKey = Paper.book().read( Prevalent.UserPasswordKey );
        if (UserPhoneKey != "" && UserPasswordKey != "") {
            if (!TextUtils.isEmpty( UserPhoneKey ) && !TextUtils.isEmpty( UserPasswordKey )) {

                loadingBar.setTitle("Logging.....");
                loadingBar.setMessage("Please wait, while we are getting your saved information...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                AllowAccess( UserPhoneKey, UserPasswordKey );

                DirectLogin();


            }
        }
    }

    private void DirectLogin()
    {

        
    }


    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child( "Users" ).child( phone ).exists()) {
                    Users usersData = dataSnapshot.child( "Users" ).child( phone ).getValue( Users.class );

                    if (usersData.getPhone().equals( phone )) {
                        if (usersData.getPassword().equals( password )) {
                            Toast.makeText( MainActivity.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT ).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent( MainActivity.this, DashboardActivity.class );
                            Prevalent.currentOnlineUser = usersData;
                            startActivity( intent );
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText( MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } else {
                    Toast.makeText( MainActivity.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT ).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


    }

    public void onlogin(View view) {
        startActivity( new Intent( MainActivity.this, LoginActivity.class ) );

    }

    public void joinnow(View view) {
        startActivity( new Intent( this, RegisterWithOtpActivity.class ) );
    }

    public  void skipuser(View view)
    {

        String phone = SkipPhoneNumber.toString();
        String password = SkipPassword.toString();
        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAccessToAccount(phone, password);
    }

    }

    private void AllowAccessToAccount(final String phone, final String password)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child("Users").child(phone).exists() )
                {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {


                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect..", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });




}
}



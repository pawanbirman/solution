package gurukulsolutions.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.User.HMLoanApplicationActivity;
import gurukulsolutions.com.User.IncomeTaxReturnActivity;
import gurukulsolutions.com.User.InsuranceActivity;
import gurukulsolutions.com.User.LoanApplicationActivity;
import gurukulsolutions.com.User.UserDashboardActivity;

public class ServicesFragment extends Fragment {
    private DatabaseReference welComeUser;
    private TextView WelcomeUserName;
    private Button pLoan,bLoan,hLoan,mLoan,yourDashboard,incomeTax,insurance;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_services, container, false );
        welComeUser=FirebaseDatabase.getInstance().getReference().child( "Users" );
        WelcomeUserName=(TextView) view.findViewById( R.id.welcome_user_name );


        pLoan=(Button) view.findViewById( R.id. service_personal_btn);
        bLoan=(Button) view.findViewById( R.id.service_business_btn ) ;
        hLoan=(Button) view.findViewById( R.id. service_home_btn);
        mLoan=(Button) view.findViewById( R.id. service_mortage_btn);
        yourDashboard=(Button) view.findViewById( R.id.service_status_btn ) ;
        incomeTax=(Button) view.findViewById( R.id.service_incometaxreturn_btn );
        insurance=(Button) view.findViewById( R.id.service_insurance_btn) ;



        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat( "MMddyyyy" );
        saveCurrentDate = currentDate.format( calendar.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HHmmss" );
        saveCurrentTime = currentTime.format( calendar.getTime() );

        productRandomKey=saveCurrentDate + saveCurrentTime;

        welComeUser.child(Prevalent.currentOnlineUser.getPhone() ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0)
                {
                    String name=dataSnapshot.child("name").getValue().toString();

                    WelcomeUserName.setText("Welcome Dear " +name+ " Please Select below Service..");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
        pLoan.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {

              Intent ploan=new Intent( getActivity(),LoanApplicationActivity.class );
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
                Intent bloan=new Intent( getActivity(),LoanApplicationActivity.class );
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
                Intent hloan=new Intent( getActivity(),HMLoanApplicationActivity.class );
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
                Intent mloan=new Intent( getActivity(),HMLoanApplicationActivity.class );
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
                Intent yourdashboard=new Intent( getActivity(),UserDashboardActivity.class );
                startActivity( yourdashboard );

            }
        } );


        incomeTax.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent incometax=new Intent( getActivity(),IncomeTaxReturnActivity.class );
                incometax.putExtra(  "Application Type","Income Tax" );
                incometax.putExtra( "pid", productRandomKey);
                startActivity( incometax );

            }
        } );

        insurance.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insurance=new Intent( getActivity(),InsuranceActivity.class );
                insurance.putExtra(  "Application Type","Insurance" );
                insurance.putExtra( "pid", productRandomKey);
                startActivity( insurance );

            }
        } );
        return view;
    }


}

package gurukulsolutions.com;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class ServiceNewFragment extends Fragment {

    private ImageView quicklyLoan,yourDashboard,incomeTax,insurance;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;
    private TextView quicklyLoanTxt,yourDashboardTxt,incomeTaxTxt,insuranceTxt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_service_new, container, false );


        quicklyLoan=(ImageView) view.findViewById(R.id.service_quickly_loan);
        yourDashboard=(ImageView) view.findViewById( R.id.service_your_dashboard ) ;
        incomeTax=(ImageView) view.findViewById( R.id.service_itr );
        insurance=(ImageView) view.findViewById( R.id.service_insurance) ;



        quicklyLoanTxt=(TextView) view.findViewById(R.id.service_quickly_loan_txt);
        yourDashboardTxt=(TextView) view.findViewById( R.id.service_your_dashboard_txt ) ;
        incomeTaxTxt=(TextView) view.findViewById( R.id.service_itr_txt );
        insuranceTxt=(TextView) view.findViewById( R.id.service_insurance_txt) ;



        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat( "MMddyyyy" );
        saveCurrentDate = currentDate.format( calendar.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HHmmss" );
        saveCurrentTime = currentTime.format( calendar.getTime() );

        productRandomKey=saveCurrentDate + saveCurrentTime;

        quicklyLoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent qloan=new Intent( getActivity(),QuicklyLoanActivity.class );
                startActivity( qloan );


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


        quicklyLoanTxt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent qloan=new Intent( getActivity(),QuicklyLoanActivity.class );
                startActivity( qloan );


            }
        } );

        yourDashboardTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent yourdashboard=new Intent( getActivity(),UserDashboardActivity.class );
                startActivity( yourdashboard );

            }
        } );


        incomeTaxTxt.setOnClickListener( new View.OnClickListener()
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

        insuranceTxt.setOnClickListener( new View.OnClickListener()
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

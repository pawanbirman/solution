package gurukulsolutions.com.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import gurukulsolutions.com.Model.GetAdminPBLoanApplications;
import gurukulsolutions.com.Model.GetApplications;
import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ViewHolder.GetAdminPBLoanApplicationsViewHolder;

public class AdminGetPBLoanApplicationsActivity extends AppCompatActivity {
    private RecyclerView AdminApplication;
    private  RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_get_pbloan_applications );

        AdminApplication=findViewById( R.id.admin_loan_dashboard );
        AdminApplication.setHasFixedSize( true );
        layoutManager=new LinearLayoutManager(  this);
        AdminApplication.setLayoutManager( layoutManager );
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        final DatabaseReference adminloandashboard=FirebaseDatabase.getInstance().getReference().child("Applications").child("Admin View").child( "PB Loan" );
        FirebaseRecyclerOptions<GetAdminPBLoanApplications> options=new FirebaseRecyclerOptions.Builder<GetAdminPBLoanApplications>()
                .setQuery(adminloandashboard
                        ,GetAdminPBLoanApplications.class)
                .build();
        FirebaseRecyclerAdapter<GetAdminPBLoanApplications,GetAdminPBLoanApplicationsViewHolder> adapter=
                new FirebaseRecyclerAdapter<GetAdminPBLoanApplications, GetAdminPBLoanApplicationsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GetAdminPBLoanApplicationsViewHolder holder, int position, @NonNull GetAdminPBLoanApplications model)
                    {
                        holder.txtAdminName.setText("Name : " +model.getBorrower_Name());
                        holder.txtAdminContactNo.setText("Phone No : " +model.getPhoneNo());
                        holder.txtAdminDOB.setText("DOB : "+model.getDOB());
                        holder.txtAdminGender.setText("Gender : "+model.getGender());
                        holder.txtAdminOccupation.setText("Occupation : " +model.getOccupation());
                        holder.txtAdminCompanyName.setText("Company Name : " +model.getCompany_Name());
                        holder.txtAdminDOJ.setText("DOJ : "+model.getDOJ());
                        holder.txtAdminNetSalary.setText("Net Salary : "+model.getNet_Salary());
                        holder. txtAdminOldLoan.setText("Old Loan : "+model.getOld_Loan_Exists_or_not());
                        holder.txtAdminOldLoanAmount.setText("Old Loan Amount : "+model.getOld_Loan_Amount());
                        holder.txtAdminNewLoanAmount.setText("New Loan Amount : "+model.getNew_Loan_Amount());
                        holder.txtAdminMeetingTime.setText("Meeting Time : "+model.getMeeting_Time());
                        holder.txtAdminAddress.setText("Address : " +model.getAddress());
                        holder.txtAdminLoanType.setText("Loan Type : "+model.getLoan_Type());
                        holder.txtAdminApplicationNo.setText("Application No : "+model.getPid());

                        Picasso.get().load(model.getAadhar_Front()).into(holder.imageAdminAadhaarFront);
                        Picasso.get().load(model.getAadhar_Back()).into(holder.imageAdminAadhaarBack);
                        Picasso.get().load(model.getPhoto()).into(holder.imageAdminPhoto);
                        Picasso.get().load(model.getPan_Card()).into(holder.imageAdminPanCard);

                    }

                    @NonNull
                    @Override
                    public GetAdminPBLoanApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_getpbloan_application_layout,parent,false);
                        GetAdminPBLoanApplicationsViewHolder holder=new GetAdminPBLoanApplicationsViewHolder(view);
                        return holder;
                    }
                };

        AdminApplication.setAdapter(adapter);
        adapter.startListening();




    }
}

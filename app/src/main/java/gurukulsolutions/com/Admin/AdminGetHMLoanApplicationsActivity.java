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

import gurukulsolutions.com.Model.GetAdminHMLoanApplications;
import gurukulsolutions.com.Model.GetAdminPBLoanApplications;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ViewHolder.GetAdminHMLoanApplicationsViewHolder;
import gurukulsolutions.com.ViewHolder.GetAdminPBLoanApplicationsViewHolder;

public class AdminGetHMLoanApplicationsActivity extends AppCompatActivity {
    private RecyclerView AdminApplication;
    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_get_hmloan_applications );

        AdminApplication=findViewById( R.id.admin_hmloan_dashboard );
        AdminApplication.setHasFixedSize( true );
        layoutManager=new LinearLayoutManager(  this);
        AdminApplication.setLayoutManager( layoutManager );
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        final DatabaseReference adminloandashboard=FirebaseDatabase.getInstance().getReference().child("Applications").child("Admin View").child( "HM Loan" );
        FirebaseRecyclerOptions<GetAdminHMLoanApplications> options=new FirebaseRecyclerOptions.Builder<GetAdminHMLoanApplications>()
                .setQuery(adminloandashboard
                        ,GetAdminHMLoanApplications.class)
                .build();
        FirebaseRecyclerAdapter<GetAdminHMLoanApplications,GetAdminHMLoanApplicationsViewHolder> adapter=
                new FirebaseRecyclerAdapter<GetAdminHMLoanApplications, GetAdminHMLoanApplicationsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GetAdminHMLoanApplicationsViewHolder holder, int position, @NonNull GetAdminHMLoanApplications model)
                    {
                        holder.txtAdminName.setText("Name : " +model.getBorrower_Name());
                        holder.txtAdminContactNo.setText("Phone No : " +model.getPhoneNo());
                        holder.txtAdminOccupation.setText("Occupation : " +model.getOccupation());
                        holder. txtAdminOldLoan.setText("Old Loan : "+model.getOld_Loan_Exists_or_not());
                        holder.txtAdminNewLoanAmount.setText("New LOan Amount : "+model.getNew_Loan_Amount());
                        holder.txtAdminMeetingTime.setText("Meeting Time : "+model.getMeeting_Time());
                        holder.txtAdminAddress.setText("Address : " +model.getAddress());
                        holder.txtAdminLoanType.setText("Loan Type : "+model.getLoan_Type());
                        holder.txtAdminApplicationNo.setText("Application No : "+model.getPid());

                        Picasso.get().load(model.getAadhar_Front()).into(holder.imageAdminhmAadhaarFront);
                        Picasso.get().load(model.getAadhar_Back()).into(holder.imageAdminhmAadhaarBack);
                        Picasso.get().load(model.getPhoto()).into(holder.imageAdminhmPhoto);
                        Picasso.get().load(model.getPan_Card()).into(holder.imageAdminhmPanCard);

                    }

                    @NonNull
                    @Override
                    public GetAdminHMLoanApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_gethmloan_application_layout,parent,false);
                        GetAdminHMLoanApplicationsViewHolder holder=new GetAdminHMLoanApplicationsViewHolder(view);
                        return holder;
                    }
                };

        AdminApplication.setAdapter(adapter);
        adapter.startListening();




    }
}

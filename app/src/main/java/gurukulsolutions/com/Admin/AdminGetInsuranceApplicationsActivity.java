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
import gurukulsolutions.com.Model.GetAdminInsuranceApplications;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ViewHolder.GetAdminHMLoanApplicationsViewHolder;
import gurukulsolutions.com.ViewHolder.GetAdminInsuranceApplicationsViewHolder;

public class AdminGetInsuranceApplicationsActivity extends AppCompatActivity {


    private RecyclerView AdminApplication;
    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_get_insurance_applications );

        AdminApplication=findViewById( R.id.admin_insurance_dashboard );
        AdminApplication.setHasFixedSize( true );
        layoutManager=new LinearLayoutManager(  this);
        AdminApplication.setLayoutManager( layoutManager );
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        final DatabaseReference adminloandashboard=FirebaseDatabase.getInstance().getReference().child("Applications").child("Admin View").child( "Insurance" );
        FirebaseRecyclerOptions<GetAdminInsuranceApplications> options=new FirebaseRecyclerOptions.Builder<GetAdminInsuranceApplications>()
                .setQuery(adminloandashboard
                        ,GetAdminInsuranceApplications.class)
                .build();
        FirebaseRecyclerAdapter<GetAdminInsuranceApplications,GetAdminInsuranceApplicationsViewHolder> adapter=
                new FirebaseRecyclerAdapter<GetAdminInsuranceApplications, GetAdminInsuranceApplicationsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GetAdminInsuranceApplicationsViewHolder holder, int position, @NonNull GetAdminInsuranceApplications model)
                    {
                        holder.txtAdminName.setText("Name : " +model.getOwner_Name());
                        holder.txtAdminContactNo.setText("Phone No : " +model.getContact_No());
                        holder.txtAdminExpiryDate.setText("Expiry Date of Current Policy : " +model.getDOExpiry_of_current_policy());
                        holder. txtAdminvehicleType.setText("Vehicle Type: "+model.getVehicle_Type());
                        holder.txtAdminInsuranceType.setText("Insurance Type : "+model.getInsurance_Type());
                        holder.txtAdminApplicationNo.setText("Application No : "+model.getPid());
                        holder.txtAdminApplicationType.setText("Application Type : "+model.getLoan_Type());

                        Picasso.get().load(model.getRC_Front()).into(holder.imageAdminRCFront);
                        Picasso.get().load(model.getRC_Back()).into(holder.imageAdminRCBack);

                    }

                    @NonNull
                    @Override
                    public GetAdminInsuranceApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_getinsurance_application_layout,parent,false);
                        GetAdminInsuranceApplicationsViewHolder holder=new GetAdminInsuranceApplicationsViewHolder(view);
                        return holder;
                    }
                };

        AdminApplication.setAdapter(adapter);
        adapter.startListening();




    }
}

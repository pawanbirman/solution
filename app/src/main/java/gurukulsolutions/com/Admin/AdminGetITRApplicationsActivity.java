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
import gurukulsolutions.com.Model.GetAdminITRApplications;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ViewHolder.GetAdminHMLoanApplicationsViewHolder;
import gurukulsolutions.com.ViewHolder.GetAdminITRApplicationsViewHolder;

public class AdminGetITRApplicationsActivity extends AppCompatActivity {
    private RecyclerView AdminApplication;
    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_get_itrapplications );

        AdminApplication=findViewById( R.id.admin_itr_dashboard );
        AdminApplication.setHasFixedSize( true );
        layoutManager=new LinearLayoutManager(  this);
        AdminApplication.setLayoutManager( layoutManager );
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        final DatabaseReference adminloandashboard=FirebaseDatabase.getInstance().getReference().child("Applications").child("Admin View").child( "ITR" );
        FirebaseRecyclerOptions<GetAdminITRApplications> options=new FirebaseRecyclerOptions.Builder<GetAdminITRApplications>()
                .setQuery(adminloandashboard
                        ,GetAdminITRApplications.class)
                .build();
        FirebaseRecyclerAdapter<GetAdminITRApplications,GetAdminITRApplicationsViewHolder> adapter=
                new FirebaseRecyclerAdapter<GetAdminITRApplications, GetAdminITRApplicationsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GetAdminITRApplicationsViewHolder holder, int position, @NonNull GetAdminITRApplications model)
                    {
                        holder.txtAdminName.setText("Name : " +model.getTax_Payer_Name());
                        holder.txtAdminContactNo.setText("Phone No : " +model.getPhoneNumber());
                        holder.txtAdminEmail.setText("EMAIL : " +model.getEmail());
                        holder. txtAdminAadhaarNo.setText("AADHAAR No : "+model.getAADHAAR_number());
                        holder.txtAdminPanNo.setText("PAN Card No : "+model.getPAN_Card());
                        holder.txtAdminApplicationType.setText("Application Type : "+model.getLoan_Type());
                        holder.txtAdminApplicationNo.setText("Application No : "+model.getPid());

                    }

                    @NonNull
                    @Override
                    public GetAdminITRApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_getitr_application_layout,parent,false);
                        GetAdminITRApplicationsViewHolder holder=new GetAdminITRApplicationsViewHolder(view);
                        return holder;
                    }
                };

        AdminApplication.setAdapter(adapter);
        adapter.startListening();




    }
}

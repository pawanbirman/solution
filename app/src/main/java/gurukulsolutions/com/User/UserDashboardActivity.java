package gurukulsolutions.com.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gurukulsolutions.com.Model.GetApplications;
import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ViewHolder.GetApplicationsViewHolder;

public class UserDashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_dashboard );

        recyclerView=findViewById( R.id.user_dashboard );
        recyclerView.setHasFixedSize( true );
        layoutManager=new LinearLayoutManager(  this);
        recyclerView.setLayoutManager( layoutManager );

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        final DatabaseReference userdashboard=FirebaseDatabase.getInstance().getReference().child("Applications");
        FirebaseRecyclerOptions<GetApplications> options=new FirebaseRecyclerOptions.Builder<GetApplications>()
                .setQuery(userdashboard.child("User View")
                        .child( Prevalent.currentOnlineUser.getPhone() ),GetApplications.class)
                .build();
        FirebaseRecyclerAdapter<GetApplications,GetApplicationsViewHolder> adapter
                =new FirebaseRecyclerAdapter <GetApplications, GetApplicationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GetApplicationsViewHolder holder, int position, @NonNull final GetApplications model)
            {
                if( model.getLoan_Type().equals( "Personal Loan" ) ||  model.getLoan_Type().equals( "Business Loan" ))

                {
                    holder.txtApplicationNo.setText( "Application No:" +model.getPid() );
                         holder.txtLoanType.setText( "Loan Type:" + model.getLoan_Type() );
                        holder.txtLoanAmount.setText( "Loan Amount:" + model.getNew_Loan_Amount() );
                        holder.txtOccupation.setText( "Occupation:" + model.getOccupation() );
                         holder.txtNetSalary.setText( "Net Salary:" + model.getNet_Salary() );


                    holder.itemView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            CharSequence options[]=new CharSequence[]
                                    {
                                            "Delete Application",
                                            "Application Status"

                                    };

                            AlertDialog.Builder builder=new AlertDialog.Builder( UserDashboardActivity.this );
                            builder.setTitle("Application Options:");

                            builder.setItems( options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i)
                                {

                                    if(i==0)
                                    {
                                        final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                        applicationRef.child("User View")
                                                .child(Prevalent.currentOnlineUser.getPhone())
                                                .child(model.getPid())
                                                .removeValue()
                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText( UserDashboardActivity.this, "Application Deleted Successfully..", Toast.LENGTH_SHORT ).show();
                                                            Intent intent=new Intent(UserDashboardActivity.this,UserDashboardActivity.class);
                                                            startActivity(intent);

                                                        }

                                                    }
                                                } );
                                    }
                                    if(i==1)
                                    {
                                        Toast.makeText( UserDashboardActivity.this, "your Application for  "+model.getLoan_Type()+ " is in process..", Toast.LENGTH_LONG ).show();

                                    }

                                }
                            } );
                            builder.show();

                        }
                    } );



                }
                else if ( model.getLoan_Type().equals( "Home Loan" ) || model.getLoan_Type().equals( "Mortgage Loan" ))
                    {
                        holder.txtApplicationNo.setText( "Application No:" +model.getPid() );
                        holder.txtLoanType.setText( "Loan Type: " +model.getLoan_Type() );
                        holder.txtLoanAmount.setText( "Loan Amount: " +model.getNew_Loan_Amount());
                        holder.txtOccupation.setText("Occupation: " +model.getOccupation());
                        holder.txtNetSalary.setText("Meeting Time:" +model.getMeeting_Time());

                        holder.itemView.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[]=new CharSequence[]
                                        {

                                                "Edit Application",
                                                "Delete Application",
                                                "Application Status"

                                        };

                                AlertDialog.Builder builder=new AlertDialog.Builder( UserDashboardActivity.this );
                                builder.setTitle("Application Options:");

                                builder.setItems( options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i)
                                    {
                                        if(i==0)
                                        {
                                            final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                            applicationRef.child("User View")
                                                    .child(Prevalent.currentOnlineUser.getPhone())
                                                    .child(model.getPid())
                                                    .removeValue()
                                                    .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task)
                                                        {
                                                            if(task.isSuccessful())
                                                            {
                                                                Toast.makeText( UserDashboardActivity.this, "Application Deleted Now Again Apply..", Toast.LENGTH_SHORT ).show();

                                                            }

                                                        }
                                                    } );
                                            Intent intent=new Intent(UserDashboardActivity.this,HMLoanApplicationActivity.class);
                                            intent.putExtra( "pid",model.getPid() );
                                            intent.putExtra( "Loan Type",model.getLoan_Type() );
                                            startActivity(intent);
                                        }
                                        if(i==1)
                                        {
                                            final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                            applicationRef.child("User View")
                                                    .child(Prevalent.currentOnlineUser.getPhone())
                                                    .child(model.getPid())
                                                    .removeValue()
                                                    .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task)
                                                        {
                                                            if(task.isSuccessful())
                                                            {
                                                                Toast.makeText( UserDashboardActivity.this, "Application Deleted Successfully..", Toast.LENGTH_SHORT ).show();
                                                                Intent intent=new Intent(UserDashboardActivity.this,UserDashboardActivity.class);
                                                                startActivity(intent);

                                                            }

                                                        }
                                                    } );
                                        }
                                        if(i==2)
                                        {
                                            Toast.makeText( UserDashboardActivity.this, "your Application for  "+model.getLoan_Type()+ " is in process..", Toast.LENGTH_LONG ).show();

                                        }

                                    }
                                } );
                                builder.show();

                            }
                        } );



                    }
                else if ( model.getLoan_Type().equals( "Income Tax" ))
                {
                    holder.txtApplicationNo.setText( "Application No:" +model.getPid() );
                    holder.txtLoanType.setText( "Application: " +model.getLoan_Type() );
                    holder.txtLoanAmount.setText( "Payer Name: " +model.getTax_Payer_Name() );
                    holder.txtOccupation.setText("AADHAAR: " +model.getAADHAAR_number());
                    holder.txtNetSalary.setText("PAN:" +model.getPAN_Card() );


                    holder.itemView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            CharSequence options[]=new CharSequence[]
                                    {

                                            "Edit Application",
                                            "Delete Application",
                                            "Application Status"

                                    };

                            AlertDialog.Builder builder=new AlertDialog.Builder( UserDashboardActivity.this );
                            builder.setTitle("Application Options:");

                            builder.setItems( options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i)
                                {
                                    if(i==0)
                                    {

                                        final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                        applicationRef.child("User View")
                                                .child(Prevalent.currentOnlineUser.getPhone())
                                                .child(model.getPid())
                                                .removeValue()
                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText( UserDashboardActivity.this, "Application Deleted Now Again Apply..", Toast.LENGTH_SHORT ).show();

                                                        }

                                                    }
                                                } );
                                        Intent intent=new Intent(UserDashboardActivity.this,IncomeTaxReturnActivity.class);
                                        intent.putExtra( "pid",model.getPid() );
                                        intent.putExtra( "Loan Type",model.getLoan_Type() );
                                        startActivity(intent);
                                    }
                                    if(i==1)
                                    {
                                        final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                        applicationRef.child("User View")
                                                .child(Prevalent.currentOnlineUser.getPhone())
                                                .child(model.getPid())
                                                .removeValue()
                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText( UserDashboardActivity.this, "Application Deleted Successfully..", Toast.LENGTH_SHORT ).show();
                                                            Intent intent=new Intent(UserDashboardActivity.this,UserDashboardActivity.class);
                                                            startActivity(intent);

                                                        }

                                                    }
                                                } );
                                    }
                                    if(i==2)
                                    {
                                        Toast.makeText( UserDashboardActivity.this, "your Application for  "+model.getLoan_Type()+ " is in process..", Toast.LENGTH_LONG ).show();

                                    }

                                }
                            } );
                            builder.show();

                        }
                    } );

                }
                else if ( model.getLoan_Type().equals( "Insurance" ))
                {
                    holder.txtApplicationNo.setText( "Application No:" +model.getPid() );
                    holder.txtLoanType.setText( "Application: " +model.getLoan_Type() );
                    holder.txtLoanAmount.setText( "Owner Name: " +model.getOwner_Name() );
                    holder.txtOccupation.setText("Vehicle Type: " +model.getVehicle_Type());
                    holder.txtNetSalary.setText("Insurance Type:" +model.getInsurance_Type());


                    holder.itemView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            CharSequence options[]=new CharSequence[]
                                    {

                                            "Edit Application",
                                            "Delete Application",
                                            "Application Status"

                                    };

                            AlertDialog.Builder builder=new AlertDialog.Builder( UserDashboardActivity.this );
                            builder.setTitle("Application Options:");

                            builder.setItems( options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i)
                                {
                                    if(i==0)
                                    {

                                        final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                        applicationRef.child("User View")
                                                .child(Prevalent.currentOnlineUser.getPhone())
                                                .child(model.getPid())
                                                .removeValue()
                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText( UserDashboardActivity.this, "Application Deleted Now Again Apply..", Toast.LENGTH_SHORT ).show();

                                                        }

                                                    }
                                                } );
                                        Intent intent=new Intent(UserDashboardActivity.this,InsuranceActivity.class);
                                        intent.putExtra( "pid",model.getPid() );
                                        intent.putExtra( "Loan Type",model.getLoan_Type() );
                                        startActivity(intent);
                                    }
                                    if(i==1)
                                    {
                                        final DatabaseReference applicationRef=FirebaseDatabase.getInstance().getReference().child( "Applications" );
                                        applicationRef.child("User View")
                                                .child(Prevalent.currentOnlineUser.getPhone())
                                                .child(model.getPid())
                                                .removeValue()
                                                .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText( UserDashboardActivity.this, "Application Deleted Successfully..", Toast.LENGTH_SHORT ).show();
                                                            Intent intent=new Intent(UserDashboardActivity.this,UserDashboardActivity.class);
                                                            startActivity(intent);

                                                        }

                                                    }
                                                } );
                                    }
                                    if(i==2)
                                    {
                                        Toast.makeText( UserDashboardActivity.this, "your Application for  "+model.getLoan_Type()+ " is in process..", Toast.LENGTH_LONG ).show();

                                    }

                                }
                            } );
                            builder.show();

                        }
                    } );

                }




            }

            @NonNull
            @Override
            public GetApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
            {
                View view=LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.user_dashboard_layout,viewGroup,false );
                GetApplicationsViewHolder holder=new GetApplicationsViewHolder( view );
                return  holder;
            }
        };

        recyclerView.setAdapter( adapter );
        adapter.startListening();

    }
}

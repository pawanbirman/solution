package gurukulsolutions.com.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import gurukulsolutions.com.Admin.AdminAddServicesActivity;
import gurukulsolutions.com.CareerFragment;
import gurukulsolutions.com.LocationFragment;
import gurukulsolutions.com.MainActivity;
import gurukulsolutions.com.Model.Products;
import gurukulsolutions.com.Prevalent.Prevalent;
import gurukulsolutions.com.QsPartnerActivity;
import gurukulsolutions.com.QuicklyLoanActivity;
import gurukulsolutions.com.R;
import gurukulsolutions.com.ServiceNewFragment;
import gurukulsolutions.com.SettingsNewActivity;
import gurukulsolutions.com.ViewHolder.DisplayViewHolder;
import io.paperdb.Paper;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference ProductRef,GetWelcomeUsername;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;
    private String getType="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle !=null)
        {
            getType = getIntent().getStringExtra("Type");
        }


        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );

        if(!getType.equals("admin"))
        {
            toolbar.setTitle("Welcome "+Prevalent.currentOnlineUser.getName());
        }
        else
            {
                toolbar.setTitle("Welcome User ");

            }

        setSupportActionBar( toolbar );
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MM dd,yyyy  " );
        saveCurrentDate = currentDate.format( calendar.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( " HH : mm :ss a" );
        saveCurrentTime = currentTime.format( calendar.getTime() );

        productRandomKey=saveCurrentDate + saveCurrentTime;




        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        ProductRef=FirebaseDatabase.getInstance().getReference().child("Products");


        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );



        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        if(!getType.equals("admin"))
        {
            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        }


        recyclerView=findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductRef,Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products,DisplayViewHolder>adapter=
                new FirebaseRecyclerAdapter<Products, DisplayViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DisplayViewHolder holder, int position, @NonNull final Products model)
                    {
                        holder.txtProductName.setText(model.getPname());
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v)
                                {
                                    if(getType.equals("admin"))
                                    {
                                        CharSequence options[]=new CharSequence[]
                                                {

                                                        "Edit Existing Idea",
                                                        "Delete Existing Idea",


                                                };

                                        AlertDialog.Builder builder=new AlertDialog.Builder( DashboardActivity.this );
                                        builder.setTitle("Admin Options:");

                                        builder.setItems( options, new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i)
                                            {
                                                if(i==0)
                                                {
                                                    Intent intent=new Intent(DashboardActivity.this,AdminAddServicesActivity.class);
                                                    intent.putExtra( "pid",model.getPid() );
                                                    intent.putExtra( "Service",model.getService() );
                                                    startActivity(intent);

                                                }

                                                if(i==1)
                                                {
                                                    ProductRef
                                                            .child(model.getPid())
                                                            .removeValue()
                                                            .addOnCompleteListener( new OnCompleteListener <Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task)
                                                                {
                                                                    if(task.isSuccessful())
                                                                    {
                                                                        Toast.makeText( DashboardActivity.this, "Deleted..", Toast.LENGTH_SHORT ).show();

                                                                    }

                                                                }
                                                            } );
                                                }

                                            }
                                        } );
                                        builder.show();

                                    }


                                }
                            } );

                    }

                    @NonNull
                    @Override
                    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.display,parent,false);
                        DisplayViewHolder holder=new DisplayViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();




    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(DashboardActivity.this);
                builder.setMessage("Are you sure want to Exit?");
                builder.setCancelable(true);
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        finish();
                        startActivity(intent);

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.dashboard, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_call)
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:09649291552"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_quickly_loan)
        {
            Intent intent = new Intent(DashboardActivity.this, QuicklyLoanActivity.class);
            startActivity(intent);
                 }
        else if (id == R.id.nav_insurance)
        {
            Intent intent = new Intent(DashboardActivity.this, InsuranceActivity.class);
            intent.putExtra(  "Application Type","Insurance" );
            intent.putExtra( "pid", productRandomKey);
            startActivity(intent);

        }
        else if (id == R.id.nav_beQSPartner)
        {
            Intent intent = new Intent(DashboardActivity.this, QsPartnerActivity.class);
            startActivity(intent);
        }
            else if (id == R.id.nav_yourDasboard)
        {
            Intent intent = new Intent(DashboardActivity.this, UserDashboardActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_incomeTaxReturn)
        {
            Intent intent = new Intent(DashboardActivity.this, IncomeTaxReturnActivity.class);
            intent.putExtra( "Application Type","Income Tax" );
            intent.putExtra( "pid", productRandomKey);
            startActivity(intent);


        }
        else if (id == R.id.nav_emi_calculator)
        {
            Intent intent = new Intent(DashboardActivity.this, EmiCalculatorActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_setting)
        {
            Intent intent = new Intent(DashboardActivity.this, SettingsNewActivity.class);
            intent.putExtra( "Type","user" );
            startActivity(intent);

        }
        else if (id == R.id.nav_logout)

        {
            Paper.book().destroy();
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody="Check out the amazing Quick Solution app For All Type Of Loan https://play.google.com/store/apps/details?id=gurukulsolutions.com . Download  now... ";
            String shareSub="your sub here";
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(shareIntent,"Share via"));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_services:

                    selectedFragment = new ServiceNewFragment();
                    break;
                case R.id.nav_location:

                    selectedFragment = new LocationFragment();
                    break;
                case R.id.nav_career:

                    selectedFragment = new CareerFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };


}

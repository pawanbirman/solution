package gurukulsolutions.com.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gurukulsolutions.com.SettingsNewActivity;
import gurukulsolutions.com.User.DashboardActivity;
import gurukulsolutions.com.MainActivity;
import gurukulsolutions.com.R;

public class AdminDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView QuicklyLoan,OnlineServices,Insurance;
    private ImageView RTO,Career,IncomeTaxReturn;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_dashboard );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle("Admin Home");
        setSupportActionBar( toolbar );

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MMddyyyy" );
        saveCurrentDate = currentDate.format( calendar.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HHmmss" );
        saveCurrentTime = currentTime.format( calendar.getTime() );

        productRandomKey=saveCurrentDate + saveCurrentTime;

        QuicklyLoan=(ImageView)findViewById( R.id.admin_service_loan );
        OnlineServices=(ImageView)findViewById( R.id.admin_service_onlineservice );
        Insurance=(ImageView)findViewById( R.id.admin_service_insurance );
        RTO=(ImageView)findViewById( R.id.admin_service_rto );
        Career=(ImageView)findViewById( R.id.admin_service_career );
        IncomeTaxReturn=(ImageView)findViewById( R.id.admin_service_incometaxreturn);
        QuicklyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "Quickly Loan");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });


        OnlineServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "Online Services");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });



        Insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "Insurance");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });


        RTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "RTO");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });


        Career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "Career");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });


        IncomeTaxReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminAddServicesActivity.class);
                intent.putExtra("Service", "Income Tax Return");
                intent.putExtra("pid",productRandomKey);
                startActivity(intent);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.admin_dashboard, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admin_pb_loan)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, AdminGetPBLoanApplicationsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_admin_incomeTaxReturn)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, AdminGetITRApplicationsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_admin_insurance)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, AdminGetInsuranceApplicationsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_admin_hm_loan)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, AdminGetHMLoanApplicationsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_admin_MaintainNewIdeas)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, DashboardActivity.class);
            intent.putExtra( "Type","admin" );
            startActivity(intent);

        } else if (id == R.id.nav_admin_setting)
        {
            Intent intent = new Intent(AdminDashboardActivity.this, SettingsNewActivity.class);
            intent.putExtra( "Type","admin" );

            startActivity(intent);

        }
        else if (id == R.id.nav_admin_logout)
        {

            Intent intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}

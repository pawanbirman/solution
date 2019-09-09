package gurukulsolutions.com.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gurukulsolutions.com.R;

public class EmiCalculatorActivity extends AppCompatActivity {

    private Button emiCalcBtn;
    private static String Tenure="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);

        final Spinner spinnerTenure=findViewById( R.id.spinnerTenure );
        ArrayAdapter<CharSequence> adapterTenure=ArrayAdapter.createFromResource( EmiCalculatorActivity.this,R.array.tenure,android.R.layout.simple_spinner_item );
        adapterTenure.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerTenure.setAdapter( adapterTenure );


        spinnerTenure.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id)
            {
                if(position==1 ||position==0)
                {
                    Tenure=String.valueOf( spinnerTenure.getSelectedItem() );

                }
                else
                {
                    Toast.makeText( EmiCalculatorActivity.this, "Please select occupation..", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent)
            {
                Toast.makeText( EmiCalculatorActivity.this, "Please select  occupation", Toast.LENGTH_SHORT ).show();

            }
        } );

        final EditText P = (EditText)findViewById(R.id.etPrincipalAmount);
        final EditText I = (EditText)findViewById(R.id.etInterestRate);
        final EditText Y = (EditText)findViewById(R.id.etTenure);

        final EditText TI = (EditText)findViewById(R.id.etTotalInterest);
        final EditText EMI = (EditText)findViewById(R.id.etEmi) ;
        final EditText TAP = (EditText)findViewById(R.id.etTotalAmount) ;

        final TextView txtTI = (TextView)findViewById(R.id.txtTotalInterest);
        final TextView txtEMI = (TextView)findViewById(R.id.txtEmi) ;
        final TextView txtTAP = (TextView)findViewById(R.id.txtTotalAmount) ;


        emiCalcBtn = (Button) findViewById(R.id.emiCalculator);

        emiCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TI.setVisibility( View.VISIBLE );
                EMI.setVisibility( View.VISIBLE );
                TAP.setVisibility( View.VISIBLE );

                txtTI.setVisibility( View.VISIBLE );
                txtEMI.setVisibility( View.VISIBLE );
                txtTAP.setVisibility( View.VISIBLE );


                String st1 = P.getText().toString();
                String st2 = I.getText().toString();
                String st3 = Y.getText().toString();

                if (TextUtils.isEmpty(st1)) {
                    P.setError("Enter Prncipal Amount");
                    P.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)) {
                    I.setError("Enter Interest Rate");
                    I.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st3)) {
                    Y.setError("Enter tenure");
                    Y.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);

                float Principal = calPric(p);

                float Rate = calInt(i);

                float Months = calMonth(y);

                float Dvdnt = calDvdnt( Rate, Months);

                float FD = calFinalDvdnt (Principal, Rate, Dvdnt);

                float D = calDivider(Dvdnt);

                float emi = calEmi(FD, D);

                float TA = calTa (emi, Months);

                float ti = calTotalInt(TA, Principal);



                EMI.setText(String.valueOf(emi));

                TI.setText(String.valueOf(ti));

                TAP.setText(String.valueOf(TA));

            }
        });
    }

    public  float calPric(float p) {

        return (float) (p);

    }

    public  float calInt(float i) {

        return (float) (i/12/100);

    }

    public  float calMonth(float y)
    {
        if(Tenure.equals("Years"))
        {
            y=y*12;
        }else if(Tenure.equals("Months"))
        {
           y=y;
        }

        return (float) (y );

    }

    public  float calDvdnt(float Rate, float Months) {

        return (float) (Math.pow(1+Rate, Months));

    }

    public  float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {

        return (float) (Principal * Rate * Dvdnt);

    }

    public  float calDivider(float Dvdnt) {

        return (float) (Dvdnt-1);

    }

    public  float calEmi(float FD, Float D) {

        return (float) (FD/D);

    }

    public  float calTa(float emi, Float Months) {

        return (float) (emi*Months);

    }

    public  float calTotalInt(float TA, float Principal) {

        return (float) (TA - Principal);

    }


}

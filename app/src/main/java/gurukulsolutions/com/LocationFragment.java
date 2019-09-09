package gurukulsolutions.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gurukulsolutions.com.User.LocationActivity;


public class LocationFragment extends Fragment {
    private Button mapLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_location, container, false);

        mapLocation=(Button) view.findViewById( R.id. maplocation_btn);



        mapLocation.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insurance=new Intent( getActivity(),LocationActivity.class );
                startActivity( insurance );

            }
        } );
        return view;
    }

}

package gurukulsolutions.com.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gurukulsolutions.com.Interface.ItemClickListener;
import gurukulsolutions.com.R;

public class GetAdminInsuranceApplicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtAdminName,txtAdminContactNo;
    public TextView txtAdminvehicleType,txtAdminInsuranceType,txtAdminApplicationNo;
    public TextView txtAdminExpiryDate,txtAdminApplicationType;
    public ImageView imageAdminRCFront,imageAdminRCBack;


    private ItemClickListener itemClickListener;

    public GetAdminInsuranceApplicationsViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txtAdminName=itemView.findViewById( R.id.admin_dashboard_insuranceOwnerName );
        txtAdminContactNo=itemView.findViewById( R.id.admin_dashboard_insurancecontactNo );
        txtAdminvehicleType=itemView.findViewById( R.id.admin_dashboard_insurancevehicletype );
        txtAdminInsuranceType=itemView.findViewById( R.id.admin_dashboard_insuranceType);
        txtAdminApplicationNo=itemView.findViewById( R.id.admin_dashboard_insuranceapplication_no );
        txtAdminExpiryDate=itemView.findViewById( R.id.admin_dashboard_insuranceExpirydateofpolicy );
        txtAdminApplicationType=itemView.findViewById( R.id.admin_dashboard_insuranceApplicationType );

        imageAdminRCFront=itemView.findViewById( R.id.admin_dashboard_insuranceRCFront);
        imageAdminRCBack=itemView.findViewById( R.id.admin_dashboard_insuranceRCBack);


    }


    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick( v,getAdapterPosition(),false );
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
